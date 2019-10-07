
package com.minsait.onesait.platform.automl.vision.domain;


public interface ImageClassifier {

    String getIdentifier();
    Label predict(Picture picture);

}
