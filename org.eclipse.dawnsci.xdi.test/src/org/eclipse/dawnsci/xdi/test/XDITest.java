package org.eclipse.dawnsci.xdi.test;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.eclipse.dawnsci.xdi.XDI;
import org.eclipse.dawnsci.xdi.XDIException;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Enclosed.class)
public class XDITest {

	@RunWith(value = Parameterized.class)
	public static class XDIGoodTest {
		private static Path goodPath;

		@Parameter
		public String filename;

		@Parameters(name = "{index}: filename - {0}")
		public static Object[] data() throws Exception {
			before();
			String[] files = goodPath.toFile().list((dir, name) -> name.endsWith(".xdi"));
			Arrays.sort(files);
			return files;
		}
	
		public static void before() throws Exception {
			goodPath = Paths.get("testfiles", "good"); 
		}

		@Test
		public void test() {
			try {
				XDI.readfile(goodPath.toString() + File.separator + filename);
			} catch (XDIException e) {
				e.printStackTrace();
				fail(e.getMessage());
			}
		}
	}
	
	@RunWith(value = Parameterized.class)
	public static class XDIBadTest {
		private static Path badPath;

		@Parameter
		public String filename;

		@Parameters(name = "{index}: filename - {0}")
		public static Object[] data() throws Exception {
			before();
			String[] files = badPath.toFile().list((dir, name) -> name.endsWith(".xdi"));
			Arrays.sort(files);
			//String[] files = new String[]{"bad_22.xdi"};
			return files;
		}
	
		public static void before() throws Exception {
			badPath = Paths.get("testfiles", "bad"); 
		}

		@Test
		public void test() {
			try {
				XDI.readfile(badPath.toString() + File.separator + filename);
				fail(String.format("%s is an invalid XDI file", filename));
			} catch (XDIException e) {
				e.printStackTrace();
			}
		}
	}
}
