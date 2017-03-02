/*-
 *******************************************************************************
 * Copyright (c) 2011, 2017 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Gerring - initial API and implementation and/or initial documentation
 *    Baha El-Kassaby - Removal of IHierchicalDataFile and HObject usage
 *******************************************************************************/ 
package org.eclipse.dawnsci.hdf5.editor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.tree.Attribute;
import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.analysis.api.tree.GroupNode;
import org.eclipse.dawnsci.analysis.api.tree.Node;
import org.eclipse.dawnsci.analysis.api.tree.NodeLink;
import org.eclipse.january.DatasetException;
import org.eclipse.january.dataset.IDataset;
import org.eclipse.january.dataset.ILazyDataset;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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
		if (sel instanceof Attribute) {
			Attribute att = (Attribute) sel;
			createAttributeValue(att);
		} else if (sel instanceof NodeLink) {
			NodeLink node = (NodeLink) sel;
			createNodeValue(node);
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
				try {
					IDataset data = lz.getSlice();
					if (Number.class.isAssignableFrom(data.getElementClass())) {
						buf.append("\n[");
						if (format == null)
							format = new DecimalFormat("#####0.0###");
						final int size = Math.min(lz.getShape()[0], 5);
						for (int i = 0; i < size; ++i) {
							buf.append(format.format(data.getDouble(i)));
							if (i < size - 1)
								buf.append(", ");
						}
						if (lz.getShape()[0] > 5)
							buf.append(" ...");
						buf.append("]\n\n");
					}
				} catch (DatasetException e) {
					logger.error("Could not get data from lazy dataset", e);
				}
			}
		}
		buf.append(node.toString());
		return buf.toString();
	}
	
	@SuppressWarnings("unused")
	private static boolean isNumericalDType(int dtype) {
		return (dtype <= 8 );
	}

	private void createAttributeValue(Attribute att) throws Exception {
	}

	private void createNodeValue(NodeLink link) throws Exception {
		Node node = link.getDestination();
		if (node instanceof DataNode) {
			final DataNode set = (DataNode) node;
			final String dType = set.getTypeName();
			final int[] shape = set.getDataset().getShape();

			final StringBuilder buf = new StringBuilder();
			if (dType != null && dType.equals("STRING")) {
				label.setText("Dataset name of '" + link.getName() + "' value:");
				try {
					final String value = set.getString();
					buf.append(value);
				} catch (Exception e) {
					// Ignored
				}
			} else if (shape!=null) {
				label.setText("Dataset name of '"+link.getName()+"' shape:");
				buf.append(Arrays.toString(shape).trim());
				
				long size = shape[0];
				for (int i = 1; i < shape.length; i++) size*=shape[i];
				if (size<10) {
					buf.append("\n\nValue:\n");
					String value = getNodeLinkValue(link);
					buf.append(value);
				}
			} else {
				label.setText("Dataset name of '"+link.getName()+"'");
			}
			appendAttributes("\nAttributes", set, buf);
			sourceViewer.getTextWidget().setText(buf.toString());
		} else if (node instanceof GroupNode) {
			final GroupNode grp = (GroupNode) node;
			label.setText("Group name of '" + link.getName() + "' children:");
			final List<String> members = new ArrayList<String>(grp.getNumberOfNodelinks());
			Iterator<String> nameIterator = grp.getNodeNameIterator();
			while (nameIterator.hasNext()) {
				String name = (String) nameIterator.next();
				members.add(name);
			}
			final StringBuilder buf = (members != null) ? new StringBuilder(members.toString()) : new StringBuilder();

			GroupNode source = (GroupNode)link.getSource();
			Attribute hdf5version = source.getAttribute("HDF5_Version");
			// if it is the root node
			if (hdf5version != null) {
				appendAttributes("\nFile Attributes", source, buf);
				appendAttributes("Attributes", grp, buf);
			} else {
				appendAttributes("\nAttributes", grp, buf);
			}
			sourceViewer.getTextWidget().setText(buf.toString());
		}
	}

	private void appendAttributes(String title, Node set, StringBuilder buf) throws Exception {
		Iterator<? extends Attribute> attIterator = set.getAttributeIterator();
		if (attIterator.hasNext())
			buf.append("\n" + title + ":\n");
		while (attIterator.hasNext()) {
			Attribute a = (Attribute) attIterator.next();
			buf.append(a.getName());
			buf.append(" = ");
			buf.append("[" + a.getFirstElement() + "]");
			buf.append("\n");
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
