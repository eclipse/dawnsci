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
 * Parameters class for the Hessian registration of images, which is based on an implementation of the SURF algorithm
 * described in [1]. <br>
 * Takes also the type of transformation the registration process will use. Support Affine[2] and Homography[3] transformations.
 * transformation for now. <br>
 * 
 * @author Baha El-Kassaby
 * 
 * [1]Herbert Bay, Andreas Ess, Tinne Tuytelaars, and Luc Van Gool, "Speeded-Up Robust Features (SURF)", CVIU June,
 * 2008, Volume 110, Issue 3, pages 346-359 <br>
 * [2]http://www.corrmap.com/features/affine_transformation_georeference.php <br>
 * [3]http://www.corrmap.com/features/homography_transformation.php
 */
public class HessianRegParameters {
	private float detectThreshold = 1;
	private int extractRadius = 2;
	private int maxFeaturesPerScale = 200;
	private int initialSampleSize = 1;
	private TransformationType type = TransformationType.AFFINE;

	/**
	 * @param detectThreshold
	 *             Minimum feature intensity.
	 * @param extractRadius
	 *             Radius used for non-max-suppression.
	 * @param maxFeaturesPerScale
	 *             Number of features it will find or if ≤ 0 it will return all features it finds.
	 * @param initialSampleSize
	 *             How often pixels are sampled in the first octave.
	 * @param type
	 *             type of Ransac algorithm run
	 **/
	public HessianRegParameters(float detectThreshold, int extractRadius, int maxFeaturesPerScale, int initialSampleSize, TransformationType type) {
		this.detectThreshold = detectThreshold;
		this.extractRadius = extractRadius;
		this.maxFeaturesPerScale = maxFeaturesPerScale;
		this.initialSampleSize = initialSampleSize;
		this.type = type;
	}

	public float getDetectThreshold() {
		return detectThreshold;
	}

	public void setDetectThreshold(float detectThreshold) {
		this.detectThreshold = detectThreshold;
	}

	public int getExtractRadius() {
		return extractRadius;
	}

	public void setExtractRadius(int extractRadius) {
		this.extractRadius = extractRadius;
	}

	public int getMaxFeaturesPerScale() {
		return maxFeaturesPerScale;
	}

	public void setMaxFeaturesPerScale(int maxFeaturesPerScale) {
		this.maxFeaturesPerScale = maxFeaturesPerScale;
	}

	public int getInitialSampleSize() {
		return initialSampleSize;
	}

	public void setInitialSampleSize(int initialSampleSize) {
		this.initialSampleSize = initialSampleSize;
	}

	public TransformationType getType() {
		return type;
	}

	public void setType(TransformationType type) {
		this.type = type;
	}

	public enum TransformationType {
		AFFINE ("Affine transformation", "Linear 2-D geometric transformations which maps variables "
				+ "(e.g. pixel intensity values located at position (x1, y1) in an input image) into "
				+ "new variables (e.g. (x2, y2) in an output image) by applying a linear combination "
				+ "of translation, rotation, scaling and/or shearing (i.e. non-uniform scaling in some"
				+ " directions) operations."),
		HOMOGRAPHY ("Homography transformation", "Transformation based on \"homogeneous coordinates\" and "
				+ "\"projective planes\". The familiar Cartesian plane is composed by a set of points"
				+ " which have a one-to-one correlation to pairs of real numbers, i.e. X-Y on the two"
				+ " axis. The \"projective plane\" instead is a superset of that real plane where for"
				+ " each point we also consider all possible (infinite) straight lines towards space.");

		private String name;
		private String description;

		TransformationType(String type, String description) {
			this.name = type;
			this.description = description;
		}

		public String getName() {
			return name;
		}

		public String getDescription() {
			return description;
		}
		public String[] getTypes() {
			return new String[] { AFFINE.name, HOMOGRAPHY.name };
		}
	}
}
