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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/generic.css">
    <link href="https://fonts.googleapis.com/css2?family=Almendra&display=swap" rel="stylesheet">
</head>
<body>
<div class="page">
    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div class="title-container">

        <img class="ornamento-esq" src="${pageContext.request.contextPath}/assets/ornamento-esq.png">

        <h1 class="title">ESQUECI MINHA SENHA</h1>

        <img class="ornamento-dir" src="${pageContext.request.contextPath}/assets/ornamento-esq.png">

    </div>

    <div class="info-box">
        Insira o código informado
    </div>
<%
    String email = (String) request.getAttribute("email");
%>
<form action="${pageContext.request.contextPath}/recoverPassword/validateOtp" method="post" class="codigo">
    <input type="hidden" value="<%=email%>" name="email">

    <label class="label">Código:</label>
    <div class="input-group">
        <input type="text" class="digit" name="number1" maxlength="1" inputmode="numeric" pattern="[0-9]">
        <input type="text" class="digit" name="number2" maxlength="1" inputmode="numeric" pattern="[0-9]">
        <input type="text" class="digit" name="number3" maxlength="1" inputmode="numeric" pattern="[0-9]">
        <input type="text" class="digit" name="number4" maxlength="1" inputmode="numeric" pattern="[0-9]">
        <input type="text" class="digit" name="number5" maxlength="1" inputmode="numeric" pattern="[0-9]">
        <input type="text" class="digit" name="number6" maxlength="1" inputmode="numeric" pattern="[0-9]">
    </div>
    <div class="buttons">
        <button class="button" type="submit">Enviar código</button>
        <a href="${pageContext.request.contextPath}/recoverPassword/reciveEmail.jsp"><button class="button" type="button">Voltar</button></a>
    </div>

</form>
</div>
</body>
<script>
    const inputs = document.querySelectorAll(".digit");

    inputs.forEach((input, index) => {
        input.addEventListener("input", () => {
            if(input.value.length === 1 && inputs[index + 1]){
                inputs[index + 1].focus();
            }
        });
        input.addEventListener("keydown", (e) => {

            if(e.key === "Backspace" && input.value === "" && inputs[index - 1]){
                inputs[index - 1].focus();
            }

        });
    });
</script>
</html>
