<%--@elvariable id="user" type=""--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- BEGIN -->
<!DOCTYPE html>
<html lang="ru">
<head>
    <title>User</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">
</head>
<body>
<div class="container">
    <table>
        <tr>
            <th>UserID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Email</th>
        </tr>
        <tr>
            <td>${user.get("id")}</td>
            <td>${user.get("firstName")}</td>
            <td>${user.get("lastName")}</td>
            <td>${user.get("email")}</td>
        </tr>
    </table>
    <form action="/users/delete?id=${user.get("id")}" method="post">
        <button type="submit" class="btn btn-danger">DELETE USER?</button>
    </form>
</div>
</body>
</html>
<!-- END -->
