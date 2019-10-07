
package com.minsait.onesait.platform.automl.vision.domain;


import com.minsait.onesait.platform.automl.vision.domain.AutoMLEnvironment;
import com.minsait.onesait.platform.automl.vision.domain.AutoMLModel;
import com.minsait.onesait.platform.automl.vision.domain.AutoMLPlatform;


public class DefaultAutoMLModel implements AutoMLModel {

    //region Getters & Setters

    public AutoMLEnvironment getEnvironment() {
        return environment;
    }

    public void setEnvironment(AutoMLEnvironment environment) {
        this.environment = environment;
    }

    public AutoMLPlatform getPlatform() {
        return platform;
    }

    public void setPlatform(AutoMLPlatform platform) {
        this.platform = platform;
    }

    //endregion

    //region Getters & Setters

    private AutoMLEnvironment environment;
    private AutoMLPlatform platform;

    //endregion

}
