package com.nulijiushimeili.librarianwebui.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class PropertiesGetter {

    static {
        new PropertiesGetter();
    }

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${project.version}")
    private String projectVersion;


}
