package org.eclipse.dawnsci.analysis.api.image;

/**
 * Thresholding filters
 */
public enum ImageThresholdType {
	GLOBAL_CUSTOM,
	GLOBAL_MEAN,
	GLOBAL_OTSU,
	GLOBAL_ENTROPY,
	ADAPTIVE_SQUARE,
	ADAPTIVE_GAUSSIAN,
	ADAPTIVE_SAUVOLA;
}