package ru.ssau.esa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.xslt.XsltView;
import org.springframework.web.servlet.view.xslt.XsltViewResolver;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public ViewResolver xsltViewResolver() {
        XsltViewResolver viewResolver = new XsltViewResolver();
        viewResolver.setOrder(1);
        viewResolver.setViewClass(XsltView.class);
        viewResolver.setViewNames("bankXSL", "carXSL", "personXSL");
        viewResolver.setPrefix("classpath:/xsl/");
        viewResolver.setSuffix(".xsl");
        return viewResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("banks");
        registry.addViewController("/banks.html").setViewName("banks");
        registry.addViewController("/persons.html").setViewName("persons");
        registry.addViewController("/cars.html").setViewName("cars");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
