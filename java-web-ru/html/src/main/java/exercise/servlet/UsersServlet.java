package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import exercise.user.User;
import exercise.deserializer.UserDeserializer;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(response, id);
    }

    public List<User> getUsers() throws IOException {
        // BEGIN
        var mapper = new ObjectMapper();
        var module = new SimpleModule();
        module.addDeserializer(User.class, new UserDeserializer());
        mapper.registerModule(module);
        var usersJson = new File(new File("src/main/resources/users.json").getAbsolutePath());
        return mapper.readValue(usersJson, new TypeReference<>() {
        });
        // END
    }

    private void showUsers(HttpServletResponse response) throws IOException {
        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        StringBuilder sb = new StringBuilder();
        addHeader(sb);
        sb.append("<body>");
        renderUsersHtml(sb);
        sb.append("</body>");
        sb.append("</html>");
        out.println(sb);
        // END
    }

    private void showUser(HttpServletResponse response, String id) throws IOException {
        // BEGIN
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        for (var user : getUsers()) {
            if (id.equals(user.getId())) {
                StringBuilder sb = new StringBuilder();
                addHeader(sb);
                sb.append("<body>");
                renderUserHtml(sb, user);
                sb.append("</body>");
                sb.append("</html>");
                out.println(sb);
                return;
            }
        }

        response.sendError(404, "User with ID=" + id + " not found.");
        // END
    }

    private void renderUserHtml(StringBuilder sb, User user) {
        sb.append("<div class=\"container\">");
        sb.append("<table>");
        sb.append("""
                <tr>
                  <th>UserID</th>
                  <th>First name</th>
                  <th>Last name</th>
                  <th>Email</th>
                </tr>""");
        sb.append("<tr>");
        sb.append("<td>").append(user.getId()).append("</td>");
        sb.append("<td>").append(user.getFirstName()).append("</td>");
        sb.append("<td>").append(user.getLastName()).append("</td>");
        sb.append("<td>").append(user.getEmail()).append("</td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</div>");
    }

    private void addHeader(StringBuilder sb) {
        sb.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                    <meta charset="UTF-8">
                    <title>Example application</title>
                    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                          rel="stylesheet"
                          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                          crossorigin="anonymous">
                </head>""");
    }

    private void renderUsersHtml(StringBuilder sb) throws IOException {
        sb.append("<div class=\"container\">");
        sb.append("<table>");
        sb.append("""
                <tr>
                  <th>UserID</th>
                  <th>Full name</th>
                </tr>""");
        getUsers().forEach(user -> {
            sb.append("<tr>");
            sb.append("<td>").append(user.getId()).append("</td>");
            sb.append("<td>").append("<a href=/users/")
                    .append(user.getId()).append(">")
                    .append(user.getFirstName()).append(" ")
                    .append(user.getLastName())
                    .append("</a> ")
                    .append("</td>");
            sb.append("</tr>");
        });
        sb.append("</table>");
        sb.append("</div>");
    }
}
