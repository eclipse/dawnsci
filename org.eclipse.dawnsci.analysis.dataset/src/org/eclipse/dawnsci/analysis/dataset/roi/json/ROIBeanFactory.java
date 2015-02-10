/*-
 * Copyright 2015 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.dataset.roi.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.dawnsci.analysis.api.roi.IROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularFitROI;
import org.eclipse.dawnsci.analysis.dataset.roi.CircularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalFitROI;
import org.eclipse.dawnsci.analysis.dataset.roi.EllipticalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.FreeDrawROI;
import org.eclipse.dawnsci.analysis.dataset.roi.GridROI;
import org.eclipse.dawnsci.analysis.dataset.roi.HyperbolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.LinearROI;
import org.eclipse.dawnsci.analysis.dataset.roi.ParabolicROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PerimeterBoxROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PointROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolygonalROI;
import org.eclipse.dawnsci.analysis.dataset.roi.PolylineROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RectangularROI;
import org.eclipse.dawnsci.analysis.dataset.roi.RingROI;
import org.eclipse.dawnsci.analysis.dataset.roi.SectorROI;

/**
 * A factory for creating beans to store ROIs to JSON
 * 
 * TODO FIXME replaces giant tests with a Map containing the class types.
 */
public class ROIBeanFactory {

	/**
	 * Method that converts an IROI to a ROIBean
	 * @param roi
	 * @return ROIBean
	 */
	public static Object encapsulate(IROI roi) throws Exception {
		Class<? extends IROI> roiClass = roi.getClass();
		String name = roi.getName();
		if(roiClass == PointROI.class){
			PointROI proi = (PointROI) roi;
			PointROIBean proibean = new PointROIBean();
			proibean.setName(name);
			proibean.setStartPoint(proi.getPoint());
			return proibean;

		} else if(roiClass == PerimeterBoxROI.class){
			PerimeterBoxROI proi = (PerimeterBoxROI) roi;
			PerimeterBoxROIBean proibean = new PerimeterBoxROIBean();
			proibean.setName(name);
			proibean.setStartPoint(proi.getPoint());
			proibean.setEndPoint(proi.getEndPoint());
			proibean.setAngle(proi.getAngle());
			proibean.setLengths(proi.getLengths());
			return proibean;

		} else if(roiClass == RectangularROI.class){
			RectangularROI rroi = (RectangularROI) roi;
			RectangularROIBean rroibean = new RectangularROIBean();
			rroibean.setName(name);
			rroibean.setStartPoint(rroi.getPoint());
			rroibean.setEndPoint(rroi.getEndPoint());
			rroibean.setAngle(rroi.getAngle());
			rroibean.setLengths(rroi.getLengths());
			return rroibean;

		} else if(roiClass == GridROI.class){
			GridROI groi = (GridROI) roi;
			GridROIBean groibean = new GridROIBean();
			groibean.setName(name);
			groibean.setStartPoint(groi.getPoint());
			groibean.setEndPoint(groi.getEndPoint());
			groibean.setAngle(groi.getAngle());
			groibean.setLengths(groi.getLengths());
			groibean.setxSpacing(groi.getxSpacing());
			groibean.setySpacing(groi.getySpacing());
			groibean.setGridLinesOn(groi.isGridLineOn());
			groibean.setMidPointOn(groi.isMidPointOn());
			return groibean;

		} else if(roiClass == LinearROI.class){
			LinearROI lroi = (LinearROI) roi;
			LinearROIBean lroibean = new LinearROIBean();
			lroibean.setName(name);
			lroibean.setStartPoint(lroi.getPoint());
			lroibean.setEndPoint(lroi.getEndPoint());
			return lroibean;

		} else if(roiClass == PolylineROI.class){
			PolylineROI plroi = (PolylineROI) roi;
			PolylineROIBean plroibean = new PolylineROIBean();
			plroibean.setName(name);
			plroibean.setStartPoint(plroi.getPoint());
			List<double[]> points = new ArrayList<double[]>();
			for (IROI p: plroi) {
				points.add(p.getPoint());
			}
			plroibean.setPoints(points);
			return plroibean;

		} else if(roiClass == PolygonalROI.class){
			PolygonalROI pgroi = (PolygonalROI) roi;
			PolygonalROIBean pgroibean = new PolygonalROIBean();
			pgroibean.setName(name);
			pgroibean.setStartPoint(pgroi.getPoint());
			List<double[]> points = new ArrayList<double[]>();
			for (IROI p: pgroi) {
				points.add(p.getPoint());
			}
			pgroibean.setPoints(points);
			return pgroibean;

		} else if(roiClass == FreeDrawROI.class){
			FreeDrawROI fdroi = (FreeDrawROI) roi;
			FreeDrawROIBean fdroibean = new FreeDrawROIBean();
			fdroibean.setName(name);
			fdroibean.setStartPoint(fdroi.getPoint());
			List<double[]> points = new ArrayList<double[]>();
			for (IROI p: fdroi) {
				points.add(p.getPoint());
			}
			fdroibean.setPoints(points);
			return fdroibean;
		} else if (roiClass == SectorROI.class) {
			SectorROI sroi = (SectorROI)roi;
			SectorROIBean sroibean = new SectorROIBean();
			sroibean.setName(name);
			sroibean.setStartPoint(sroi.getPoint());
			sroibean.setAngles(sroi.getAngles());
			sroibean.setRadii(sroi.getRadii());
			sroibean.setSymmetry(sroi.getSymmetry());
			sroibean.setDpp(sroi.getDpp());
			sroibean.setClippingCompensation(sroi.isClippingCompensation());
			sroibean.setCombineSymmetry(sroi.isCombineSymmetry());
			sroibean.setAverageArea(sroi.isAverageArea());
			return sroibean;
		} else if (roiClass == RingROI.class) {
			RingROI rroi = (RingROI) roi;
			RingROIBean rroibean = new RingROIBean();
			rroibean.setName(name);
			rroibean.setStartPoint(rroi.getPoint());
			rroibean.setRadii(rroi.getRadii());
			rroibean.setDpp(rroi.getDpp());
			rroibean.setAverageArea(rroi.isAverageArea());
			rroibean.setClippingCompensation(rroi.isClippingCompensation());
			return rroibean;
		} else if(roiClass == CircularROI.class){
			CircularROI croi = (CircularROI) roi;
			CircularROIBean croibean = new CircularROIBean();
			croibean.setName(name);
			croibean.setStartPoint(croi.getCentre());
			croibean.setRadius(croi.getRadius());
			return croibean;

		} else if(roiClass == EllipticalROI.class){
			EllipticalROI eroi = (EllipticalROI) roi;
			EllipticalROIBean eroibean = new EllipticalROIBean();
			eroibean.setName(name);
			eroibean.setStartPoint(eroi.getPoint());
			eroibean.setSemiAxes(eroi.getSemiAxes());
			eroibean.setAngle(eroi.getAngle());
			return eroibean;

		} else if(roiClass == CircularFitROI.class){
			CircularFitROI croi = (CircularFitROI) roi;
			CircularFitROIBean croibean = new CircularFitROIBean();
			croibean.setName(name);
			croibean.setRadius(croi.getRadius());
			croibean.setStartPoint(croi.getPoint());
			List<double[]> points = new ArrayList<double[]>();
			for (IROI p: croi.getPoints()) {
				points.add(p.getPoint());
			}
			croibean.setPoints(points);
			return croibean;

		} else if(roiClass == EllipticalFitROI.class){
			EllipticalFitROI eroi = (EllipticalFitROI) roi;
			EllipticalFitROIBean eroibean = new EllipticalFitROIBean();
			eroibean.setName(name);
			eroibean.setStartPoint(eroi.getPoint());
			eroibean.setSemiAxes(eroi.getSemiAxes());
			eroibean.setAngle(eroi.getAngle());
			List<double[]> points = new ArrayList<double[]>();
			for (IROI p: eroi.getPoints()) {
				points.add(p.getPoint());
			}
			eroibean.setPoints(points);
			return eroibean;

		} else if(roiClass == ParabolicROI.class){
			ParabolicROI proi = (ParabolicROI) roi;
			ParabolicROIBean proibean = new ParabolicROIBean();
			proibean.setName(proi.getName());
			proibean.setAngle(proi.getAngle());
			proibean.setFocalParameter(proi.getFocalParameter());
			proibean.setStartPoint(proi.getPoint());
			return proibean;

		} else if(roiClass == HyperbolicROI.class){
			HyperbolicROI hroi = (HyperbolicROI) roi;
			HyperbolicROIBean hroibean = new HyperbolicROIBean();
			hroibean.setName(hroi.getName());
			hroibean.setAngle(hroi.getAngle());
			hroibean.setEccentricity(hroi.getEccentricity());
			hroibean.setSemi(hroi.getSemilatusRectum());
			hroibean.setStartPoint(hroi.getPoint());
			return hroibean;

		} else {
			throw new Exception("Unsupported Type "+roiClass);
		}
	}

	public static IROI decapsulate(Object object) throws Exception {
		
		final ROIBean bean = (ROIBean)object;
		final Class beanClass = bean.getClass();
		
		if (beanClass == PointROIBean.class){
			PointROI proi = new PointROI();
			proi.setName(bean.getName());
			proi.setPoint(bean.getStartPoint());
			return proi;			

		} else if(beanClass == PerimeterBoxROIBean.class){
			PerimeterBoxROI proi = new PerimeterBoxROI();
			proi.setName(bean.getName());
			proi.setPoint(bean.getStartPoint());
			PerimeterBoxROIBean pbean = (PerimeterBoxROIBean)bean;
			proi.setLengths(pbean.getLengths());
			proi.setAngle(pbean.getAngle());
			return proi;

		} else if(beanClass == RectangularROIBean.class){
			RectangularROIBean rbean = (RectangularROIBean)bean;
			RectangularROI rroi = new RectangularROI(rbean.getStartPoint()[0], 
					rbean.getStartPoint()[1], rbean.getLengths()[0], 
					rbean.getLengths()[1], rbean.getAngle());
			rroi.setName(rbean.getName());
			return rroi;
			
		} else if(beanClass == GridROIBean.class){
			GridROIBean cbean = (GridROIBean)bean;
			GridROI groi = new GridROI(cbean.getStartPoint()[0], 
					cbean.getStartPoint()[1], cbean.getLengths()[0], 
					cbean.getLengths()[1], cbean.getAngle(),
					cbean.getxSpacing(), cbean.getySpacing(),
					cbean.isGridLinesOn(), cbean.isMidPointOn());
			groi.setName(cbean.getName());
			return groi;

		} else if(beanClass == LinearROIBean.class){
			LinearROIBean cbean = (LinearROIBean)bean;
			LinearROI lroi = new LinearROI();
			lroi.setPoint(cbean.getStartPoint());
			lroi.setEndPoint(cbean.getEndPoint());
			lroi.setName(cbean.getName());
			return lroi;

		} else if(beanClass == PolylineROIBean.class){
			PolylineROIBean cbean = (PolylineROIBean)bean;
			PolylineROI plroi = new PolylineROI(cbean.getStartPoint());
			Iterator<double[]> it = cbean.getPoints().iterator();
			while (it.hasNext()) {
				double[] point = it.next();
				plroi.insertPoint(point);
			}
			return plroi;

		} else if(beanClass == PolygonalROIBean.class){
			PolygonalROIBean cbean = (PolygonalROIBean)bean;
			PolygonalROI pgroi = new PolygonalROI(cbean.getStartPoint());
			Iterator<double[]> it = cbean.getPoints().iterator();
			while (it.hasNext()) {
				double[] point = it.next();
				pgroi.insertPoint(point);
			}
			pgroi.setName(cbean.getName());
			return pgroi;

		} else if(beanClass == FreeDrawROIBean.class){
			FreeDrawROIBean cbean = (FreeDrawROIBean)bean;
			FreeDrawROI fdroi = new FreeDrawROI(cbean.getStartPoint());
			Iterator<double[]> it = cbean.getPoints().iterator();
			while (it.hasNext()){
				double[] point = it.next();
				fdroi.insertPoint(point);
			}
			fdroi.setName(cbean.getName());
			return fdroi;

		} else if (beanClass == SectorROIBean.class) {
			SectorROIBean cbean = (SectorROIBean)bean;
			SectorROI sroi = new SectorROI();
			sroi.setName(cbean.getName());
			sroi.setPoint(cbean.getStartPoint());
			sroi.setRadii(cbean.getRadii());
			sroi.setAngles(cbean.getAngles());
			sroi.setDpp(cbean.getDpp());
			sroi.setSymmetry(cbean.getSymmetry());
			sroi.setClippingCompensation(cbean.isClippingCompensation());
			sroi.setCombineSymmetry(cbean.isCombineSymmetry());
			sroi.setAverageArea(cbean.isAverageArea());
			return sroi;

		} else if (beanClass == RingROIBean.class) {
			RingROIBean cbean = (RingROIBean)bean;
			RingROI sroi = new RingROI();
			sroi.setName(cbean.getName());
			sroi.setPoint(cbean.getStartPoint());
			sroi.setRadii(cbean.getRadii());
			sroi.setDpp(cbean.getDpp());
			sroi.setAverageArea(cbean.isAverageArea());
			sroi.setClippingCompensation(cbean.isClippingCompensation());
			return sroi;

		} else if(beanClass == CircularROIBean.class){
			CircularROIBean cbean = (CircularROIBean)bean;
			CircularROI croi = new CircularROI(cbean.getRadius(), 
					cbean.getStartPoint()[0], cbean.getStartPoint()[1]);
			croi.setName(cbean.getName());
			return croi;

			
		} else if(beanClass == EllipticalROIBean.class){
			EllipticalROIBean cbean = (EllipticalROIBean)bean;
			EllipticalROI croi = new EllipticalROI();
			croi.setName(cbean.getName());
			croi.setPoint(cbean.getStartPoint());
			croi.setAngle(cbean.getAngle());
			croi.setSemiaxes(cbean.getSemiAxes());
			return croi;

			
		} else if(beanClass == CircularFitROIBean.class){
			CircularFitROIBean cbean = (CircularFitROIBean)bean;
			Iterator<double[]> it = cbean.getPoints().iterator();
			PolylineROI poly = new PolylineROI();
			while (it.hasNext()) {
				double[] point = it.next();
				poly.insertPoint(point);
			}
			CircularFitROI croi = new CircularFitROI(poly);
			croi.setName(cbean.getName());
			return croi;

			
		} else if(beanClass == EllipticalFitROIBean.class){
			EllipticalFitROIBean cbean = (EllipticalFitROIBean)bean;
			Iterator<double[]> it = cbean.getPoints().iterator();
			PolylineROI poly = new PolylineROI();
			while (it.hasNext()) {
				double[] point = it.next();
				poly.insertPoint(point);
			}
			EllipticalFitROI eroi = new EllipticalFitROI(poly);
			eroi.setName(cbean.getName());
			eroi.setSemiaxes(cbean.getSemiAxes());
			eroi.setAngle(cbean.getAngle());
			return eroi;
			
		} else if(beanClass == ParabolicROIBean.class){
			ParabolicROIBean cbean = (ParabolicROIBean)bean;
			ParabolicROI proi = new ParabolicROI();
			proi.setPoint(cbean.getStartPoint());
			proi.setFocalParameter(cbean.getFocalParameter());
			proi.setAngle(cbean.getAngle());
			proi.setName(cbean.getName());
			return proi;

			
		} else if(beanClass == HyperbolicROIBean.class){
			HyperbolicROIBean cbean = (HyperbolicROIBean)bean;
			HyperbolicROI hroi = new HyperbolicROI();
			hroi.setAngle(cbean.getAngle());
			hroi.setEccentricity(cbean.getEccentricity());
			hroi.setPoint(cbean.getStartPoint());
			hroi.setSemilatusRectum(cbean.getSemi());
			return hroi;


		} else {
			throw new Exception("Unsupported Type "+beanClass);
		}
	}

	/**
	 * Get the bean class for a string type.
	 * @param type
	 * @return class for String type.
	 * @throws ClassNotFoundException
	 */
	public static Class<? extends ROIBean> getClass(String type) throws ClassNotFoundException {
		return (Class<? extends ROIBean>)Class.forName(ROIBeanFactory.class.getPackage().getName()+"." + type + "Bean");
	}

}
