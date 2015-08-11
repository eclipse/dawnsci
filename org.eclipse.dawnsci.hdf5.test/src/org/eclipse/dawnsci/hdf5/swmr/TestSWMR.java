package org.eclipse.dawnsci.hdf5.swmr;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ncsa.hdf.hdf5lib.H5;
import ncsa.hdf.hdf5lib.HDF5Constants;
import ncsa.hdf.hdf5lib.exceptions.HDF5Exception;
import ncsa.hdf.hdf5lib.exceptions.HDF5LibraryException;

public class TestSWMR {

	private final static String filename = "test-scratch/testSWMR.h5";

	@Before
	public void setup() throws NullPointerException, HDF5Exception {
		// create skeleton file with 1D dataset of unlimited length
		File parent = new File(filename).getParentFile();
		if (!parent.exists()) {
			parent.mkdir();
		}
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
		long fileID = H5.H5Fcreate(filename, HDF5Constants.H5F_ACC_TRUNC, HDF5Constants.H5P_DEFAULT, fapl);
		long dsID = H5.H5Screate_simple(1, new long[] {0}, new long[] {HDF5Constants.H5S_UNLIMITED});
		long dcpl = H5.H5Pcreate(HDF5Constants.H5P_DATASET_CREATE);
		H5.H5Pset_chunk(dcpl, 1, new long[] {2});
		long dID = H5.H5Dcreate(fileID, "/data",
								HDF5Constants.H5T_IEEE_F32LE, dsID,
								HDF5Constants.H5P_DEFAULT, dcpl, HDF5Constants.H5P_DEFAULT);
		H5.H5Dflush(dID);
		H5.H5Drefresh(dID);

		H5.H5Dclose(dID);
		H5.H5Pclose(dcpl);
		H5.H5Sclose(dsID);
		H5.H5Fclose(fileID);
		H5.H5Pclose(fapl);
	}

	/**
	 * open file in SWMR or non-SWMR, read dataset and wait
	 * @param file
	 * @param swmr
	 * @param sleep
	 * @throws HDF5LibraryException
	 */
	private void readSWMR(String file, boolean swmr, long sleep) throws HDF5LibraryException {
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		int access = HDF5Constants.H5F_ACC_RDONLY;
		if (swmr) {
			H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
			access |= HDF5Constants.H5F_ACC_SWMR_READ;
		}
		long fileID = H5.H5Fopen(file, access, fapl);
		long dID = H5.H5Dopen(fileID, "/data", HDF5Constants.H5P_DEFAULT);

		long fsID = H5.H5Dget_space(dID);
		int rank = (int) H5.H5Sget_simple_extent_ndims(fsID);
		long[] shape = new long[rank];
		long[] maxShape = new long[rank];
		H5.H5Sget_simple_extent_dims(fsID, shape, maxShape);

		try {
			Assert.assertEquals(1, rank);
			Assert.assertArrayEquals(new long[] {4}, shape);
			Assert.assertArrayEquals(new long[] {HDF5Constants.H5S_UNLIMITED}, maxShape);
			float[] buf = new float[4];
			H5.H5Dread_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buf);
			Assert.assertArrayEquals(new float[] {0, 1, 2, 3}, buf, 1f-5);
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
			}
		} finally {
			H5.H5Sclose(fsID);
			H5.H5Dclose(dID);
			H5.H5Fclose(fileID);
			H5.H5Pclose(fapl);
		}
	}

	@Test
	public void testReadSWMR() throws Exception {
		readSWMR("testfiles/testnonSWMR.h5", true, 0);
		readSWMR("testfiles/testSWMR.h5", true, 0);
	}

	@Test
	public void testRead() throws Exception {
		readSWMR("testfiles/testnonSWMR.h5", false, 0);
		readSWMR("testfiles/testSWMR.h5", false, 0);
	}

	private Thread createReadThread(final String file, final boolean swmr, final long sleep) {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					readSWMR(file, swmr, sleep);
				} catch (Exception e) {
					System.err.println(Thread.currentThread().getName());
//					e.printStackTrace();
				}
				
			}
		}, "Thd read: swmr=" + swmr + ", sleep=" + sleep + "ms");
	}

	private static final long SLEEP = 500;

	@Test
	public void testReadClosedSimultaneously() throws HDF5Exception, NullPointerException {
		long sleep = SLEEP;
		String[] files = new String[] {"testfiles/testnonSWMR.h5", "testfiles/testSWMR.h5"};

		for (int k = 0; k < 2; k++) {
			String file = files[k];
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					System.err.println(file + " " + i + " " + j);
					Thread ta = createReadThread(file, i != 0, sleep);
					Thread tb = createReadThread(file, j != 0, sleep);
					System.err.println("Starting A");
					ta.start();
					try {
						Thread.sleep(sleep / 3);
					} catch (InterruptedException e) {
					}
					System.err.println("Starting B");
					tb.start();

					while (ta.isAlive() || tb.isAlive()) {
						try {
							Thread.sleep(sleep / 2);
						} catch (InterruptedException e) {
						}
					}
					System.err.println("A & B finished");

					try {
						Thread.sleep(sleep);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}

	/**
	 * open and write in SWMR mode and wait then optionally extend, write and wait again
	 * @param file
	 * @param sleep
	 * @param second
	 * @throws HDF5LibraryException
	 */
	private void writeSWMR(String file, long sleep, boolean second) throws HDF5LibraryException {
		long fapl = H5.H5Pcreate(HDF5Constants.H5P_FILE_ACCESS);
		H5.H5Pset_libver_bounds(fapl, HDF5Constants.H5F_LIBVER_LATEST, HDF5Constants.H5F_LIBVER_LATEST);
		long fileID = H5.H5Fopen(file, HDF5Constants.H5F_ACC_RDWR, fapl);
		float[] data = new float[] {0, 1, 2, 3};
		H5.H5Fstart_swmr_write(fileID);
		long dID = H5.H5Dopen(fileID, "/data", HDF5Constants.H5P_DEFAULT);
		H5.H5Dset_extent(dID, new long[] {4});
		H5.H5Dwrite_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, data);
		H5.H5Dflush(dID);
		H5.H5Drefresh(dID);

		float[] buf = new float[4];
		H5.H5Dread_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, buf);

		try {
			Thread.sleep(sleep);
		} catch (InterruptedException e) {
		}

		if (second) {
			data = new float[] {0, 1, 2, 3, 0, 1, 2, 3};
			H5.H5Dset_extent(dID, new long[] {8});
			H5.H5Dwrite_float(dID, HDF5Constants.H5T_IEEE_F32LE, HDF5Constants.H5S_ALL, HDF5Constants.H5S_ALL, HDF5Constants.H5P_DEFAULT, data);
			H5.H5Dflush(dID);
			H5.H5Drefresh(dID);
			
			try {
				Thread.sleep(sleep);
			} catch (InterruptedException e) {
			}
		}

		H5.H5Dclose(dID);
		H5.H5Fclose(fileID);
		H5.H5Pclose(fapl);
	}

	@Test
	public void testWrite() throws HDF5Exception, NullPointerException {
		writeSWMR(filename, 0, false);
	}

	@Test
	public void testMultipleWrite() throws HDF5Exception, NullPointerException {
		writeSWMR(filename, 0, true);
	}

	private Thread createWriteThread(final String file, final long sleep, final boolean second) {
		return new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					writeSWMR(file, sleep, second);
				} catch (HDF5LibraryException e) {
					System.err.println(Thread.currentThread().getName());
//					e.printStackTrace();
				}
				
			}
		}, "Thd write: second=" + second + ", sleep=" + sleep + "ms");
	}

	@Test
	public void testReadOpenSimultaneously() throws HDF5Exception, NullPointerException {
		long sleep = SLEEP;

		Thread tw = createWriteThread(filename, 12 * sleep, false);
		tw.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
	
		String file = filename;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 2; j++) {
				System.err.println(file + " " + i + " " + j);
				Thread ta = createReadThread(file, i != 0, sleep);
				Thread tb = createReadThread(file, j != 0, sleep);
				System.err.println("Starting A");
				ta.start();
				try {
					Thread.sleep(sleep / 3);
				} catch (InterruptedException e) {
				}
				System.err.println("Starting B");
				tb.start();

				while (ta.isAlive() || tb.isAlive()) {
					try {
						Thread.sleep(sleep / 2);
					} catch (InterruptedException e) {
					}
				}
				System.err.println("A & B finished");

				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
				}
			}
		}

		try {
			System.err.println("Waiting to join...");
			tw.join();
		} catch (InterruptedException e) {
		}
		System.err.println("Finished");
	}

	@Test
	public void testWriteReadSWMR() throws HDF5LibraryException {
		long sleep = SLEEP;
		Thread tw = createWriteThread(filename, sleep, false);
		tw.start();
		try {
			Thread.sleep(sleep/3);
		} catch (InterruptedException e) {
		}
		try {
			readSWMR(filename, true, 0);
		} finally {
			try {
				tw.join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Test
	public void testWriteReadNonSWMR() throws HDF5LibraryException {
		long sleep = SLEEP;
		Thread tw = createWriteThread(filename, sleep, false);
		tw.start();
		try {
			Thread.sleep(sleep/3);
		} catch (InterruptedException e) {
		}
		try {
			readSWMR(filename, false, 0);
		} finally {
			try {
				tw.join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Test
	public void testWriteReadEarlyNonSWMR() throws HDF5LibraryException {
		long sleep = SLEEP;
		Thread tw = createWriteThread(filename, sleep, false);
		tw.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		try {
			readSWMR(filename, false, 0);
			Assert.fail("Should not failed as not written yet");
		} catch (AssertionError e) {
			// success
		} finally {
			try {
				tw.join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Test
	public void testWriteReadEarlySWMR() throws HDF5LibraryException {
		long sleep = SLEEP;
		Thread tw = createWriteThread(filename, sleep, false);
		tw.start();
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}
		try {
			readSWMR(filename, true, 0);
			Assert.fail("Should not failed as not written yet");
		} catch (AssertionError e) {
			// success
		} finally {
			try {
				tw.join();
			} catch (InterruptedException e) {
			}
		}
	}

	@Test
	public void testMissingExtLink() throws HDF5LibraryException, NullPointerException {
		long fileID = H5.H5Fcreate("test-scratch/missinglink.h5", HDF5Constants.H5F_ACC_TRUNC, HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);

		H5.H5Lcreate_external("missing.h5", "/target", fileID, "/ext", HDF5Constants.H5P_DEFAULT, HDF5Constants.H5P_DEFAULT);

		H5.H5Fclose(fileID);

		fileID = H5.H5Fopen("test-scratch/testLinks.h5", HDF5Constants.H5F_ACC_RDONLY, HDF5Constants.H5P_DEFAULT);

//		H5L_info_t info = H5.H5Lget_info(fileID, "/ext", HDF5Constants.H5P_DEFAULT);
		String[] linkName = new String[2]; // file name and file path
		H5.H5Lget_val(fileID, "/ext", linkName, HDF5Constants.H5P_DEFAULT);
		Assert.assertEquals("/target", linkName[0]);
		Assert.assertEquals("missing.h5", linkName[1]);
	}
}
