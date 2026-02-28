<%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 05/02/2026
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/admin/ViewAdmins" method="get">
        <button type="submit">CRUD Admins</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewTeachers" method="post">
        <button type="submit">CRUD Teachers</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewSubjects" method="post">
        <button type="submit">CRUD Subjects</button>
    </form>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Sair</button>
    </form>
</body>
</html>
