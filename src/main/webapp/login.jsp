<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="login" method="POST">
    <div> <label>Email:</label> <input type="email" name="email" required> </div>
    <br>
    <div> <label>Senha:</label> <input type="password" name="senha" required> </div>
    <br>

    <button type="submit">Entrar</button>

</form>
<hr>
<br><br><br>
<a href="register.jsp">Cadastre-se</a>
</body>
</html>