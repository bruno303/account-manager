package com.bso.accountmanager.application.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.bso.accountmanager")
@EntityScan(basePackages = "com.bso.accountmanager.infrastructure.entities")
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = "com.bso.accountmanager.infrastructure.repositories")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AccountManagerSpringBootApplication {
    
}
