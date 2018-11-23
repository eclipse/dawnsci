package org.eclipse.dawnsci.remotedataset.client;

import java.util.List;

import org.eclipse.january.dataset.ShapeUtils;

public class RemoteDatasetInfo {
	
	private String name;
	private int[] shape;
	private int[] maxShape;
	private int dtype;
	private int isize;
	private int[] chunking;

	public RemoteDatasetInfo(List<String> info) {

		this.name  = info.get(0);
		this.shape = toIntArray(info.get(1));
		if (this.shape.length == 1 && shape[0] == -1) {
			System.err.println();
			
		}
		
		this.dtype = Integer.parseInt(info.get(2));
		this.isize = Integer.parseInt(info.get(3));

		if (info.size() > 4) {
			this.maxShape = (toIntArray(info.get(4)));
			this.chunking = (toIntArray(info.get(5)));
		} else {
			this.maxShape = shape;
			this.chunking = shape;
		}
	}
	
	public String getName() {
		return name;
	}

	public int[] getShape() {
		return shape;
	}

	public int[] getMaxShape() {
		return maxShape;
	}

	public int getDtype() {
		return dtype;
	}

	public int getIsize() {
		return isize;
	}

	public int[] getChunking() {
		return chunking;
	}

	private int[] toIntArray(String array) {
		// array is null, or of the form [1,2,3,4]
		if (array.equals("null"))
			return null;
		if (array.length() <= 2) {
			return new int[0]; // special case of scalar dataset
		}

		final String[] split = array.substring(1, array.length()-1).split(",");
		final int[]    ret   = new int[split.length];
		for (int i = 0; i < split.length; i++) {
			ret[i] = Integer.parseInt(split[i].trim());
			}
		return ret;
	}

}
