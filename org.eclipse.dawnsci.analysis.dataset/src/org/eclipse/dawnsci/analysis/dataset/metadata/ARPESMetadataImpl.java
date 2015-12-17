/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.metadata;

import java.io.Serializable;
import java.util.Arrays;

import org.eclipse.dawnsci.analysis.api.dataset.IDataset;
import org.eclipse.dawnsci.analysis.api.dataset.ILazyDataset;
import org.eclipse.dawnsci.analysis.api.io.IDataHolder;
import org.eclipse.dawnsci.analysis.api.metadata.ARPESMetadata;
import org.eclipse.dawnsci.analysis.dataset.impl.DoubleDataset;

public class ARPESMetadataImpl implements ARPESMetadata {

	private static final String NX_ARPES_MANIPULATOR_SAAZIMUTHAL = "/entry1/instrument/manipulator/saazimuthal";
	private static final String NX_ARPES_MANIPULATOR_SATILT = "/entry1/instrument/manipulator/satilt";
	private static final String NX_ARPES_MANIPULATOR_SAPOLAR = "/entry1/instrument/manipulator/sapolar";
	private static final String NX_ARPES_ANALYSER_ENERGIES = "/entry1/instrument/analyser/energies";
	private static final String NX_ARPES_ANALYSER_ANGLES = "/entry1/instrument/analyser/angles";
	private static final String NX_ARPES_SAMPLE_TEMPERATURE = "/entry1/sample/temperature";
	private static final String NX_ARPES_MONOCHROMATOR_ENERGY = "/entry1/instrument/monochromator/energy";
	private static final String NX_ARPES_ANALYSER_PASS_ENERGY = "/entry1/instrument/analyser/pass_energy";
	private static final String NX_ARPES_ANALYSER_DATA = "/entry1/instrument/analyser/data";

	private static double getFirstValue(IDataHolder dh, String item) throws Exception {
		Serializable v = dh.getMetadata().getMetaValue(item);
		if (v != null) {
			return Double.parseDouble((String) v);
		}

		ILazyDataset l = dh.getLazyDataset(item);
		int r = l.getRank();
		int[] stop = new int[r];
		Arrays.fill(stop, 1);
		IDataset d = l.getSlice(null, stop, null);
		return d.getDouble(new int[r]);
	}

	public static ILazyDataset getFromDataHolder(IDataHolder dh) {
		// TODO this needs to be fixed up a little to be useful, but this will do to start.
		// Add ARPES specific metadata where required.
		if (dh.contains(NX_ARPES_ANALYSER_DATA)) {
			ILazyDataset data = dh.getLazyDataset(NX_ARPES_ANALYSER_DATA);
			ARPESMetadataImpl arpesMetadata = new ARPESMetadataImpl();
			try {
				arpesMetadata.setPassEnergy(Double.parseDouble((String) dh.getMetadata().getMetaValue(
						NX_ARPES_ANALYSER_PASS_ENERGY)));
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				arpesMetadata.setPhotonEnergy(getFirstValue(dh, NX_ARPES_MONOCHROMATOR_ENERGY));
			} catch (Exception e) {
				System.out.println(e);
			}
			try {
				arpesMetadata.setTemperature(getFirstValue(dh, NX_ARPES_SAMPLE_TEMPERATURE));
			} catch (Exception e) {
				System.out.println(e);
			}
			
			if (dh.contains(NX_ARPES_ANALYSER_ANGLES)) {
				arpesMetadata.setAnalyserAngles(dh.getLazyDataset(NX_ARPES_ANALYSER_ANGLES));
			} else {
				arpesMetadata.setAnalyserAngles(new DoubleDataset(new double[] {0.0}, new int[] {1}));
			}
			
			if (dh.contains(NX_ARPES_ANALYSER_ENERGIES)) {
				arpesMetadata.setKineticEnergies(dh.getLazyDataset(NX_ARPES_ANALYSER_ENERGIES));
			} else {
				arpesMetadata.setKineticEnergies(new DoubleDataset(new double[] {0.0}, new int[] {1}));
			}
			
			if (dh.contains(NX_ARPES_MANIPULATOR_SAPOLAR)) {
				arpesMetadata.setPolarAngles(dh.getLazyDataset(NX_ARPES_MANIPULATOR_SAPOLAR));
			} else {
				arpesMetadata.setPolarAngles(new DoubleDataset(new double[] {0.0}, new int[] {1}));
			}
			
			if (dh.contains(NX_ARPES_MANIPULATOR_SATILT)) {
				arpesMetadata.setTiltAngles(dh.getLazyDataset(NX_ARPES_MANIPULATOR_SATILT));
			} else {
				arpesMetadata.setTiltAngles(new DoubleDataset(new double[] {0.0}, new int[] {1}));
			}
		
			if (dh.contains(NX_ARPES_MANIPULATOR_SAAZIMUTHAL)) {
				arpesMetadata.setAzimuthalAngles(dh.getLazyDataset(NX_ARPES_MANIPULATOR_SAAZIMUTHAL));
			} else {
				arpesMetadata.setAzimuthalAngles(new DoubleDataset(new double[] {0.0}, new int[] {1}));
			}
			data.addMetadata(arpesMetadata);
			return data;
		}
		return null;
	}
	
	double photonEnergy = 0.0;
	double workFunction = 0.0;
	double passEnergy = 0.0;
	double temperature = 0.0;
	double energyAxisGlobalOffset = 0.0;
	double angleAxisGlobalOffset = 0.0;
	ILazyDataset kineticEnergies = null;
	ILazyDataset analyserAngles = null;
	ILazyDataset polarAngles = null;
	ILazyDataset tiltAngles = null;
	ILazyDataset azimuthalAngles = null;
	ILazyDataset energyAxisOffset = null;
	ILazyDataset bindingEnergies = null;
	ILazyDataset photoelectronMomentum = null;

	
	public ARPESMetadataImpl() {
	}

	public ARPESMetadataImpl(ARPESMetadata metadata) {
		super();
		this.photonEnergy = metadata.getPhotonEnergy();
		this.workFunction = metadata.getWorkFunction();
		this.kineticEnergies = metadata.getKineticEnergies();

		this.analyserAngles = getView(metadata.getAnalyserAngles());
		this.polarAngles = getView(metadata.getPolarAngles());
		this.tiltAngles = getView(metadata.getTiltAngles());
		this.azimuthalAngles = getView(metadata.getAzimuthalAngles());
		this.energyAxisOffset = getView(metadata.getEnergyAxisOffset());
		this.bindingEnergies = getView(metadata.getBindingEnergies());
		this.photoelectronMomentum = getView(metadata.getPhotoelectronMomentum());
	}
	
	private ILazyDataset getView(ILazyDataset l) {
		return l == null ? null : l.getSliceView();
	}

	@Override
	public double getPhotonEnergy() {
		return photonEnergy;
	}

	public void setPhotonEnergy(double photonEnergy) {
		this.photonEnergy = photonEnergy;
	}

	@Override
	public double getWorkFunction() {
		return workFunction;
	}

	public void setWorkFunction(double workFunction) {
		this.workFunction = workFunction;
	}

	@Override
	public double getPassEnergy() {
		return passEnergy;
	}

	public void setPassEnergy(double passEnergy) {
		this.passEnergy = passEnergy;
	}

	@Override
	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	@Override
	public double getEnergyAxisGlobalOffset() {
		return energyAxisGlobalOffset;
	}

	public void setEnergyAxisGlobalOffset(double energyAxisGlobalOffset) {
		this.energyAxisGlobalOffset = energyAxisGlobalOffset;
	}

	@Override
	public double getAngleAxisGlobalOffset() {
		return angleAxisGlobalOffset;
	}

	public void setAngleAxisGlobalOffset(double angleAxisGlobalOffset) {
		this.angleAxisGlobalOffset = angleAxisGlobalOffset;
	}

	@Override
	public ILazyDataset getKineticEnergies() {
		return kineticEnergies;
	}

	public void setKineticEnergies(ILazyDataset kineticEnergies) {
		this.kineticEnergies = kineticEnergies;
	}

	@Override
	public ILazyDataset getAnalyserAngles() {
		return analyserAngles;
	}

	public void setAnalyserAngles(ILazyDataset analyserAngles) {
		this.analyserAngles = analyserAngles;
	}

	@Override
	public ILazyDataset getPolarAngles() {
		return polarAngles;
	}

	public void setPolarAngles(ILazyDataset polarAngles) {
		this.polarAngles = polarAngles;
	}

//	public void setPolarAngles(double staticPolarAngle) {
//		polarAngles = DatasetFactory.createFromObject(staticPolarAngle);
//	}

	@Override
	public ILazyDataset getTiltAngles() {
		return tiltAngles;
	}

	public void setTiltAngles(ILazyDataset tiltAngles) {
		this.tiltAngles = tiltAngles;
	}

	@Override
	public ILazyDataset getAzimuthalAngles() {
		return azimuthalAngles;
	}

	public void setAzimuthalAngles(ILazyDataset azimuthalAngles) {
		this.azimuthalAngles = azimuthalAngles;
	}

	@Override
	public ILazyDataset getEnergyAxisOffset() {
		return energyAxisOffset;
	}

	public void setEnergyAxisOffset(ILazyDataset energyAxisOffset) {
		this.energyAxisOffset = energyAxisOffset;
	}

	@Override
	public ILazyDataset getBindingEnergies() {
		return bindingEnergies;
	}

	public void setBindingEnergies(ILazyDataset bindingEnergies) {
		this.bindingEnergies = bindingEnergies;
	}

	@Override
	public ILazyDataset getPhotoelectronMomentum() {
		return photoelectronMomentum;
	}

	public void setPhotoelectronMomentum(ILazyDataset photoelectronMomentum) {
		this.photoelectronMomentum = photoelectronMomentum;
	}

	@Override
	public ARPESMetadataImpl clone() {
		return new ARPESMetadataImpl(this);
	}

}
