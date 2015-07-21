/*-
 * Copyright 2015 Diamond Light Source Ltd.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.eclipse.dawnsci.analysis.api.tree;

/**
 * Interface to allow UI interactions with items in a tree such as a node or attribute
 */
public interface TreeAdaptable {

	/**
	 * @return full path to file
	 */
	public abstract String getFile();

	/**
	 * @return full path to node or node attribute
	 */
	public abstract String getNode();

	/**
	 * @return node link (can be null)
	 */
	public abstract NodeLink getNodeLink();

	/**
	 * @return attribute (can be null)
	 */
	public abstract Attribute getAttribute();
}