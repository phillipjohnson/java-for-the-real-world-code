package com.letstalkdata.iscream;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support
        .AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.letstalkdata.iscream.WebConfig");

        container.addListener(new ContextLoaderListener(context));

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistration.Dynamic dispatcher = container
                .addServlet("dispatcher", dispatcherServlet);

        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
