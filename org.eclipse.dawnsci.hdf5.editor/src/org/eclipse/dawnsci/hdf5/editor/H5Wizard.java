/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.editor;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.dawnsci.hdf.object.HierarchicalDataFactory;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H5Wizard extends Wizard implements INewWizard {

	private static Logger logger = LoggerFactory.getLogger(H5Wizard.class);
	
	private H5ResourcePage     page;
	private IStructuredSelection selection;

	public H5Wizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new H5ResourcePage("Folder for H5 File",selection, H5ResourcePage.RESOURCE_CHOICE.PROJECT_AND_NAME, "empty_data_file.h5");
		addPage(page);
	}

	@Override
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}

	

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		
		final IContainer container = page.getProjectContainer();
		final String     seqName   = page.getSequenceName();
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(container,  seqName, monitor);
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}

		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	private void doFinish(IContainer container, String h5Name, IProgressMonitor monitor) {
		try {
			
			monitor.beginTask("Create "+h5Name, 5);
            final IFile      file      = container instanceof IFolder
                                       ? ((IFolder)container).getFile(h5Name)
                                       : ((IProject)container).getFile(h5Name);
            
            HierarchicalDataFactory.create(file.getLocation().toOSString());
            
            monitor.worked(1);
            container.refreshLocal(IResource.DEPTH_ONE, monitor);
            
            
		} catch (Exception ne) {
			logger.error("Cannot create sequence", ne);
		}		
	}


}
