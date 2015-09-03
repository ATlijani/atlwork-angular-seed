package com.atlwork.angularseed;

import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.base.Joiner;

@SpringBootApplication
@EnableJpaRepositories("com.atlwork.angularseed.app.repository")
@EnableJpaAuditing(auditorAwareRef = "userNameAuditorAware")
@EnableTransactionManagement
public class MainApplication {

    public static void main(String[] args) throws UnknownHostException {

	SpringApplication app = new SpringApplication(MainApplication.class);
	SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);

	addDefaultProfile(app, source);
	addLiquibaseScanPackages();
    }

    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {

	if (!source.containsProperty("spring.profiles.active") && !System.getenv().containsKey("SPRING_PROFILES_ACTIVE")) {
	    app.setAdditionalProfiles(ApplicationConstants.FICHE_PROJET_DEVELOPMENT_PROFILE);
	}
    }

    private static void addLiquibaseScanPackages() {

	System.setProperty(
		"liquibase.scan.packages",
		Joiner.on(",").join("liquibase.change", "liquibase.database", "liquibase.parser", "liquibase.precondition", "liquibase.datatype",
			"liquibase.serializer", "liquibase.sqlgenerator", "liquibase.executor", "liquibase.snapshot", "liquibase.logging", "liquibase.diff",
			"liquibase.structure", "liquibase.structurecompare", "liquibase.lockservice", "liquibase.ext", "liquibase.changelog"));
    }
}
