<%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 27/02/2026
  Time: 08:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String email = (String) request.getAttribute("email");
%>
<form action="${pageContext.request.contextPath}/recoverPassword/validateOtp" method="post">
    <input type="hidden" value="<%=email%>" name="email">
    <label>Código enviado no seu email:<input type="text" name="otp" maxlength="6" placeholder="Digite o código enviado no seu email"></label>
    <button type="submit">Enviar</button>
</form>
</body>
</html>
