
package com.minsait.onesait.platform.automl.vision.domain;


public interface ImageClassifierRepository {

    ImageClassifier getByIdentifier(String identifier) throws ModelNotFoundException, ModelNotSuitableException;

}
