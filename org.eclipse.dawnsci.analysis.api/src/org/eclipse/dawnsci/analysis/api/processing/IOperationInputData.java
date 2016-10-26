package org.eclipse.dawnsci.analysis.api.processing;

import org.eclipse.dawnsci.analysis.api.processing.IOperation;
import org.eclipse.dawnsci.analysis.api.processing.OperationData;
import org.eclipse.dawnsci.analysis.api.processing.model.IOperationModel;
import org.eclipse.january.dataset.IDataset;

import com.sun.xml.internal.bind.v2.model.core.ID;

public interface IOperationInputData {

	public IDataset getInputData();
	public void setInputData(IDataset data);
	
	public IOperation<? extends IOperationModel, ? extends OperationData> getCurrentOperation();
	
}
