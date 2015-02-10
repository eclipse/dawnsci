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
package org.eclipse.dawnsci.plotting.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.dawnsci.plotting.examples.util.BundleUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.part.ViewPart;



public abstract class SourceCodeView extends ViewPart {
	
	/**
	 * 
	 */
	@Override
	public void createPartControl(Composite parent) {
		
		final CTabFolder folder = new CTabFolder(parent, SWT.BOTTOM | SWT.FLAT);
		
		final CTabItem plotTab = new CTabItem(folder, SWT.NONE, 0);
		plotTab.setText("Plot");	
		Composite content = new Composite(folder, SWT.NONE);
		content.setLayout(new FillLayout());
		createExampleContent(content);		
		plotTab.setControl(content);
	
		
		final CTabItem sourceTab = new CTabItem(folder, SWT.NONE, 1);
		sourceTab.setText("Source");	
		content = new Composite(folder, SWT.NONE);
		content.setLayout(new FillLayout());
		createSourceContent(content);
		sourceTab.setControl(content);

		folder.setSelection(0);
	}
	
	/**
	 * Basic source code viewer...
	 * @param content
	 */
	private void createSourceContent(Composite content) {
		
		JavaLineStyler lineStyler = new JavaLineStyler();
		
		StyledText text = new StyledText(content, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		GridData spec = new GridData();
		spec.horizontalAlignment = GridData.FILL;
		spec.grabExcessHorizontalSpace = true;
		spec.verticalAlignment = GridData.FILL;
		spec.grabExcessVerticalSpace = true;
		text.setLayoutData(spec);
		text.addLineStyleListener(lineStyler);
		text.setEditable(false);
		
		// Providing that they run this from a debug session:
		try {
			File   dir = BundleUtils.getBundleLocation("org.eclipse.dawnsci.plotting.examples");
			String loc = "/src/"+getClass().getName().replace('.', '/')+".java";
			File   src = new File(dir, loc);
			text.setText(readFile(src).toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected abstract void createExampleContent(Composite parent);

	
	
	protected static void removeMargins(Composite area) {
		final GridLayout layout = (GridLayout)area.getLayout();
		if (layout==null) return;
		layout.horizontalSpacing=0;
		layout.verticalSpacing  =0;
		layout.marginBottom     =0;
		layout.marginTop        =0;
		layout.marginLeft       =0;
		layout.marginRight      =0;
		layout.marginHeight     =0;
		layout.marginWidth      =0;

	}

	/**
	 * @param file
	 * @return StringBuffer
	 * @throws Exception
	 */
	private static final StringBuffer readFile(final File file) throws IOException {
		return readFile(new FileInputStream(file), "UTF-8");
	}
	/**
	 * @param in
	 * @param charsetName
	 * @return StringBuffer
	 * @throws Exception
	 */
	public static final StringBuffer readFile(final InputStream in, final String charsetName) throws IOException {

		BufferedReader ir = null;
		try {
			if (charsetName != null) {
				ir = new BufferedReader(new InputStreamReader(in, charsetName));
			} else {
				ir = new BufferedReader(new InputStreamReader(in));
			}

			// deliberately do not remove BOM here
			int c;
			StringBuffer currentStrBuffer = new StringBuffer();
			final char[] buf = new char[4096];
			while ((c = ir.read(buf, 0, 4096)) > 0) {
				currentStrBuffer.append(buf, 0, c);
			}
			return currentStrBuffer;

		} finally {
			if (ir != null) {
				ir.close();
			}
		}
	}

}
