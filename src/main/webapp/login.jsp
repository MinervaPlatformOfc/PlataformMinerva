<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/generic.css">
    <link href="https://fonts.googleapis.com/css2?family=Almendra&display=swap" rel="stylesheet">

    <title>JSP - </title>
</head>
<body>

<div class="page">

    <img class="logo" src="" alt="IMAGEM JOAOOOO">

    <div class="title-container">

        <img class="ornamento-esq" src="${pageContext.request.contextPath}/assets/ornamento-esq.png">

        <h1 class="title">ENTRAR</h1>

        <img class="ornamento-dir" src="${pageContext.request.contextPath}/assets/ornamento-esq.png">

    </div>

<!-- FORM LOGIN -->
<form action="login" method="POST">
    <div>
        <label CLASS="label">Email:</label>
        <input class="input" type="email" name="email" placeholder="Digite seu email" required>
    </div>
    <div>
        <label class="label">Senha:</label>
        <input class="input" type="password" name="senha" placeholder="Digite sua senha" required>
    </div>

    <br>

    <div class="buttons">
        <button class="button" type="submit">Entrar</button>
        <a class="a" href="${pageContext.request.contextPath}/register.jsp">
            <button class="button" type="button">Cadastre-se</button>
        </a>    </div>
</form>
    <br>
<a class="a" href="recoverPassword/reciveEmail.jsp">Esqueci minha senha</a>
</div>
</body>
</html>
