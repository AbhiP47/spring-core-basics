package org.abhinav;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
    }
}

/*

Spring MVC is a Java framework used to build robust, scalable web applications. It is part of the larger Spring Framework
 ecosystem and strictly follows the Model-View-Controller (MVC) design pattern.

Instead of writing raw Servlets and handling HTTP requests manually (like managing context paths and mapping URLs yourself),
 Spring MVC abstracts away the boilerplate code, allowing you to focus purely on business logic.

The Core Architecture & Request Workflow
Spring MVC revolves around a central servlet called the DispatcherServlet, which acts as the Front Controller. It intercepts
 all incoming HTTP requests and routes them to the appropriate handlers.

Here is exactly how a request flows through Spring MVC:

The Request Arrives: The client (browser) sends an HTTP request (e.g., /users).

Front Controller (DispatcherServlet): The DispatcherServlet intercepts the request and consults the HandlerMapping to find
 out which controller should handle it.

Controller Execution: The request is handed off to the appropriate Controller. The controller executes business logic
 (often interacting with a service layer or database) and returns a ModelAndView object (containing the data/Model and the name of the web page/View).

View Resolution: If the application returns HTML, the DispatcherServlet hands the logical view name (like "index")
 to a ViewResolver, which finds the actual physical file (like /WEB-INF/jsp/index.jsp).

The Response: The view renders the data onto the page and sends the finalized HTML (or raw JSON/XML if you are building
 a REST API) back to the client.

Breaking Down the MVC Components
Model: This represents the application data and business logic. In a Spring app, these are typically POJOs
(Plain Old Java Objects) or entity classes carrying data from the database.

View: The user interface that renders the model data. This could be traditional server-side rendering technologies
 like Thymeleaf, JSP, or Freemarker. (In modern setups, the "View" is often bypassed entirely using @RestController
 to return raw JSON directly to a frontend framework like React or Angular).

Controller: The brain of the operation. It handles user requests, binds incoming form parameters or JSON bodies to
Java objects, calls services, and decides what data to send back.

Why Use Spring MVC Over Traditional Servlets?
Compared to standard Jakarta/Java Servlets, Spring MVC offers a few massive upgrades:

Annotation-Driven Setup: Instead of complex configurations, you map endpoints using clean annotations like
 @Controller, @GetMapping, and @PostMapping.

Automatic Data Binding & Validation: It automatically converts incoming HTTP request parameters or JSON payloads
 straight into Java objects (and validates them using @Valid).

Seamless Dependency Injection: Because it’s built on top of the core Spring framework, your controllers can
easily inject services and repositories using @Autowired.
 */