
package com.minsait.onesait.platform.automl.vision.domain;


public class ModelNotFoundException extends Exception {

    public ModelNotFoundException(String identifier) {
        super("Model '" + identifier + "' not found!");
    }

}
