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
package org.eclipse.dawnsci.hdf5.editor;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ContainerSelectionDialog;
import org.eclipse.ui.dialogs.WizardDataTransferPage;

public class H5ResourcePage extends WizardDataTransferPage {

	public static enum RESOURCE_CHOICE {
		PROJECT_AND_NAME, PROJECT_AND_EXTERNAL_FOLDER;
	}
	
	private Text   containerNameField;
	private Text   folderNameField;
	private Text   sequenceNameField;
	private IStructuredSelection selection;
	private RESOURCE_CHOICE choice;
	private String defaultName;

	protected H5ResourcePage(final String name, 
			                   IStructuredSelection selection, 
			                   RESOURCE_CHOICE choice,
			                   final String defaultName) {
		super(name);
		this.choice    = choice;
		this.selection = selection;
		this.defaultName=defaultName;
		setTitle("Choose folder to import");
		setDescription("Choose a folder to create a data link in the project.");
	}

	private String getContainerPath(IStructuredSelection selection) {
		if (selection == null) return null;
		final Object res = selection.getFirstElement();
		if (res instanceof IAdaptable) {
			final IContainer container = (IContainer)((IAdaptable)res).getAdapter(IContainer.class);
			return container.getFullPath().toPortableString();
		}
		return null;
	}

	@Override
	public void createControl(Composite parent) {
       
		initializeDialogUnits(parent);

        Composite composite = new Composite(parent, SWT.NULL);
        composite.setLayout(new GridLayout(3,false));

        createDestinationGroup(composite);
        if (choice == RESOURCE_CHOICE.PROJECT_AND_EXTERNAL_FOLDER) {
            createFolderChoiceGroup(composite);
        }
        if (choice == RESOURCE_CHOICE.PROJECT_AND_NAME) {
        	createNameChoiceGroup(composite);
        }
        setErrorMessage(null);	// should not initially have error message
        setPageComplete(false);
        
        setControl(composite);
        dialogChanged();
	}
	
    private void createNameChoiceGroup(Composite containerGroup) {
        // container label
        Label resourcesLabel = new Label(containerGroup, SWT.NONE);
        resourcesLabel.setText("Sequence Name ");
        
        this.sequenceNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
        sequenceNameField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        sequenceNameField.setText(defaultName);
        sequenceNameField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
        });
	}

	protected final void createDestinationGroup(Composite containerGroup) {

        // container label
        Label resourcesLabel = new Label(containerGroup, SWT.NONE);
        resourcesLabel.setText("Parent Folder ");
        
        // container name entry field
        this.containerNameField = new Text(containerGroup, SWT.SINGLE | SWT.BORDER);
        containerNameField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
        final String containerPath = getContainerPath(selection);
        if (containerPath!=null) containerNameField.setText(containerPath);
        containerNameField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
        });
 
        // container browse button
        Button containerBrowseButton = new Button(containerGroup, SWT.PUSH);
        containerBrowseButton.setText("Browse...");
        containerBrowseButton.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false));
        containerBrowseButton.addListener(SWT.Selection, new Listener() {			
			@Override
			public void handleEvent(Event event) {
				final IPath path = queryForContainer(getSpecifiedContainer(),"Choose folder to import to", "Choose Folder");
				containerNameField.setText(path.toString());
				dialogChanged();
			}
        });
        setButtonLayoutData(containerBrowseButton);
    }
    
    /**
     * Queries the user to supply a container resource.
     *
     * @return the path to an existing or new container, or <code>null</code> if the
     *    user cancelled the dialog
     */
    protected IPath queryForContainer(IContainer initialSelection, String msg,
            String title) {
        ContainerSelectionDialog dialog = new ContainerSelectionDialog(
                getControl().getShell(), initialSelection,
                false, msg);
        if (title != null) {
			dialog.setTitle(title);
		}
        dialog.showClosedProjects(false);
        dialog.open();
        Object[] result = dialog.getResult();
        if (result != null && result.length == 1) {
            return (IPath) result[0];
        }
        return null;
    }
    /**
     * Returns the container resource specified in the container name entry field,
     * or <code>null</code> if such a container does not exist in the workbench.
     *
     * @return the container resource specified in the container name entry field,
     *   or <code>null</code>
     */
    protected IContainer getSpecifiedContainer() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IPath path = getContainerFullPath();
        if (workspace.getRoot().exists(path)){
        	IResource resource = workspace.getRoot().findMember(path);
        	if(resource.getType() == IResource.FILE) {
				return null;
			}
        	return (IContainer) resource;
        	
        }
            

        return null;
    }
    /**
     * Returns the path of the container resource specified in the container
     * name entry field, or <code>null</code> if no name has been typed in.
     * <p>
     * The container specified by the full path might not exist and would need to
     * be created.
     * </p>
     *
     * @return the full path of the container resource specified in
     *   the container name entry field, or <code>null</code>
     */
    protected IPath getContainerFullPath() {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();

        //make the path absolute to allow for optional leading slash
        IPath testPath = getResourcePath();

        if (testPath.equals(workspace.getRoot().getFullPath())) {
			return testPath;
		}

        IStatus result = workspace.validatePath(testPath.toString(),
                IResource.PROJECT | IResource.FOLDER | IResource.ROOT);
        if (result.isOK()) {
            return testPath;
        }

        return null;
    }
    protected IPath getResourcePath() {
        return getPathFromText(this.containerNameField);
    }
    
	private void createFolderChoiceGroup(Composite folderChoiceGroup) {

        Label groupLabel = new Label(folderChoiceGroup, SWT.NONE);
        groupLabel.setText("External Folder");
        groupLabel.setFont(folderChoiceGroup.getFont());

        // source name entry field
        this.folderNameField = new Text(folderChoiceGroup, SWT.BORDER);
        GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL
                | GridData.GRAB_HORIZONTAL);
        data.widthHint = SIZING_TEXT_FIELD_WIDTH;
        folderNameField.setLayoutData(data);
        folderNameField.setFont(folderChoiceGroup.getFont());
        folderNameField.addModifyListener(new ModifyListener() {		
			@Override
			public void modifyText(ModifyEvent e) {
				dialogChanged();
			}
		});
 
        // source browse button
        Button folderBrowseButton = new Button(folderChoiceGroup, SWT.PUSH);
        folderBrowseButton.setText("Browse...");
        folderBrowseButton.addListener(SWT.Selection, new Listener() {			
			@Override
			public void handleEvent(Event event) {
				final DirectoryDialog dialog = new DirectoryDialog(getControl().getShell());
				if (folderNameField.getText()!=null) dialog.setFilterPath(folderNameField.getText());
				folderNameField.setText(dialog.open());
				dialogChanged();
			}
		});
        folderBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
        folderBrowseButton.setFont(folderChoiceGroup.getFont());
        setButtonLayoutData(folderBrowseButton);
	}


	/**
	 * Ensures that both text fields are set.
	 */

	private void dialogChanged() {
		
		final IContainer container = getProjectContainer();
		if (container==null) {
			updateStatus("Please select a valid parent project.");
			return;
		}
			
		if (choice == RESOURCE_CHOICE.PROJECT_AND_EXTERNAL_FOLDER) {
			final File dir = getExternalFolder();
			if (dir==null) {
				updateStatus("Please select a valid import folder.");
				return;
			}
			if (!dir.exists()) {
				updateStatus("Please select an existing folder.");
				return;
			}
			if (!dir.isDirectory()) {
				updateStatus("Please select directory not a file.");
				return;
			}
			if (container.getFolder(new Path(container.getFullPath().toPortableString()+"/"+dir.getName())).exists()) {
				updateStatus("Please select a folder not existing or delete the existing folder with the name '"+dir.getName()+"'");
				return;
			}
		}
		
		if (choice == RESOURCE_CHOICE.PROJECT_AND_NAME) {
			final String seqName = getSequenceName();
			if (seqName==null) {
				updateStatus("Please set a name for the resource to create.");
				return;
			}
			if (!seqName.endsWith(getFileExtension(defaultName))) {
				updateStatus("The sequence name must end with '"+getFileExtension(defaultName)+"'");
				return;
			}
			if (container.findMember(seqName)!=null) {
				updateStatus("The file '"+seqName+"' already exists.");
				return;
			}
		}
		
		updateStatus(null);
	}
	private static String getFileExtension(String fileName) {
		int posExt = fileName.lastIndexOf(".");
		// No File Extension
		return posExt == -1 ? "" : fileName.substring(posExt + 1);
	}
	
	public String getSequenceName() {
		final String name = sequenceNameField.getText();
		if (name==null || "".equals(name)) return null;
		return name;
	}

	public File getExternalFolder() {
		final String path = folderNameField.getText();
		if (path==null || "".equals(path)) return null;
		return new File(path);
	}

	public IContainer getProjectContainer() {
		try {
	        IWorkspace workspace = ResourcesPlugin.getWorkspace();
	
	        //make the path absolute to allow for optional leading slash
	        IPath path = getResourcePath();
	        
	        final IResource container = workspace.getRoot().findMember(path);
	        
	        return (IContainer)container;
	        
		} catch (Throwable ne) {
			return null;
		}
	}

	private void updateStatus(String message) {
		setErrorMessage(message);
		setPageComplete(message == null);
	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean allowNewContainerName() {
		return false;
	}

}
