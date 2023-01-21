package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.commons.lang3.ArrayUtils;

import static exercise.Data.getUsers;
import static exercise.Data.getNextId;

public class UsersServlet extends HttpServlet {

    private final List<Map<String, String>> users = getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }


    private Map<String, String> getUserById(String id) {
        return users
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list" -> showUsers(request, response);
            case "new" -> newUser(request, response);
            case "edit" -> editUser(request, response);
            case "show" -> showUser(request, response);
            case "delete" -> deleteUser(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "new" -> createUser(request, response);
            case "edit" -> updateUser(request, response);
            case "delete" -> destroyUser(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException, ServletException {

        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
    }


    private void showUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
    }

    private void newUser(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        var user = new HashMap<String, String>();
        user.put("firstName", "");
        user.put("lastName", "");
        user.put("email", "");
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void createUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        // BEGIN
        var user = new HashMap<String, String>();
        var firstName = getFirstName(request);
        var lastName = getLastName(request);
        var email = getEmail(request);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        if (firstName.isEmpty() || lastName.isEmpty()) {
            request.setAttribute(
                    "error",
                    "Error: Unprocessable Entity. Поля 'Имя' и 'Фамилия' не могут быть пустыми"
            );
            request.setAttribute("user", user);
            response.setStatus(422);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/new.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        user.put("id", getNextId());
        users.add(user);
        response.sendRedirect("/users");
        // END
    }

    private void editUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // BEGIN
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void updateUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // BEGIN
        var firstName = getFirstName(request);
        var lastName = getLastName(request);
        var email = getEmail(request);
        user.put("firstName", firstName);
        user.put("lastName", lastName);
        user.put("email", email);
        if (firstName.isEmpty() || lastName.isEmpty()) {
            request.setAttribute(
                    "error",
                    "Error: Unprocessable Entity. Поля 'Имя' и 'Фамилия' не могут быть пустыми"
            );
            request.setAttribute("user", user);
            response.setStatus(422);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/edit.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        users.add(user);
        response.sendRedirect("/users/show?id=" + id);
        // END
    }

    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void destroyUser(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        users.remove(user);
        response.sendRedirect("/users");
    }

    private static String getLastName(HttpServletRequest request) {
        return request.getParameter("lastName");
    }

    private static String getFirstName(HttpServletRequest request) {
        return request.getParameter("firstName");
    }

    private static String getEmail(HttpServletRequest request) {
        return request.getParameter("email");
    }
}
