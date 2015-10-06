package org.eclipse.dawnsci.nexus.validation;

import static javax.measure.unit.SI.*;

import javax.measure.quantity.AmountOfSubstance;
import javax.measure.quantity.Angle;
import javax.measure.quantity.Area;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.Duration;
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
import javax.measure.quantity.Volume;
import javax.measure.unit.Unit;


public enum NexusUnitCategory {
	
	NX_ANGLE(Angle.UNIT),
	
	// TODO: check standard unit for this unit category (perhaps any units are ok?)
	NX_ANY(Dimensionless.UNIT),
	
	NX_AREA(Area.UNIT),
	
	NX_CHARGE(ElectricCharge.UNIT),
	
	NX_CROSS_SECTION(Area.UNIT),
	
	NX_CURRENT(ElectricCurrent.UNIT),
	
	NX_DIMENSIONLESS(Dimensionless.UNIT),
	
	/**
	 * Emmittance, a length * angle, e.g. metre-radians
	 */
	NX_EMITTANCE(METRE.times(RADIAN)),
	
	NX_ENERGY(Energy.UNIT),
	
	/**
	 * TODO: what unit of flux to use?
	 * The nexus format documentation gives the example: s-1 cm-2,
	 */
	NX_FLUX(SECOND.inverse().divide(Area.UNIT)),
	
	NX_FREQUENCY(Frequency.UNIT),
	
	NX_LENGTH(METRE),
	
	NX_MASS(Mass.UNIT),
	
	NX_MASS_DENSITY(Mass.UNIT.divide(Volume.UNIT)),
	
	NX_MOLECULAR_WEIGHT(Mass.UNIT.divide(AmountOfSubstance.UNIT)),
	
	/**
	 * Alias to {@link #NX_TIME}
	 */
	NX_PERIOD(Duration.UNIT),
	
	NX_PER_AREA(Area.UNIT.inverse()),
	
	NX_PER_LENGTH(Length.UNIT.inverse()),
	
	NX_POWER(Power.UNIT),
	
	NX_PRESSURE(Pressure.UNIT),
	
	/**
	 * Alias to NX_NUMBER
	 * TODO check unit for this category - could use sub-interface of Dimensionless
	 */
	NX_PULSES(Dimensionless.UNIT),
	
	NX_SCATTERING_LENGTH_DENSITY(Area.UNIT.inverse()),
	
	NX_SOLID_ANGLE(SolidAngle.UNIT),
	
	NX_TEMPERATURE(Temperature.UNIT),
	
	NX_TIME(Duration.UNIT),
	
	/**
	 * Alias to {@link #NX_TIME}
	 */
	NX_TIME_OF_FLIGHT(Duration.UNIT),
	
	/**
	 * TODO, what units for unitless? could we use a subinterface of Dimensionless?
	 */
	NX_UNITLESS(null),
	
	NX_VOLTAGE(ElectricPotential.UNIT),
	
	NX_VOLUME(Volume.UNIT),
	
	NX_WAVELENGTH(Length.UNIT),
	
	NX_WAVENUMBER(Length.UNIT.inverse());
	
	private Unit<?> standardUnit;
	
	private NexusUnitCategory(Unit<?> unit) {
		// make sure we have the standard unit
		standardUnit = unit.getStandardUnit();
	}
	
	public boolean isCompatible(Unit<?> unit) {
		return unit.getStandardUnit().equals(standardUnit);
	}

}
