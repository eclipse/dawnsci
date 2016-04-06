/*-
 *******************************************************************************
 * Copyright (c) 2011, 2014, 2016 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Colin Palmer - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.dawnsci.json.test.testobject;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.dawnsci.json.test.TestStatusBean;

/**
 * Bean to serialise with JSON and be sent to the server.
 *
 * JSON is used rather than the direct object because we may want to have
 * a python server.
 *
 * @author Matthew Gerring
 *
 */
public class ProjectBean extends TestStatusBean {

	private String      projectName;
	private String      cystalName;
	private List<SweepBean> sweeps;
	private double      wavelength = Double.NaN;
	private String      commandLineSwitches;
	private boolean     anomalous;
    private String      spaceGroup;
	private String      unitCell;
	private String      resolution;


	public String getCommandLineSwitches() {
		return commandLineSwitches;
	}

	public void setCommandLineSwitches(String commandLine) {
		this.commandLineSwitches = commandLine;
	}

	public boolean isAnomalous() {
		return anomalous;
	}

	public void setAnomalous(boolean anomalous) {
		this.anomalous = anomalous;
	}

	public ProjectBean(){
	}

	@Override
	public void merge(TestStatusBean with) {
        super.merge(with);
        ProjectBean db = (ProjectBean)with;
        this.projectName  = db.projectName;
        this.cystalName   = db.cystalName;
        this.sweeps       = db.sweeps;
        this.wavelength   = db.wavelength;
        this.spaceGroup   = db.spaceGroup;
        this.unitCell     = db.unitCell;
        this.anomalous    = db.anomalous;
        this.commandLineSwitches  = db.commandLineSwitches;
        this.resolution   = db.resolution;
	}


	public void addSweep(SweepBean bean) {
		if (sweeps==null) sweeps = new ArrayList<SweepBean>(7);
		sweeps.add(bean);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (anomalous ? 1231 : 1237);
		result = prime
				* result
				+ ((commandLineSwitches == null) ? 0 : commandLineSwitches
						.hashCode());
		result = prime * result
				+ ((cystalName == null) ? 0 : cystalName.hashCode());
		result = prime * result
				+ ((projectName == null) ? 0 : projectName.hashCode());
		result = prime * result
				+ ((resolution == null) ? 0 : resolution.hashCode());
		result = prime * result
				+ ((spaceGroup == null) ? 0 : spaceGroup.hashCode());
		result = prime * result + ((sweeps == null) ? 0 : sweeps.hashCode());
		result = prime * result
				+ ((unitCell == null) ? 0 : unitCell.hashCode());
		long temp;
		temp = Double.doubleToLongBits(wavelength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjectBean other = (ProjectBean) obj;
		if (anomalous != other.anomalous)
			return false;
		if (commandLineSwitches == null) {
			if (other.commandLineSwitches != null)
				return false;
		} else if (!commandLineSwitches.equals(other.commandLineSwitches))
			return false;
		if (cystalName == null) {
			if (other.cystalName != null)
				return false;
		} else if (!cystalName.equals(other.cystalName))
			return false;
		if (projectName == null) {
			if (other.projectName != null)
				return false;
		} else if (!projectName.equals(other.projectName))
			return false;
		if (resolution == null) {
			if (other.resolution != null)
				return false;
		} else if (!resolution.equals(other.resolution))
			return false;
		if (spaceGroup == null) {
			if (other.spaceGroup != null)
				return false;
		} else if (!spaceGroup.equals(other.spaceGroup))
			return false;
		if (sweeps == null) {
			if (other.sweeps != null)
				return false;
		} else if (!sweeps.equals(other.sweeps))
			return false;
		if (unitCell == null) {
			if (other.unitCell != null)
				return false;
		} else if (!unitCell.equals(other.unitCell))
			return false;
		if (Double.doubleToLongBits(wavelength) != Double
				.doubleToLongBits(other.wavelength))
			return false;
		return true;
	}
	/**
	 *  Creates name based on all the data collections.
	 */
	@Override
	public void createName() {
		SweepBean start = sweeps.get(0);
		SweepBean end   = sweeps.get(sweeps.size()-1);
		name = start.getName()+" - "+end.getName();
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCystalName() {
		return cystalName;
	}

	public void setCystalName(String cystalName) {
		this.cystalName = cystalName;
	}

	public List<SweepBean> getSweeps() {
		return sweeps;
	}

	public void setSweeps(List<SweepBean> sweeps) {
		this.sweeps = sweeps;
	}

	public double getWavelength() {
		return wavelength;
	}

	public void setWavelength(double wavelength) {
		this.wavelength = wavelength;
	}

	public String getSpaceGroup() {
		return spaceGroup;
	}

	public void setSpaceGroup(String spaceGroup) {
		this.spaceGroup = spaceGroup;
	}

	public String getUnitCell() {
		return unitCell;
	}

	public void setUnitCell(String unitCell) {
		this.unitCell = unitCell;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
}
