<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>quiz</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/generic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/aluno/quiz/quiz.css">
    <link href="https://fonts.googleapis.com/css2?family=Almendra&display=swap" rel="stylesheet">

</head>
<body>
<section id="apresentacao" class="page">

    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div class="title-container">
        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png" class="ornamento-esq">
        <h1 class="title">Chapéu Seletor</h1>
        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png" class="ornamento-dir">
    </div>

    <div class="seletor-container">
        <div class="seletor">
            <p class="p">
            Ah, vocês podem me achar pouco atraente,
            Mas não me julguem só pela aparência
            Engulo a mim mesmo se puderem encontrar
            Um chapéu mais inteligente que o papai aqui,
            Podem guardar seus chapéus-coco bem pretos,
            Suas cartolas altas de cetim brilhoso
            Porque eu sou o chapéu seletor de hogwarts
            e dou de dez a zero em qualquer outro chapéu.
            Não há nada escondido em sua cabeça
            Que o chapéu seletor não consiga ver,
            Por isso é só me porem na cabeça que eu vou dizer
            Em que casa de hogwarts deverão ficar

            Vamos, me experimentem! Não devem temer!
            Nem se atrapalhar! Estarão em boas mãos!
            (Mesmo que chapéus não tenham pés nem mãos)
            Porque sou único, sou um Chapéu Pensador!
         </p>
        </div>
        <img src="${pageContext.request.contextPath}/aluno/quiz/imgs/Chapeu%20seletor.png" class="img-seletor">

    </div>

    <button class="button" id="botaoIniciar">
        Iniciar Questionário
    </button>

</section>
<section id="perguntas" class="page escondido">
    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div class="title-container">
        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png" class="ornamento-esq">
        <h1 class="title">Pergunta</h1>
        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png" class="ornamento-dir">
    </div>

    <div class="info-box-question">
        <p id="textoPergunta">
            Em um exame difícil, você percebe que um colega está usando um feitiço de cola. O que você faz?
        </p>


    </div>
    <div class="input-group-resposta">
        <input type="button" class="button" value="Não digo nada; cada um usa as armas que tem para vencer">
        <input type="button" class="button" value="Sinto pena, mas não conto para o professor">
        <input type="button" class="button" value="Fico curioso para saber que feitiço é esse">
        <input type="button" class="button" value="Confronto o colega na hora">
    </div>


</section>
<section id="resposta" class="page escondido">
    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div class="title-container">
        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png" class="ornamento-esq">
        <h1 class="title">Resultado</h1>
        <img src="${pageContext.request.contextPath}/assets/ornamento-esq.png" class="ornamento-dir">
    </div>

    <div class="resultado-container">

        <img class="img" id="imgCasa" src="">

        <div class="info-box">
            <h2 id="casaResultado"></h2>
            <p class="p" id="textoResultado"></p>
        </div>

    </div>
</section>


    <div id="botoesResultado" class="escondido buttons">
        <form action="${pageContext.request.contextPath}/aluno/quiz" method="post">
            <input type="hidden" name="houseName" id="houseName" value="">
            <input type="hidden" id="emailUsuario" name="email" value="<%= (String) request.getAttribute("email")%>">
            <button class="button" id="aprovarResultado" type="submit">Aprovar resultado</button>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/aluno/quiz/quiz.js"></script>
    <script>
        const contextPath = "<%= request.getContextPath() %>";
    </script>
</body>
</html>