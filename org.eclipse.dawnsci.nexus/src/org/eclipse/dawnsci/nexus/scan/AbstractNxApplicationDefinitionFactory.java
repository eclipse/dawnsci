package org.eclipse.dawnsci.nexus.scan;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

public class AbstractNxApplicationDefinitionFactory implements
		NxApplicationDefinitionFactory {
	
	private Map<String, String> linkPaths = new HashMap<>();
	
	private Map<String, Node> linkNodes = new HashMap<>();

	@Override
	public void addLink(String fromPath, String toPath) {
		linkPaths.put(fromPath, toPath);
	}

	@Override
	public void addLink(String fromPath, Node node) {
		// TODO Auto-generated method stub

	}

	@Override
	public NXentry build(NXentry entry) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInstrument(NXinstrument instrument)
			throws NexusValidationException {
		// TODO validate instrument here?
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(NXobject component) {
		// TODO validate component here?
		// TODO: should we have this method or only do this in the handwritten subclasses
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validate() throws NexusValidationException {
		// TODO Auto-generated method stub
		
	}


}
