
package com.minsait.onesait.platform.automl.vision.domain;


public class Picture {

    //region Initialization

    public Picture(byte[] image) {
        this.image = image;
    }

    //endregion

    //region Getters & Setters

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    //endregion

    //region Private Properties

    private byte[] image;

    //endregion

}
