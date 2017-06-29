/*-
 * Copyright 2017 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api;

import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.quantity.Acceleration;
import javax.measure.quantity.Dimensionless;
import javax.measure.quantity.ElectricCharge;
import javax.measure.quantity.ElectricConductance;
import javax.measure.quantity.ElectricResistance;
import javax.measure.quantity.Length;
import javax.measure.quantity.MagneticFlux;
import javax.measure.quantity.Mass;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;
import javax.measure.spi.ServiceProvider;

import si.uom.SI;
import tec.units.ri.unit.Units;
import tec.units.ri.quantity.Quantities;

/**
 * Physics constants found on <a href="http://physics.nist.gov/cuu/Constants/bibliography.html">The NIST Reference on
 * Constants, Units and Uncertainty</a>
 */
public class Constants {

	public final static Unit<Dimensionless> dimensionLessUnit = ServiceProvider.current().getQuantityFactory(Dimensionless.class).getSystemUnit();

	/**
	 * Holds the standard acceleration due to gravity (approximately equal to the acceleration due to gravity on the
	 * Earth's surface).
	 *
	 * @see <a href="http://en.wikipedia.org/wiki/Acceleration_due_to_gravity"> Wikipedia: Acceleration due to
	 *      gravity</a>
	 */
	public final static Quantity<Acceleration> g = Quantities.getQuantity(980665, SI.METRE_PER_SQUARE_SECOND).divide(100000);

	/**
	 * Holds the electron rest mass.
	 * 
	 * uncertainty: 0.00000011E-31
	 */
	public final static Quantity<Mass> me = Quantities.getQuantity(9.10938356E-31, Units.KILOGRAM);

	/**
	 * Holds the proton rest mass.
	 * 
	 * uncertainty: 0.000000021E-27
	 */
	public final static Quantity<Mass> mp = Quantities.getQuantity(1.672621898E-27, Units.KILOGRAM);

	/**
	 * Holds the neutron rest mass.
	 * 
	 * uncertainty: 0.00000021E-27
	 */
	public final static Quantity<Mass> mn = Quantities.getQuantity(1.674927471E-27, Units.KILOGRAM);

	/**
	 * Holds the deuteron rest mass.
	 * 
	 * uncertainty: 0.000000041E-27
	 */
	public final static Quantity<Mass> md = Quantities.getQuantity(3.343583719E-27, Units.KILOGRAM);

	/**
	 * Holds the muon rest mass.
	 * uncertainty:0.000000048E-28
	 */
	public final static Quantity<Mass> mμ = Quantities.getQuantity(1.883531594E-28, Units.KILOGRAM);

	/**
	 * Holds π².
	 */
	public final static Quantity<Dimensionless> π_square = Quantities.getQuantity(9.8696044010893586188344909998762, dimensionLessUnit);

	/**
	 * Holds the speed of light in vacuum (exact).
	 */
	public final static Quantity<Speed> c = Quantities.getQuantity(299792458, Units.METRE_PER_SECOND);

	/**
	 * Holds {@link #c}².
	 */
	public final static Quantity<?> c_square = Quantities.getQuantity(299792458L * 299792458L, Units.METRE_PER_SECOND.pow(2));

	/**
	 * Holds the Boltzmann constant.
	 * uncertainty: 0.00000079E-23
	 * @see <a href="http://en.wikipedia.org/wiki/Boltzmanns_constant"> Wikipedia: Boltzmann constant</a>
	 */
	public final static Quantity<?> k = Quantities.getQuantity(1.38064852E-23, Units.JOULE.divide(Units.KELVIN));

	/**
	 * Holds the Planck constant.
	 * @See <a href="http://en.wikipedia.org/wiki/Plancks_constant">Planck's constant</a>
	 */
	public final static Quantity<?> ℎ = Quantities.getQuantity(6.626070040E-34, Units.JOULE.multiply(Units.SECOND));

	/**
	 * Holds the Planck constant over 2π.
	 */
	public final static Quantity<?> ℏ = ℎ.divide(Math.PI * 2);

	/**
	 * Holds the elementary charge (positron charge).
	 * uncertainty: 0.0000000098E-19
	 * @see <a href="http://en.wikipedia.org/wiki/Elementary_charge"> Wikipedia: Elementary Charge</a>
	 */
	public final static Quantity<ElectricCharge> e = Quantities.getQuantity(1.6021766208E-19, Units.COULOMB);

	/**
	 * Holds the permeability of vacuum or magnetic constant.
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Permeability_%28electromagnetism%29"> Wikipedia: Permeability
	 *      (electromagnetism)</a>
	 */
	public final static Quantity<?> µ0 = Quantities.getQuantity(1.2566370614359172953850573533118E-6,
			Units.NEWTON.divide(Units.AMPERE.pow(2))); // 4π×10−7 N/A²

	/**
	 * Holds the permittivity of vacuum or electric constant (1/(µ0·c²))
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Permittivity"> Wikipedia: Permittivity</a>
	 */
	public final static Quantity<?> ε0 = µ0.multiply(c.multiply(c)).inverse();

	/**
	 * Holds the characteristic impedance of vacuum (µ0·c).
	 */
	public final static Quantity<ElectricResistance> Z0 = Quantities.getQuantity(µ0.multiply(c).getValue().doubleValue(), Units.OHM);

	/**
	 * Holds the fine structure constant (e²/(2·ε0·c·h))
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Fine_structure_constant"> Wikipedia: Fine Structure Constant</a>
	 */
	public final static Quantity<Dimensionless> α = Quantities.getQuantity(e.multiply(e).divide(ε0.multiply(c).multiply(ℎ).multiply(2)).getValue().doubleValue(), dimensionLessUnit);

	/**
	 * Holds the Newtonian constant of gravitation.
	 * uncertainty: 0.00031E-11
	 * @see <a href="http://en.wikipedia.org/wiki/Gravitational_constant"> Wikipedia: Gravitational Constant</a>
	 */
	public final static Quantity<?> G = Quantities.getQuantity(6.67408E-11,
			Units.METRE.pow(3).divide(Units.KILOGRAM).divide(Units.SECOND.pow(2)));

	/**
	 * Holds the Avogadro constant.
	 * uncertainty: 0.000000074E23
	 * @See <a href="http://en.wikipedia.org/wiki/Avogadro%27s_number"> Wikipedia: Avogadro's number</a>
	 */
	public final static Quantity<?> N = Quantities.getQuantity(6.022140857E23, Units.MOLE.inverse());

	/**
	 * Holds the molar gas constant (N·k)
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Gas_constant"> Wikipedia: Gas constant</a>
	 */
	public final static Quantity<?> R = N.multiply(k);

	/**
	 * Holds the Faraday constant (N·e)
	 * 
	 * @see <a href="http://en.wikipedia.org/wiki/Faraday_constant"> Wikipedia: Faraday constant</a>
	 */
	public final static Quantity<?> F = N.multiply(e);

	/**
	 * Holds the Stefan-Boltzmann constant ((π²/60)·k<sup>4</sup>/(ℏ³·c²))
	 */
	public final static Quantity<?> σ = π_square.divide(60).multiply(Math.pow(k.getValue().doubleValue(), 4)
			/ (Math.pow(ℏ.getValue().doubleValue(), 3) * Math.pow(c.getValue().doubleValue(), 2)));

	/**
	 * Holds the unified atomic mass unit (0.001 kg/mol)/N
	 */
	public final static Quantity<Mass> amu = Quantities.getQuantity(Quantities.getQuantity(1E-3, Units.KILOGRAM.divide(Units.MOLE)).divide(N).getValue().doubleValue(), Units.KILOGRAM);

	/**
	 * Holds the Rydberg constant (α²·me·c/2h).
	 * uncertainty: 0.000065
	 * @see <a href="http://en.wikipedia.org/wiki/Rydberg_constant"> Wikipedia: Rydberg constant</a>
	 */
	public final static Quantity<?> Rinf
	// Do not use formala as experimental incertainty is very low.
			= Quantities.getQuantity(10973731.568508, Units.METRE.inverse());

	/**
	 * Holds the Bohr radius (α/(4π·Rinf))
	 */
	public final static Quantity<Length> a0 = Quantities.getQuantity(α.divide(4 * Math.PI * Rinf.getValue().doubleValue()).getValue().doubleValue(), Units.METRE);

	/**
	 * Holds the Hartree energy (2Rinf·h·c)
	 */
	public final static Quantity<?> Eh = Rinf.multiply(ℎ).multiply(c).multiply(2);

	/**
	 * Holds the magnetic flux quantum (h/2e)
	 */
	public final static Quantity<MagneticFlux> Φ0 = Quantities.getQuantity(ℎ.divide(e).divide(2).getValue().doubleValue(), Units.WEBER);

	/**
	 * Holds the conductance quantum (2e²/h)
	 */
	public final static Quantity<ElectricConductance> G0 = Quantities.getQuantity(
			e.multiply(e).divide(ℎ).multiply(2).getValue().doubleValue(),
			ServiceProvider.current().getQuantityFactory(ElectricConductance.class).getSystemUnit());

	/**
	 * Holds the Bohr magneton (ℏ·e/2me)
	 */
	public final static Quantity<?> µB = e.multiply(ℏ).divide(me).divide(2);

	/**
	 * Holds the nuclear magneton (ℏ·e/2mp)
	 */
	public final static Quantity<?> µN = e.multiply(ℏ).divide(mp).divide(2);

	/**
	 * Holds the Planck mass (ℏ·c/G)<sup>1/2</sup>
	 */
	public final static Quantity<Mass> mP = Quantities.getQuantity(Math.sqrt(ℏ.multiply(c).divide(G).getValue().doubleValue()), Units.KILOGRAM);

	/**
	 * Holds the Planck length (ℏ/(mP·c))
	 */
	public final static Quantity<Length> lP = Quantities.getQuantity(ℏ.divide(mP.multiply(c)).getValue().doubleValue(), Units.METRE);

	/**
	 * Holds the Planck time (lP/c)
	 */
	public final static Quantity<Time> tP = Quantities.getQuantity(lP.divide(c).getValue().doubleValue(), Units.SECOND);
}
