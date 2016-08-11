/*-
 *******************************************************************************
 * Copyright (c) 2011, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Peter Chang - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.january.dataset;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.text.Format;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.math3.stat.descriptive.SummaryStatistics;
import org.eclipse.january.DatasetException;
import org.eclipse.january.IMonitor;
import org.eclipse.january.MetadataException;
import org.eclipse.january.metadata.ErrorMetadata;
import org.eclipse.january.metadata.MetadataFactory;
import org.eclipse.january.metadata.MetadataType;
import org.eclipse.january.metadata.internal.ErrorMetadataImpl;

/**
 * Generic container class for data 
 * <p/>
 * Each subclass has an array of primitive types, elements of this array are grouped or
 * compounded to make items 
 * <p/>
 * Data items can be boolean, integer, float, complex float, vector float, etc
 */
public abstract class AbstractDataset extends LazyDatasetBase implements Dataset {

	protected int size; // number of items

	transient protected AbstractDataset base; // is null when not a view
	protected int[] stride; // can be null for row-major, contiguous datasets
	protected int offset;

	/**
	 * The data itself, held in a 1D array, but the object will wrap it to appear as possessing as many dimensions as
	 * wanted
	 */
	protected Serializable odata = null;

	/**
	 * Set aliased data as base data
	 */
	abstract protected void setData();

	/**
	 * These members hold cached values. If their values are null, then recalculate, otherwise just use the values
	 */
	transient protected HashMap<String, Object> storedValues = null;

	/**
	 * Constructor required for serialisation.
	 */
	public AbstractDataset() {
	}

	@Override
	public synchronized Dataset synchronizedCopy() {
		return clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!getClass().equals(obj.getClass())) {
			if (getRank() == 0) // for zero-rank datasets
				return obj.equals(getObjectAbs(0));
			return false;
		}

		Dataset other = (Dataset) obj;
		if (getElementsPerItem() != other.getElementsPerItem())
			return false;
		if (size != other.getSize())
			return false;
		if (!Arrays.equals(shape, other.getShapeRef())) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return getHash();
	}

	@Override
	abstract public AbstractDataset clone();

	protected Format stringFormat = null;

	@Override
	public void setStringFormat(Format format) {
		stringFormat = format;
	}

	@Override
	public Dataset copy(final int dtype) {
		if (getDType() == dtype) {
			return this;
		}
		return DatasetUtils.copy(this, dtype);
	}

	@Override
	public <T extends Dataset> T copy(Class<T> clazz) {
		return DatasetUtils.copy(clazz, this);
	}

	@Override
	public Dataset cast(final int dtype) {
		if (getDType() == dtype) {
			return this;
		}
		return DatasetUtils.cast(this, dtype);
	}

	@Override
	public <T extends Dataset> T cast(Class<T> clazz) {
		return DatasetUtils.cast(clazz, this);
	}

	@Override
	public Dataset cast(final boolean repeat, final int dtype, final int isize) {
		if (getDType() == dtype && getElementsPerItem() == isize) {
			return this;
		}
		return DatasetUtils.cast(this, repeat, dtype, isize);
	}

	@Override
	abstract public AbstractDataset getView(boolean deepCopyMetadata);

	/**
	 * Copy fields from original to view
	 * @param orig
	 * @param view
	 * @param clone if true, then clone everything but bulk data
	 * @param cloneMetadata if true, clone metadata
	 */
	protected static void copyToView(Dataset orig, AbstractDataset view, boolean clone, boolean cloneMetadata) {
		view.name = orig.getName();
		view.size = orig.getSize();
		view.odata = orig.getBuffer();
		view.offset = orig.getOffset();
		view.base = orig instanceof AbstractDataset ? ((AbstractDataset) orig).base : null;

		if (clone) {
			view.shape = orig.getShape();
			copyStoredValues(orig, view, false);
			view.stride = orig instanceof AbstractDataset && ((AbstractDataset) orig).stride != null ?
					((AbstractDataset) orig).stride.clone() : null;
		} else {
			view.shape = orig.getShapeRef();
			view.stride = orig instanceof AbstractDataset ? ((AbstractDataset) orig).stride : null;
		}

		view.metadata = getMetadataMap(orig, cloneMetadata);
		int odtype = orig.getDType();
		int vdtype = view.getDType();
		if (DTypeUtils.getBestDType(odtype, vdtype) != vdtype) {
			view.storedValues = null; // as copy is a demotion
		}
		if (odtype != vdtype && view.storedValues != null) {
			view.storedValues.remove(STORE_SHAPELESS_HASH);
			view.storedValues.remove(STORE_HASH);
		}
	}

	protected static Map<Class<? extends MetadataType>, List<MetadataType>> getMetadataMap(Dataset a, boolean clone) {
		if (a == null)
			return null;

		List<MetadataType> all = null;
		try {
			all = a.getMetadata(null);
		} catch (Exception e) {
		}
		if (all == null)
			return null;

		HashMap<Class<? extends MetadataType>, List<MetadataType>> map = new HashMap<Class<? extends MetadataType>, List<MetadataType>>();

		for (MetadataType m : all) {
			if (m == null) {
				continue;
			}
			Class<? extends MetadataType> c = findMetadataTypeSubInterfaces(m.getClass());
			List<MetadataType> l = map.get(c);
			if (l == null) {
				l = new ArrayList<MetadataType>();
				map.put(c, l);
			}
			if (clone)
				m = m.clone();
			l.add(m);
		}
		return map;
	}

	@Override
	public IntegerDataset getIndices() {
		final IntegerDataset ret = DatasetUtils.indices(shape);
		if (getName() != null) {
			ret.setName("Indices of " + getName());
		}
		return ret;
	}

	@Override
	public Dataset getTransposedView(int... axes) {
		axes = checkPermutatedAxes(shape, axes);

		AbstractDataset t = getView(true);
		if (axes == null || getRank() == 1)
			return t;

		int rank = shape.length;
		int[] tstride = new int[rank];
		int[] toffset = new int[1];
		int[] nshape = createStrides(new SliceND(shape), this, tstride, toffset);
		int[] nstride = new int[rank];
		for (int i = 0; i < rank; i++) {
			final int ax = axes[i];
			nstride[i] = tstride[ax];
			nshape[i] = shape[ax];
		}
		t.shape = nshape;
		t.stride = nstride;
		t.offset = toffset[0];
		t.base = base == null ? this : base;
		copyStoredValues(this, t, true);
		t.transposeMetadata(axes);
		return t;
	}

	@Override
	public Dataset transpose(int... axes) {
		Dataset t = getTransposedView(axes);
		return t == null ? clone() : t.clone();
	}

	@Override
	public Dataset swapAxes(int axis1, int axis2) {
		int rank = shape.length;
		if (axis1 < 0)
			axis1 += rank;
		if (axis2 < 0)
			axis2 += rank;

		if (axis1 < 0 || axis2 < 0 || axis1 >= rank || axis2 >= rank) {
			logger.error("Axis value invalid - out of range");
			throw new IllegalArgumentException("Axis value invalid - out of range");
		}

		if (rank == 1 || axis1 == axis2) {
			return this;
		}

		int[] axes = new int[rank];
		for (int i = 0; i < rank; i++) {
			axes[i] = i;
		}		

		axes[axis1] = axis2;
		axes[axis2] = axis1;
		return getTransposedView(axes);
	}

	private boolean isContiguous() {
		if (stride == null)
			return true;

		if (offset != 0)
			return false;

		int s = getElementsPerItem();
		for (int j = getRank() - 1; j >= 0; j--) {
			if (stride[j] != s) {
				return false;
			}
			s *= shape[j];
		}

		return true;
	}

	@Override
	public Dataset flatten() {
		if (!isContiguous()) { // need to make a copy if not contiguous
			return clone().flatten();
		}
		return reshape(size);
	}

	/**
	 * Fill dataset from object at depth dimension
	 * @param obj
	 * @param depth
	 * @param pos position
	 */
	protected void fillData(Object obj, final int depth, final int[] pos) {
		if (obj == null) {
			int dtype = getDType();
			if (dtype == FLOAT32)
				set(Float.NaN, pos);
			else if (dtype == FLOAT64)
				set(Double.NaN, pos);
			return;
		}

		Class<?> clazz = obj.getClass();
		if (obj instanceof List<?>) {
			List<?> jl = (List<?>) obj;
			int l = jl.size();
			for (int i = 0; i < l; i++) {
				Object lo = jl.get(i);
				fillData(lo, depth + 1, pos);
				pos[depth]++;
			}
			pos[depth] = 0;
		} else if (clazz.isArray()) {
			int l = Array.getLength(obj);
			if (clazz.equals(odata.getClass())) {
				System.arraycopy(obj, 0, odata, get1DIndex(pos), l);
			} else if (clazz.getComponentType().isPrimitive()) {
				for (int i = 0; i < l; i++) {
					set(Array.get(obj, i), pos);
					pos[depth]++;
				}
				pos[depth] = 0;
			} else {
				for (int i = 0; i < l; i++) {
					fillData(Array.get(obj, i), depth + 1, pos);
					pos[depth]++;
				}
				pos[depth] = 0;
			}
		} else if (obj instanceof IDataset) {
			boolean[] a = new boolean[shape.length];
			for (int i = depth; i < a.length; i++)
				a[i] = true;
			setSlice(obj, getSliceIteratorFromAxes(pos, a));
		} else {
			set(obj, pos);
		}
	}

	@Override
	public IndexIterator getIterator(final boolean withPosition) {
		if (stride != null)
			return new StrideIterator(shape, stride, offset);
		return withPosition ? new ContiguousIteratorWithPosition(shape, size) : new ContiguousIterator(size);
	}

	@Override
	public IndexIterator getIterator() {
		return getIterator(false);
	}

	@Override
	public PositionIterator getPositionIterator(final int... axes) {
		return new PositionIterator(shape, axes);
	}

	@Override
	public IndexIterator getSliceIterator(final int[] start, final int[] stop, final int[] step) {
		return getSliceIterator(new SliceND(shape, start, stop, step));
	}

	/**
	 * @param slice
	 * @return an slice iterator that operates like an IndexIterator
	 */
	public IndexIterator getSliceIterator(SliceND slice) {
		if (ShapeUtils.calcLongSize(slice.getShape()) == 0) {
			return new NullIterator(shape, slice.getShape());
		}
		if (stride != null)
			return new StrideIterator(getElementsPerItem(), shape, stride, offset, slice);

		return new SliceIterator(shape, size, slice);
	}

	@Override
	public SliceIterator getSliceIteratorFromAxes(final int[] pos, boolean[] axes) {
		int rank = shape.length;
		int[] start;
		int[] stop = new int[rank];
		int[] step = new int[rank];

		if (pos == null) {
			start = new int[rank];
		} else if (pos.length == rank) {
			start = pos.clone();
		} else {
			throw new IllegalArgumentException("pos array length is not equal to rank of dataset");
		}
		if (axes == null) {
			axes = new boolean[rank];
			Arrays.fill(axes, true);
		} else if (axes.length != rank) {
			throw new IllegalArgumentException("axes array length is not equal to rank of dataset");
		}

		for (int i = 0; i < rank; i++) {
			if (axes[i]) {
				stop[i] = shape[i];
			} else {
				stop[i] = start[i] + 1;
			}
			step[i] = 1;
		}
		return (SliceIterator) getSliceIterator(start, stop, step);
	}

	@Override
	public BooleanIterator getBooleanIterator(Dataset choice) {
		return getBooleanIterator(choice, true);
	}

	@Override
	public BooleanIterator getBooleanIterator(Dataset choice, boolean value) {
		return new BooleanIterator(getIterator(), choice, value);
	}

	@Override
	public Dataset getByBoolean(Dataset selection) {
		checkCompatibility(selection);

		final int length = ((Number) selection.sum()).intValue();
		final int is = getElementsPerItem();
		Dataset r = DatasetFactory.zeros(is, new int[] { length }, getDType());
		BooleanIterator biter = getBooleanIterator(selection);

		int i = 0;
		while (biter.hasNext()) {
			r.setObjectAbs(i, getObjectAbs(biter.index));
			i += is;
		}
		return r;
	}

	@Override
	public Dataset getBy1DIndex(IntegerDataset index) {
		final int is = getElementsPerItem();
		final Dataset r = DatasetFactory.zeros(is, index.getShape(), getDType());
		final IntegerIterator iter = new IntegerIterator(index, size, is);

		int i = 0;
		while (iter.hasNext()) {
			r.setObjectAbs(i, getObjectAbs(iter.index));
			i += is;
		}
		return r;
	}

	@Override
	public Dataset getByIndexes(final Object... indexes) {
		final IntegersIterator iter = new IntegersIterator(shape, indexes);
		final int is = getElementsPerItem();
		final Dataset r = DatasetFactory.zeros(is, iter.getShape(), getDType());

		final int[] pos = iter.getPos();
		int i = 0;
		while (iter.hasNext()) {
			r.setObjectAbs(i, getObject(pos));
			i += is;
		}
		return r;
	}

	@Override
	public Class<?> getElementClass() {
		return DTypeUtils.getElementClass(getDType());
	}

	@Override
	public boolean hasFloatingPointElements() {
		Class<?> cls = getElementClass();
		return cls == Float.class || cls == Double.class;
	}

	@Override
	public int getElementsPerItem() {
		return DTypeUtils.getElementsPerItem(getDType());
	}

	@Override
	public int getItemBytes() {
		return DTypeUtils.getItemBytes(getDType(), getElementsPerItem());
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public int[] getShape() {
		// make a copy of the dimensions data, and put that out
		if (shape == null) {
			logger.warn("Shape is null!!!");
			return new int[] {};
		}
		return shape.clone();
	}

	@Override
	public int getRank() {
		return shape == null ? 0 : shape.length;
	}

	@Override
	public int getNbytes() {
		return getSize() * getItemBytes();
	}

	/**
	 * Check for -1 placeholder in shape and replace if necessary
	 * @param shape
	 * @param size
	 */
	private void checkShape(int[] shape, int size) {
		int rank = shape.length;
		int found = -1;
		int nsize = 1;
		for (int i = 0; i < rank; i++) {
			int d = shape[i];
			if (d == -1) {
				if (found == -1) {
					found = i;
				} else {
					logger.error("Can only have one -1 placeholder in shape");
					throw new IllegalArgumentException("Can only have one -1 placeholder in shape");
				}
			} else {
				nsize *= d;
			}
		}
		if (found >= 0) {
			shape[found] = size/nsize;
		} else if (nsize != size && !(rank == 0 && size == 0)) {
			logger.error("New shape is not same size as old shape");
			throw new IllegalArgumentException("New size is not same as the old size. Old size is "+size+" new size is "+nsize+" and shape is "+Arrays.toString(shape));
		}
	}

	@Override
	public void setShape(final int... shape) {
		int[] nshape = shape.clone();
		checkShape(nshape, size);
		if (Arrays.equals(this.shape, nshape) || size == 0) {
			return;
		}

		if (stride != null) {
			// the only compatible shapes are ones where new dimensions are factors of old dimensions
			// or are combined adjacent old dimensions 
			int[] oshape = this.shape;
			int orank = oshape.length;
			int nrank = nshape.length;
			int diff = nrank - orank;
			int[] nstride = new int[nrank];
			boolean ones = true;
			// work forwards for broadcasting cases
			for (int i = 0, j = 0; i < orank || j < nrank;) {
				if (j >= diff && i < orank && j < nrank && oshape[i] == nshape[j]) {
					nstride[j++] = stride[i++];
				} else if (j < nrank && nshape[j] == 1) {
					nstride[j++] = 0;
				} else if (i < orank && oshape[i] == 1) {
					i++;
				} else {
					if (j < nrank)
						j++;
					if (i < orank)
						i++;
					ones = false;
				}
			}
			if (!ones) { // not just ones differ in shapes
				int[] ostride = stride;
				int ob = 0;
				int oe = 1;
				int nb = 0;
				int ne = 1;
				while (ob < orank && nb < nrank) {
					int ol = oshape[ob];
					int nl = nshape[nb];
					
					if (nl < ol) { // find group of shape dimensions that form common size
						do { // case where new shape spreads single dimension over several dimensions
							if (ne == nrank) {
								break;
							}
							nl *= nshape[ne++];
						} while (nl < ol);
						if (nl != ol) {
							logger.error("Subshape is incompatible with single dimension");
							throw new IllegalArgumentException("Subshape is incompatible with single dimension");
						}
						int on = ne - 1;
						while (nshape[on] == 1) {
							on--;
						}

						nstride[on] = ostride[ob];
						for (int n = on - 1; n >= nb; n--) {
							if (nshape[n] == 1)
								continue;

							nstride[n] = nshape[on] * nstride[on];
							on = n;
						}
					} else if (ol < nl) {
						do { // case where new shape combines several dimensions into one dimension
							if (oe == orank) {
								break;
							}
							ol *= oshape[oe++];
						} while (ol < nl);
						if (nl != ol) {
							logger.error("Single dimension is incompatible with subshape");
							throw new IllegalArgumentException("Single dimension is incompatible with subshape");
						}

						int oo = oe - 1;
						while (oshape[oo] == 1) {
							oo--;
						}
						int os = ostride[oo];
						for (int o = oo - 1; o >= ob; o--) {
							if (oshape[o] == 1)
								continue;
							if (ostride[o] != oshape[oo] * ostride[oo]) {
								logger.error("Subshape cannot be a non-contiguous view");
								throw new IllegalArgumentException("Subshape cannot be a non-contiguous view");
							}
							oo = o;
						}
						nstride[nb] = os;
					} else {
						nstride[nb] = ostride[ob];
					}

					ob = oe++;
					nb = ne++;
				}
			}
	
			stride = nstride;
		}

		reshapeMetadata(this.shape, nshape);
		this.shape = nshape;

		if (storedValues != null)
			filterStoredValues(storedValues); // as it is dependent on shape
	}

	@Override
	public int[] getShapeRef() {
		return shape;
	}

	@Override
	public int getOffset() {
		return offset;
	}

	@Override
	public int[] getStrides() {
		return stride;
	}

	@Override
	public Serializable getBuffer() {
		return odata;
	}

	@Override
	public void overrideInternal(Serializable buffer, int... shape) {
		if (buffer != null) {
			odata = buffer;
			setData();
			setDirty();
		}
	
		if (shape != null) {
			this.shape = shape.clone();
			size = ShapeUtils.calcSize(this.shape);
		}
	}

	/**
	 * Create a stride array from dataset
	 * @param a dataset
	 * @param offset output offset
	 * @return new strides
	 */
	public static int[] createStrides(Dataset a, final int[] offset) {
		return createStrides(a.getElementsPerItem(), a.getShapeRef(), a.getStrides(), a.getOffset(), offset);
	}

	/**
	 * Create a stride array from dataset
	 * @param isize
	 * @param shape
	 * @param oStride original stride
	 * @param oOffset original offset (only used if there is an original stride)
	 * @param offset output offset
	 * @return new strides
	 */
	public static int[] createStrides(final int isize, final int[] shape, final int[] oStride, final int oOffset, final int[] offset) {
		int rank = shape.length;
		final int[] stride;
		if (oStride == null) {
			offset[0] = 0;
			stride = new int[rank];
			int s = isize;
			for (int j = rank - 1; j >= 0; j--) {
				stride[j] = s;
				s *= shape[j];
			}
		} else {
			offset[0] = oOffset;
			stride = oStride.clone();
		}
		return stride;
	}

	/**
	 * Create a stride array from slice information and a dataset
	 * @param slice
	 * @param a dataset
	 * @param stride output stride
	 * @param offset output offset
	 * @return new shape
	 */
	public static int[] createStrides(final SliceND slice, final Dataset a, final int[] stride, final int[] offset) {
		return createStrides(slice, a.getElementsPerItem(), a.getShapeRef(), a.getStrides(), a.getOffset(), stride, offset);
	}

	/**
	 * Create a stride array from slice and dataset information
	 * @param slice
	 * @param isize
	 * @param shape
	 * @param oStride original stride
	 * @param oOffset original offset (only used if there is an original stride)
	 * @param stride output stride
	 * @param offset output offset
	 * @return new shape
	 */
	public static int[] createStrides(final SliceND slice, final int isize, final int[] shape, final int[] oStride, final int oOffset, final int[] stride, final int[] offset) {
		int[] lstart = slice.getStart();
		int[] lstep = slice.getStep();
		int[] newShape = slice.getShape();
		int rank = shape.length;

		if (oStride == null) {
			int s = isize;
			offset[0] = 0;
			for (int j = rank - 1; j >= 0; j--) {
				stride[j] = s * lstep[j];
				offset[0] += s * lstart[j];
				s *= shape[j];
			}
		} else {
			offset[0] = oOffset;
			for (int j = 0; j < rank; j++) {
				int s = oStride[j];
				stride[j] = lstep[j] * s;
				offset[0] += lstart[j] * s;
			}
		}

		return newShape;
	}

	@Override
	public Dataset getBroadcastView(int... broadcastShape) {
		AbstractDataset view = getView(true);
		
		if (!Arrays.equals(shape, broadcastShape)) {
			List<int[]> nShapes = BroadcastUtils.broadcastShapesToMax(broadcastShape, shape);
			view.setShape(nShapes.get(0));
			view.stride = BroadcastUtils.createBroadcastStrides(view, broadcastShape);
			view.shape = broadcastShape.clone();
			view.size = ShapeUtils.calcSize(broadcastShape);
			if (view.name == null || view.name.isEmpty()) {
				view.name = "Broadcast from " + Arrays.toString(shape);
			} else {
				view.name = "Broadcast of " + view.name + " from " + Arrays.toString(shape);
			}
		}
		return view;
	}

	@Override
	public Dataset getSliceView(final int[] start, final int[] stop, final int[] step) {
		return getSliceView(new SliceND(shape, start, stop, step));
	}

	@Override
	public Dataset getSliceView(Slice... slice) {
		if (slice == null || slice.length == 0) {
			return getView(true);
		}

		return getSliceView(new SliceND(shape, slice));
	}

	/**
	 * Get a slice of the dataset. The returned dataset is a view on a selection of items
	 * @param slice
	 * @return slice view
	 */
	@Override
	public Dataset getSliceView(SliceND slice) {
		if (slice.isAll()) {
			return getView(true);
		}

		final int rank = shape.length;
		int[] sStride = new int[rank];
		int[] sOffset = new int[1];

		int[] sShape = createStrides(slice, this, sStride, sOffset);
	
		AbstractDataset s = getView(false);
		s.shape = sShape;
		s.size = ShapeUtils.calcSize(sShape);
		s.stride = sStride;
		s.offset = sOffset[0];
		s.base = base == null ? this : base;

		s.metadata = copyMetadata();
		s.sliceMetadata(true, slice);

		s.setDirty();
		s.setName(name + BLOCK_OPEN + slice + BLOCK_CLOSE);

		return s;
	}

	/**
	 * Get flattened view index of given position 
	 * @param pos
	 *            the integer array specifying the n-D position
	 * @return the index on the flattened dataset
	 */
	private int getFlat1DIndex(final int[] pos) {
		final int imax = pos.length;
		if (imax == 0) {
			return 0;
		}

		return get1DIndexFromShape(pos);
	}

	@Override
	public int get1DIndex(final int... n) {
		final int imax = n.length;
		final int rank = shape.length;
		if (imax == 0) {
			if (rank == 0 || (rank == 1 && shape[0] <= 1))
				return stride == null ? 0 : offset;
			throw new IllegalArgumentException("One or more index parameters must be supplied");
		} else if (imax > rank) {
			throw new IllegalArgumentException("No of index parameters is different to the shape of data: " + imax
					+ " given " + rank + " required");
		}

		return stride == null ? get1DIndexFromShape(n) : get1DIndexFromStrides(n);
	}

	private static void throwAIOOBException(int i, int s, int d) {
		throw new ArrayIndexOutOfBoundsException("Index (" + i + ") out of range [-" + s + "," + s
				+ "] in dimension " + d);
	}

	/**
	 * @param i
	 * @return the index on the data array corresponding to that location
	 */
	protected int get1DIndex(int i) {
		if (shape.length > 1) {
			logger.debug("This dataset is not 1D but was addressed as such");
//			throw new IllegalArgumentException("This dataset is not 1D but was addressed as such");
			return get1DIndex(new int[] {i});
		}
		if (i < 0) {
			i += shape[0];
		}
		if (i < 0 || i >= shape[0]) {
			throwAIOOBException(i, shape[0], 0);
		}
		return stride == null ? i : i*stride[0] + offset;
	}

	/**
	 * @param i
	 * @param j
	 * @return the index on the data array corresponding to that location
	 */
	protected int get1DIndex(int i, int j) {
		if (shape.length != 2) {
			logger.debug("This dataset is not 2D but was addressed as such");
//			throw new IllegalArgumentException("This dataset is not 2D but was addressed as such");
			return get1DIndex(new int[] {i, j});
		}
		if (i < 0) {
			i += shape[0];
		}
		if (i < 0 || i >= shape[0]) {
			throwAIOOBException(i, shape[0], 0);
		}
		if (j < 0) {
			j += shape[1];
		}
		if (j < 0 || j >= shape[1]) {
			throwAIOOBException(i, shape[1], 1);
		}
		return stride == null ? i*shape[1] + j : i*stride[0] + j*stride[1] + offset;
	}

	protected int get1DIndexFromShape(final int... n) {
		return get1DIndexFromShape(shape, n);
	}

	protected static int get1DIndexFromShape(final int[] shape, final int... n) {
		final int imax = n.length;
		final int rank = shape.length;
//		if (rank != imax) {
//			throw new IllegalArgumentException("Number of position indexes must be equal to rank");
//		}
		int index = 0;
		int i = 0;
		for (; i < imax; i++) {
			final int si = shape[i];
			int ni = n[i];
			if (ni < 0) {
				ni += si;
			}
			if (ni < 0 || ni >= si) {
				throwAIOOBException(ni, si, i);
			}
			index = index * si + ni;
		}
		for (; i < rank; i++) {
			index *= shape[i];
		}

		return index;
	}

	private int get1DIndexFromStrides(final int... n) {
		return get1DIndexFromStrides(shape, stride, offset, n);
	}

	private static int get1DIndexFromStrides(final int[] shape, final int[] stride, final int offset, final int... n) {
		final int rank = shape.length;
		if (rank != n.length) {
			throw new IllegalArgumentException("Number of position indexes must be equal to rank");
		}
		int index = offset;
		for (int i = 0; i < rank; i++) {
			final int st = stride[i];
			if (st != 0) { // not broadcasted
				final int si = shape[i];
				int ni = n[i];
				if (ni < 0) {
					ni += si;
				}
				if (ni < 0 || ni >= si) {
					throwAIOOBException(ni, si, i);
				}
				index += st * ni;
			}
		}
		return index;
	}

	@Override
	public int[] getNDPosition(final int n) {
		if (isIndexInRange(n)) {
			throw new IllegalArgumentException("Index provided " + n
					+ "is larger then the size of the containing array");
		}

		return stride == null ? ShapeUtils.getNDPositionFromShape(n, shape) : getNDPositionFromStrides(n);
	}

	private boolean isIndexInRange(final int n) {
		if (stride == null) {
			return n >= size;
		}
		return n >= getBufferLength();
	}

	/**
	 * @return entire buffer length
	 */
	abstract protected int getBufferLength();

	private int[] getNDPositionFromStrides(int n) {
		n -= offset;
		int rank = shape.length;
		if (rank == 1) {
			return new int[] { n / stride[0] };
		}

		int[] output = new int[rank];
		int i = 0;
		while (i != n) { // TODO find more efficient way than this exhaustive search
			int j = rank - 1;
			for (; j >= 0; j--) {
				output[j]++;
				i += stride[j];
				if (output[j] >= shape[j]) {
					output[j] = 0;
					i -= shape[j] * stride[j];
				} else {
					break;
				}
			}
			if (j == -1) {
				logger.error("Index was not found in this strided dataset");
				throw new IllegalArgumentException("Index was not found in this strided dataset");
			}
		}

		return output;
	}

	@Override
	public int checkAxis(int axis) {
		return checkAxis(shape.length, axis);
	}

	/**
	 * Check that axis is in range [-rank,rank)
	 * 
	 * @param rank
	 * @param axis
	 * @return sanitized axis in range [0, rank)
	 */
	protected static int checkAxis(int rank, int axis) {
		if (axis < 0) {
			axis += rank;
		}

		if (axis < 0 || axis >= rank) {
			throw new IndexOutOfBoundsException("Axis " + axis + " given is out of range [0, " + rank + ")");
		}
		return axis;
	}

	protected static final char BLOCK_OPEN = '[';
	protected static final char BLOCK_CLOSE = ']';

	@Override
	public String toString() {
		return toString(false);
	}

	@Override
	public String toString(boolean showData) {
		final int rank = shape == null ? 0 : shape.length;
		final StringBuilder out = new StringBuilder();

		if (!showData) {
			if (name != null && name.length() > 0) {
				out.append("Dataset '");
				out.append(name);
				out.append("' has shape ");
			} else {
				out.append("Dataset shape is ");
			}

			out.append(BLOCK_OPEN);
			if (rank > 0) {
				out.append(shape[0]);
			}
			for (int i = 1; i < rank; i++) {
				out.append(", " + shape[i]);
			}
			out.append(BLOCK_CLOSE);
			return out.toString();
		}

		if (size == 0) {
			return out.toString();
		}

		if (rank > 0) {
			int[] pos = new int[rank];
			final StringBuilder lead = new StringBuilder();
			printBlocks(out, lead, 0, pos);
		} else {
			out.append(getString());
		}
		return out.toString();
	}

	/**
	 * Limit to strings output via the toString() method
	 */
	private static int maxStringLength = 120;

	/**
	 * Set maximum line length for toString() method
	 * @param maxLineLength
	 */
	public static void setMaxLineLength(int maxLineLength) {
		maxStringLength = maxLineLength;
	}

	/**
	 * @return maximum line length for toString() method
	 */
	public static int getMaxLineLength() {
		return maxStringLength;
	}

	/**
	 * Limit to number of sub-blocks output via the toString() method
	 */
	private static final int MAX_SUBBLOCKS = 6;

	private final static String SEPARATOR = ",";
	private final static String SPACE = " ";
	private final static String ELLIPSIS = "...";
	private final static String NEWLINE = "\n";

	/**
	 * Make a line of output for last dimension of dataset
	 * 
	 * @param start
	 * @return line
	 */
	private StringBuilder makeLine(final int end, final int... start) {
		StringBuilder line = new StringBuilder();
		final int[] pos;
		if (end >= start.length) {
			pos = Arrays.copyOf(start, end + 1);
		} else {
			pos = start;
		}
		pos[end] = 0;
		line.append(BLOCK_OPEN);
		line.append(getString(pos));

		final int length = shape[end];

		// trim elements printed if length exceed estimate of maximum elements
		int excess = length - maxStringLength / 3; // space + number + separator
		if (excess > 0) {
			int index = (length - excess) / 2;
			for (int y = 1; y < index; y++) {
				line.append(SEPARATOR + SPACE);
				pos[end] = y;
				line.append(getString(pos));
			}
			index = (length + excess) / 2;
			for (int y = index; y < length; y++) {
				line.append(SEPARATOR + SPACE);
				pos[end] = y;
				line.append(getString(pos));
			}
		} else {
			for (int y = 1; y < length; y++) {
				line.append(SEPARATOR + SPACE);
				pos[end] = y;
				line.append(getString(pos));
			}
		}
		line.append(BLOCK_CLOSE);

		// trim string down to limit
		excess = line.length() - maxStringLength - ELLIPSIS.length() - 1;
		if (excess > 0) {
			int index = line.substring(0, (line.length() - excess) / 2).lastIndexOf(SEPARATOR) + 2;
			StringBuilder out = new StringBuilder(line.subSequence(0, index));
			out.append(ELLIPSIS + SEPARATOR);
			index = line.substring((line.length() + excess) / 2).indexOf(SEPARATOR) + (line.length() + excess) / 2 + 1;
			out.append(line.subSequence(index, line.length()));
			return out;
		}

		return line;
	}

	/**
	 * recursive method to print blocks
	 */
	private void printBlocks(final StringBuilder out, final StringBuilder lead, final int level, final int[] pos) {
		if (out.length() > 0) {
			char last = out.charAt(out.length() - 1);
			if (last != BLOCK_OPEN) {
				out.append(lead);
			}
		}
		final int end = getRank() - 1;
		if (level != end) {
			out.append(BLOCK_OPEN);
			int length = shape[level];

			// first sub-block
			pos[level] = 0;
			StringBuilder newlead = new StringBuilder(lead);
			newlead.append(SPACE);
			printBlocks(out, newlead, level + 1, pos);
			if (length < 2) { // escape
				out.append(BLOCK_CLOSE);
				return;
			}

			out.append(SEPARATOR + NEWLINE);
			for (int i = level + 1; i < end; i++) {
				out.append(NEWLINE);
			}

			// middle sub-blocks
			if (length < MAX_SUBBLOCKS) {
				for (int x = 1; x < length - 1; x++) {
					pos[level] = x;
					printBlocks(out, newlead, level + 1, pos);
					if (end <= level + 1) {
						out.append(SEPARATOR + NEWLINE);
					} else {
						out.append(SEPARATOR + NEWLINE + NEWLINE);
					}
				}
			} else {
				final int excess = length - MAX_SUBBLOCKS;
				int xmax = (length - excess) / 2;
				for (int x = 1; x < xmax; x++) {
					pos[level] = x;
					printBlocks(out, newlead, level + 1, pos);
					if (end <= level + 1) {
						out.append(SEPARATOR + NEWLINE);
					} else {
						out.append(SEPARATOR + NEWLINE + NEWLINE);
					}
				}
				out.append(newlead);
				out.append(ELLIPSIS + SEPARATOR + NEWLINE);
				xmax = (length + excess) / 2;
				for (int x = xmax; x < length - 1; x++) {
					pos[level] = x;
					printBlocks(out, newlead, level + 1, pos);
					if (end <= level + 1) {
						out.append(SEPARATOR + NEWLINE);
					} else {
						out.append(SEPARATOR + NEWLINE + NEWLINE);
					}
				}
			}

			// last sub-block
			pos[level] = length - 1;
			printBlocks(out, newlead, level + 1, pos);
			out.append(BLOCK_CLOSE);
		} else {
			out.append(makeLine(end, pos));
		}
	}

	@Override
	public void setDirty() {
		if (storedValues != null)
			storedValues.clear();
	}

	@Override
	public Dataset squeezeEnds() {
		return squeeze(true);
	}

	@Override
	public Dataset squeeze() {
		return squeeze(false);
	}

	@Override
	public Dataset squeeze(boolean onlyFromEnds) {
		final int[] tshape = ShapeUtils.squeezeShape(shape, onlyFromEnds);
		final int[] oshape = shape;
		if (stride == null) {
			shape = tshape;
		} else {
			int rank = shape.length;
			int trank = tshape.length;
			if (trank < rank) {
				int[] tstride = new int[tshape.length];
				if (onlyFromEnds) {
					for (int i = 0; i < rank; i++) {
						if (shape[i] != 1) {
							for (int k = 0; k < trank; k++) {
								tstride[k] = stride[i++];
							}
							break;
						}
					}
				} else {
					int t = 0;
					for (int i = 0; i < rank; i++) {
						if (shape[i] != 1) {
							tstride[t++] = stride[i];
						}
					}
				}
				shape = tshape;
				stride = tstride;
			}
		}

		reshapeMetadata(oshape, shape);
		return this;
	}

	@Override
	public boolean isCompatibleWith(final ILazyDataset g) {
		return ShapeUtils.areShapesCompatible(shape, g.getShape());
	}

	@Override
	public void checkCompatibility(final ILazyDataset g) throws IllegalArgumentException {
		ShapeUtils.checkCompatibility(this, g);
	}

	@Override
	public Dataset reshape(final int... shape) {
		Dataset a = getView(true);
		try {
			a.setShape(shape);
		} catch (IllegalArgumentException e) {
			a = a.clone();
			a.setShape(shape);
		}
		return a;
	}

	/**
	 * @param start
	 * @param stop
	 * @param step
	 * @return number of steps to take
	 */
	protected static int calcSteps(final double start, final double stop, final double step) {
		if (step > 0) {
			return (int) Math.ceil((stop - start) / step);
		}
		return (int) Math.ceil((stop - start) / step);
	}

	@Override
	public boolean isComplex() {
		int type = getDType();
		return type == COMPLEX64 || type == COMPLEX128;
	}

	@Override
	public Dataset getReal() {
		return this;
	}

	@Override
	public Dataset getRealView() {
		return getView(true);
	}

	@Override
	public Dataset getSlice(final int[] start, final int[] stop, final int[] step) {
		return getSlice(new SliceND(shape, start, stop, step));
	}

	@Override
	public Dataset getSlice(Slice... slice) {
		return getSlice(new SliceND(shape, slice));
	}

	@Override
	public Dataset getSlice(IMonitor monitor, Slice... slice) {
		return getSlice(slice);
	}

	@Override
	public Dataset getSlice(IMonitor monitor, SliceND slice) {
		return getSlice(slice);
	}

	@Override
	public Dataset getSlice(IMonitor monitor, int[] start, int[] stop, int[] step) {
		return getSlice(start, stop, step);
	}

	/**
	 * Get a slice of the dataset. The returned dataset is a copied selection of items
	 * @param slice
	 * @return The dataset of the sliced data
	 */
	@Override
	public Dataset getSlice(final SliceND slice) {
		SliceIterator it = (SliceIterator) getSliceIterator(slice);
		AbstractDataset s = getSlice(it);
		s.metadata = copyMetadata();
		s.sliceMetadata(true, slice);
		return s;
	}

	/**
	 * Get a slice of the dataset. The returned dataset is a copied selection of items
	 * 
	 * @param iterator Slice iterator
	 * @return The dataset of the sliced data
	 */
	abstract public AbstractDataset getSlice(final SliceIterator iterator);

	@Override
	public Dataset setSlice(final Object obj, final SliceND slice) {
		Dataset ds;
		if (obj instanceof Dataset) {
			ds = (Dataset) obj;
		} else if (obj instanceof IDataset) {
			ds = DatasetUtils.convertToDataset((IDataset) obj);
		} else {
			int dtype = getDType();
			if (dtype != Dataset.BOOL) {
				dtype = DTypeUtils.getLargestDType(dtype);
			}
			ds = DatasetFactory.createFromObject(getElementsPerItem(), dtype, obj);
		}

		return setSlicedView(getSliceView(slice), ds);
	}

	@Override
	public Dataset setSlice(final Object obj, final int[] start, final int[] stop, final int[] step) {
		return setSlice(obj, new SliceND(shape, start, stop, step));
	}

	/**
	 * Set a view of current dataset to given dataset with broadcasting
	 * @param view
	 * @param d
	 * @return this dataset
	 */
	abstract Dataset setSlicedView(Dataset view, Dataset d);

	@Override
	public Dataset setSlice(Object obj, Slice... slice) {
		if (slice == null || slice.length == 0) {
			return setSlice(obj, new SliceND(shape));
		}
		return setSlice(obj, new SliceND(shape, slice));
	}

	@Override
	public boolean all() {
		return Comparisons.allTrue(this);
	}

	@Override
	public BooleanDataset all(final int axis) {
		return Comparisons.allTrue(this, axis);
	}

	@Override
	public boolean any() {
		return Comparisons.anyTrue(this);
	}

	@Override
	public BooleanDataset any(final int axis) {
		return Comparisons.anyTrue(this, axis);
	}

	@Override
	public Dataset ifloorDivide(final Object o) {
		return idivide(o).ifloor();
	}

	@Override
	public double residual(final Object o) {
		return residual(o, null, false);
	}

	@Override
	public double residual(final Object o, boolean ignoreNaNs) {
		return residual(o, null, ignoreNaNs);
	}

	public static final String STORE_HASH = "hash";
	protected static final String STORE_SHAPELESS_HASH = "shapelessHash";
	public static final String STORE_MAX = "max";
	public static final String STORE_MIN = "min";
	protected static final String STORE_MAX_POS = "maxPos";
	protected static final String STORE_MIN_POS = "minPos";
	protected static final String STORE_STATS = "stats";
	protected static final String STORE_SUM = "sum";
	protected static final String STORE_MEAN = "mean";
	protected static final String STORE_VAR = "var";
	protected static final String STORE_COUNT = "count";
	private static final String STORE_INDEX = "Index";
	protected static final String STORE_BROADCAST = "Broadcast";

	/**
	 * Get value from store
	 * 
	 * @param key
	 * @return value
	 */
	public Object getStoredValue(String key) {
		if (storedValues == null) {
			return null;
		}

		return storedValues.get(key);
	}

	/**
	 * Set value in store
	 * <p>
	 * This is a <b>private method</b>: do not use!
	 * 
	 * @param key
	 * @param obj
	 */
	public void setStoredValue(String key, Object obj) {
		if (storedValues == null) {
			storedValues = new HashMap<String, Object>();
		}

		storedValues.put(key, obj);
	}

	protected static String storeName(boolean ignoreNaNs, String name) {
		return storeName(ignoreNaNs, false, name);
	}

	protected static String storeName(boolean ignoreNaNs, boolean ignoreInfs, String name) {
		return (ignoreInfs ? "inf" : "") + (ignoreNaNs ? "nan" : "") +  name;
	}

	/**
	 * Copy stored values from original to derived dataset
	 * @param orig
	 * @param derived
	 * @param shapeChanged
	 */
	protected static void copyStoredValues(IDataset orig, AbstractDataset derived, boolean shapeChanged) {
		if (orig instanceof AbstractDataset && ((AbstractDataset) orig).storedValues != null) {
			derived.storedValues = new HashMap<String, Object>(((AbstractDataset) orig).storedValues);
			if (shapeChanged) {
				filterStoredValues(derived.storedValues);
			}
		}
	}

	private static void filterStoredValues(Map<String, Object> map) {
		map.remove(STORE_HASH);
		List<String> keys = new ArrayList<String>();
		for (String n : map.keySet()) {
			if (n.contains("-")) { // remove anything which is axis-specific
				keys.add(n);
			}
		}
		for (String n : keys) {
			map.remove(n);
		}
	}

	/**
	 * Calculate minimum and maximum for a dataset
	 * @param ignoreNaNs if true, ignore NaNs
	 * @param ignoreInfs if true, ignore infinities
	 */
	protected void calculateMaxMin(final boolean ignoreNaNs, final boolean ignoreInfs) {
		IndexIterator iter = getIterator();
		double amax = Double.NEGATIVE_INFINITY;
		double amin = Double.POSITIVE_INFINITY;
		double hash = 0;
		boolean hasNaNs = false;

		while (iter.hasNext()) {
			final double val = getElementDoubleAbs(iter.index);
			if (Double.isNaN(val)) {
				hash = (hash * 19) % Integer.MAX_VALUE;
				if (ignoreNaNs)
					continue;
				hasNaNs = true;
			} else if (Double.isInfinite(val)) {
				hash = (hash * 19) % Integer.MAX_VALUE;
				if (ignoreInfs)
					continue;
			} else {
				hash = (hash * 19 + val) % Integer.MAX_VALUE;
			}

			if (val > amax) {
				amax = val;
			}
			if (val < amin) {
				amin = val;
			}
		}

		int ihash = ((int) hash) * 19 + getDType() * 17 + getElementsPerItem();
		setStoredValue(storeName(ignoreNaNs, ignoreInfs, STORE_SHAPELESS_HASH), ihash);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MAX), hasNaNs ? Double.NaN : fromDoubleToNumber(amax));
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MIN), hasNaNs ? Double.NaN : fromDoubleToNumber(amin));
	}

	/**
	 * Calculate summary statistics for a dataset
	 * @param ignoreNaNs if true, ignore NaNs
	 * @param ignoreInfs if true, ignore infinities
	 * @param name
	 */
	protected void calculateSummaryStats(final boolean ignoreNaNs, final boolean ignoreInfs, final String name) {
		final IndexIterator iter = getIterator();
		final SummaryStatistics stats = new SummaryStatistics();
		//sum of logs is slow and we dont use it, so blocking its calculation here
		stats.setSumLogImpl(new NullStorelessUnivariateStatistic());

		if (storedValues == null || !storedValues.containsKey(STORE_HASH)) {
			boolean hasNaNs = false;
			double hash = 0;

			while (iter.hasNext()) {
				final double val = getElementDoubleAbs(iter.index);
				if (Double.isNaN(val)) {
					hash = (hash * 19) % Integer.MAX_VALUE;
					if (ignoreNaNs)
						continue;
					hasNaNs = true;
				} else if (Double.isInfinite(val)) {
					hash = (hash * 19) % Integer.MAX_VALUE;
					if (ignoreInfs)
						continue;
				} else {
					hash = (hash * 19 + val) % Integer.MAX_VALUE;
				}
				stats.addValue(val);
			}

			int ihash = ((int) hash) * 19 + getDType() * 17 + getElementsPerItem();
			setStoredValue(storeName(ignoreNaNs, ignoreInfs, STORE_SHAPELESS_HASH), ihash);
			storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MAX), hasNaNs ? Double.NaN : fromDoubleToNumber(stats.getMax()));
			storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MIN), hasNaNs ? Double.NaN : fromDoubleToNumber(stats.getMin()));
			storedValues.put(name, stats);
		} else {
			while (iter.hasNext()) {
				final double val = getElementDoubleAbs(iter.index);
				if (ignoreNaNs && Double.isNaN(val)) {
					continue;
				}
				if (ignoreInfs && Double.isInfinite(val)) {
					continue;
				}

				stats.addValue(val);
			}

			storedValues.put(name, stats);
		}
	}

	/**
	 * Calculate summary statistics for a dataset along an axis
	 * @param ignoreNaNs if true, ignore NaNs
	 * @param ignoreInfs if true, ignore infinities
	 * @param axis
	 */
	protected void calculateSummaryStats(final boolean ignoreNaNs, final boolean ignoreInfs, final int axis) {
		int rank = getRank();

		int[] oshape = getShape();
		int alen = oshape[axis];
		oshape[axis] = 1;

		int[] nshape = new int[rank - 1];
		for (int i = 0; i < axis; i++) {
			nshape[i] = oshape[i];
		}
		for (int i = axis + 1; i < rank; i++) {
			nshape[i - 1] = oshape[i];
		}

		final int dtype = getDType();
		IntegerDataset count = new IntegerDataset(nshape);
		Dataset max = DatasetFactory.zeros(nshape, dtype);
		Dataset min = DatasetFactory.zeros(nshape, dtype);
		IntegerDataset maxIndex = new IntegerDataset(nshape);
		IntegerDataset minIndex = new IntegerDataset(nshape);
		Dataset sum = DatasetFactory.zeros(nshape, DTypeUtils.getLargestDType(dtype));
		DoubleDataset mean = new DoubleDataset(nshape);
		DoubleDataset var = new DoubleDataset(nshape);

		IndexIterator qiter = max.getIterator(true);
		int[] qpos = qiter.getPos();
		int[] spos = oshape.clone();

		while (qiter.hasNext()) {
			int i = 0;
			for (; i < axis; i++) {
				spos[i] = qpos[i];
			}
			spos[i++] = 0;
			for (; i < rank; i++) {
				spos[i] = qpos[i - 1];
			}

			final SummaryStatistics stats = new SummaryStatistics();
			//sum of logs is slow and we dont use it, so blocking its calculation here
			stats.setSumLogImpl(new NullStorelessUnivariateStatistic());
			
			double amax = Double.NEGATIVE_INFINITY;
			double amin = Double.POSITIVE_INFINITY;
			boolean hasNaNs = false;
			if (ignoreNaNs) {
				for (int j = 0; j < alen; j++) {
					spos[axis] = j;
					final double val = getDouble(spos);

					if (Double.isNaN(val)) {
						hasNaNs = true;
						continue;
					} else if (ignoreInfs && Double.isInfinite(val)) {
						continue;
					}

					if (val > amax) {
						amax = val;
					}
					if (val < amin) {
						amin = val;
					}

					stats.addValue(val);
				}
			} else {
				for (int j = 0; j < alen; j++) {
					spos[axis] = j;
					final double val = getDouble(spos);

					if (hasNaNs) {
						if (!Double.isNaN(val))
							stats.addValue(0);
						continue;
					}

					if (Double.isNaN(val)) {
						amax = Double.NaN;
						amin = Double.NaN;
						hasNaNs = true;
					} else if (ignoreInfs && Double.isInfinite(val)) {
						continue;
					} else {
						if (val > amax) {
							amax = val;
						}
						if (val < amin) {
							amin = val;
						}
					}
					stats.addValue(val);
				}
			}

			count.setAbs(qiter.index, (int) stats.getN());

			max.setObjectAbs(qiter.index, amax);
			min.setObjectAbs(qiter.index, amin);
			boolean fmax = false;
			boolean fmin = false;
			if (hasNaNs) {
				if (ignoreNaNs) {
					for (int j = 0; j < alen; j++) {
						spos[axis] = j;
						final double val = getDouble(spos);
						if (Double.isNaN(val))
							continue;

						if (!fmax && val == amax) {
							maxIndex.setAbs(qiter.index, j);
							fmax = true;
							if (fmin)
								break;
						}
						if (!fmin && val == amin) {
							minIndex.setAbs(qiter.index, j);
							fmin = true;
							if (fmax)
								break;
						}
					}
				} else {
					for (int j = 0; j < alen; j++) {
						spos[axis] = j;
						final double val = getDouble(spos);
						if (Double.isNaN(val)) {
							maxIndex.setAbs(qiter.index, j);
							minIndex.setAbs(qiter.index, j);
							break;
						}
					}
				}
			} else {
				for (int j = 0; j < alen; j++) {
					spos[axis] = j;
					final double val = getDouble(spos);
					if (!fmax && val == amax) {
						maxIndex.setAbs(qiter.index, j);
						fmax = true;
						if (fmin)
							break;
					}
					if (!fmin && val == amin) {
						minIndex.setAbs(qiter.index, j);
						fmin = true;
						if (fmax)
							break;
					}
				}
			}
			sum.setObjectAbs(qiter.index, stats.getSum());
			mean.setAbs(qiter.index, stats.getMean());
			var.setAbs(qiter.index, stats.getVariance());
		}
		setStoredValue(storeName(ignoreNaNs, ignoreInfs, STORE_COUNT + "-" + axis), count);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MAX + "-" + axis), max);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MIN + "-" + axis), min);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_SUM + "-" + axis), sum);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MEAN + "-" + axis), mean);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_VAR + "-" + axis), var);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MAX + STORE_INDEX + "-" + axis), maxIndex);
		storedValues.put(storeName(ignoreNaNs, ignoreInfs, STORE_MIN + STORE_INDEX + "-" + axis), minIndex);
	}

	/**
	 * @param x
	 * @return number from given double
	 */
	abstract protected Number fromDoubleToNumber(double x);

	private SummaryStatistics getStatistics(boolean ignoreNaNs) {
		boolean ignoreInfs = false; // TODO
		if (!hasFloatingPointElements()) {
			ignoreNaNs = false;
		}

		String n = storeName(ignoreNaNs, ignoreInfs, STORE_STATS);
		SummaryStatistics stats = (SummaryStatistics) getStoredValue(n);
		if (stats == null) {
			calculateSummaryStats(ignoreNaNs, ignoreInfs, n);
			stats = (SummaryStatistics) getStoredValue(n);
		}

		return stats;
	}

	@Override
	public int[] maxPos() {
		return maxPos(false);
	}

	@Override
	public int[] minPos() {
		return minPos(false);
	}

	private int getHash() {
		Object value = getStoredValue(STORE_HASH);
		if (value == null) {
			value = getStoredValue(STORE_SHAPELESS_HASH);
			if (value == null) {
				calculateMaxMin(false, false);
				value = getStoredValue(STORE_SHAPELESS_HASH);
			}

			int ihash = (Integer) value;
			int rank = shape.length;
			for (int i = 0; i < rank; i++) {
				ihash = ihash * 17 + shape[i];
			}
			storedValues.put(STORE_HASH, ihash);
			return ihash;
		}

		return (Integer) value;
	}

	protected Object getMaxMin(boolean ignoreNaNs, boolean ignoreInfs, String key) {
		if (!hasFloatingPointElements()) {
			ignoreNaNs = false;
			ignoreInfs = false;
		}

		key = storeName(ignoreNaNs, ignoreInfs , key);
		Object value = getStoredValue(key);
		if (value == null) {
			calculateMaxMin(ignoreNaNs, ignoreInfs);
			value = getStoredValue(key);
		}

		return value;
	}

	private Object getStatistics(boolean ignoreNaNs, int axis, String stat) {
		if (!hasFloatingPointElements())
			ignoreNaNs = false;

		boolean ignoreInfs = false; // TODO
		stat = storeName(ignoreNaNs, ignoreInfs , stat);
		axis = checkAxis(axis);
		Object obj = getStoredValue(stat);

		if (obj == null) {
			calculateSummaryStats(ignoreNaNs, ignoreInfs, axis);
			obj = getStoredValue(stat);
		}

		return obj;
	}

	@Override
	public Number max(boolean... ignoreInvalids) {
		boolean igNan = ignoreInvalids!=null && ignoreInvalids.length>0 ? ignoreInvalids[0] : false;
		boolean igInf = ignoreInvalids!=null && ignoreInvalids.length>1 ? ignoreInvalids[1] : igNan;
		return (Number) getMaxMin(igNan, igInf, STORE_MAX);
	}

	@Override
	public Dataset max(int axis) {
		return max(false, axis);
	}

	@Override
	public Dataset max(boolean ignoreNaNs, int axis) {
		return (Dataset) getStatistics(ignoreNaNs, axis, STORE_MAX + "-" + axis);
	}

	@Override
	public Number min(boolean... ignoreInvalids) {
		boolean igNan = ignoreInvalids!=null && ignoreInvalids.length>0 ? ignoreInvalids[0] : false;
		boolean igInf = ignoreInvalids!=null && ignoreInvalids.length>1 ? ignoreInvalids[1] : igNan;
		return (Number) getMaxMin(igNan, igInf, STORE_MIN);
	}

	@Override
	public Dataset min(int axis) {
		return min(false, axis);
	}

	@Override
	public Dataset min(boolean ignoreNaNs, int axis) {
		return (Dataset) getStatistics(ignoreNaNs, axis, STORE_MIN + "-" + axis);
	}

	@Override
	public int argMax() {
		return argMax(false);
	}

	@Override
	public int argMax(boolean ignoreInvalids) {
		return getFlat1DIndex(maxPos(ignoreInvalids));
	}

	@Override
	public IntegerDataset argMax(int axis) {
		return argMax(false, axis);
	}

	@Override
	public IntegerDataset argMax(boolean ignoreNaNs, int axis) {
		return (IntegerDataset) getStatistics(ignoreNaNs, axis, STORE_MAX + STORE_INDEX + "-" + axis);
	}

	@Override
	public int argMin() {
		return argMin(false);
	}

	@Override
	public int argMin(boolean ignoreInvalids) {
		return getFlat1DIndex(minPos(ignoreInvalids));
	}

	@Override
	public IntegerDataset argMin(int axis) {
		return argMin(false, axis);
	}

	@Override
	public IntegerDataset argMin(boolean ignoreNaNs, int axis) {
		return (IntegerDataset) getStatistics(ignoreNaNs, axis, STORE_MIN + STORE_INDEX + "-" + axis);
	}

	@Override
	public Number peakToPeak() {
		return fromDoubleToNumber(max().doubleValue() - min().doubleValue());
	}

	@Override
	public Dataset peakToPeak(int axis) {
		return Maths.subtract(max(axis), min(axis));
	}

	@Override
	public long count() {
		return count(false);
	}

	@Override
	public long count(boolean ignoreNaNs) {
		return getStatistics(ignoreNaNs).getN();
	}

	@Override
	public Dataset count(int axis) {
		return count(false, axis);
	}

	@Override
	public Dataset count(boolean ignoreNaNs, int axis) {
		return (Dataset) getStatistics(ignoreNaNs, axis, STORE_COUNT + "-" + axis);
	}

	@Override
	public Object sum() {
		return sum(false);
	}

	@Override
	public Object sum(boolean ignoreNaNs) {
		return getStatistics(ignoreNaNs).getSum();
	}

	@Override
	public Dataset sum(int axis) {
		return sum(false, axis);
	}

	@Override
	public Dataset sum(boolean ignoreNaNs, int axis) {
		return (Dataset) getStatistics(ignoreNaNs, axis, STORE_SUM + "-" + axis);
	}

	@Override
	public Object typedSum() {
		return typedSum(getDType());
	}

	@Override
	public Object typedSum(int dtype) {
		return DTypeUtils.fromDoubleToBiggestNumber(getStatistics(false).getSum(), dtype);
	}

	@Override
	public Dataset typedSum(int dtype, int axis) {
		return DatasetUtils.cast(sum(axis), dtype);
	}

	@Override
	public Object product() {
		return Stats.product(this);
	}

	@Override
	public Dataset product(int axis) {
		return Stats.product(this, axis);
	}

	@Override
	public Object typedProduct(int dtype) {
		return Stats.typedProduct(this, dtype);
	}

	@Override
	public Dataset typedProduct(int dtype, int axis) {
		return Stats.typedProduct(this, dtype, axis);
	}

	@Override
	public Object mean(boolean... ignoreNaNs) {
		boolean ig = ignoreNaNs!=null && ignoreNaNs.length>0 ? ignoreNaNs[0] : false;
		return getStatistics(ig).getMean();
	}

	@Override
	public Dataset mean(int axis) {
		return mean(false, axis);
	}

	@Override
	public Dataset mean(boolean ignoreNaNs, int axis) {
		return (Dataset) getStatistics(ignoreNaNs, axis, STORE_MEAN + "-" + axis);
	}

	@Override
	public Number variance() {
		return variance(false);
	}

	@Override
	public Number variance(boolean isDatasetWholePopulation) {
		SummaryStatistics stats = getStatistics(false);

		return isDatasetWholePopulation ? stats.getPopulationVariance() : stats.getVariance();
	}

	@Override
	public Dataset variance(int axis) {
		return (Dataset) getStatistics(false, axis, STORE_VAR + "-" + axis);
	}

	@Override
	public Number stdDeviation() {
		return Math.sqrt(variance().doubleValue());
	}

	@Override
	public Number stdDeviation(boolean isDatasetWholePopulation) {
		return Math.sqrt(variance(isDatasetWholePopulation).doubleValue());
	}

	@Override
	public Dataset stdDeviation(int axis) {
		final Dataset v = (Dataset) getStatistics(false, axis, STORE_VAR + "-" + axis);
		return Maths.sqrt(v);
	}

	@Override
	public Number rootMeanSquare() {
		final SummaryStatistics stats = getStatistics(false);
		final double mean = stats.getMean();
		return Math.sqrt(stats.getVariance() + mean * mean);
	}

	@Override
	public Dataset rootMeanSquare(int axis) {
		Dataset v = (Dataset) getStatistics(false, axis, STORE_VAR + "-" + axis);
		Dataset m = (Dataset) getStatistics(false, axis, STORE_MEAN + "-" + axis);
		Dataset result = Maths.power(m, 2);
		return Maths.sqrt(result.iadd(v));
	}

	/**
	 * Set item from compatible dataset in a direct and speedy way. Remember to setDirty afterwards.
	 * 
	 * @param dindex
	 * @param sindex
	 * @param src
	 *            is the source data buffer
	 */
	protected abstract void setItemDirect(final int dindex, final int sindex, final Object src);

	protected Dataset getInternalError() {
		ILazyDataset led = super.getError();
		if (led == null)
			return null;

		Dataset ed = null;
		try {
			ed = DatasetUtils.sliceAndConvertLazyDataset(led);
		} catch (DatasetException e) {
			logger.error("Could not get data from lazy dataset", e);
		}
		if (!(led instanceof Dataset)) {
			setError(ed); // set back
		}

		// check for broadcast strides
		Object bs = getStoredValue(STORE_BROADCAST);
		if (bs == null) {
			bs = new BroadcastStride(ed, shape);
			setStoredValue(STORE_BROADCAST, bs);
		}
		return ed;
	}

	class BroadcastStride {
		private int[] bStride;
		private int[] nShape;
		private int bOffset;

		public BroadcastStride(Dataset d, final int[] newShape) {
			d.setShape(BroadcastUtils.padShape(d.getShapeRef(), newShape.length - d.getRank())); // set to padded shape
			bStride = BroadcastUtils.createBroadcastStrides(d, newShape);
			nShape = newShape.clone();
			bOffset = d.getOffset();
		}

		public int get1DIndex(int i) {
			if (i < 0) {
				i += nShape[0];
			}
			if (i < 0 || i >= nShape[0]) {
				throwAIOOBException(i, nShape[0], 0);
			}
			return i*bStride[0] + bOffset;
		}

		protected int get1DIndex(int i, int j) {
			if (i < 0) {
				i += nShape[0];
			}
			if (i < 0 || i >= nShape[0]) {
				throwAIOOBException(i, nShape[0], 0);
			}
			if (j < 0) {
				j += nShape[1];
			}
			if (j < 0 || j >= nShape[1]) {
				throwAIOOBException(i, nShape[1], 1);
			}
			return i*bStride[0] + j*bStride[1] + bOffset;
		}

		protected int get1DIndex(int... n) {
			return get1DIndexFromStrides(nShape, bStride, bOffset, n);
		}
	}

	@Override
	public Dataset getError() {
		Dataset ed = getInternalError();
		if (ed == null)
			return null;

		if (ed.getSize() != getSize()) {
			DoubleDataset errors = new DoubleDataset(shape);
			errors.setSlice(ed);
			return errors;
		}
		return ed;
	}

	@Override
	public double getError(final int i) {
		Dataset ed = getInternalError();
		if (ed == null)
			return 0;

		BroadcastStride bs = (BroadcastStride) getStoredValue(STORE_BROADCAST);
		return ed.getElementDoubleAbs(bs.get1DIndex(i));
	}

	@Override
	public double getError(final int i, final int j) {
		Dataset ed = getInternalError();
		if (ed == null)
			return 0;

		BroadcastStride bs = (BroadcastStride) getStoredValue(STORE_BROADCAST);
		return ed.getElementDoubleAbs(bs.get1DIndex(i, j));
	}

	@Override
	public double getError(int... pos) {
		Dataset ed = getInternalError();
		if (ed == null)
			return 0;

		BroadcastStride bs = (BroadcastStride) getStoredValue(STORE_BROADCAST);
		return ed.getElementDoubleAbs(bs.get1DIndex(pos));
	}

	@Override
	public double[] getErrorArray(final int i) {
		Dataset ed = getInternalError();
		if (ed == null)
			return null;

		return new double[] {getError(i)};
	}

	@Override
	public double[] getErrorArray(final int i, final int j) {
		Dataset ed = getInternalError();
		if (ed == null)
			return null;

		return new double[] {getError(i, j)};
	}

	@Override
	public double[] getErrorArray(int... pos) {
		Dataset ed = getInternalError();
		if (ed == null)
			return null;

		return new double[] {getError(pos)};
	}

	protected Dataset getInternalSquaredError() {
		Dataset sed = getErrorBuffer();
		// check for broadcast strides
		Object bs = getStoredValue(STORE_BROADCAST);
		if (bs == null) {
			bs = new BroadcastStride(sed, shape);
			setStoredValue(STORE_BROADCAST, bs);
		}
		return sed;
	}

	@Override
	public Dataset getErrorBuffer() {
		ErrorMetadata emd = getErrorMetadata();
		if (emd == null)
			return null;

		if (!(emd instanceof ErrorMetadataImpl)) {
			ILazyDataset led = emd.getError();
			Dataset ed;
			try {
				ed = DatasetUtils.sliceAndConvertLazyDataset(led);
				emd  = MetadataFactory.createMetadata(ErrorMetadata.class);
				setMetadata(emd);
				((ErrorMetadataImpl) emd).setError(ed);
			} catch (MetadataException me) {
				logger.error("Could not create metadata", me);
			} catch (DatasetException e) {
				logger.error("Could not get data from lazy dataset", e);
			}
		}

		return ((ErrorMetadataImpl) emd).getSquaredError();
	}

	@Override
	public void setError(Serializable errors) {
		super.setError(errors);
		Object bs = getStoredValue(STORE_BROADCAST);
		if (bs != null) {
			setStoredValue(STORE_BROADCAST, null);
		}
	}

	/**
	 * Set a copy of the buffer that backs the (squared) error data
	 * @param buffer can be null, anything that can be used to create a DoubleDataset or CompoundDoubleDataset
	 */
	@Override
	public void setErrorBuffer(Serializable buffer) {
		Object bs = getStoredValue(STORE_BROADCAST);
		if (bs != null) {
			setStoredValue(STORE_BROADCAST, null);
		}

		if (buffer == null) {
			clearMetadata(ErrorMetadata.class);
			return;
		}

		IDataset d = (IDataset) createFromSerializable(buffer, false);
		ErrorMetadata emd = getErrorMetadata();
		if (!(emd instanceof ErrorMetadataImpl)) {
			try {
				emd = MetadataFactory.createMetadata(ErrorMetadata.class);
				setMetadata(emd);
			} catch (MetadataException me) {
				logger.error("Could not create metadata", me);
			}
		}
		((ErrorMetadataImpl) emd).setSquaredError(d);
	}
}
