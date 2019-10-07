
package com.minsait.onesait.platform.automl.vision.domain;


import java.util.Collection;


public interface ImageClassifierRepository {

    Collection<AutoMLModel> getAll();
    ImageClassifier getByIdentifier(String identifier) throws ModelNotFoundException, ModelNotSuitableException;

}
