package com.stacksimplify.restServices.springbootbuildingblocks;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Locale;

// Main application class
@EnableAdminServer
@SpringBootApplication

public class SpringbootBuildingblocksApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBuildingblocksApplication.class, args);
    }

    public LocaleResolver localeResolver() {
        AcceptHeaderLocaleResolver headerLocaleResolver = new AcceptHeaderLocaleResolver();
        headerLocaleResolver.setDefaultLocale(Locale.US);
        return headerLocaleResolver;
    }

    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
}
