package org.eclipse.dawnsci.nexus;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.dataset.impl.Dataset;
import org.eclipse.dawnsci.analysis.dataset.impl.DatasetFactory;

/**
 * Simple NeXus file test based on 'verysimple.nx5' example described in the
 * NeXus documentation at http://download.nexusformat.org/doc/html/introduction.html
 * Direct link: http://download.nexusformat.org/doc/html/_downloads/verysimple.nx5
 */
public class SimpleNexusFileTest extends AbstractNexusFileTestBase {

	private static final String FILE_NAME = "verysimple.nxs";

	@Override
	protected NXroot createNXroot() {
		// create the root object of the nexus file
		NXroot root = NexusNodeFactory.createNXroot();
		root.setAttributeFile_name(FILE_NAME);
		root.setAttributeFile_time("2014-09-08T09:07:11.939912");
		root.setAttributeNeXus_version("4.3.0");
		root.setAttributeHDF5_Version("1.8.9");
		root.setAttribute(null, "h5py_version", "2.3.0");

		// create the single entry object of the nexus file
		NXentry entry = NexusNodeFactory.createNXentry();
		root.setEntry(entry);

		NXdata dataGroup = NexusNodeFactory.createNXdata();
		entry.setData(dataGroup);

		long[] countsData = new long[] {
				1193, 4474, 53220, 274310, 515430,
				827880, 1227100, 1434640, 1330280, 1037070,
				598720, 316460, 56677, 1000, 1000
		};

		dataGroup.setDataset("counts", DatasetFactory.createFromObject(countsData));
		dataGroup.setAttribute("counts", "long_name", "photodiode counts");
		dataGroup.setAttribute("counts", "signal", 1.0);
		dataGroup.setAttribute("counts", "axes", "two_theta");

		IDataset twoTheta = DatasetFactory.createRange(18.9094, 18.9122, 0.0002, Dataset.FLOAT64);
		dataGroup.setDataset("two_theta", twoTheta);
		dataGroup.setAttribute("two_theta", "units", "degrees");
		dataGroup.setAttribute("two_theta", "long_name", "two_theta (degrees)");

		return root;
	}

	@Override
	protected String getFilename() {
		return FILE_NAME;
	}

}
