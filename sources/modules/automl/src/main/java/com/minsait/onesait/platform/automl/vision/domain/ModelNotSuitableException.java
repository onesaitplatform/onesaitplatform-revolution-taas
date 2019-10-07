
package com.minsait.onesait.platform.automl.vision.domain;


public class ModelNotSuitableException extends Exception {

    public ModelNotSuitableException(String identifier, String task) {
        super("Model '" + identifier + "' not suitable for '" + task +"'!");
    }

}
