/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Matthew Dickie - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.builder.appdef.impl;

import java.text.MessageFormat;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;
import org.eclipse.dawnsci.nexus.NXdata;
import org.eclipse.dawnsci.nexus.NXdetector;
import org.eclipse.dawnsci.nexus.NXentry;
import org.eclipse.dawnsci.nexus.NXinstrument;
import org.eclipse.dawnsci.nexus.NXmonitor;
import org.eclipse.dawnsci.nexus.NXobject;
import org.eclipse.dawnsci.nexus.NXpositioner;
import org.eclipse.dawnsci.nexus.NXsample;
import org.eclipse.dawnsci.nexus.NXsource;
import org.eclipse.dawnsci.nexus.NXsubentry;
import org.eclipse.dawnsci.nexus.NexusApplicationDefinition;
import org.eclipse.dawnsci.nexus.NexusException;
import org.eclipse.dawnsci.nexus.NexusNodeFactory;
import org.eclipse.dawnsci.nexus.builder.NexusDataBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusEntryBuilder;
import org.eclipse.dawnsci.nexus.builder.NexusObjectProvider;
import org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder;
import org.eclipse.dawnsci.nexus.validation.NXtomoValidator;
import org.eclipse.dawnsci.nexus.validation.NexusValidationException;

/**
 * Application builder for {@link NexusApplicationDefinition#NX_TOMO}
 * applications.
 * 
 * Note that there are two ways to populate the {@link NXsample} group within the application,
 * either by providing a fully populated {@link NXsample} object by invoking
 * {@link #setSample(NexusObjectProvider)}, or by adding setting each field individually
 * using {@link #setSampleName(String)}, {@link #setRotationAngle(NexusObjectProvider)},
 * {@link #setXTranslation(DataNode)}, etc. For each data field two methods exist, one
 * that takes a {@link NexusObjectProvider} that produces an {@link NXpositioner} - in
 * this case a link to the 'value' data node of this positioner will be added, or by
 * directly adding a {@link DataNode}.
 * 
 */
public class TomoApplicationBuilder extends AbstractNexusApplicationBuilder implements NexusApplicationBuilder {

	private static final String NX_Y_TRANSLATION = "y_translation";
	
	private static final String NX_Z_TRANSLATION = "z_translation";
	
	private NXinstrument instrument = null;

	private NXsample sample = null;
	
	/**
	 * Creates a new {@link TomoApplicationBuilder} within the {@link NXentry} for the
	 * given nexus entry builder
	 * @param nexusEntryBuilder
	 * @param subentry
	 */
	public TomoApplicationBuilder(final NexusEntryBuilder nexusEntryBuilder, final NXsubentry subentry) {
		super(NexusApplicationDefinition.NX_TOMO, nexusEntryBuilder, subentry);
	}

	/**
	 * Not supported. Use the methods specific to this application instead
	 * @see org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder#add(org.eclipse.dawnsci.nexus.builder.NexusObjectProvider)
	 */
	@Override
	public <N extends NXobject> N add(NexusObjectProvider<N> nexusObjectProvider) throws NexusException {
		throw new UnsupportedOperationException("This method is not supported for this application definition. Please use the specific method for each object provider");
	}
	
	/**
	 * Sets the title of the application to that given
	 * @param title title
	 */
	public void setTitle(String title) {
		subentry.setTitleScalar(title);
	}
	
	/**
	 * Links the title of the application to the given data node.
	 * @param title title data node to link to
	 */
	public void setTitle(DataNode title) {
		subentry.addDataNode(NXsubentry.NX_TITLE, title);
	}

	/**
	 * Sets the source to that provided by the given {@link NexusObjectProvider}.
	 * @param source
	 */
	public void setSource(NexusObjectProvider<NXsource> source) {
		instrument.setSource(source.getNexusObject(getNexusNodeFactory(), true));
	}

	/**
	 * Sets the detector to that provided by the given {@link NexusObjectProvider}.
	 * @param detector
	 */
	public void setDetector(NexusObjectProvider<NXdetector> detector) {
		instrument.setDetector(detector.getNexusObject(getNexusNodeFactory(), true));
	}
	
	/**
	 * Sets the sample to that provided by this given {@link NexusObjectProvider}.
	 * @param sample
	 * @throws NexusException
	 */
	public void setSample(NexusObjectProvider<NXsample> sample) throws NexusException {
		this.sample = sample.getNexusObject(getNexusNodeFactory(), true);
		if (subentry.getSample() != null) {
			subentry.removeGroupNode("sample");
		}
		subentry.setSample(this.sample);
	}

	/**
	 * Sets the sample name.
	 * @param sampleName
	 */
	public void setSampleName(String sampleName) {
		sample.setNameScalar(sampleName);
	}
	
	/**
	 * Sets the rotation angle
	 * @param rotationAnglePositioner rotation angle positioner
	 * @throws NexusException
	 */
	public void setRotationAngle(NexusObjectProvider<NXpositioner> rotationAnglePositioner) throws NexusException {
		final DataNode rotationAngleDataNode = getDataNode(rotationAnglePositioner);
		sample.addDataNode(NXsample.NX_ROTATION_ANGLE, rotationAngleDataNode);
	}

	/**
	 * Sets the rotation angle
	 * @param rotationAnglePositioner rotation angle data node
	 * @throws NexusException
	 */
	public void setRotationAngle(DataNode rotationAngle) throws NexusException {
		sample.addDataNode(NXsample.NX_ROTATION_ANGLE, rotationAngle);
	}

	/**
	 * Sets the 'x' translation.
	 * @param xPositioner x positioner
	 * @throws NexusException
	 */
	public void setXTranslation(NexusObjectProvider<NXpositioner> xPositioner) throws NexusException {
		final DataNode xTranslation = getDataNode(xPositioner);
		sample.addDataNode(NXsample.NX_X_TRANSLATION, xTranslation);
	}
	
	/**
	 * Sets the 'x' translation
	 * @param xTranslation x translation data node
	 * @throws NexusException
	 */
	public void setXTranslation(DataNode xTranslation) throws NexusException {
		sample.addDataNode(NXsample.NX_X_TRANSLATION, xTranslation); 
	}
	
	/**
	 * Sets the 'y' translation
	 * @param yPositioner y positioner
	 * @throws NexusException
	 */
	public void setYTranslation(NexusObjectProvider<NXpositioner> yPositioner) throws NexusException {
		final DataNode yTranslation = getDataNode(yPositioner);
		sample.addDataNode(NX_Y_TRANSLATION, yTranslation);
	}
	
	/**
	 * Sets the 'y' translation
	 * @param yTranslation y translation data node
	 * @throws NexusException
	 */
	public void setYTranslation(DataNode yTranslation) throws NexusException {
		sample.addDataNode(NX_Y_TRANSLATION, yTranslation);
	}

	/**
	 * Sets the 'z' translation
	 * @param zPositioner z positioner
	 * @throws NexusException
	 */
	public void setZTranslation(NexusObjectProvider<NXpositioner> zPositioner) throws NexusException {
		final DataNode zTranslation = getDataNode(zPositioner);
		sample.addDataNode(NX_Z_TRANSLATION, zTranslation);
	}

	/**
	 * Sets the 'z' translation
	 * @param zTranslation z translation
	 * @throws NexusException
	 */
	public void setZTranslation(DataNode zTranslation) throws NexusException {
		sample.addDataNode(NX_Z_TRANSLATION, zTranslation);
	}

	/**
	 * Sets the control device
	 * @param controlDevice control device
	 * @throws NexusException
	 */
	public void setControl(NexusObjectProvider<NXpositioner> controlDevice) throws NexusException {
		NXmonitor control = getNexusNodeFactory().createNXmonitor();
		subentry.setMonitor("control", control);
		
		DataNode dataNode = getDataNode(controlDevice);
		control.addDataNode(NXmonitor.NX_DATA, dataNode);  
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder#newData()
	 */
	@Override
	public NexusDataBuilder newData() throws NexusException {
		NXdata nxData = getNexusNodeFactory().createNXdata();
		subentry.setData(nxData);

		PredeterminedLinksApplicationDataBuilder dataModel = new PredeterminedLinksApplicationDataBuilder(nxData);
		addPredeterminedLinks(dataModel);

		return dataModel;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder#validate()
	 */
	@Override
	public void validate() throws NexusValidationException {
		NXtomoValidator validator = new NXtomoValidator();
		validator.validate(subentry);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.dawnsci.nexus.builder.appdef.NexusApplicationBuilder#addDefaultGroups()
	 */
	@Override
	public void addDefaultGroups() {
		NexusNodeFactory nodeFactory = getNexusNodeFactory();
		instrument = nodeFactory.createNXinstrument();
		subentry.setInstrument(instrument);

		sample = nodeFactory.createNXsample();
		subentry.setSample(sample);
	}

	/**
	 * Adds the predetermined links to the given {@link PredeterminedLinksApplicationDataBuilder}.
	 * @param dataBuilder
	 * @throws NexusException
	 */
	protected void addPredeterminedLinks(PredeterminedLinksApplicationDataBuilder dataBuilder) throws NexusException {
		dataBuilder.addLink("data", getDataNode("instrument/detector/data"));
		dataBuilder.addLink("rotation_angle", getDataNode("sample/rotation_angle"));
		dataBuilder.addLink("image_key", getDataNode("instrument/detector/image_key"));
	}

	private <N extends NXobject> DataNode getDataNode(NexusObjectProvider<N> nexusObjectProvider) throws NexusException {
		final N nexusObject = nexusObjectProvider.getNexusObject(getNexusNodeFactory(), true);
		final String dataNodeName = nexusObjectProvider.getDefaultWritableDataFieldName();
		final DataNode dataNode = nexusObject.getDataNode(dataNodeName);
		if (dataNode == null) {
			throw new NexusException(MessageFormat.format("No such data node for {0} with name ''{1}''",
					nexusObjectProvider.getClass().getSimpleName(), dataNodeName));
		}
		
		return dataNode;
	}

}
