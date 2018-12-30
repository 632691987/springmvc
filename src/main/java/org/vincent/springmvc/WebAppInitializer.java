package org.vincent.springmvc;

import org.springframework.core.env.StandardEnvironment;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.vincent.springmvc.system.SystemConstant;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer {

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(ApplicationConfig.class);
        ctx.setServletContext(servletContext);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servletContext.setInitParameter(StandardEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, String.join(",", new String[]{SystemConstant.ProfileSpringPure, SystemConstant.ProfileProduction}));
        dynamic.addMapping("/");
        dynamic.setLoadOnStartup(1);
    }

}
