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

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

import javax.swing.tree.DefaultMutableTreeNode;

import ncsa.hdf.object.Attribute;
import ncsa.hdf.object.Dataset;
import ncsa.hdf.object.Datatype;
import ncsa.hdf.object.Group;
import ncsa.hdf.object.HObject;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.dawnsci.analysis.api.tree.TreeAdaptable;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.HierarchicalDataUtils;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class H5ValuePage extends Page  implements ISelectionListener, IPartListener {

	private static Logger logger = LoggerFactory.getLogger(H5ValuePage.class);
	
	protected CLabel       label;
	protected SourceViewer sourceViewer;
	protected StructuredSelection lastSelection;

	protected Composite container;

	/**
	 * Create contents of the view part.
	 * @param parent
	 */
	@Override
	public void createControl(Composite parent) {
		
		this.container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		final GridLayout layout = (GridLayout)container.getLayout();
		layout.horizontalSpacing=0;
		layout.verticalSpacing  =0;
		layout.marginBottom     =0;
		layout.marginTop        =0;
		layout.marginLeft       =0;
		layout.marginRight      =0;
		layout.marginHeight     =0;
		layout.marginWidth      =0;
		container.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		this.label  = new CLabel(container, SWT.LEFT);
		label.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		label.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		
		this.sourceViewer = new SourceViewer(container, null, SWT.H_SCROLL | SWT.V_SCROLL | SWT.READ_ONLY );
		sourceViewer.setEditable(false);
		sourceViewer.getTextWidget().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    		
		getSite().getPage().getWorkbenchWindow().getSelectionService().addSelectionListener(this);
		getSite().getPage().addPartListener(this);
		
		try {
			updateSelection(getActivePage().getSelection());
		} catch (Throwable ignored) {
			// There might not be a selection or page.
		}

	}

	@Override
	public Control getControl() {
		return container;
	}
	
	@Override
	public void setFocus() {
		sourceViewer.getTextWidget().setFocus();
	}

	public void dispose() {
		super.dispose();
		getSite().getPage().getWorkbenchWindow().getSelectionService().removeSelectionListener(this);
		getSite().getPage().removePartListener(this);
		lastSelection=null;
	}

	@Override
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		try {
			updateSelection(selection);
		} catch (Exception e) {
			logger.error("Cannot update value", e);
		}
	}

	protected void updateSelection(ISelection selection) throws Exception {
		
		if (selection instanceof StructuredSelection) {
			this.lastSelection = (StructuredSelection)selection;
			final Object sel = lastSelection.getFirstElement();
			
			updateObjectSelection(sel);				
			
			sourceViewer.refresh();
			label.getParent().layout(new Control[]{label, sourceViewer.getTextWidget()});
			
			return;
		}
		
		clear();
	}

	/**
	 * Set it back to blank
	 */
	private void clear() {
		label.setText("");
		sourceViewer.getTextWidget().setText("");
	}

	@Override
	public void partActivated(IWorkbenchPart part) {
		if (part == this) {
			try {
				updateSelection(lastSelection);
			} catch (Throwable ignored) {
				// There might not be a selection or page.
			}
		}
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
		
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
		
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
		
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
		
	}

	public void updateObjectSelection(Object sel)  throws Exception{

		if (sel instanceof DefaultMutableTreeNode) {
			final DefaultMutableTreeNode node = (DefaultMutableTreeNode)sel;
			final Object                 ob   = node.getUserObject();
			if (ob instanceof HObject) {
				createH5Value((HObject)ob);
			}
 		} else if (sel instanceof H5Path) { // Might be nexus part.
			try {
				final String path   = sel instanceof H5Path ? ((H5Path)sel).getPath() : ((NodeLink)sel).getPath();
				final IEditorPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
				if (part instanceof IH5Editor) {
					final String filePath = ((IH5Editor)part).getFilePath();
					
					IHierarchicalDataFile file = null;
					try {
						file = HierarchicalDataFactory.getReader(filePath);
						final HObject ob = (HObject)file.getData(path);
						createH5Value(ob);
					} finally {
						if (file!=null) file.close();
					}
				}
			} catch (Exception ne) {
				logger.error(ne.getMessage()); // Not serious, no need for stack.
			}
 		} else if (sel instanceof TreeAdaptable) {
 			NodeLink nl = ((TreeAdaptable) sel).getNodeLink();
 			if (nl != null) {
	 			if (nl.isDestinationGroup())
	 				label.setText("Group name of '" + nl.getName() + "' children:");
	 			else if (nl.isDestinationData())
	 				label.setText("Dataset name of '" + nl.getName() + "' value:");
	
	 			sourceViewer.getTextWidget().setText(getNodeLinkValue(nl));
 			} else {
 				org.eclipse.dawnsci.analysis.api.tree.Attribute a = ((TreeAdaptable) sel).getAttribute();
	 			if (a != null) {
	 				sourceViewer.getTextWidget().setText(a.toString());
	 			}
 			}
		}
	}
	
	private NumberFormat format;
	
	// Fix for http://jira.diamond.ac.uk/browse/DAWNSCI-747
	private String getNodeLinkValue(NodeLink nl) {
		StringBuilder buf = new StringBuilder();
		Node node = nl.getDestination();
		if (node instanceof DataNode) {
			DataNode hd = (DataNode)node;
			ILazyDataset lz = hd.getDataset();
			if (lz.getRank()==1 && lz.getShape()[0]<500) {
								
				final IDataset data = lz.getSlice();
				if (Number.class.isAssignableFrom(data.elementClass())) {
				    buf.append("\n[");
				    if (format==null) format = new DecimalFormat("#####0.0###");
				    final int size = Math.min(lz.getShape()[0], 5);
				    for (int i = 0; i < size; ++i) {
					    buf.append(format.format(data.getDouble(i)));
					    if (i<size-1) buf.append(", ");
				    }
				    if (lz.getShape()[0]>5) buf.append(" ...");
				    buf.append("]\n\n");
				}

			}
		}
		buf.append(node.toString());
		return buf.toString();
	}
	
	private static boolean isNumericalDType(int dtype) {
		return (dtype <= 8 );
	}


	private void createH5Value(HObject ob) throws Exception {
		
		if (ob instanceof Dataset) {
			final Dataset  set   = (Dataset)ob;
			final Datatype dType = set.getDatatype();
			final long[] shape = (long[])set.getDims();
			
			final StringBuilder buf = new StringBuilder();
			if (dType!=null && dType.getDatatypeClass()==Datatype.CLASS_STRING) {
				label.setText("Dataset name of '"+set.getName()+"' value:");
				final long id = set.open();
				try {
					final String[] value = (String[])set.read();
					buf.append(value[0]);
				} catch (Exception e) {
					// Ignored
				} finally {
					set.close(id);
				}
			} else if (shape!=null) {
				label.setText("Dataset name of '"+set.getName()+"' shape:");
				buf.append(Arrays.toString(shape).trim());
				
				long size = shape[0];
				for (int i = 1; i < shape.length; i++) size*=shape[i];
				if (size<10) {
					buf.append("\n\nValue:\n");
					
					final Object data = set.getData();
				    int length = Array.getLength(data);
				    buf.append('[');
				    for (int i = 0; i < length; ++i) {
					    buf.append(Array.get(data, i));
					    if (i<length-1) buf.append(',');
				    }
				    buf.append(']');
				}
				
			} else {
				label.setText("Dataset name of '"+set.getName()+"'");
			}
			appendAttributes(set, buf);
			sourceViewer.getTextWidget().setText(buf.toString());
			
		} if (ob instanceof Group) {
			final Group  grp   = (Group)ob;
			
			label.setText("Group name of '"+grp.getName()+"' children:");
			final List members = grp.getMemberList();
			
			final StringBuilder buf = (members!=null) ? new StringBuilder(members.toString()) : new StringBuilder();
			final Group par = grp.getParent();
			if (par.isRoot())  appendAttributes("\nFile Attributes", par, buf);
			
			appendAttributes(par.isRoot() ? "Attributes" : "\nAttributes", grp, buf);
			sourceViewer.getTextWidget().setText(buf.toString());

		}
	}
	
	private void appendAttributes(HObject set, StringBuilder buf) throws Exception {

		appendAttributes("\nAttributes", set, buf);
	}
	
	private void appendAttributes(String title, HObject set, StringBuilder buf) throws Exception {
		
		final List meta = set.getMetadata();
		if (meta==null || meta.isEmpty()) return;
		
		buf.append("\n"+title+":\n");
		for (Object attribute : meta) {
			
			if (attribute instanceof Attribute) {
				Attribute a = (Attribute)attribute;
				buf.append(a.getName());
				buf.append(" = ");
				buf.append(HierarchicalDataUtils.extractValue(a.getValue()));
				buf.append("\n");
			}
		}
	}



	private static IWorkbenchPage getActivePage() {
		final IWorkbench bench = PlatformUI.getWorkbench();
		if (bench == null) return null;
		final IWorkbenchWindow window = bench.getActiveWorkbenchWindow();
		if (window == null) return null;
		return window.getActivePage();
	}
	

}
