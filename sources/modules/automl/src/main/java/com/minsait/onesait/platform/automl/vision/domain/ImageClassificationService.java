
package com.minsait.onesait.platform.automl.vision.domain;


public interface ImageClassificationService {

    public Label classify(String modelIdentifier, Picture picture) throws ModelNotFoundException, ModelNotSuitableException;

}
