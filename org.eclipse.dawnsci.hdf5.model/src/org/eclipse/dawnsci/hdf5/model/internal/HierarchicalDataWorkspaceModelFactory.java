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
package org.eclipse.dawnsci.hdf5.model.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.dawnsci.hdf5.HierarchicalDataFactory;
import org.eclipse.dawnsci.hdf5.IHierarchicalDataFile;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataFileModel;
import org.eclipse.dawnsci.hdf5.model.IHierarchicalDataModel;

public class HierarchicalDataWorkspaceModelFactory {

	/**
	 * Returns the IHierarchicalDataModel. If one does not exists, this method
	 * will create one and register a workspace listener.
	 *
	 * @return
	 */
	public static IHierarchicalDataModel getHierarchicalDataModel() {
		IHierarchicalDataModelGetFileModel getModel = new GetFileModelFactory();
		final HierarchicalDataModel model = new HierarchicalDataModel(getModel);

		IResourceChangeListener listener = new IResourceChangeListener() {

			@Override
			public void resourceChanged(IResourceChangeEvent event) {
				IResourceDelta delta = event.getDelta();
				if (delta != null) {
					try {
						delta.accept(new DeltaVisitor(model));
					} catch (CoreException e) {
						// our visitor does not throw CoreException
					}
				}
			}
		};
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		workspace.addResourceChangeListener(listener);

		return model;
	}

	/**
	 * Get a IHierarchicalDataFile when the File Model needs it.
	 */
	private static final class GetFileReaderFactory implements
			IHierarchicalDataFileGetReader {
		private final String fullPath;

		public GetFileReaderFactory(String fullPath) {
			this.fullPath = fullPath;
		}

		@Override
		public IHierarchicalDataFile getReader() throws Exception {
			return HierarchicalDataFactory.getReader(fullPath);
		}
	}

	/**
	 * Create a IHierarchicalDataFileModel when the workspace model does not
	 * have one cached already.
	 */
	private static final class GetFileModelFactory implements
			IHierarchicalDataModelGetFileModel {

		@Override
		public IHierarchicalDataFileModel createFileModel(final String fullPath) {
			IHierarchicalDataFileGetReader getReader = new GetFileReaderFactory(
					fullPath);
			HierarchicalDataFileModel hierarchicalDataFileModel = new HierarchicalDataFileModel(
					getReader);
			return hierarchicalDataFileModel;

		}
	}

	/**
	 * Delta visitor for IHierarchicalDataModel to respond to changes in
	 * workspace model.
	 */
	public static final class DeltaVisitor implements IResourceDeltaVisitor {
		private HierarchicalDataModel model;

		public DeltaVisitor(HierarchicalDataModel model) {
			this.model = model;
		}

		/*
		 * Invalidate the cache for the resource if it has been added/removed or
		 * had it's content changed. (non-Javadoc)
		 *
		 * @see
		 * org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse
		 * .core.resources.IResourceDelta)
		 */
		public boolean visit(IResourceDelta delta) {
			boolean clear = false;
			IResource resource = delta.getResource();

			if (resource instanceof IFile) {
				switch (delta.getKind()) {
				case IResourceDelta.ADDED:
				case IResourceDelta.REMOVED:
					clear = true;
					break;
				case IResourceDelta.CHANGED:
					int flags = delta.getFlags();
					if ((flags & (IResourceDelta.CONTENT | IResourceDelta.LOCAL_CHANGED)) != 0) {
						clear = true;
					}
				default:
					break;
				}
				if (clear) {
					model.clearFileCache((IFile) resource);
				}
			}
			return true;
		}
	}

}
