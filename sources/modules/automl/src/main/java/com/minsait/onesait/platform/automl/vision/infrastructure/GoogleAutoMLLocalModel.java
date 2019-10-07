
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.cloud.automl.v1beta1.ModelName;
import com.minsait.onesait.platform.automl.vision.domain.AutoMLPlatform;
import com.minsait.onesait.platform.automl.vision.domain.DefaultAutoMLModel;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;


public class GoogleAutoMLLocalModel extends DefaultAutoMLModel {

    //region Initialization

    public GoogleAutoMLLocalModel(String fileName) {
        super();
        this.fileName = fileName;
        this.setPlatform(AutoMLPlatform.GOOGLE);
    }

    //endregion

    //region Public Methods

    @JsonIgnore
    public File getFile() {
        try {
            ClassPathResource resource = new ClassPathResource(this.getFileName());
            return resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //endregion

    //region Getters & Setters

    public String getFileName() {
        return fileName;
    }

    //endregion

    //region Private Properties

    private final String fileName;

    //endregion

}
