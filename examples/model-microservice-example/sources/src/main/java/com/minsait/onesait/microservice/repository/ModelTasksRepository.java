package com.minsait.onesait.microservice.repository;

import com.minsait.onesait.microservice.model.ModelTask;

public interface ModelTasksRepository {
	

	boolean isThereModelTask(String modelendpoint, String modelversion);

	public ModelTask getUniqueModelTask(String modelendpoint, String modelversion);
	public boolean addUniqueModelTask(String modelendpoint, String modelversion, ModelTask modeltask);
	
	public ModelTask getWorkerModelTask(String modelendpoint, String modelversion, String workerid);
	public boolean addWorkersModelTask(String modelendpoint, String modelversion, String workerid, ModelTask modeltask);

}
