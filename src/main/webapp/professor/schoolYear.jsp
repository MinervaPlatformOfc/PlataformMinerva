<%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 20/02/2026
  Time: 00:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String type = request.getParameter("type");
    String actionUrl = null;

    if ("comment".equals(type)) {
        actionUrl = "/teacher/listByYear";
    } else if ("grade".equals(type)){
        actionUrl = "/teacher/listStudentByNotes";
    }
    else {
        System.out.println("a ulr nao foi pega");
    }

%>

<form action="${pageContext.request.contextPath}<%= actionUrl %>" method="get">
    <button type="submit" name="schoolYear" value="1">1 série</button>
    <button type="submit" name="schoolYear" value="2">2 série</button>
    <button type="submit" name="schoolYear" value="3">3 série</button>
</form>
</body>
</html>
