
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.minsait.onesait.platform.automl.vision.domain.AutoMLModel;
import com.minsait.onesait.platform.automl.vision.domain.LabelMapper;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Repository
public class StaticImageClassifierRepository extends DefaultImageClassifierRepository {

    //region Initialization

    public StaticImageClassifierRepository(GoogleCloudContext googleCloudContext) {
        super(googleCloudContext);
        this.modelMap = new HashMap<>();
        this.modelMap.put(skinCancerModelID, this.getSkinCancerModel(googleCloudContext));
        this.modelMap.put(skinCancerEdgeModelID, this.getSkinCancerEdgeModel(googleCloudContext));
        this.modelMap.put(skinCancerLocalModelID, this.getSkinCancerLocalModel());
    }

    //endregion

    //region DefaultImageClassifierRepository Override

    @Override
    public Collection<AutoMLModel> getAll() {
        return this.modelMap.values();
    }

    @Override
    protected AutoMLModel getModel(String identifier) {
        return this.modelMap.get(identifier);
    }

    //endregion

    //region Private Properties

    private static final String skinCancerModelID = "14c6918e-b033-47ce-ac18-3b82f34dcc7f";
    private static final String skinCancerEdgeModelID = "5d2a8731-4801-4932-a604-cc0086e5ba2f";
    private static final String skinCancerLocalModelID = "6c4c59ce-446a-4586-a696-c67ffa33a985";

    private static final String project = "taas-automl";
    private static final String location = "us-central1";

    private static final String skinCancerLocalModelName = "skin_cancer_onesait_edge_v1";

    private Map<String, AutoMLModel> modelMap;

    //endregion

    //region Private Methods

    private GoogleAutoMLModel getSkinCancerModel(GoogleCloudContext googleCloudContext) {
        String model = "ICN2057748675154557771";
        LabelMapper mapper = new SkinCancerLabelMapper();
        return new GoogleAutoMLRemoteImageClassifier(googleCloudContext,
                skinCancerModelID, project, location, model, mapper);
    }

    private GoogleAutoMLModel getSkinCancerEdgeModel(GoogleCloudContext googleCloudContext) {
        String model = "ICN7810036557836700582";
        LabelMapper mapper = new SkinCancerLabelMapper();
        return new GoogleAutoMLRemoteImageClassifier(googleCloudContext,
                skinCancerEdgeModelID, project, location, model, mapper);
    }

    private GoogleAutoMLLocalModel getSkinCancerLocalModel() {
        LabelMapper mapper = new SkinCancerLabelMapper();
        return new GoogleAutoMLLocalTensorflowImageClassifier(skinCancerLocalModelName, skinCancerLocalModelID, mapper);
    }

    //endregion

}
