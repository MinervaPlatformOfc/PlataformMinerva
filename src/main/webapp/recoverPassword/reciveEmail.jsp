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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/generic.css">
    <link href="https://fonts.googleapis.com/css2?family=Almendra&display=swap" rel="stylesheet">
</head><body>

<div class="page">

    <img class="logo" src="" alt="IMAGEM JOAOOOO">

    <div class="title-container">

        <img class="ornamento-esq" src="../assets/ornamento-esq.png">

        <h1 class="title">ESQUECI MINHA SENHA</h1>

        <img class="ornamento-dir" src="../assets/ornamento-esq.png">

    </div>

    <div class="info-box">
        Para redefinir sua senha, informe seu e-mail cadastrado na sua conta e lhe enviaremos um código.
    </div>

    <form action="${pageContext.request.contextPath}/recoverPassword" method="post">

        <label class="label">Email</label>
        <div class="input-group">
            <input class="input" type="email" name="email" placeholder="Digite seu email">
        </div>

        <div class="buttons">
            <button class="button" type="submit">Enviar email</button>
            <a href="${pageContext.request.contextPath}/login.jsp"><button class="button" type="button">Voltar</button></a>
        </div>

    </form>

</div>

</body>
</html>
