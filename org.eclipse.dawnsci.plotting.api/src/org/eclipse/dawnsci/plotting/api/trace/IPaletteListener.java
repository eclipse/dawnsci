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

import java.util.EventListener;

/**
 
 
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

 * @author Matthew Gerring
 *
 */
public interface IPaletteListener extends EventListener {

	/**
	 * Called when palette data changed
	 * @param evt
	 */
	public void paletteChanged(PaletteEvent evt);
	
	/**
	 * Called when min changed.
	 * @param evt
	 */
	public void minChanged(PaletteEvent evt);
	
	/**
	 * Called when max changed.
	 * @param evt
	 */
	public void maxChanged(PaletteEvent evt);
	
	/**
	 * Called when max cut changed.
	 * @param evt
	 */
	public void maxCutChanged(PaletteEvent evt);
	
	
	/**
	 * Called when max cut changed.
	 * @param evt
	 */
	public void minCutChanged(PaletteEvent evt);
	
	/**
	 * Called when max cut changed.
	 * @param evt
	 */
	public void nanBoundsChanged(PaletteEvent evt);
	
	/**
	 * Called when the image origin is changed by the user.
	 * @param evt
	 */
	public void imageOriginChanged(PaletteEvent evt);
	
	/**
	 * To get the new mask the source of the event should be 
	 * cast to IImageTrace and the getMask() method used on that
	 * object.
	 * 
	 * @param evt
	 */
	public void maskChanged(PaletteEvent evt);
	
	/**
	 * Notification when the rescaleHistogram setting has been changed
	 * 
	 * @param evt the palette event
	 */
	public void rescaleHistogramChanged(PaletteEvent evt);

	public class Stub implements IPaletteListener {

		@Override
		public void paletteChanged(PaletteEvent evt) {
			updateEvent(evt);
			
		}

		@Override
		public void minChanged(PaletteEvent evt) {
			updateEvent(evt);
			
		}

		@Override
		public void maxChanged(PaletteEvent evt) {
			updateEvent(evt);
			
		}

		@Override
		public void maxCutChanged(PaletteEvent evt) {
			updateEvent(evt);
			
		}

		@Override
		public void minCutChanged(PaletteEvent evt) {
			updateEvent(evt);
			
		}

		@Override
		public void nanBoundsChanged(PaletteEvent evt) {
			updateEvent(evt);
			
		}

		@Override
		public void maskChanged(PaletteEvent evt) {
			updateEvent(evt);
		}
		
		@Override
		public void imageOriginChanged(PaletteEvent evt) {
			updateEvent(evt);
		}

		@Override
		public void rescaleHistogramChanged(PaletteEvent evt) {
			updateEvent(evt);
		}
		
		protected void updateEvent(PaletteEvent evt) {
			// TODO
		}


	}

}
