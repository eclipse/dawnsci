package org.eclipse.dawnsci.plotting.api.trace;

import java.util.List;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;

/**
 * A trace for adding together multiple other traces and plotting
 * them as if they were images.
 * 
 * The initial version is for multiple IImageTrace but future versions 
 * might include an IImageTrace covered by a vector trace.
 * 
 * @author Matthew Gerring
 *
 */
public interface ICompositeTrace extends ITrace {

	/**
	 * Add a subimage to the stacked image trace. The name of the 
	 * image must be unique inside the IStackedImageTrace
	 * 
	 * @param index or -1 to simply add the image on to the end.
	 * @param image
	 */
	public void add(ITrace image, int index) throws IllegalArgumentException;
	
	/**
	 * Add a subimage to the stacked image trace.
	 * @param image
	 * @param axes
	 */
	public void removeImage(String name);

	
	/**
	 * @return the axes which enclose all data
	 */
	public List<IDataset> getAxes();
	
	/**
	 * Set the colormap for the non-rgb datasets in the composite
	 * 
	 * @param name
	 */
	public void setPalette(String name);

	
}
