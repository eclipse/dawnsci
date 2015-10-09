/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.plotting.api.trace;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.HistoType;
import org.eclipse.dawnsci.plotting.api.histogram.ImageServiceBean.ImageOrigin;

/**
 * Interface used for the plotting system to plot images.
 * 
 * In the LightWeightPlotter this is called ImageTrace.
 * 
 * @author Matthew Gerring
 * 
 * <pre>
 *    Histogramming Explanation
 *    ---------------------------
 *    Image intensity distribution:
 * 
 *                 ++----------------------**---------------
 *                 +                      *  *              
 *                 ++                    *    *             
 *                 |                     *    *             
 *                 ++                    *     *            
 *                 *                    *       *            
 *                 +*                   *       *            
 *                 |*                  *        *            
 *                 +*                  *        *           
 *                 |                  *          *         
 *                 ++                 *          *          
 *                 |                  *           *        
 *                 ++                 *           *        
 *                 |                 *            *        
 *                 ++                *            *       
 *                                  *              *      
 *         Min Cut           Min    *              *      Max                     Max cut
 *  Red <- |   (min colour)  |    (color range, palette)  |      (max color)      | -> Blue
 *                                 *                 *  
 *                 |              *        +         *  
 * ----------------++------------**--------+----------**----+---------------**+---------------++
 * </pre>
 */
public interface IImageTrace extends IPaletteTrace, IDownsampledTrace, ICoordinateSystemTrace{

		
	public enum DownsampleType {
		
		POINT(0, "Point, top left of bin"),  // select corner point of bin
		MEAN(1, "Mean value of bin"),   // mean average over bin
		MAXIMUM(2, "Maximum value of bin"), // use maximum value in bin
		MINIMUM(3, "Minimum value of bin"); // use minimum value in bin
		
		private String label;
		private int index;
		
		DownsampleType(int index, String label) {
			this.index = index;
			this.label = label;
		}
		
		public String getLabel() {
			return label;
		}
		public int getIndex() {
			return index;
		}
		public static DownsampleType forLabel(String label) {
			for (DownsampleType type : values()) {
				if (type.label.equals(label)) return type;
			}
			return null;
		}
	}
	
	/**
	 * If the original data was RGB data, the trace may sum this and ignore
	 * the RGB values. In this case the original RGB data as read from disk
	 * is available by calling this method.
	 * @return
	 */
	public IDataset getRGBData();

	/**
	 * Default is TOP_LEFT unlike normal plotting
	 * @return
	 */
	public ImageOrigin getImageOrigin();
	
	/**
	 * Repaints the axes and the image to the new origin.
	 * @param origin
	 */
	public void setImageOrigin(final ImageOrigin origin);
	
	
	/**
	 * Call to set image data
	 * @param image
	 * @param axes - may be null
	 * @param performAutoScale - true to rescale to new selection, otherwise keeps last axis position.
	 * @return false if could not set data
	 */
	public boolean setData(final IDataset image, List<? extends IDataset> axes, boolean performAutoScale);
	
	/**
	 * Change the axes without changing the underlying data.
	 * @param axes
	 * @param performAutoScale
	 */
	public void setAxes(List<? extends IDataset> axes, boolean performAutoScale);
	
	/**
	 * @return the axes if they were set - may be null
	 */
	public List<IDataset> getAxes();
	
	/**
	 * 
	 * @return the down-sample type being used for plotting less data
	 * than received.
	 */
	public DownsampleType getDownsampleType();
	
	/**
	 * Change the down-sample type, will also refresh the UI.
	 * @param type
	 */
	public void setDownsampleType(DownsampleType type);
	
	/**
	 * @param rehisto image when run
	 */
	public void rehistogram();
	
	/**
	 * return the HistoType being used
	 * @return
	 */
	public HistoType getHistoType();
	
	/**
	 * Sets the histo type.
	 */
	public boolean setHistoType(HistoType type);

	/**
	 * You may set the image not to redraw images during updating a number of 
	 * settings for efficiency reasons. Do this in a try{} finally{} block to 
	 * avoid it being left off.
	 * 
	 * @param b
	 */
	void setImageUpdateActive(boolean b);
	
	/**
	 * Call to redraw the image, normally the same as repaint on Figure.
	 */
	public void repaint();
	
	
	/**
	 * 
	 * @return the current downsampled Dataset being used to draw the image.
	 */
	public IDataset getDownsampled();

	/**
	 * 
	 * @return the current downsampled mask or null if there is no mask.
	 */
	public IDataset getDownsampledMask();

	
	/**
	 * @return the bin side in pixels which will be used when drawing the image. 
               The bin is a square of side = the return value.
	 */
	public int getDownsampleBin();

	/**
	 * The masking dataset of there is one, normally null.
	 * false to mask the pixel, true to leave as is.
	 * 
	 * @return
	 */
	public IDataset getMask();
	
	/**
	 * The masking dataset of there is one, normally null.
	 * false to mask the pixel, true to leave as is.
	 * 
	 * If you don't send a BooleanDataset the system may attempt a cast
	 * and throw an exception.
	 * 
	 * @return
	 */
	public void setMask(IDataset bd);

	/**
	 * Can be used to clear memory used by the image if the plotting system is
	 * no longer active.
	 */
	public void sleep();

	/**
	 * Set the transparency setting
	 * The alpha value must be between 0 (transparent) and 255 (opaque).
	 * A value of -1 is the default and means that no CPU is used for alpha.
	 * @param alpha
	 */
	public void setAlpha(int alpha);
	
	/**
	 * Return the transparency value of the image.
	 * The alpha value must be between 0 (transparent) and 255 (opaque).
	 * A value of -1 is the default and means that no CPU is used for alpha.
	 */
	public int getAlpha();
	
	public void setGlobalRange(double[] globalRange);
}
