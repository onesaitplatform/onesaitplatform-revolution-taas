
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.automl.v1beta1.ModelName;
import com.minsait.onesait.platform.automl.vision.domain.AutoMLPlatform;
import com.minsait.onesait.platform.automl.vision.domain.DefaultAutoMLModel;


public class GoogleAutoMLModel extends DefaultAutoMLModel {

    //region Initialization

    public GoogleAutoMLModel(String project, String location, String model) {
        super();
        this.project = project;
        this.location = location;
        this.model = model;
        this.setPlatform(AutoMLPlatform.GOOGLE);
    }

    //endregion

    //region Public Methods

    @JsonIgnore
    public ModelName getModelName() {
        return ModelName.of(this.getProject(), this.getLocation(), this.getModel());
    }

    //endregion

    //region Getters & Setters

    public String getProject() {
        return project;
    }

    public String getLocation() {
        return location;
    }

    public String getModel() {
        return model;
    }

    //endregion

    //region Private Properties

    private final String project;
    private final String location;
    private final String model;

    //endregion

}
