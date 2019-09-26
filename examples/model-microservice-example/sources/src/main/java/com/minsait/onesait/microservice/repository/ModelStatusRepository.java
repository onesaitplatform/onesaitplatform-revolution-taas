package com.minsait.onesait.microservice.repository;

import java.util.ArrayList;

import com.minsait.onesait.microservice.model.ModelStatus;

public interface ModelStatusRepository {
	
	public ModelStatus getUniqueModelStatus(String modelendpoint, String modelversion);
	
	public ModelStatus addModelStatus(String modelendpoint, String modelversion, ModelStatus modelStatus);

	boolean isModelTraining(String modelendpoint, String modelversion);

	ArrayList<ModelStatus> getModelWorkersStatus(String modelendpoint, String modelversion);

	ArrayList<String> getModelWorkersIds(String modelendpoint, String modelversion);

}
