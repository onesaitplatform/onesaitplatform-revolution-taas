
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.Credentials;
import com.google.cloud.automl.v1beta1.*;
import com.google.protobuf.ByteString;
import com.minsait.onesait.platform.automl.vision.domain.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class GoogleAutoMLRemoteImageClassifier extends GoogleAutoMLModel implements GoogleAutoMLImageClassifier {

    //region Initialization

    public GoogleAutoMLRemoteImageClassifier(GoogleCloudContext context, String identifier, String project,
                                             String location, String model, LabelMapper mapper) {
        super(project, location, model);
        this.context = context;
        this.identifier = identifier;
        this.mapper = mapper;
        this.setEnvironment(AutoMLEnvironment.REMOTE);
    }

    //endregion

    //region GoogleAutoMLImageClassifier Implementation

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Label predict(Picture picture) {
        com.google.cloud.automl.v1beta1.Image theImage;
        try (PredictionServiceClient predictionServiceClient = this.getClient()) {
            ByteString bytes = ByteString.copyFrom(picture.getImage());
            theImage = com.google.cloud.automl.v1beta1.Image.newBuilder().setImageBytes(bytes).build();
            ModelName name = this.getModelName();
            ExamplePayload payload = ExamplePayload.newBuilder().setImage(theImage).build();
            Map<String, String> params = new HashMap<>();
            PredictResponse response = predictionServiceClient.predict(name, payload, params);
            return this.mapResponse(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //endregion

    //region Private Properties

    private final GoogleCloudContext context;
    private final String identifier;
    private final LabelMapper mapper;

    //endregion

    //region Private Methods

    private PredictionServiceClient getClient() throws IOException {
        Credentials credentials = this.context.getCredentials();
        PredictionServiceSettings settings =
                PredictionServiceSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                        .build();
        return PredictionServiceClient.create(settings);
    }

    private Label mapResponse(PredictResponse response) {
        if (response.getPayloadCount() == 0) {
            return null;
        }
        AnnotationPayload annotation = response.getPayload(0);
        String identifier = annotation.getDisplayName();
        String name = this.mapper.getNameFrom(identifier);
        float score = annotation.getClassification().getScore();
        Label label = new Label(identifier, name, score);
        return label;
    }

    //endregion

}
