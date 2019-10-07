
package com.minsait.onesait.platform.automl.vision.domain;


import java.util.Collection;


public interface ImageClassificationService {

    Collection<AutoMLModel> getModels();
    Label classify(String modelIdentifier, Picture picture) throws ModelNotFoundException, ModelNotSuitableException;

}
