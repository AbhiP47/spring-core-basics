package org.abhinav.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.abhinav.model.User;
import org.abhinav.service.UserService;

import java.io.IOException;
import java.util.List;
//@WebServlet is an annotation (from javax.servlet.annotation / jakarta.servlet.annotation package)
// used to register a servlet and map it to a URL pattern
@WebServlet("/users")
/*
Here, "/users" is shorthand for the urlPatterns attribute — meaning
this servlet handles any request to http://host:port/context-path/users
 */
public class UserServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    public void doPost(HttpServletRequest request ,
                       HttpServletResponse response) throws IOException {

        Integer id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String mobile = request.getParameter("mobile");

        if (id == null || email == null ||
                name == null || mobile == null) {
            response.setStatus(400);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\n" +
                            "    \"message\" : \"Some fields are missing\"\n" +
                            "}"
            );
        }

        User user = new User(id, name, email, mobile);

        User createdUser = userService.createUser(user);

        response.setStatus(201);
        response.setContentType("application/json");
        response.getWriter().write(
                "{\n" +
                        "    \"message\" : \"User Added successfully\"\n" +
                        "}"
        );
    }

    public void doGet(HttpServletRequest request ,
                      HttpServletResponse response) throws IOException {
        String idParam = request.getParameter("id");

        if(idParam == null) {
            List<User> users = userService.getAllUsers();
            response.setStatus(200);
            response.setContentType("application/json");
            response.getWriter().write(usersToJson(users));
            return;
        }
        Integer id = Integer.parseInt(idParam);

        User userResp = userService.getUserById(id);

        if(userResp == null) {
            response.setStatus(404);
            response.setContentType("application/json");
        }

        response.setStatus(200);
        response.setContentType("application/json");
        response.getWriter().write(userToJson(userResp));
    }


    @Override
    public void doPut(HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) {

    }

    @Override
    public void doDelete(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) {

    }

    private String userToJson(User user) {
        return "{\n" +
                "    \"id\" : " + user.getId() + ",\n" +
                "    \"name\" : " + user.getName() + ",\n" +
                "    \"email\" : " + user.getEmail() + ",\n" +
                "    \"mobile\" : " + user.getMobile() + "\n" +
                "}";
    }

    private String usersToJson(List<User> users) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");

        for(int i = 0; i<users.size(); i++) {
            stringBuilder.append(userToJson(users.get(i)));

            if(i < users.size() - 1) {
                stringBuilder.append(",");
            }
        }

        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}

/*
How It Works Internally (Step-by-Step)
1. Compile-time — Annotation is just metadata

At compile time, @WebServlet doesn't "do" anything by itself. It's just metadata attached to the
class file (stored in the .class file).

2. Deployment / Startup — Container scans for annotations

When Tomcat (or any Servlet 3.0+ compliant container) starts up and deploys your application, it
 performs classpath scanning.
It looks through all classes in WEB-INF/classes and WEB-INF/lib for classes annotated with @WebServlet.
This scanning is done via the Servlet 3.0 Pluggability mechanism — specifically, a ServletContainerInitializer
 combined with @HandlesTypes.

3. Registration — Container builds the servlet registry

For each class found with @WebServlet, Tomcat automatically:

Creates a ServletRegistration internally (equivalent to what <servlet> + <servlet-mapping> do in web.xml)
Registers the URL pattern → Servlet class mapping
Sets up load-on-startup order (if specified), init parameters, etc.

4. Runtime — Request handling

When a request comes for /users:

Tomcat's Connector receives it
Engine → Host → Context (Mapper component) matches the URL against registered patterns
Finds that /users maps to UserServlet
Instantiates the servlet (if not already instantiated) and calls init() (only once)
Calls service() → which internally routes to doGet(), doPost(), etc., based on the HTTP method used
 */