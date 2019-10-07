
package com.minsait.onesait.platform.automl.vision.common;


import com.minsait.onesait.platform.automl.vision.infrastructure.GoogleCloudContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DependencyConfiguration {

    @Bean
    public GoogleCloudContext googleCloudContext() {
        return GoogleCloudContext.getTaasAutoMLContext();
    }

}
