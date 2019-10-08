
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.minsait.onesait.platform.automl.vision.domain.*;
import org.tensorflow.Graph;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;


public class GoogleAutoMLLocalTensorflowImageClassifier extends GoogleAutoMLLocalModel implements GoogleAutoMLImageClassifier {

    //region Initialization

    public GoogleAutoMLLocalTensorflowImageClassifier(String fileName, String identifier, LabelMapper mapper) {
        super(fileName);
        this.identifier = identifier;
        this.mapper = mapper;
        this.setEnvironment(AutoMLEnvironment.LOCAL);
    }

    //endregion

    //region GoogleAutoMLImageClassifier Implementation

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Label predict(Picture picture) {
        String dir = null;
        try {
            dir = this.getSavedModelDir().getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        SavedModelBundle savedModelBundle = SavedModelBundle.load(dir, "serve");
        try (Graph g = savedModelBundle.graph()) {
            try (Session session = savedModelBundle.session()) {
                ByteBuffer buffer = ByteBuffer.wrap(picture.getImage());
                long[] shape = new long[] { (long) picture.getImage().length/4 };
                Tensor inputTensor = Tensor.create(Float.class, shape, buffer);
                String output = predict(session, inputTensor);
                return this.mapResponse(output);
            }
        }
    }

    //endregion

    //region Private Properties

    private final String identifier;
    private final LabelMapper mapper;

    //endregion

    //region Private Methods

    private File getSavedModelDir() throws IOException, URISyntaxException {
        ClassLoader classLoader = (GoogleAutoMLLocalTensorflowImageClassifier.this.getClass()).getClassLoader();
        String protoPath = ("static/" + this.getFileName() + "/saved_model.pb");
        URL protoResource = classLoader.getResource(protoPath);
        if (protoResource == null) {
            throw new NoSuchFileException(protoPath);
        }
        File protoFile = (Paths.get(protoResource.toURI())).toFile();
        return protoFile.getParentFile();
    }

    private String predict(Session session, Tensor inputTensor) {
        try {
            Tensor result = session.runner()
                    .feed("Placeholder", inputTensor)
                    .fetch("Placeholder_1").run().get(0);
            return new String(result.bytesValue(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Label mapResponse(String output) {
        String identifier = output;
        String name = this.mapper.getNameFrom(identifier);
        float score = -1.0f;
        Label label = new Label(identifier, name, score);
        return label;
    }

    //endregion

}
