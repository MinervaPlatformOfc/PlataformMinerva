<%@ page import="com.example.minerva.dto.ErrorDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Erro</title>
</head>

<%
    ErrorDTO error = (ErrorDTO) request.getAttribute("error");
%>

<body>

    <h1>Ocorreu um erro!</h1>
    <br>
    <h3>Código do erro: <%=error.getStatusCode()%></h3>

    <p>Mensagem: <%=error.getMessage()%></p>

    <p>URI de requisição: <%=error.getRequestUri()%></p>

    <p><%=error.getException() != null ? "Erro: "+ error.getException(): ""%></p>
</body>
</html>
