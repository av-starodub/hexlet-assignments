<%--@elvariable id="user" type=""--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <a href='/users/delete?id=${user.get("id")}'><input type=button value="DELETE"></a>
</div>
</body>
</html>
<!-- END -->
