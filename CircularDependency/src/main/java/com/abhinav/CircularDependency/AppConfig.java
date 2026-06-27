package com.abhinav.CircularDependency;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
Our @Configuration annotation has an @Component annotation inside it
 so that the Spring can create an object of AppConfig in the IOC container to use the AppConfig.

 */
@ComponentScan("com.abhinav.CircularDependency")
public class AppConfig {
}
