package com.student.collabration.StudentCollabration.configuration;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class FreeMarkerConfig {

    @Bean
    public Configuration freemarkerConfig() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_31);
        configuration.setClassLoaderForTemplateLoading(getClass().getClassLoader(), "/templates");
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        return configuration;
    }
}

