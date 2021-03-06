package ru.gvozdilin.meet.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

    public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Override
        protected String[] getServletMappings() {
            return new String[]{"/"};
        }
        @Override
        protected Class<?>[] getRootConfigClasses() {
            return new Class[]{SpringConfig.class, WebConfig.class};
        }
        @Override
        protected Class<?>[] getServletConfigClasses() {
            return new Class[0];
        }
    }




