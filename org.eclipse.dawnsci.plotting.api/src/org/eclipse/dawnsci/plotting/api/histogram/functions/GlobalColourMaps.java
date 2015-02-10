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

package org.eclipse.dawnsci.plotting.api.histogram.functions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class GlobalColourMaps {

	public static final String[] colourMapNames = {"Grey",
		   "Film negative",
		   "sqrt",
		   "Film negative (sqrt)",
		   "Traditional pm3d (black-blue-red-yellow)",
		   "Ncd Colour",
		   "Green-red-violet",
		   "Ocean (green-blue-white)",
		   "Hot (black-red-yellow-white)",
		   "Colour printable on gray (black-blue-violet-yellow-white)",
		   "Rainbow (blue-green-yellow-red)",
		   "AFM hot (black-red-yellow-white)",
		   "Low Skewed Grayscale (black-white)",
		   "High Skewed Grayscale (black-white)",
		   "Geographical",
		   "Mark's special"};	
	
	public static HashMap<String,Integer> indexOfMappingFunctions = null;
	public static LinkedList<AbstractMapFunction> mappingFunctions = null;
	public static ArrayList<Integer> colourSelectList = new ArrayList<Integer>();
	
	private static final Logger logger = LoggerFactory.getLogger(GlobalColourMaps.class);	
	private static float specialMinThreshold = 0.1f;
	private static float specialMaxThreshold = 0.9f;
	
	/**
	 * Add a mapping function to the view's list
	 * @param mappingFunction
	 */	
	private static void addToMappings(AbstractMapFunction mappingFunction) {
		int i;
		if (indexOfMappingFunctions == null) {
			indexOfMappingFunctions = new HashMap<String, Integer>();
			i = 0;
		} else {
			i = mappingFunctions.size();
		}
		String function = mappingFunction.getMapFunctionName();
		if (indexOfMappingFunctions.containsKey(function)) {
			logger.warn("Adding a mapping function whose name, {}, already exists", function);
			logger.warn("Overwriting the index");
		}
		mappingFunctions.add(mappingFunction);
		indexOfMappingFunctions.put(function, i);
	}	

	public static AbstractMapFunction getFunction(String functionName) {
		final int index = indexOfMappingFunctions.get(functionName);
		if (index>-1) return mappingFunctions.get(index);
		return null;
	}

	private static void addToColourMapBox(String red, boolean redInvert,
										  String green, boolean greenInvert, 
										  String blue, boolean blueInvert,
										  String alpha, boolean alphaInvert) {
		Integer index;
		
		index = indexOfMappingFunctions.get(red);
		if (index == null) {
			logger.warn("Cannot find mapping function {}, resorting to x");
			index = indexOfMappingFunctions.get("x");
		}
		if (redInvert) index *= -1;
		colourSelectList.add(index);
		index = indexOfMappingFunctions.get(green);
		if (index == null) {
			logger.warn("Cannot find mapping function {}, resorting to x");
			index = indexOfMappingFunctions.get("x");
		}
		if (greenInvert) index *= -1;
		colourSelectList.add(index);
		index = indexOfMappingFunctions.get(blue);
		if (index == null) {
			logger.warn("Cannot find mapping function {}, resorting to x");
			index = indexOfMappingFunctions.get("x");
		}
		if (blueInvert) index *= -1;
		colourSelectList.add(index);
		index = indexOfMappingFunctions.get(alpha);
		if (index == null) {
			logger.warn("Cannot find mapping function {}, resorting to 1.0");
			index = indexOfMappingFunctions.get("1.0");
		}
		if (alphaInvert) index *= -1;
		colourSelectList.add(index);
	}
	
	public static void InitializeColourMaps() {
		if (indexOfMappingFunctions == null) {
			 mappingFunctions = new LinkedList<AbstractMapFunction>();
			// 0
			ConstMapFunction mapZero = new ConstMapFunction(0, "0");
			addToMappings(mapZero);
			// 1
			ConstMapFunction mapHalf = new ConstMapFunction(0.5, "0.5");
			addToMappings(mapHalf);
			// 2
			ConstMapFunction mapOne = new ConstMapFunction(1.0, "1.0");
			addToMappings(mapOne);
			// 3
			LinearMapFunction linearMap = new LinearMapFunction();
			addToMappings(linearMap);
			// initial pick
	
			SquareMapFunction squareMap = new SquareMapFunction();
			addToMappings(squareMap);
	
			CubicMapFunction cubicMap = new CubicMapFunction();
			addToMappings(cubicMap);
	
			QuarticMapFunction quarticMap = new QuarticMapFunction();
			addToMappings(quarticMap);
	
			SquareRootMapFunction sqrtMap = new SquareRootMapFunction();
			addToMappings(sqrtMap);
	
			FourthRootMapFunction frthrtMap = new FourthRootMapFunction();
			addToMappings(frthrtMap);
	
			SinMapFunction sin90Map = new SinMapFunction("sin(x*90)", 0.5*Math.PI, false);
			addToMappings(sin90Map);
	
			CosMapFunction cos90Map = new CosMapFunction("cos(x*90)", 0.5*Math.PI, false);
			addToMappings(cos90Map);
	
			LinearMapFunction lineOff5abs = new LinearMapFunction("|x-0.5|", -0.5, true);
			addToMappings(lineOff5abs);
	
			SquareScaleOffsetMapFunction amap = new SquareScaleOffsetMapFunction("(2x-1)^2", 2.0, -1.0);
			addToMappings(amap);
	
			SinMapFunction sin180map = new SinMapFunction("sin(x*180)", Math.PI, false);
			addToMappings(sin180map);
	
			CosMapFunction cos180map = new CosMapFunction("|cos(x*180)|", Math.PI, true);
			addToMappings(cos180map);
	
			SinMapFunction sin360map = new SinMapFunction("sin(x*360)", 2.0*Math.PI, false);
			addToMappings(sin360map);
	
			CosMapFunction cos360map = new CosMapFunction("cos(x*360)", 2.0*Math.PI, false);
			addToMappings(cos360map);
	
			SinMapFunction asin360map = new SinMapFunction("|sin(x*360)|", 2.0*Math.PI, true);
			addToMappings(asin360map);
	
			CosMapFunction acos360map = new CosMapFunction("|cos(x*360)|", 2.0*Math.PI, true);
			addToMappings(acos360map);
	
			SinMapFunction asin720map = new SinMapFunction("|sin(x*720)|", 4.0*Math.PI, true);
			addToMappings(asin720map);
	
			CosMapFunction acos720map = new CosMapFunction("|cos(x*720)|", 4.0*Math.PI, true);
			addToMappings(acos720map);
	
			LinearMapFunction linea = new LinearMapFunction("3x", 3.0, false);
			addToMappings(linea);
	
			LinearMapFunction lineb = new LinearMapFunction("3x-1", 3.0, -1.0, false);
			addToMappings(lineb);
	
			LinearMapFunction linec = new LinearMapFunction("3x-2", 3.0, -2.0, false);
			addToMappings(linec);
	
			LinearMapFunction alineb = new LinearMapFunction("|3x-1|", 3.0, -1.0, true);
			addToMappings(alineb);
	
			LinearMapFunction alinec = new LinearMapFunction("|3x-2|", 3.0, -2.0, true);
			addToMappings(alinec);
	
			LinearMapFunction lined = new LinearMapFunction("(3x-1)/2", 1.5, -0.5, false);
			addToMappings(lined);
	
			LinearMapFunction linee = new LinearMapFunction("(3x-2)/2", 1.5, -1.0, false);
			addToMappings(linee);
	
			LinearMapFunction alined = new LinearMapFunction("|3x-1|/2", 1.5, -0.5, true);
			addToMappings(alined);
	
			LinearMapFunction alinee = new LinearMapFunction("|3x-2|/2", 1.5, -1.0, true);
			addToMappings(alinee);
	
			LinearMapFunction linef = new LinearMapFunction("2x", 2.0, false);
			addToMappings(linef);
	
			LinearMapFunction lineg = new LinearMapFunction("2x-0.5", 2.0, -0.5, false);
			addToMappings(lineg);
	
			LinearMapFunction lineh = new LinearMapFunction("2x-1", 2.0, -1.0, false);
			addToMappings(lineh);
	
			LinearMapFunction alineg = new LinearMapFunction("|2x-0.5|", 2.0, -0.5, true);
			addToMappings(alineg);
	
			LinearMapFunction clinea = new LinearMapFunction("x/0.32-0.78125", (1.0/0.32), -0.78125, false);
			addToMappings(clinea);
	
			LinearMapFunction clineb = new LinearMapFunction("2x-0.84", 2.0, -0.84, false);
			addToMappings(clineb);
	
			CustomAPieceWiseLinearMapFunction linei = new CustomAPieceWiseLinearMapFunction();
			addToMappings(linei);
			
			NCDGamma2RedFunction ncdRed = new NCDGamma2RedFunction();
			addToMappings(ncdRed);
			
			NCDGamma2GreenFunction ncdGreen = new NCDGamma2GreenFunction();
			addToMappings(ncdGreen);
			
			NCDGamma2BlueFunction ncdBlue = new NCDGamma2BlueFunction();
			addToMappings(ncdBlue);
			
			GeoBlueMapFunction geoBlue = new GeoBlueMapFunction();
			GeoGreenMapFunction geoGreen = new GeoGreenMapFunction();
			GeoRedMapFunction geoRed = new GeoRedMapFunction();
			addToMappings(geoRed);
			addToMappings(geoGreen);
			addToMappings(geoBlue);
			
			SpecialExposureFunction redExpFunc = new SpecialExposureFunction("RedSpecial",specialMinThreshold, specialMaxThreshold,'r');
			addToMappings(redExpFunc);
			SpecialExposureFunction greenExpFunc = new SpecialExposureFunction("GreenSpecial",specialMinThreshold, specialMaxThreshold, 'g');
			addToMappings(greenExpFunc);
			SpecialExposureFunction blueExpFunc = new SpecialExposureFunction("BlueSpecial",specialMinThreshold, specialMaxThreshold, 'b');
			addToMappings(blueExpFunc);		

			UserCustomFunction customFunc = new UserCustomFunction("Custom",24);
			addToMappings(customFunc);
			UserCustomFunction redCustomFunc = new UserCustomFunction("Red Custom",24);
			addToMappings(redCustomFunc); 
			UserCustomFunction greenCustomFunc = new UserCustomFunction("Green Custom",24);
			addToMappings(greenCustomFunc);
			UserCustomFunction blueCustomFunc = new UserCustomFunction("Blue Custom",24);
			addToMappings(blueCustomFunc);
			UserCustomFunction alphaCustomFunc = new UserCustomFunction("Alpha Custom",24);
			addToMappings(alphaCustomFunc);
			
			addToColourMapBox( "x", false, "x", false, "x", false, "1.0", false);
			addToColourMapBox( "x", true, "x", true, "x", true, "1.0", false);
			addToColourMapBox( "sqrt(x)", false, "sqrt(x)", false, "sqrt(x)", false, "1.0", false);
			addToColourMapBox( "sqrt(x)", true, "sqrt(x)", true, "sqrt(x)", true, "1.0", false);
			addToColourMapBox( "sqrt(x)", false, "x^3", false, "sin(x*360)", false, "1.0", false);
			addToColourMapBox( "NCD Gamma II Red",false,"NCD Gamma II Green",false,"NCD Gamma II Blue",false,"1.0",false);
			addToColourMapBox( "x", false, "|x-0.5|", false, "x^4", false, "1.0", false);
	
			addToColourMapBox( "3x-2", false, "|3x-1|/2", false, "x", false, "1.0", false);
			addToColourMapBox( "3x", false, "3x-1", false, "3x-2", false, "1.0", false);
			addToColourMapBox( "x/0.32-0.78125", false, "2x-0.84", false,
					"4x;1;-2x+1.84;12.5x-11.5", false, "1.0", false);
			addToColourMapBox( "|2x-0.5|", false, "sin(x*180)", false, "cos(x*90)", false, "1.0", false);
			addToColourMapBox( "2x", false, "2x-0.5", false, "2x-1", false, "1.0", false);
			addToColourMapBox( "sqrt(x)", false, "sqrt(x)", false, "sqrt(x)", false, "1.0", false);
			addToColourMapBox( "x^2", false, "x^2", false, "x^2", false, "1.0", false);
			addToColourMapBox("GeoRed",false,"GeoGreen",false,"GeoBlue",false,"1.0",false);
			addToColourMapBox("RedSpecial",false,"GreenSpecial",false,"BlueSpecial",false,"1.0",false);
		}
	}
	
}
