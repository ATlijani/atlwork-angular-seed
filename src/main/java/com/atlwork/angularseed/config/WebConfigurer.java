package com.atlwork.angularseed.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.atlwork.angularseed.ApplicationConstants;
import com.atlwork.angularseed.config.filter.CachingHttpHeadersFilter;
import com.atlwork.angularseed.config.filter.StaticResourcesProductionFilter;

@Configuration
public class WebConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

    @Autowired
    private Environment env;

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {

	EnumSet<DispatcherType> disps = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD, DispatcherType.ASYNC);

	if (env.acceptsProfiles(ApplicationConstants.FICHE_PROJET_PRODUCTION_PROFILE)) {
	    initCachingHttpHeadersFilter(servletContext, disps);
	    initStaticResourcesProductionFilter(servletContext, disps);
	}
	if (env.acceptsProfiles(ApplicationConstants.FICHE_PROJET_DEVELOPMENT_PROFILE)) {
	    initH2Console(servletContext);
	}
    }

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {

	MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
	mappings.add("html", "text/html;charset=utf-8");
	container.setMimeMappings(mappings);
    }

    private void initStaticResourcesProductionFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {

	FilterRegistration.Dynamic staticResourcesProductionFilter = servletContext.addFilter("staticResourcesProductionFilter",
		new StaticResourcesProductionFilter());

	staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/");
	staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/index.html");
	staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
	staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/yo/*");
	staticResourcesProductionFilter.setAsyncSupported(true);
    }

    private void initCachingHttpHeadersFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {

	FilterRegistration.Dynamic cachingHttpHeadersFilter = servletContext.addFilter("cachingHttpHeadersFilter", new CachingHttpHeadersFilter(env));

	cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
	cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/yo/*");
	cachingHttpHeadersFilter.setAsyncSupported(true);
    }

    private void initH2Console(ServletContext servletContext) {

	ServletRegistration.Dynamic h2ConsoleServlet = servletContext.addServlet("H2Console", new org.h2.server.web.WebServlet());
	h2ConsoleServlet.addMapping("/console/*");
	h2ConsoleServlet.setInitParameter("-properties", "src/main/resources");
	h2ConsoleServlet.setLoadOnStartup(1);
    }
}
