/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.panda.prueba1.controller;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.codahale.metrics.jersey2.InstrumentedResourceMethodApplicationListener;;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

/**
 *
 * @author ncscamacho
 */
@javax.ws.rs.ApplicationPath("resources")
public class ApplicationConfig extends Application {

    @Inject
    private com.panda.prueba1.metrics.MetricsConfigurer metricsConfigurer;

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON.class);
        resources.add(com.wordnik.swagger.jaxrs.listing.ResourceListingProvider.class);
        addRestResourceClasses(resources);
        return resources;
    }


    @Override
    public Set<Object> getSingletons() {
        final Set<Object> instances = new HashSet<>();
        instances.add(new JacksonJsonProvider());
        instances.add(new InstrumentedResourceMethodApplicationListener(metricsConfigurer.getMetricRegistry()));
        return instances;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.panda.prueba1.controller.AccountController.class);
        resources.add(com.panda.prueba1.controller.PersonaController.class);
        resources.add(com.panda.prueba1.controller.UserController.class);
        resources.add(com.panda.prueba1.controller.UserJWTController.class);
        resources.add(com.panda.prueba1.metrics.DiagnosticFilter.class);
        resources.add(com.panda.prueba1.security.SecurityUtils.class);
        resources.add(com.panda.prueba1.security.jwt.JWTAuthenticationFilter.class);
    }
    
}
