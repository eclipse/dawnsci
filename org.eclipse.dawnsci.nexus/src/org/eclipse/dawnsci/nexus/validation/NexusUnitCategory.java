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
 *******************************************************************************/
package org.eclipse.dawnsci.nexus.validation;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.AmountOfSubstance;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.ElectricCharge;
import javax.measure.quantity.ElectricCurrent;
import javax.measure.quantity.ElectricPotential;
import javax.measure.quantity.Energy;
import javax.measure.quantity.Frequency;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Power;
import javax.measure.quantity.Pressure;
import javax.measure.quantity.SolidAngle;
import javax.measure.quantity.Temperature;
import javax.measure.quantity.Time;
import javax.measure.quantity.Volume;
import javax.measure.spi.ServiceProvider;

import tec.units.ri.unit.Units;

public enum NexusUnitCategory {
	
	NX_ANGLE(getUnit(Angle.class)),
	
	// TODO: check standard unit for this unit category (perhaps any units are ok?)
	NX_ANY(getUnit(Dimensionless.class)),
	
	NX_AREA(getUnit(Area.class)),
	
	NX_CHARGE(getUnit(ElectricCharge.class)),
	
	NX_CROSS_SECTION(getUnit(Area.class)),
	
	NX_CURRENT(getUnit(ElectricCurrent.class)),
	
	NX_DIMENSIONLESS(getUnit(Dimensionless.class)),
	
	/**
	 * Emmittance, a length * angle, e.g. metre-radians
	 */
	NX_EMITTANCE(Units.METRE.multiply(Units.RADIAN)),
	
	NX_ENERGY(getUnit(Energy.class)),
	
	/**
	 * TODO: what unit of flux to use?
	 * The nexus format documentation gives the example: s-1 cm-2,
	 */
	NX_FLUX(Units.SECOND.inverse().divide(getUnit(Area.class))),
	
	NX_FREQUENCY(getUnit(Frequency.class)),
	
	NX_LENGTH(Units.METRE.getSystemUnit()),
	
	NX_MASS(getUnit(Mass.class)),
	
	NX_MASS_DENSITY(getUnit(Mass.class).divide(getUnit(Volume.class))),
	
	NX_MOLECULAR_WEIGHT(getUnit(Mass.class).divide(getUnit(AmountOfSubstance.class))),
	
	/**
	 * Alias to {@link #NX_TIME}
	 */
	NX_PERIOD(getUnit(Time.class)),
	
	NX_PER_AREA(getUnit(Area.class).inverse()),
	
	NX_PER_LENGTH(getUnit(Length.class).inverse()),
	
	NX_POWER(getUnit(Power.class)),
	
	NX_PRESSURE(getUnit(Pressure.class)),
	
	/**
	 * Alias to NX_NUMBER
	 * TODO check unit for this category - could use sub-interface of Dimensionless
	 */
	NX_PULSES(getUnit(Dimensionless.class)),
	
	NX_SCATTERING_LENGTH_DENSITY(getUnit(Area.class).inverse()),
	
	NX_SOLID_ANGLE(getUnit(SolidAngle.class)),
	
	NX_TEMPERATURE(getUnit(Temperature.class)),
	
	NX_TIME(getUnit(Time.class)),
	
	/**
	 * Alias to {@link #NX_TIME}
	 */
	NX_TIME_OF_FLIGHT(getUnit(Time.class)),
	
	/**
	 * TODO, what units for unitless? could we use a subinterface of Dimensionless?
	 */
	NX_UNITLESS(null),
	
	NX_VOLTAGE(getUnit(ElectricPotential.class)),
	
	NX_VOLUME(getUnit(Volume.class)),
	
	NX_WAVELENGTH(getUnit(Length.class)),
	
	NX_WAVENUMBER(getUnit(Length.class).inverse());
	
	private Unit<?> standardUnit;
	
	private NexusUnitCategory(Unit<?> unit) {
		// make sure we have the standard unit
		standardUnit = unit;
	}
	
	public boolean isCompatible(Unit<?> unit) {
		return unit.equals(standardUnit);
	}

	static Unit<? extends Quantity<?>> getUnit(Class clazz) {
		return ServiceProvider.current().getQuantityFactory(clazz).getSystemUnit();
	}

}
