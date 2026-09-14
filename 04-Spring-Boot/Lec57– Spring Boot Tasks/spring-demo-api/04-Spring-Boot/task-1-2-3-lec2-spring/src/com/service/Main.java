package com.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
// import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        /*
         * ========== With XML ==========
         *
         * ApplicationContext context =
         *         new ClassPathXmlApplicationContext("applicationContext.xml");
         *
         *
         * // ========== Task 1 ==========
         *
         * UserService personService =
         *         context.getBean("personService", UserService.class);
         *
         * personService.save("Ahmed");
         * personService.update("Ahmed");
         *
         *
         * UserService managerService =
         *         context.getBean("managerService", UserService.class);
         *
         * managerService.save("Mohamed");
         * managerService.update("Mohamed");
         *
         *
         * // ========== Task 2 ==========
         *
         * AccountService accountService =
         *         context.getBean("accountService", AccountService.class);
         *
         * accountService.getSavePerson("Ahmed");
         *
         *
         * // ========== Task 3 ==========
         *
         * PersonService personService1 =
         *         context.getBean("personService", PersonService.class);
         *
         * PersonService personService2 =
         *         context.getBean("personService", PersonService.class);
         *
         * personService1.save("Ahmed");
         * personService2.save("Mohamed");
         *
         * System.out.println(
         *         personService1 == personService2
         * );
         */


        // ========== Without XML ==========

        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);


        // ========== Task 1 ==========

        UserService personService =
                context.getBean("personService", UserService.class);

        personService.save("Ahmed");
        personService.update("Ahmed");


        UserService managerService =
                context.getBean("managerService", UserService.class);

        managerService.save("Mohamed");
        managerService.update("Mohamed");


        // ========== Task 2 ==========

        AccountService accountService =
                context.getBean("accountService", AccountService.class);

        accountService.getSavePerson("Ahmed");


        // ========== Task 3 ==========

        PersonService personService1 =
                context.getBean("personService", PersonService.class);

        PersonService personService2 =
                context.getBean("personService", PersonService.class);

        personService1.save("Ahmed");

        personService2.save("Mohamed");


        // Check Prototype Scope

        System.out.println(
                personService1 == personService2
        );
    }
}