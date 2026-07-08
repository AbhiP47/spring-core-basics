package org.abhinav;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

    }
}

/*
How servlets work in Spring Boot?

Step 1 — Embedded Tomcat, not external
When you add spring-boot-starter-web, it pulls in an embedded Tomcat as a dependency. Tomcat isn't
 a separate installed server here — it's just a set of Java classes (TomcatServletWebServerFactory, etc.) that Spring Boot creates and starts programmatically inside your main().
Step 2 — Auto-configuration class does the registration
Spring Boot has a class:
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration

This is triggered automatically (via @EnableAutoConfiguration, which @SpringBootApplication includes)
whenever spring-webmvc is on the classpath. It:

-> Creates a DispatcherServlet bean
-> Registers it with a ServletRegistrationBean, mapped to / by default

Step 3 — Embedded Tomcat starts and picks it up
When you run SpringApplication.run(...), Spring Boot:

Creates the ApplicationContext
Detects it's a web application → creates an embedded TomcatServletWebServerFactory
Starts an actual embedded Tomcat instance in-process
Registers the DispatcherServlet bean (created in Step 2) with that embedded Tomcat's context — internally
 calling the same ServletContext.addServlet() API as before
Tomcat starts listening on the configured port (default 8080)


No web.xml, no @WebServlet, no WebApplicationInitializer needed — Spring Boot's auto-configuration + embedded server
handles all of it behind the scenes.

What is the command for Windows to run Apache Tomcat Server?


Marshaled confident knowledge of Tomcat server commands
Marshaled confident knowledge of Tomcat server commands
Here's a quick note on this too:

Running Apache Tomcat on Windows
Prerequisites:

Tomcat is downloaded and extracted (e.g., to C:\apache-tomcat-9.0.xx)
JAVA_HOME environment variable is set correctly (pointing to your JDK installation)
Command to Start Tomcat:

Navigate to the bin folder inside your Tomcat installation directory, then run:

startup.bat
Full steps:

cd C:\apache-tomcat-9.0.xx\bin
startup.bat
This opens a new command prompt window showing Tomcat's startup logs. If it starts successfully, you'll see a message like:

INFO: Server startup in [xxxx] ms
You can then verify it's running by opening a browser and going to:

http://localhost:8080
(You should see the Tomcat welcome page.)

Command to Stop Tomcat
cd C:\apache-tomcat-9.0.xx\bin
shutdown.bat
Notes:
startup.bat internally calls catalina.bat start
You can also directly use: catalina.bat run — this runs Tomcat in the current command prompt (useful for seeing logs live, and it stops when you close/Ctrl+C the window).
If JAVA_HOME is not set, Tomcat won't start and will throw an error like The JAVA_HOME environment variable is not defined correctly.
What are the things I can do by running the Apache Tomcat server on my PC?


Opted for direct answer without external resources
Opted for direct answer without external resources
What You Can Do by Running Apache Tomcat on Your PC
Once Tomcat is up and running locally, here's what becomes possible:

1. Host and Test Java Web Applications

Deploy .war files (Servlets, JSPs, Spring MVC apps) into the webapps folder and access them via http://localhost:8080/your-app
Test your web app in a real servlet container environment before deploying to production
2. Run Spring Boot Applications (Embedded Mode)

If Tomcat is embedded in your Spring Boot app, running the app automatically starts Tomcat — no manual deployment needed
Lets you test REST APIs, controllers, and full request/response flow locally
3. Develop and Debug Servlets/JSPs

Write raw Servlets or JSPs, compile them, and see how the container handles lifecycle (init(), service(), destroy())
Useful for understanding low-level web app behavior before using frameworks
4. Access the Tomcat Manager App

Tomcat ships with a Manager Application (http://localhost:8080/manager) where you can:
Deploy/undeploy/reload applications without restarting the server
View running app statuses, memory usage, and session counts
Start/stop individual web apps
5. Test Database-Connected Applications

Configure JNDI DataSources in context.xml to connect your app to a local/remote database (MySQL, PostgreSQL, etc.) and test DB operations end-to-end
6. Simulate Production-Like Environment

Test HTTPS/SSL setup locally by configuring a connector with a keystore
Test session management, cookies, authentication flows
Try load balancing/clustering concepts (advanced use)
7. Learn/Practice Server Configuration

Modify server.xml to change port numbers, add connectors, configure virtual hosts
Modify web.xml for servlet mappings, filters, listeners
8. View Logs for Debugging

Tomcat generates logs in the logs folder (catalina.out, localhost.log, etc.) — helpful for tracing errors, exceptions, and request activity during development
9. Host Multiple Applications Simultaneously

Deploy several independent web apps under the same Tomcat instance, each accessible via a different context path (/app1, /app2, etc.)
In short:
Running Tomcat locally basically turns your PC into a mini web server, letting you build, deploy, test, and debug Java-based web applications (Servlets, JSPs, Spring MVC, Spring Boot apps) exactly as they would behave on a live production server — just on localhost.

@WebServlet("/users")
 Let's play this annotation in detail. How does it work?


Examined annotation mechanics, mapping strategies, and container deployment processes
Examined annotation mechanics, mapping strategies, and container deployment processes
@WebServlet("/users") — Detailed Explanation
What is it?
@WebServlet is an annotation (from javax.servlet.annotation / jakarta.servlet.annotation package) used to register a servlet and map it to a URL pattern — without needing to declare it manually in web.xml.

java
@WebServlet("/users")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.getWriter().println("Fetching users list");
    }
}
Here, "/users" is shorthand for the urlPatterns attribute — meaning this servlet handles any request to http://host:port/context-path/users.

How It Works Internally (Step-by-Step)
1. Compile-time — Annotation is just metadata

At compile time, @WebServlet doesn't "do" anything by itself. It's just metadata attached to the class file (stored in the .class file).
2. Deployment / Startup — Container scans for annotations

When Tomcat (or any Servlet 3.0+ compliant container) starts up and deploys your application, it performs classpath scanning.
It looks through all classes in WEB-INF/classes and WEB-INF/lib for classes annotated with @WebServlet.
This scanning is done via the Servlet 3.0 Pluggability mechanism — specifically, a ServletContainerInitializer combined with @HandlesTypes.
3. Registration — Container builds the servlet registry

For each class found with @WebServlet, Tomcat automatically:
Creates a ServletRegistration internally (equivalent to what <servlet> + <servlet-mapping> do in web.xml)
Registers the URL pattern → Servlet class mapping
Sets up load-on-startup order (if specified), init parameters, etc.
Essentially, this is auto-generated:
xml
<servlet>
    <servlet-name>UserServlet</servlet-name>
    <servlet-class>com.example.UserServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>UserServlet</servlet-name>
    <url-pattern>/users</url-pattern>
</servlet-mapping>
4. Runtime — Request handling

When a request comes for /users:
Tomcat's Connector receives it
Engine → Host → Context (Mapper component) matches the URL against registered patterns
Finds that /users maps to UserServlet
Instantiates the servlet (if not already instantiated) and calls init() (only once)
Calls service() → which internally routes to doGet(), doPost(), etc., based on the HTTP method used
Attributes of @WebServlet
Attribute	Purpose
value / urlPatterns	URL(s) this servlet maps to (can be array: {"/users", "/user-list"})
name	Logical name of the servlet (defaults to fully qualified class name if omitted)
initParams	Initialization parameters (@WebInitParam(name="x", value="y"))
loadOnStartup	Order in which servlet should be loaded at startup (instead of lazy loading on first request)
asyncSupported	Whether this servlet supports asynchronous request processing
description	Optional description metadata
Example with more attributes:

java
@WebServlet(
    name = "UserServlet",
    urlPatterns = {"/users", "/user-list"},
    initParams = {@WebInitParam(name = "maxUsers", value = "100")},
    loadOnStartup = 1,
    asyncSupported = true
)
public class UserServlet extends HttpServlet { ... }
Important Notes
value and urlPatterns are actually the same attribute — value is just a shorthand alias, so @WebServlet("/users") is exactly equal to @WebServlet(urlPatterns = "/users"). You cannot use both together.
This annotation only works if:
Your web.xml (if present) doesn't have metadata-complete="true" (which disables annotation scanning)
You're using Servlet 3.0 or above compliant container (Tomcat 7+)
This is why in Spring MVC, you typically don't use @WebServlet directly — instead, DispatcherServlet itself is registered this way internally (or via WebApplicationInitializer / Spring Boot auto-configuration), and then Spring's own @RequestMapping/@GetMapping annotations take over routing within that single servlet.
Quick Comparison: @WebServlet vs web.xml
@WebServlet (Annotation)	web.xml (Deployment Descriptor)
Configuration lives in the Java class itself	Configuration lives in a separate XML file
Introduced in Servlet 3.0	Traditional approach (all versions)
Less boilerplate, easier to maintain	More verbose but centralized configuration
Scanned automatically at deployment	Explicitly read by container at startup
how this happens automatically in the Spring MVC.


Examined Spring's automatic servlet registration and request routing mechanisms
Examined Spring's automatic servlet registration and request routing mechanisms
How DispatcherServlet Gets Registered Automatically in Spring MVC
This happens in two different ways depending on whether you're using traditional Spring MVC (deployed as WAR) or Spring Boot. Let's cover both.

A) Traditional Spring MVC (WAR deployment, no Spring Boot)
Here, Spring doesn't use @WebServlet at all. Instead, it relies on the Servlet 3.0 Pluggability API (the same mechanism that powers @WebServlet scanning, but used programmatically).

The Mechanism: ServletContainerInitializer

Step 1 — Servlet spec provides a hook
The Servlet 3.0 spec defines an interface:

java
public interface ServletContainerInitializer {
    void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException;
}
Any class implementing this, listed in a file called META-INF/services/javax.servlet.ServletContainerInitializer, gets automatically called by the container (Tomcat) at startup — before any request is served.

Step 2 — Spring provides its own implementation
Spring-web's JAR includes a class called:

java
org.springframework.web.SpringServletContainerInitializer
This is registered in Spring's own META-INF/services/javax.servlet.ServletContainerInitializer file (bundled inside spring-web.jar). So the moment spring-web.jar is on your classpath, Tomcat automatically discovers and invokes this class at startup — no config needed.

Step 3 — It looks for your initializer classes
SpringServletContainerInitializer is annotated with:

java
@HandlesTypes(WebApplicationInitializer.class)
This tells the container: "Scan the classpath for any class implementing WebApplicationInitializer, and pass them to me."

Step 4 — You implement WebApplicationInitializer

java
public class MyWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) {
        AnnotationConfigWebApplicationContext context =
            new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);
        context.setServletContext(container);

        ServletRegistration.Dynamic dispatcher =
            container.addServlet("dispatcher", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");   // maps DispatcherServlet to root URL
    }
}
Step 5 — Registration happens programmatically
Inside this method, container.addServlet(...) is called — this is the exact same underlying API that @WebServlet scanning uses internally, just invoked manually instead of via annotation. This is how DispatcherServlet gets registered and mapped to / (or any pattern you choose) — entirely in Java code, no web.xml, no @WebServlet needed.

Tomcat starts
   → finds SpringServletContainerInitializer via META-INF/services (from spring-web.jar)
   → calls onStartup()
   → which finds YOUR WebApplicationInitializer classes
   → which registers DispatcherServlet programmatically
B) Spring Boot (the modern, common way)
Spring Boot removes even this manual step using auto-configuration.

Step 1 — Embedded Tomcat, not external
When you add spring-boot-starter-web, it pulls in an embedded Tomcat as a dependency. Tomcat isn't a separate installed server here — it's just a set of Java classes (TomcatServletWebServerFactory, etc.) that Spring Boot creates and starts programmatically inside your main().

Step 2 — Auto-configuration class does the registration
Spring Boot has a class:

java
org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration
This is triggered automatically (via @EnableAutoConfiguration, which @SpringBootApplication includes) whenever spring-webmvc is on the classpath. It:

Creates a DispatcherServlet bean
Registers it with a ServletRegistrationBean, mapped to / by default
java
@Bean
public DispatcherServlet dispatcherServlet() {
    return new DispatcherServlet();
}

@Bean
public ServletRegistrationBean<DispatcherServlet> dispatcherServletRegistration(
        DispatcherServlet dispatcherServlet) {
    return new ServletRegistrationBean<>(dispatcherServlet, "/");
}
Step 3 — Embedded Tomcat starts and picks it up
When you run SpringApplication.run(...), Spring Boot:

Creates the ApplicationContext
Detects it's a web application → creates an embedded TomcatServletWebServerFactory
Starts an actual embedded Tomcat instance in-process
Registers the DispatcherServlet bean (created in Step 2) with that embedded Tomcat's context — internally calling the same ServletContext.addServlet() API as before
Tomcat starts listening on the configured port (default 8080)
main() runs
   → SpringApplication.run()
   → Spring Boot auto-config creates DispatcherServlet bean
   → Embedded Tomcat (TomcatServletWebServerFactory) is created
   → DispatcherServlet registered into Tomcat's ServletContext automatically
   → Tomcat starts, listens on port 8080
   → All requests → routed to DispatcherServlet
No web.xml, no @WebServlet, no WebApplicationInitializer needed — Spring Boot's auto-configuration + embedded server handles all of it behind the scenes.

C) After DispatcherServlet is Registered — What Happens Next?
This is the key insight: only ONE servlet (DispatcherServlet) is ever registered with Tomcat — mapped to /.
 It becomes the single Front Controller.

 Once a request comes in:
Request → Tomcat → DispatcherServlet (the only registered servlet)
                        ↓
        DispatcherServlet internally uses HandlerMapping
        to match the URL to a @Controller method
        (annotated with @RequestMapping / @GetMapping / @PostMapping etc.)


        So routing within your app (/users, /orders, /products, etc.) is not done via multiple @WebServlet
        registrations. It's done by Spring's own internal routing system (RequestMappingHandlerMapping),
        which reads your @Controller/@RestController classes and maps their methods to URLs — all inside
        the single DispatcherServlet.

 */


/*
DispatcherServlet is the front controller of a Spring MVC/Spring Boot application — a single servlet
that receives every incoming HTTP request and delegates it to the appropriate component for processing.

It extends HttpServlet, so at its core, it's still a regular servlet — just a very smart one provided
by Spring.
In Spring Boot, it's auto-configured and auto-registered with the embedded Tomcat (mapped to / by default)
 — you don't set it up manually.

    Request Flow


 Client Request
     ↓
DispatcherServlet (single entry point)
     ↓
HandlerMapping → finds the right Controller method for the URL
     ↓
Controller (@RestController/@Controller) → executes business logic
     ↓
Returns response data (JSON) or ModelAndView (for JSP/Thymeleaf)
     ↓
(If view-based) ViewResolver → resolves the actual view to render
     ↓
Response sent back to Client

In short, DispatcherServlet:

Intercepts all incoming requests
Finds the matching @Controller/@RestController method using HandlerMapping
Invokes that method and gets the result
Converts the result to the response (JSON via HttpMessageConverter, or resolves a view via ViewResolver)
Sends the final response back to the client

Why it matters: It centralizes request handling, so you never write raw servlets — you just write @Controller
 classes, and DispatcherServlet handles all the routing and coordination internally.
 */