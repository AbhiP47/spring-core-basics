package org.abhinav;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/*
In Spring Core, you had to act as the "coordinator" manually wiring up everything. In Spring Boot, the framework
 takes over the heavy lifting, primarily driven by a single annotation: @SpringBootApplication.

 The magic of this annotation is that it is actually a 3-in-1 combo annotation. If you peek inside its source
 code, it wraps three core Spring Core annotations into one:

A. @Configuration
It inherits the exact same @Configuration mechanics you already know. This means your main Spring Boot class is an
 AppConfig file itself. You can still write @Bean factory methods directly inside it if you want to.

B. @ComponentScan
In Spring Core, you had to explicitly type @ComponentScan("com.abhinav"). @SpringBootApplication implicitly applies
@ComponentScan on the package it is currently sitting in, plus all of its sub-packages.

Why it's better: As long as you keep your controllers, services, and repositories inside sub-packages of
 your main class, you never have to type out component scanning packages again.

C. @EnableAutoConfiguration
This is the heart of Spring Boot. In Spring Core, if you wanted to connect to a PostgreSQL database or use
a JSON parser, you had to manually download the jars, write a @Bean for the DataSource, construct the
connection pool manager, and handle configurations yourself.

@EnableAutoConfiguration uses a concept called "Class-path scanning":

It looks at your pom.xml dependencies (like a Spring Boot Starter).

It looks at what classes are available on your classpath.

It makes an assumption: "Hey, I see the PostgreSQL driver jar on the classpath, and I see a
 database URL in application.properties. The developer clearly wants a database connection. I will
  automatically build and configure the DataSource bean for them behind the scenes!"
  for ex : Add the Spring Boot Starter Web dependency to our pom.xml file.
   When the Maven loads, our Spring Boot auto-configuration creates the beans required for the Tomcat server to start.
    Whenever we run our application, the Tomcat server starts automatically.
 */
@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(Main.class,args);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.placeOrder();


    }
    @Bean
    public UserService createUserBean()
    {
        return new UserService();
    }
}

/*
# Spring Boot — Auto-Configuration Notes

## 1. What is Spring Boot?
Spring Boot is a framework built on top of the Spring Framework. It removes the need for a lot of
 manual setup (XML configs, boilerplate code) so you can build Spring applications quickly.

Key features of Spring Boot:
- Auto-Configuration (automatically sets things up for you)
- Embedded servers (Tomcat, Jetty — no need to deploy separately)
- Starter dependencies (easy to add libraries)
- Production-ready tools (Actuator, monitoring, etc.)

---

## 2. What is Auto-Configuration?

**Definition:**
Auto-Configuration is a Spring Boot feature that automatically configures your application based on:
- The dependencies (jars) present in your classpath
- The beans you have already defined
- Any properties you've set in `application.properties` / `application.yml`

**In simple words:**
Spring Boot looks at what libraries you have added (e.g., spring-boot-starter-web) and automatically
 sets up the related beans/configuration for you, so you don't have to write that config manually.

**Example:**
If you add `spring-boot-starter-data-jpa` and a database driver (like MySQL) to your project, Spring
 Boot automatically configures:
- DataSource
- EntityManagerFactory
- Transaction Manager

You don't have to write this configuration yourself — Spring Boot detects the dependency and does
it for you.

---

## 3. How Auto-Configuration Works (Behind the Scenes)

1. **`@SpringBootApplication`** annotation triggers everything. It is a combination of:
   - `@Configuration` → marks the class as a configuration source
   - `@ComponentScan` → scans for Spring components (Beans, Controllers, Services)
   - `@EnableAutoConfiguration` → the actual trigger for auto-configuration

2. **`@EnableAutoConfiguration`** tells Spring Boot:
   "Look at the dependencies on the classpath and configure beans automatically based on what's
   needed."

3. Spring Boot checks a file called:
   ```
   META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
   ```
   (In older versions: `spring.factories`)

   This file lists all possible auto-configuration classes (hundreds of them), like:
   - `DataSourceAutoConfiguration`
   - `WebMvcAutoConfiguration`
   - `JpaRepositoriesAutoConfiguration`

4. Spring Boot then uses **conditional annotations** to decide which configurations actually apply:
   - `@ConditionalOnClass` → applies only if a certain class is on the classpath
   - `@ConditionalOnMissingBean` → applies only if you haven't defined that bean yourself
   - `@ConditionalOnProperty` → applies only if a certain property is set
   - `@ConditionalOnBean` → applies only if another bean already exists

So, it's "smart" — it only configures what's actually needed and doesn't override what you've already
 configured.

---

## 4. Example to Understand Easily

Say you add this dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

Spring Boot detects:
- Tomcat is on classpath → auto-configures an embedded Tomcat server
- Spring MVC is on classpath → auto-configures DispatcherServlet, JSON converters, etc.

You didn't write a single line of config — it just works.

---

## 5. How to Customize / Override Auto-Configuration

- **Override a bean:** Define your own bean of the same type — Spring Boot will use yours instead
 (thanks to `@ConditionalOnMissingBean`).
- **Exclude specific auto-configuration:**
  ```java
  @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
  ```
- **Use properties file** to tweak behavior:
  ```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/mydb
  ```

---

## 6. How to Debug What Got Auto-Configured

Add this property to see a detailed report of what was applied and why:
```properties
debug=true
```

This prints an **auto-configuration report** in the console showing:
- Positive matches (what got applied)
- Negative matches (what was skipped and why)

---

## 7. Quick Summary (For Revision)

| Concept | Meaning |
|---|---|
| Auto-Configuration | Spring Boot automatically configures beans based on classpath & properties |
| Trigger | `@EnableAutoConfiguration` (inside `@SpringBootApplication`) |
| Decision logic | Conditional annotations (`@ConditionalOnClass`, `@ConditionalOnMissingBean`, etc.) |
| Config source list | `AutoConfiguration.imports` file (or `spring.factories` in older versions) |
| Customize | Override beans, use `exclude`, or set properties |
| Debug | `debug=true` in properties file |

---

## 8. One-Line Definition (for quick recall)
> Auto-Configuration in Spring Boot automatically sets up your application's beans and components based
 on the libraries present in your project and the configuration you've provided — reducing manual setup.
 */