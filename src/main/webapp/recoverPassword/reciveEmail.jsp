<%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 27/02/2026
  Time: 08:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/recoverPassword" method="post">
  <label>Qual o seu email? <input type="email" name="email" placeholder="AAAAAAAAAA"></label>
  <button type="submit">Enviar</button>
</form>
</body>
</html>
