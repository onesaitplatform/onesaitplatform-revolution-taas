
package com.minsait.onesait.platform.automl.vision.domain;


public class Label {

    // region Getters & Setters

    public Label(String identifier, String name, float score) {
        this.identifier = identifier;
        this.name = name;
        this.score = score;
    }

    //endregion

    // region Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    //endregion

    // region Private Properties

    private String identifier;
    private String name;
    private float score;

    //endregion

}
