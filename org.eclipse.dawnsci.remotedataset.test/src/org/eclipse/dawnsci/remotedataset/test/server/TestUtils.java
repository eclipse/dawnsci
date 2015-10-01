/*-
 *******************************************************************************
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.remotedataset.test.server;

import java.io.File;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.FileStoreEditorInput;

public class TestUtils {

	/**
	 * Process UI input but do not return for the specified time interval.
	 * 
	 * @param waitTimeMillis
	 *            the number of milliseconds
	 */
	public static void delay(long waitTimeMillis) {
		delay(waitTimeMillis, false);
	}

	/**
	 * Process UI input but do not return for the specified time interval.
	 * 
	 * @param waitTimeMillis
	 *            the number of milliseconds
	 * @param returnInsteadOfSleep
	 *            Once there is nothing left to do return instead of sleep. In practice this means that async messages
	 *            should be complete before this method returns (unless it times out first)
	 */
	public static void delay(long waitTimeMillis, boolean returnInsteadOfSleep) {
		
		if (PlatformUI.isWorkbenchRunning()) {

			Display display = PlatformUI.getWorkbench().getDisplay();

			// If this is the UI thread,
			// then process input.
			long endTimeMillis = System.currentTimeMillis() + waitTimeMillis;
			while (System.currentTimeMillis() < endTimeMillis) {
				try {
					if (!display.readAndDispatch()) {
						if (returnInsteadOfSleep)
							return;
						display.sleep();
					}
				} catch (Exception ne) {
					if (returnInsteadOfSleep)
						return;
					try {
						Thread.sleep(waitTimeMillis);
					} catch (InterruptedException e) {
						// Ignored
					}
					break;
				}
			}
			display.update();
			
		} else {
			if (returnInsteadOfSleep)
				return;
			try {
				Thread.sleep(waitTimeMillis);
			} catch (InterruptedException e) {
				// Ignored.
			}
		}
	}
	
	/**
	 * Gets the page, even during startup.
	 * @return the page
	 */
	public static IWorkbenchPage getPage() {
		return getPage(null);
	}
	
	/**
	 * Gets the page, even during startup.
	 * @return the page
	 */
	public static IWorkbenchPage getPage(IWorkbenchPartSite site) {
		if (site != null) {
			IWorkbenchPage page = site.getPage();
			if (page != null) return page;
		}
		IWorkbenchPage activePage = getActivePage();
		if (activePage!=null) return activePage;
		return getDefaultPage();
	}
	
	/**
	 * @return IWorkbenchPage
	 */
	public static IWorkbenchPage getActivePage() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		if (bench==null) return null;
		final IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
		if (window==null) return null;
		return window.getActivePage();
	}
		
	/**
	 * @return IWorkbenchPage
	 */
	public static IWorkbenchPage getDefaultPage() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		if (bench==null) return null;
		final IWorkbenchWindow[] windows = bench.getWorkbenchWindows();
		if (windows==null) return null;
		
		return windows[0].getActivePage();
	}

	/**
	 * Attempts to get a free port starting at the passed in port and
	 * working up.
	 * 
	 * @param startPort
	 * @return
	 */
	public static int getFreePort(final int startPort) {
		
	    int port = startPort;
	    while(!TestUtils.isPortFree(port)) port++;
	    	
	    return port;
	}


	/**
	 * Checks if a port is free.
	 * @param port
	 * @return
	 */
	public static boolean isPortFree(int port) {

	    ServerSocket ss = null;
	    DatagramSocket ds = null;
	    try {
	        ss = new ServerSocket(port);
	        ss.setReuseAddress(true);
	        ds = new DatagramSocket(port);
	        ds.setReuseAddress(true);
	        return true;
	    } catch (IOException e) {
	    	// Swallow this, it's not free
	    	return false;
	    } finally {
	        if (ds != null) {
	            ds.close();
	        }

	        if (ss != null) {
	            try {
	                ss.close();
	            } catch (IOException e) {
	                /* should not be thrown */
	            }
	        }
	    }

	}

	
	public static IEditorPart openExternalEditor(String filename) throws PartInitException {
		return openExternalEditor(getExternalFileStoreEditorInput(filename), filename);
	}
		
	/**
	 * Opens an external editor on an IEditorInput containing the file having filePath
	 * @param editorInput
	 * @param filePath
	 * @throws PartInitException
	 */
	public static IEditorPart openExternalEditor(IEditorInput editorInput, String filePath) throws PartInitException {
		//TODO Maybe this method could be improved by omitting filepath which comes from editorInput, but "how?" should be defined here
		final IWorkbenchPage page = getPage();
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(filePath);
		if (desc == null) desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(filePath+".txt");
		return page.openEditor(editorInput, desc.getId());
	}
	
	public static IEditorInput getExternalFileStoreEditorInput(String filename) {
		final IFileStore externalFile = EFS.getLocalFileSystem().fromLocalFile(new File(filename));
		return new FileStoreEditorInput(externalFile);
	}

	public static void recursiveDelete(Path directory) throws IOException {
		Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}

		});
	}
}
