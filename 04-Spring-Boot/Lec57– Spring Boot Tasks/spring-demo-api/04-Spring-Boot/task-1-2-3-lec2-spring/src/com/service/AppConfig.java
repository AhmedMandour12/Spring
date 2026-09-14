package com.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    // Task 1,3

	 @Bean(initMethod = "init", destroyMethod = "destroy")
	 @Scope("prototype")
    public PersonService personService() {
        return new PersonService();
    }

    @Bean
    public UserService managerService() {
        return new ManagerService();
    }


    // Task 2

    @Bean
    public AccountService accountService() {
        return new AccountServiceImpl(personService());
    }
}