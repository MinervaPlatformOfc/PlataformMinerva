<%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 27/02/2026
  Time: 09:21
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
<form action="${pageContext.request.contextPath}/recoverPassword/updatePassword" method="post">
    <input type="hidden" value="<%=email%>" name="email">
    <label>Nova senha:<input type="password" name="password" placeholder="Digite a nova senha que você desejar" required minlength="8" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':&quot;\\|,.<>\/?]).+$"></label>
    <br>
    <small>A senha deve conter:<br>No mínimo 8 caracteres, 1 maiúscula, 1 minúscula e 1 especial.</small>
    <br><br><button type="submit">Alterar</button>
</form>

</body>
</html>
