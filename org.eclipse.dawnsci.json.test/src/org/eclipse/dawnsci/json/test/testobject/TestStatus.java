/*
 * Copyright (c) 2012 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.dawnsci.json.test.testobject;

/**
 * States of jobs on the cluster.
 *
 * @author Matthew Gerring
 *
 */
public enum TestStatus { // TODO Should this be called QueueStatus or JobStatus to avoid confusion?

	SUBMITTED, QUEUED, RUNNING, REQUEST_PAUSE, PAUSED, REQUEST_RESUME, RESUMED, REQUEST_TERMINATE, TERMINATED, FAILED, COMPLETE, UNFINISHED, NONE;

	/**
	 *
	 * @return true if the run was taken from the queue and something was actually executed on it.
	 */
	public boolean isStarted() {
		return this!=SUBMITTED;
	}

	public boolean isFinal() {
		return this==TERMINATED || this==FAILED || this==COMPLETE || this==UNFINISHED || this==NONE;
	}

	public boolean isRunning() {
		return this==RUNNING || this==RESUMED;
	}

	public boolean isRequest() {
		return toString().startsWith("REQUEST_");
	}

	public boolean isPaused() {
		return this==REQUEST_PAUSE || this==PAUSED;
	}

	/**
	 * Being actively run, including pause.
	 */
	public boolean isActive() {
		return (isRunning() || isPaused()) && !isFinal();
	}

	public boolean isTerminated() {
		return this==REQUEST_TERMINATE || this==TERMINATED;
	}
}
