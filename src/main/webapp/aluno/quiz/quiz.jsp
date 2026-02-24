<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/aluno/quiz/quiz.css">
</head>
<body>
    <section id="apresentacao">
        <h1>Bem-Vindo a Plataforma Minerva!</h1>
        <h2>Descubra sua <span>casa</span> pelo nosso quiz</h2>
        <button>Vamos lá!</button>
    </section>
    <section id="perguntas" class="escondido">
        <div>
            <p>Em um exame difícil, você percebe que um colega está usando um feitiço de cola. O que você faz?</p>
            <input type="button" value="Não digo nada; cada um usa as armas que tem para vencer">
            <input type="button" value="Sinto pena, mas não conto para o professor; não quero prejudicá-lo">
            <input type="button" value="Fico curioso para saber que feitiço é esse e como ele funciona">
            <input type="button" value="Confronto o colega na hora; não é justo com os outros">
        </div>
    </section>
    <section id="resposta" class="escondido">
        <h1>Parabéns sua casa é...</h1>
        <h2></h2>
        <legend>
            <img src="" alt="">
            <p></p>
        </legend>
    </section>


    <div id="botoesResultado" class="escondido">
        <form action="${pageContext.request.contextPath}/aluno/quiz" method="post">
            <input type="hidden" name="houseName" id="houseName" value="">
            <input type="hidden" id="emailUsuario" name="email" value="<%= (String) request.getAttribute("email")%>">
            <button id="aprovarResultado" type="submit">Aprovar resultado</button>
        </form>
        <button id="recomecarQuiz">Recomeçar</button>
    </div>
    <script src="${pageContext.request.contextPath}/aluno/quiz/quiz.js"></script>
    <script>
        const contextPath = "<%= request.getContextPath() %>";
    </script>
</body>
</html>