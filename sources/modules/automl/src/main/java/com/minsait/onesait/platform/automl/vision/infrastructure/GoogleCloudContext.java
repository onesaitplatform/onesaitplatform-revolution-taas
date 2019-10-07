
package com.minsait.onesait.platform.automl.vision.infrastructure;


import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.core.io.ClassPathResource;

import java.io.*;


public class GoogleCloudContext {

    //region Static Methods

    public static GoogleCloudContext getTaasAutoMLContext() {
        Credentials credentials = null;
        try {
            InputStream input = getCredentiasFileStream();
            credentials = ServiceAccountCredentials.fromStream(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GoogleCloudContext(credentials);
    }

    //endregion

    //region Initialization

    public GoogleCloudContext(Credentials credentials) {
        this.credentials = credentials;
    }

    //endregion

    //region Getters & Setters

    public Credentials getCredentials() {
        return credentials;
    }

    //endregion

    //region Private Properties

    private final Credentials credentials;

    //endregion

    //region Private Static Methods

    private static InputStream getCredentiasFileStream() throws IOException {
        return new ClassPathResource("static/taas-automl-aee40599966c.json").getInputStream();
    }

    //endregion

}
