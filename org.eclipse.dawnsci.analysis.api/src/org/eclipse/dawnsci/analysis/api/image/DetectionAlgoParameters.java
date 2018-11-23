/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.image;

/**
 * Parameter class used to contain the necessary parameters required by the detection algorithm. For now RANSAC and
 * Least Median of Squares algorithms (found in BoofCV) are supported.
 * 
 * @author Baha El-Kassaby
 */
public class DetectionAlgoParameters {

	private int randSeed = 0xFEED;
	private int maxIterations;
	private double thresholdFit;
	private int inlierFraction;
	private DetectionType type;

	/**
	 * 
	 * @param randSeed
	 *              The random seed used by the random number generator.
	 * @param maxIterations
	 *              The maximum number of iterations the chosen algorithm will perform.
	 * @param thresholdFit
	 *              How close of a fit a points needs to be to the model to be considered a fit.
	 * @param inlierFraction
	 *              Data which is this fraction or lower is considered an inlier and used to 
	 *              recompute model parameters at the end. Set to 0 to turn off. Domain: 0 to 1.
	 * @param type
	 *              Type of iterative algorithm to use
	 */
	public DetectionAlgoParameters(int randSeed, int maxIterations, double thresholdFit, int inlierFraction, DetectionType type) {
		this.randSeed = randSeed;
		this.maxIterations = maxIterations;
		this.thresholdFit = thresholdFit;
		this.inlierFraction = inlierFraction;
		this.type = type;
	}

	public int getRandSeed() {
		return randSeed;
	}

	public void setRandSeed(int randSeed) {
		this.randSeed = randSeed;
	}

	public int getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(int maxIterations) {
		this.maxIterations = maxIterations;
	}

	public double getThresholdFit() {
		return thresholdFit;
	}

	public void setThresholdFit(double thresholdFit) {
		this.thresholdFit = thresholdFit;
	}

	public int getInlierFraction() {
		return inlierFraction;
	}

	public void setInlierFraction(int inlierFraction) {
		this.inlierFraction = inlierFraction;
	}

	public void setType(DetectionType type) {
		this.type = type;
	}
	public DetectionType getType() {
		return type;
	}

	public enum DetectionType {
		RANSAC("Ransac", "RANSAC is an abbreviation for \"RANdom SAmple Consensus\" "
				+ "and is an iterative algorithm. The model with the largest set of inliers "
				+ "is found by randomly sampling the set of points and fitting a "
				+ "model. The algorithm terminates when the maximum number of "
				+ "iterations has been reached. An inlier is defined as a point "
				+ "which has an error less than a user specified threshold to the "
				+ "estimated model being considered.  The algorithm was first published "
				+ "by Fischler and Bolles in 1981."), 
		LMEDS("Least Median of Squares", "Another technique similar to RANSAC known "
				+ "as Least Median of Squares (LMedS).  For each iteration a small "
				+ "number N points are selected. A model is fit to these points and "
				+ "then the error is computed for the whole set. The model which minimizes "
				+ "the median is selected as the final model. No pruning or formal selection "
				+ "of inlier set is done.");

		private String description;
		private String name;

		DetectionType(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public String getDescription() {
			return description;
		}

		public String getName() {
			return name;
		}
	}
}
