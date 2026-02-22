
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>CRUD - Users</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>Email</th>
            <th>Password</th>
            <th>Name</th>
        </tr>
        <c:forEach var="adm" items="${adminList}">
            <tr>
                <td>${adm.getEmail()}</td>
                <td>${adm.getPassword()}</td>
                <td>${adm.getName()}</td>

            </tr>

        </c:forEach>
    </table>
</body>
</html>
