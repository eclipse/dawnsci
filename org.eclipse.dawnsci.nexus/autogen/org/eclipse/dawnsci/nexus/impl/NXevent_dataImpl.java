/*-
 *******************************************************************************
 * Copyright (c) 2015 Diamond Light Source Ltd.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * This file was auto-generated from the NXDL XML definition.
 * Generated at: 2017-06-23T10:28:44.471+01:00
 *******************************************************************************/

package org.eclipse.dawnsci.nexus.impl;

import java.util.Date;
import java.util.Set;
import java.util.EnumSet;

import org.eclipse.dawnsci.analysis.api.tree.DataNode;

import org.eclipse.january.dataset.IDataset;

import org.eclipse.dawnsci.nexus.*;

/**
 * NXevent_data is a special group for storing data from neutron
 * detectors in event mode. In this mode, the detector electronics
 * emits a stream of detectorID, timestamp pairs. With detectorID
 * describing the detector element in which the neutron was detected
 * and timestamp the timestamp at which the neutron event was
 * detected. In NeXus detectorID maps to event_id, event_time_offset
 * to the timestamp.
 * As this kind of data is common at pulsed neutron
 * sources, the timestamp is almost always relative to the start of a
 * neutron pulse. Thus the pulse timestamp is recorded too together
 * with an index in the event_id, event_time_offset pair at which data for
 * that pulse starts. At reactor source the same pulsed data effect
 * may be achieved through the use of choppers or in stroboscopic
 * measurement setups.
 * In order to make random access to timestamped data
 * faster there is an optional array pair of
 * cue_timestamp_zero and cue_index. The cue_timestamp_zero will
 * contain courser timestamps then in the time array, say
 * every five minutes. The cue_index will then contain the
 * index into the event_id,event_time_offset pair of arrays for that
 * courser cue_timestamp_zero.
 * 
 * @version 1.1
 */
public class NXevent_dataImpl extends NXobjectImpl implements NXevent_data {

	private static final long serialVersionUID = 1L;  // no state in this class, so always compatible


	public static final Set<NexusBaseClass> PERMITTED_CHILD_GROUP_CLASSES = EnumSet.noneOf(NexusBaseClass.class);

	public NXevent_dataImpl() {
		super();
	}

	public NXevent_dataImpl(final long oid) {
		super(oid);
	}
	
	@Override
	public Class<? extends NXobject> getNXclass() {
		return NXevent_data.class;
	}
	
	@Override
	public NexusBaseClass getNexusBaseClass() {
		return NexusBaseClass.NX_EVENT_DATA;
	}
	
	@Override
	public Set<NexusBaseClass> getPermittedChildGroupClasses() {
		return PERMITTED_CHILD_GROUP_CLASSES;
	}
	

	@Override
	public IDataset getEvent_time_offset() {
		return getDataset(NX_EVENT_TIME_OFFSET);
	}

	@Override
	public Long getEvent_time_offsetScalar() {
		return getLong(NX_EVENT_TIME_OFFSET);
	}

	@Override
	public DataNode setEvent_time_offset(IDataset event_time_offset) {
		return setDataset(NX_EVENT_TIME_OFFSET, event_time_offset);
	}

	@Override
	public DataNode setEvent_time_offsetScalar(Long event_time_offset) {
		return setField(NX_EVENT_TIME_OFFSET, event_time_offset);
	}

	@Override
	public IDataset getEvent_id() {
		return getDataset(NX_EVENT_ID);
	}

	@Override
	public Long getEvent_idScalar() {
		return getLong(NX_EVENT_ID);
	}

	@Override
	public DataNode setEvent_id(IDataset event_id) {
		return setDataset(NX_EVENT_ID, event_id);
	}

	@Override
	public DataNode setEvent_idScalar(Long event_id) {
		return setField(NX_EVENT_ID, event_id);
	}

	@Override
	public IDataset getEvent_time_zero() {
		return getDataset(NX_EVENT_TIME_ZERO);
	}

	@Override
	public Long getEvent_time_zeroScalar() {
		return getLong(NX_EVENT_TIME_ZERO);
	}

	@Override
	public DataNode setEvent_time_zero(IDataset event_time_zero) {
		return setDataset(NX_EVENT_TIME_ZERO, event_time_zero);
	}

	@Override
	public DataNode setEvent_time_zeroScalar(Long event_time_zero) {
		return setField(NX_EVENT_TIME_ZERO, event_time_zero);
	}

	@Override
	public Date getEvent_time_zeroAttributeOffset() {
		return getAttrDate(NX_EVENT_TIME_ZERO, NX_EVENT_TIME_ZERO_ATTRIBUTE_OFFSET);
	}

	@Override
	public void setEvent_time_zeroAttributeOffset(Date offset) {
		setAttribute(NX_EVENT_TIME_ZERO, NX_EVENT_TIME_ZERO_ATTRIBUTE_OFFSET, offset);
	}

	@Override
	public IDataset getEvent_index() {
		return getDataset(NX_EVENT_INDEX);
	}

	@Override
	public Long getEvent_indexScalar() {
		return getLong(NX_EVENT_INDEX);
	}

	@Override
	public DataNode setEvent_index(IDataset event_index) {
		return setDataset(NX_EVENT_INDEX, event_index);
	}

	@Override
	public DataNode setEvent_indexScalar(Long event_index) {
		return setField(NX_EVENT_INDEX, event_index);
	}

	@Override
	public IDataset getPulse_height() {
		return getDataset(NX_PULSE_HEIGHT);
	}

	@Override
	public Double getPulse_heightScalar() {
		return getDouble(NX_PULSE_HEIGHT);
	}

	@Override
	public DataNode setPulse_height(IDataset pulse_height) {
		return setDataset(NX_PULSE_HEIGHT, pulse_height);
	}

	@Override
	public DataNode setPulse_heightScalar(Double pulse_height) {
		return setField(NX_PULSE_HEIGHT, pulse_height);
	}

	@Override
	public IDataset getCue_timestamp_zero() {
		return getDataset(NX_CUE_TIMESTAMP_ZERO);
	}

	@Override
	public Date getCue_timestamp_zeroScalar() {
		return getDate(NX_CUE_TIMESTAMP_ZERO);
	}

	@Override
	public DataNode setCue_timestamp_zero(IDataset cue_timestamp_zero) {
		return setDataset(NX_CUE_TIMESTAMP_ZERO, cue_timestamp_zero);
	}

	@Override
	public DataNode setCue_timestamp_zeroScalar(Date cue_timestamp_zero) {
		return setDate(NX_CUE_TIMESTAMP_ZERO, cue_timestamp_zero);
	}

	@Override
	public Date getCue_timestamp_zeroAttributeStart() {
		return getAttrDate(NX_CUE_TIMESTAMP_ZERO, NX_CUE_TIMESTAMP_ZERO_ATTRIBUTE_START);
	}

	@Override
	public void setCue_timestamp_zeroAttributeStart(Date start) {
		setAttribute(NX_CUE_TIMESTAMP_ZERO, NX_CUE_TIMESTAMP_ZERO_ATTRIBUTE_START, start);
	}

	@Override
	public IDataset getCue_index() {
		return getDataset(NX_CUE_INDEX);
	}

	@Override
	public Long getCue_indexScalar() {
		return getLong(NX_CUE_INDEX);
	}

	@Override
	public DataNode setCue_index(IDataset cue_index) {
		return setDataset(NX_CUE_INDEX, cue_index);
	}

	@Override
	public DataNode setCue_indexScalar(Long cue_index) {
		return setField(NX_CUE_INDEX, cue_index);
	}

}
