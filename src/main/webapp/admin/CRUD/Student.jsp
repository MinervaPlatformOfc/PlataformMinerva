<%--
  Created by IntelliJ IDEA.
  User: eduardodomingues-ieg
  Date: 24/02/2026
  Time: 20:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Enviar matrícula no gmail para registrar usuário!</h1>
<form action="${pageContext.request.contextPath}/admin/generateRegistration" method="post">
    <div> <label>Email:</label> <input type="email" name="email" required> </div>
    <br>
    <button type="submit">Enviar Código de Matrícula</button>
</form>
</body>
</html>
