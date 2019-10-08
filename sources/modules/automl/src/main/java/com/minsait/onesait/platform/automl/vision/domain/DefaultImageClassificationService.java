
package com.minsait.onesait.platform.automl.vision.domain;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class DefaultImageClassificationService implements ImageClassificationService {

    //region Initialization

    @Autowired
    public DefaultImageClassificationService(ImageClassifierRepository repository) {
        this.repository = repository;
    }

    //endregion

    //region Public Methods

    @Override
    public Collection<AutoMLModel> getModels() {
        return this.repository.getAll();
    }

    public Label classify(String modelIdentifier, Picture picture) throws ModelNotFoundException, ModelNotSuitableException {
        ImageClassifier classifier = this.repository.getByIdentifier(modelIdentifier);
        return classifier.predict(picture);
    }

    //endregion

    //region Private Properties

    private final ImageClassifierRepository repository;

    //endregion

}
