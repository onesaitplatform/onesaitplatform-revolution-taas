
package com.minsait.onesait.platform.automl.vision.domain;


public interface AutoMLModel {

    String getIdentifier();
    AutoMLEnvironment getEnvironment();
    AutoMLPlatform getPlatform();

}
