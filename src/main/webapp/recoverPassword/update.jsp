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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/generic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/update.css">
    <link href="https://fonts.googleapis.com/css2?family=Almendra&display=swap" rel="stylesheet">
</head>
<body>
<div class="page">

    <img class="logo" src="" alt="IMAGEM JOAOOOO">

    <div class="title-container">

        <img class="ornamento-esq" src="../assets/ornamento-esq.png">

        <h1 class="title">ESQUECI MINHA SENHA</h1>

        <img class="ornamento-dir" src="../assets/ornamento-esq.png">

    </div>


<%
    String email = (String) request.getAttribute("email");
%>
<form action="${pageContext.request.contextPath}/recoverPassword/updatePassword" method="post">
    <input type="hidden" value="<%=email%>" name="email">
    <label class="label">
        Nova senha:<input class="input" type="password" name="password" placeholder="Digite a nova senha que você desejar" required minlength="8" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':&quot;\\|,.<>\/?]).+$">
    </label>
    <br>

    <div class="info">
        <h3 class="info-title">Informe sua nova senha</h3>

        <p>A senha deve conter:</p>

        <ul class="password-rules">
            <li>No mínimo 8 caracteres</li>
            <li>1 letra maiúscula</li>
            <li>1 letra minúscula</li>
            <li>1 caractere especial</li>
        </ul>
    </div>


    <div class="buttons">
        <button class="button" type="submit">Alterar senha</button>
        <a href="${pageContext.request.contextPath}/recoverPassword/reciveEmail.jsp"><button class="button" type="button">Voltar</button></a>
    </div>
</form>
</div>
</body>
<script>
    const inputs = document.querySelectorAll(".codigo input");

    inputs.forEach((input, index) => {
        input.addEventListener("input", () => {
            if(input.value.length === 1 && inputs[index + 1]){
                inputs[index + 1].focus();
            }
        });
    });


    const inputs = document.querySelectorAll(".digit");
    const codigoFinal = document.getElementById("codigoFinal");

    function atualizarCodigo(){
        let codigo = "";
        inputs.forEach(input => codigo += input.value);
        codigoFinal.value = codigo;
    }

    inputs.forEach(input => {
        input.addEventListener("input", atualizarCodigo);
    });
</script>
</html>
