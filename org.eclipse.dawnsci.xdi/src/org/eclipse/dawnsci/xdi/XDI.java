package org.eclipse.dawnsci.xdi;

public class XDI {
	private XDI() {
		
	}

	public static final String XDI_VERSION = "1.1.0";
	
	public static XDIFile readfile(String filename) throws XDIException {
		return new XDIFile(filename);
	}
}
