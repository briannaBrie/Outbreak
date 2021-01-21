package com.example.outbreak.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class Webconfig implements WebMvcConfigurer {
    //We use this class so that we don't need to create a new controller for login

    @Bean
    public LocaleResolver localeResolver(){
        //set the local resolver for this session and as long as you are in this
        //session you use it. You override the default one
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();

        //Two ways to use the english default locale. Use the predefined one or
        //create one of your own. e.g one in English and then make a Swahili one
        //sessionLocaleResolver.setDefaultLocale(Locale.ENGLISH);
        sessionLocaleResolver.setDefaultLocale(new Locale("en"));

        return sessionLocaleResolver;
    }
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
        //listener to tell when the user changes the locale/language
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("language");

        return lci;
    }

    @Bean
    public MessageSource source(){
        //define the type we are using and its location in the project
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");

        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validatorFactoryBean(){
        //tells the app where the translations for the bean validation messages are located
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(source());

        return bean;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //add the interceptor created to the registry
        registry.addInterceptor(localeChangeInterceptor());
    }
}
