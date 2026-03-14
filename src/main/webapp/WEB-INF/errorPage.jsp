<%@ page import="com.example.minerva.dto.ErrorDTO" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<% ErrorDTO error = (ErrorDTO) request.getAttribute("error"); %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <title>Erro</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/error.css">

</head>

<body>

<div class="page">

    <!-- LOGO -->
    <img class="logo"
         src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png"
         alt="logo">


    <!-- TITULO COM ORNAMENTOS -->
    <div class="title-container">

        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png"
             class="ornamento-esq"
             alt="ornamento">

        <h1 class="error-code">
            Erro <%= error.getStatusCode() %>
        </h1>

        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png"
             class="ornamento-dir"
             alt="ornamento">

    </div>


    <!-- CAIXA DO ERRO -->
    <div class="error-box">

        <p class="main-message">
            Algo deu errado, por favor reinicie a página.<br>
            Caso o erro persista, entre em contato com a secretária de Hogwarts.
        </p>

        <div class="details">

            <p>
                <b>Mensagem:</b>
                <span class="error-message">
                    <%= error.getMessage() %>
                </span>
            </p>

            <p>
                <b>URI da requisição:</b>
                <%= error.getRequestUri() %>
            </p>

            <p>
                <%= error.getException() != null ? "Erro: " + error.getException() : "" %>
            </p>

        </div>

        <!-- BOTÃO -->
        <button class="retry-button" onclick="location.reload()">
            Reiniciar página
        </button>

    </div>

</div>

</body>
</html>
