
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.minsait.onesait.platform.automl.vision.domain.LabelMapper;


public class SkinCancerLabelMapper implements LabelMapper {

    public String getNameFrom(String identifier) {
        switch (identifier) {
            case "akiec":
                return "Actinic keratoses and intraepithelial carcinoma";
            case "bcc":
                return "Basal cell carcinoma";
            case "bkl":
                return "Benign keratosis-like lesion";
            case "df":
                return "Dermatofibroma";
            case "mel":
                return "Melanoma";
            case "nv":
                return "Melanocytic nevi";
            case "vasc":
                return "Vascular lesion";
            default:
                return "Unrecognized lesion";
        }
    }

}
