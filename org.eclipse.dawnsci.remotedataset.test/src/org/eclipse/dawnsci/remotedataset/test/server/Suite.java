package org.eclipse.dawnsci.remotedataset.test.server;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(org.junit.runners.Suite.class)
@SuiteClasses({
	SliceClientTest.class,
    RemoteDatasetTest.class,
    RemoteDatasetSliceTest.class,
    FileMonitoringTest.class,
    RemoteDataTest.class
})
public class Suite {

}
