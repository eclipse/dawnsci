package org.eclipse.dawnsci.nexus.scan;

import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

/**
 * Abstract superclass of NeXus application definition factory objects.
 * An object of this type knows how to build a tree of NeXus base classes
 * meeting an application definition
 * @author mattdickie
 *
 */
public interface NxApplicationDefinitionFactory {
	
	/**
	 * TODO: which of these two methods (or perhaps both, for convenience)
	 * 
	 * Add a link from the given location in the application definition to the
	 * given location in the main entry.
	 * 
	 * @param from
	 * @param to
	 */
	public void addLink(String fromPath, String toPath) throws NexusValidationException;
	
	/**
	 * TODO: which of these two methods (or perhaps both, for convenience)
	 * 
	 * TODO: can't actually add the link until the build method is called.
	 * 
	 * @param fromPath
	 * @param node
	 */
	public void addLink(String fromPath, Node node) throws NexusValidationException;
	
	/**
	 * TODO do we validate the components as they are added?
	 * Note: this is a shortcut for addLink(/NXentry/Nxinstrument, 
	 * @param instrument
	 */
	public void setInstrument(NXinstrument instrument) throws NexusValidationException;
	
	/**
	 * Adds a single component.
	 * TODO: Is this always a child of NXinstrument?
	 * TODO: shall we use this method, or just on individual base classes?
	 * @param component
	 */
	public void addComponent(NXobject component);
	
	public void validate() throws NexusValidationException;
	
	/**
	 * Build the 
	 * TODO: it is theoretically possible for application definitions to have more than
	 * one root entry, although at present none do
	 * 
	 * TODO: this validates the application definition
	 * 
	 * @return
	 */
	public NXentry build(final NXentry entry) throws NexusValidationException;
	
	

}
