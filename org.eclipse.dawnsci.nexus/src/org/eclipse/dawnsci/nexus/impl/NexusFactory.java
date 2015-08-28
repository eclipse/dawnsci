package org.eclipse.dawnsci.nexus.impl;

import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXroot;

public class NexusFactory {
	
	private static int nextoid = 1;
	
	private static int getNextOid() {
		return nextoid++;
	}
	
	public static NXrootImpl createNXroot() {
		return new NXrootImpl(getNextOid());
	}
	
	public static NXdataImpl createNXdata() {
		return new NXdataImpl(getNextOid());
	}
	
	public static NXentryImpl createNXentry() {
		return new NXentryImpl(getNextOid());
	}
	
	public static NXtransformationsImpl createNXtransformations() {
		return new NXtransformationsImpl(getNextOid());
	}

}
