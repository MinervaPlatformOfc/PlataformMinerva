<%@ page import="com.example.minerva.dto.ErrorDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error!</title>
</head>

<%
    ErrorDTO error = (ErrorDTO) request.getAttribute("error");
%>

<body>
    <h1><%=error.getStatusCode()%></h1>

    <h3><%=error.getMessage()%></h3>

    <p><%=error.getRequestUri()%></p>

    <p><%=error.getException()%></p>
</body>
</html>
