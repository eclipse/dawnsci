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
package org.eclipse.dawnsci.plotting.api.histogram;

import java.awt.image.BufferedImage;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

/**
 * 
   Histogramming Explanation
   ---------------------------
   Image intensity distribution:

                ++----------------------**---------------
                +                      *+ *              
                ++                    *    *             
                |                     *    *             
                ++                    *     *            
                *                    *       *            
                +*                   *       *            
                |*                  *        *            
                +*                  *        *           
                |                  *          *         
                ++                 *          *          
                |                  *           *        
                ++                 *           *        
                |                 *            *        
                ++                *            *       
                                 *              *      
        Min Cut           Min    *              *      Max                     Max cut
 Red <- |   (min colour)  |    (color range, palette)  |      (max color)      | -> Blue
                                *                 *  
                |              *        +         *  
----------------++------------**---------+----------**----+---------------**+---------------++

 */
public interface IImageService {

	
	/**
	 * Get a full image data for a given data set and PaletteData
	 * @param set
	 * @return
	 */
	public ImageData getImageData(ImageServiceBean bean) throws Exception;

	/**
	 * Get a full image for a given data set and PaletteData
	 * @param set
	 * @return
	 */
	public Image getImage(ImageServiceBean bean) throws Exception;
	
	/**
	 * Call to calculate min, max to be used in the histogram. These are the
	 * min and max inside the bounds which are to be part of the main histogram.
	 * The mean and real max values are also calculated.
	 * 
	 * The Dataset and the histogram type must be set in the bean.
	 * 
	 * The return array[2] was added in "Updated for Diffraction Tool." commit,
	 * but no trace of such usage. However it should not be removed, because
	 * it is useful as return array[3].
	 * 
	 * @param bean
	 * @return [0] = min [1] = max(=mean*constant) [2] = mean [3] max
	 */
	public double[] getFastStatistics(ImageServiceBean bean);
	
	/**
	 * Call to convert the ImageData to a BufferedImage which may be written to 
	 * file using a proper RBG map.
	 * 
	 * @param data
	 * @return
	 */
	public BufferedImage getBufferedImage(final ImageData data);
	
	/**
	 * Reads the preferences for the histogram and creates an image service bean for these.
	 * 
	 * @return
	 */
	public ImageServiceBean createBeanFromPreferences();
}
