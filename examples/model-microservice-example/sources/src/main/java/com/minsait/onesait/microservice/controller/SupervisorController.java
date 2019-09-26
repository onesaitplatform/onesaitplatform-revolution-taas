package com.minsait.onesait.microservice.controller;

import org.springframework.http.ResponseEntity;

import com.minsait.onesait.microservice.model.ModelStatus;
import com.minsait.onesait.microservice.model.ModelTask;

public interface SupervisorController {
	
	public ResponseEntity<String> receiveStatus(String modelendpoint, String modelversion, ModelStatus modelStatus);
	
	public ResponseEntity<?> status(String modelendpoint, String modelversion);
	
	public ResponseEntity<String> receiveTask(String modelendpoint, String modelversion, ModelTask modeltask);
	
	public ResponseEntity<?> task(String modelendpoint, String modelversion, String workerid);


}
