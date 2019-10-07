
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.minsait.onesait.platform.automl.vision.domain.*;

import java.util.ArrayList;
import java.util.Collection;


public class DefaultImageClassifierRepository implements ImageClassifierRepository {

    //region Initialization

    public DefaultImageClassifierRepository(GoogleCloudContext googleCloudContext) {
        this.googleCloudContext = googleCloudContext;
    }

    //endregion

    //region ImageClassifierRepository Implementation

    @Override
    public Collection<AutoMLModel> getAll() {
        return new ArrayList<>();
    }

    @Override
    public ImageClassifier getByIdentifier(String identifier) throws ModelNotFoundException, ModelNotSuitableException {
        AutoMLModel model = this.getModel(identifier);
        if (model == null) {
            throw new ModelNotFoundException(identifier);
        }
        if (model instanceof ImageClassifier) {
            return (ImageClassifier) model;
        }
        throw new ModelNotSuitableException(identifier, "image classification");
    }

    //endregion

    //region Private Properties

    private final GoogleCloudContext googleCloudContext;

    //endregion

    //region Protected Methods

    protected AutoMLModel getModel(String identifier) {
        return null;
    }

    //endregion

}
