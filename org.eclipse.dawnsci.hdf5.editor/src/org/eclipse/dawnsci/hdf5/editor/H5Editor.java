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

import javax.swing.tree.TreeNode;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.dawnsci.hdf.object.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf.object.IHierarchicalDataFile;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IReusableEditor;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.EditorPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H5Editor extends EditorPart implements IReusableEditor, IH5Editor {

	public static final String ID = "org.eclipse.dawnsci.hdf5.editor.raw.tree";
	
    private static final Logger logger = LoggerFactory.getLogger(H5Editor.class);
	
	private TreeViewer tree;
	
	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		
		setSite(site);
		super.setInput(input);
        setPartName(input.getName());
	}

	@Override
	public void setInput(final IEditorInput input) {
		super.setInput(input);
		setPartName(input.getName());
		refresh();
	}	

	/**
	 * Get the file path from a FileStoreEditorInput. Removes any "file:"
	 * from the URI to the file path if it exists.
	 * 
	 * @param fileInput
	 * @return String
	 */
	public String getFilePath(IEditorInput fileInput) {
		
		final IFile file = (IFile)fileInput.getAdapter(IFile.class);
		if (file!=null) return file.getLocation().toOSString();

		if (fileInput instanceof IURIEditorInput) {
			String path = ((IURIEditorInput)fileInput).getURI().toString();
			if (path.startsWith("file:")) path = path.substring(5);
			path = path.replace("%20", " ");
			return path;
		} 
		return null;
	}

	
	@Override
	public void createPartControl(final Composite parent) {
		
		parent.setLayout(new GridLayout(1, false));

//      TODO Searching not possible as tree is VIRTUAL and ILazy
//		final Text searchText = new Text(parent, SWT.SINGLE | SWT.BORDER | SWT.SEARCH | SWT.ICON_CANCEL);
//		searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
//		searchText.setToolTipText("Search on data set name or expression value." );

		tree = new TreeViewer(parent, SWT.BORDER|SWT.VIRTUAL);
		tree.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		tree.getTree().setHeaderVisible(true);
		tree.setUseHashlookup(true);

		final String[] titles = { "Name", "Class", "Dims", "Description", "Size" };
		final int[]    widths = { 250, 120, 80, 150, 150 };
		TreeViewerColumn tVCol;
		for (int i = 0; i < titles.length; i++) {
			tVCol = new TreeViewerColumn(tree, SWT.NONE);
			TreeColumn tCol = tVCol.getColumn();
			tCol.setText(titles[i]);
			tCol.setWidth(widths[i]);
			tCol.setMoveable(true);
		}
		
		final H5SelectionProvider provider = new H5SelectionProvider();
		getSite().setSelectionProvider(provider);
		
		/**
		 * Allows double click to fire special tree events for DLS plotting
		 */
		createTreeListeners(provider);
		
//		final H5Filter filter = new H5Filter();
//		searchText.addModifyListener(new ModifyListener() {		
//			@Override
//			public void modifyText(ModifyEvent e) {
//				if (parent.isDisposed()) return;
//				filter.setSearchText(searchText.getText());
//				refresh();
//			}
//		});
	}

	@Override
	public void setFocus() {
		
		// We only give content when the UI is actually selected
		// as this makes the system more lazy when this editor is
		// used on a multi editor as a second or greater tab.
		if (tree.getContentProvider()==null) {
			refresh();
		}
		
		tree.getControl().setFocus();
	}

	private void refresh() {
		
		try {
			// NOTE This file may get closed externally
	    	IHierarchicalDataFile file = HierarchicalDataFactory.getReader(getFilePath(getEditorInput()));
	        try {
	
				final TreeNode root = file.getNode();
				tree.getTree().setItemCount(root.getChildCount());
				tree.setContentProvider(new H5ContentProvider());
				tree.setLabelProvider(new H5LabelProvider(file));
				tree.setInput(root);
				tree.expandToLevel(1);
				        	
			} catch (Exception e) {
				logger.error("Cannot open h5 file "+getFilePath(getEditorInput()), e);
	        } 
		} catch (Exception neOther) {
			logger.error("Cannot open H5 file!", neOther);
		}

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		
	}

	@Override
	public void doSaveAs() {
		
	}

	@Override
	public boolean isDirty() {
		return false;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

    /**
     * The adapter IContentProvider gives the value of the H5Dataset
     */
	public Object getAdapter(final Class clazz) {

		if (clazz == IContentProvider.class) {
			return new H5ValuePage();
		}

		return super.getAdapter(clazz);
	}
	
	@Override
	public String getFilePath() {
		return getFilePath(getEditorInput());
	}
	
	/**
	 * The reason we do not use the TreeViewer as the selection provider is that
	 * we want to send different selections between double and single clicks.
	 * 
	 * @param provider
	 */
	private void createTreeListeners(final H5SelectionProvider provider) {

		/**
		 * This line is used to make the double click work into the DLS plotting.
		 * This requires a HDF5Selection to be sent. 
		 */
		tree.getControl().addListener(SWT.MouseDown, new Listener() {
			@Override
			public void handleEvent(Event event) {
				provider.setSelection(tree.getSelection());
			}
		});

		/**
		 * This line is used to make the double click work into the DLS plotting.
		 * This requires a HDF5Selection to be sent. 
		 */
		tree.getControl().addListener(SWT.MouseDoubleClick, new Listener() {
			@Override
			public void handleEvent(Event event) {
				
				try {
					IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
					if (page != null) {
						try {
							if (page.findView("org.dawb.passerelle.views.ValueView")==null) {
								page.showView("org.dawb.passerelle.views.ValueView");
							}
						} catch (PartInitException e) {
							// do nothing
						}
					}
				} catch (Throwable ignored) {
					// It is not failure to not open the value view.
				}

				final IConfigurationElement[] ele = Platform.getExtensionRegistry().getConfigurationElementsFor("org.eclipse.dawnsci.hdf5.editor.double.click.listener");
				for (IConfigurationElement e : ele) {
					IH5DoubleClickSelectionProvider prov=null;
					try {
						prov = (IH5DoubleClickSelectionProvider)e.createExecutableExtension("class");
					} catch (CoreException e1) {
						continue;
					}
					ISelection selection;
					try {
						selection = prov.getSelection(tree.getSelection(), getFilePath());
					} catch (Exception e1) {
						logger.error("Cannot translate exception!", e1);
						continue;
					}
					if (selection!=null) {
						provider.setSelection(selection);
						return;
					}
				}
			}
		});
	}

}
