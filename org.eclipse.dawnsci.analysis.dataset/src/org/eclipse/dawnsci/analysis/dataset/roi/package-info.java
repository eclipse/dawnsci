/**
 * This package contains the regions of interest and some mathematics which go with them.
 * 
 * Static mathematical methods *rules*.
 * - Golden rules 
 * 1. Throw exceptions, do not catch or deal with them. They are API.
 * 2. Pass in an IMonitor and ensure this is passed down to any slicing you do. It allows the user of your method to cancel during running.
 * 
 * - Silver rules
 * 3. Avoid long argument lists, use beans for holding data instead. This makes it less work when the API changes later, which unless you are a godlike programmer, it will.
 * 
 */
package org.eclipse.dawnsci.analysis.dataset.roi;

