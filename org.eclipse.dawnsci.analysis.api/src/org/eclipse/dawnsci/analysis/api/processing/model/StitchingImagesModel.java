/*-
 * Copyright (c) 2011, 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.analysis.api.processing.model;

public class StitchingImagesModel extends AbstractOperationModel {

	@OperationModelField(label = "Stack Layout Rows", hint = "Number of rows in the image stack")
	private int rows = 3;
	@OperationModelField(label = "Stack Layout Columns", hint = "Number of columns in the image stack")
	private int columns = 3;
	@OperationModelField(label = "Field of View", hint = "Field of view in microns")
	private double fieldOfView = 50;
	@OperationModelField(label = "Use feature Association", hint = "Use feature association to perfom the stitching, mosaic operation otherwise")
	private boolean featureAssociated = true;
	@OperationModelField(label = "X Translation", hint = "X translation of each image in pixels")
	private double xTransl = 25;
	@OperationModelField(label = "Y Translation", hint = "Y translation of each image in pixels")
	private double yTransl = 25;

	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		firePropertyChange("imageStackRows", this.rows, this.rows = rows);
	}
	public int getColumns() {
		return columns;
	}
	public void setColumns(int columns) {
		firePropertyChange("imageStackColumns", this.columns, this.columns = columns);
	}
	public double getFieldOfView() {
		return fieldOfView;
	}
	public void setFieldOfView(double fieldOfView) {
		firePropertyChange("fieldOfView", this.fieldOfView, this.fieldOfView = fieldOfView);
	}
	public boolean isFeatureAssociated() {
		return featureAssociated;
	}
	public void setFeatureAssociated(boolean featureAssociated) {
		firePropertyChange("hasFeatureAssociation", this.featureAssociated, this.featureAssociated = featureAssociated);
	}
	public double getxTransl() {
		return xTransl;
	}
	public void setxTransl(double xTransl) {
		firePropertyChange("xTranslation", this.xTransl, this.xTransl = xTransl);
	}
	public double getyTransl() {
		return yTransl;
	}
	public void setyTransl(double yTransl) {
		firePropertyChange("yTranslation", this.yTransl, this.yTransl = yTransl);
	}
}
