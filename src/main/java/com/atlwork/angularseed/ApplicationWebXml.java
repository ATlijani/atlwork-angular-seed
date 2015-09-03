package com.atlwork.angularseed;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

public class ApplicationWebXml extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {

	return applicationBuilder.profiles(addDefaultProfile()).showBanner(false).sources(MainApplication.class);
    }

    private String addDefaultProfile() {

	String profile = System.getProperty("spring.profiles.active");
	return profile != null ? profile : ApplicationConstants.FICHE_PROJET_DEVELOPMENT_PROFILE;
    }
}
