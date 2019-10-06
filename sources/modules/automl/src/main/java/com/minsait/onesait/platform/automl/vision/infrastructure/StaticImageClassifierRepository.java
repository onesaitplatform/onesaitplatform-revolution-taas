
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.minsait.onesait.platform.automl.vision.domain.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;


@Repository
public class StaticImageClassifierRepository extends DefaultImageClassifierRepository {

    //region Initialization

    public StaticImageClassifierRepository(GoogleCloudContext googleCloudContext) {
        super(googleCloudContext);
        this.modelMap = new HashMap<>();
        this.modelMap.put(skinCancerModelID, this.getSkinCancerModel(googleCloudContext));
    }

    //endregion

    //region DefaultImageClassifierRepository Override

    @Override
    protected AutoMLModel getModel(String identifier) {
        return this.modelMap.get(identifier);
    }

    //endregion

    //region Private Properties

    private static final String skinCancerModelID = "14c6918e-b033-47ce-ac18-3b82f34dcc7f";

    private Map<String, AutoMLModel> modelMap;

    //endregion

    //region Private Methods

    private GoogleAutoMLModel getSkinCancerModel(GoogleCloudContext googleCloudContext) {
        String project = "taas-automl";
        String location = "us-central1";
        String model = "ICN2057748675154557771";
        LabelMapper mapper = new SkinCancerLabelMapper();
        return new GoogleAutoMLRemoteImageClassifier(googleCloudContext,
                skinCancerModelID, project, location, model, mapper);
    }

    //endregion

}
