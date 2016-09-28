/*-
 * Copyright 2016 Diamond Light Source Ltd.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */

package org.eclipse.dawnsci.analysis.api.worker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * A worker that works through jobs given to it
 */
public class Worker {

	private List<Long> time;

	private BlockingDeque<Runnable> queue;
	private Thread thread;
	private boolean finished;
	private Throwable throwable;
	
	private long checkingPeriod = 100; // period between checking finished flag in microseconds

	private final boolean recordTimes;

	/**
	 * Start a worker thread without any timing of jobs
	 * @param name
	 */
	public Worker(final String name) {
		this(name, false);
	}

	/**
	 * Start a worker thread
	 * @param name
	 * @param timeJobs if true, then time each job
	 */
	public Worker(final String name, boolean timeJobs) {
		recordTimes = timeJobs;
		queue = new LinkedBlockingDeque<>();
		time = new ArrayList<>();
		finished = false;
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (!finished) {
					try {
						Runnable job = queue.poll(checkingPeriod, TimeUnit.MICROSECONDS);
						if (job != null) {
							long now = recordTimes ? -System.nanoTime() : 0;
							try {
								job.run();
							} catch (Throwable t) {
								throwable = t;
								finished = true;
							}
							if (recordTimes) {
								now += System.nanoTime();
								time.add(now/1000l);
							}
						}
					} catch (InterruptedException e) {
						throwable = e;
						finished = true;
					}
				}
//				System.err.println("Finished thread: " + name);
			}
		});
		thread.setName(name);
		thread.start();
	}

	/**
	 * Add job to worker's queue
	 * @param job
	 */
	public void addJob(Runnable job) {
		if (finished) {
			throw new UnsupportedOperationException("Worker is finished and will not accept any more jobs");
		}
		queue.add(job);
	}

	/**
	 * Flush all jobs and block till done
	 */
	public void flush() {
		final long wait = (checkingPeriod*100)/1000;
		while (queue.size() != 0) {
			try {
				thread.join(wait);
			} catch (InterruptedException e) {
			}
		}
	}

	/**
	 * @param milliseconds to wait before finishing
	 */
	public void finish(long milliseconds) {
		if (!finished) {
			if (queue.size() != 0) {
//				System.err.printf("%s has %d left\n", thread.toString(), queue.size());
				try {
					thread.join(milliseconds);
				} catch (InterruptedException e) {
				}
			}
			finished = true;
		}
		queue.clear();
	}

	/**
	 * @return true if worker has finished (check throwable to see if it ended prematurely)
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * @return times in microseconds if asked to time each job, otherwise empty
	 */
	public List<Long> getTimes() {
		return Collections.unmodifiableList(time);
	}

	/**
	 * @return throwable if worker finished early, otherwise null
	 */
	public Throwable getThrowable() {
		return throwable;
	}
}
