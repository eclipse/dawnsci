package org.eclipse.dawnsci.nexus.scan;

import org.eclipse.dawnsci.analysis.api.tree.TreeFile;
import org.eclipse.dawnsci.analysis.tree.impl.TreeFileImpl;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXroot;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.impl.NXentryImpl;
import org.eclipse.dawnsci.nexus.impl.NXobjectFactory;
import org.eclipse.dawnsci.nexus.impl.NXrootImpl;

public class DefaultNexusFileBuilder implements NexusFileBuilder {
	
	private NXobjectFactory nxObjectFactory = new NXobjectFactory();
	
	private NexusScan nexusScan;
	
	private String filePath;
	
	public DefaultNexusFileBuilder() {
	}
	
	public DefaultNexusFileBuilder(final NexusScan nexusScan) {
		// TODO: do we prefer a setter or a constructor?
		this.nexusScan = nexusScan;
	}
	
	@Override
	public TreeFile buildNexusFile() {
		if (nexusScan == null) {
			throw new IllegalStateException("Nexus scan not set");
		}
		if (filePath == null) {
			throw new IllegalStateException("Nexus path not set");
		}
		
		final TreeFileImpl treeFile = nxObjectFactory.createTreeFile(filePath);
		final NXroot rootNode = createNexusTree();
		treeFile.setGroupNode(rootNode);
		
		return treeFile;
	}
	
	private void saveFile(TreeFile nexusTree) throws Exception {
		// TODO: update project dependency so we can see gda.data.nexus?
//		NexusFile nexusFile = NexusUtils.createNexusFile(nexusTree.getFilename());
//		try {
//			nexusFile.addNode("/", nexusTree.getGroupNode());
//		} finally {
//			nexusFile.flush();
//			nexusFile.close();
//		}
	}

	protected NXroot createNexusTree() {
		NXrootImpl root = nxObjectFactory.createNXroot();
		root.setAttributeFile_name(filePath);
		// TODO set other attributes: nexus version, hdf5 version, etc
//		root.setAttributeFile_time(
		
		NXentryImpl entry = nxObjectFactory.createNXentry();
		root.setEntry(entry);
		
		NXinstrument instrument = nxObjectFactory.createNXinstrument();
		entry.setInstrument(instrument);
		
		NXsample sample = nxObjectFactory.createNXsample();
		entry.setSample(sample);
		
		for (NxDevice<?> nexusDevice : nexusScan.getDevices()) {
			final String deviceName = nexusDevice.getName();
			NXobject baseClassInstance = nexusDevice.createBaseClassInstance();
			
			switch (nexusDevice.getDeviceType()) {
			case INSTRUMENT:
				instrument.addGroupNode(deviceName, baseClassInstance);
				break;
			case SAMPLE:
				instrument.addGroupNode(deviceName, baseClassInstance);
				break;
			}
		}
		
		// TODO data group?
		// TODO other standard groups?
		
		return root;
	}

	@Override
	public void setNexusScan(NexusScan nexusScan) {
		this.nexusScan = nexusScan;
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	

}
