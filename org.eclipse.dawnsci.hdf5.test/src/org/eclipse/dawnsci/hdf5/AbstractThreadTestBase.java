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

package org.eclipse.dawnsci.hdf5;

/**
 * Class to extend, does not have any tests itself.
 */
public abstract class AbstractThreadTestBase {
	

	public void testInTestThread() throws Throwable{
		doTestOfDataSet(0);
		
		System.out.println("One thread passed.");
	}
	
	protected abstract void doTestOfDataSet(int index) throws Throwable;

	private Throwable exception;
	
	/**
	 * Test shows that nexus API cannot handle threads. 
	 * 
	 * @throws Exception
	 */
	public void testWithTenThreads() throws Throwable {
		
		testWithNThreads(10);
	}

	protected void testWithNThreads(int threadNumber) throws Throwable {
		
        exception = null;
		
        final boolean[] done    = new boolean[threadNumber];
        final Thread[]  threads = new Thread[threadNumber];
        for (int i = 0; i < threads.length; i++) {
        	final int index = i;
        	threads[i] =  new Thread(new Runnable() {
        		@Override
        		public void run() {
        			done[index] = false;
        			try {
        				doTestOfDataSet(index);
					} catch (Throwable e) {
						exception = e;
					} finally {
	        			done[index] = true;
					}
        		}
        	});
		}
        
        for (int i = 0; i < threads.length; i++) {
        	threads[i].start();
        }
        
        // Wait for them to do their thing.
        WHILE_LOOP: while(true) {
        	for (boolean d : done) {
				if (!d) {
					Thread.sleep(1000);
					continue WHILE_LOOP;
				}
        	}
        	break;
        }
        
        if (exception!=null) throw exception;

        
		System.out.println(threadNumber+" threads passed."); 	
	}


}
