package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.util.List;
import java.util.Map;

import exercise.Data;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    private final List<Map<String, String>> users = Data.getUsers();

    private String getId(HttpServletRequest request) {
        return request.getParameter("id");
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, "");
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String action = getAction(request);

        switch (action) {
            case "show" -> showUser(request, response);
            case "delete" -> showDeletePage(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        if (action.equals("delete")) {
            deleteUser(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException, ServletException {


        // BEGIN
        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        // Получаем id пользователя из строки запроса
        String id = getId(request);

        // Получаем пользователя по его id
        Map<String, String> user = getUserById(id);

        // Если пользователь не найден, нужно вернуть код ответа 404
        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // BEGIN
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/show.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void showDeletePage(HttpServletRequest request,
                                HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        // BEGIN
        request.setAttribute("user", user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/delete.jsp");
        requestDispatcher.forward(request, response);
        // END
    }

    private void deleteUser(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {

        String id = getId(request);

        Map<String, String> user = getUserById(id);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        // BEGIN
        users.remove(user);
        request.setAttribute("users", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/users.jsp");
        requestDispatcher.forward(request, response);
        // END
    }


    private Map<String, String> getUserById(String id) {
        return users
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);
    }
}
