<%@ page import="com.example.minerva.dto.StudentHomeDTO" %>
<%@ page import="com.example.minerva.model.House" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    StudentHomeDTO dto = (StudentHomeDTO) request.getAttribute("homeDto");
    List<House> ranking = (List<House>) request.getAttribute("ranking");

    int id = dto.getId();
    String nome = dto.getName();
    String houseNameRaw = dto.getHouseName();

    String houseName = houseNameRaw.equals("Grifinória") ? "grifinoria" :
            houseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                    houseNameRaw.equals("Sonserina") ? "sonserina" : "corvinal";

    /* descobrir posição da casa */
    int posicao = 0;
    if (ranking != null) {
        for (int i = 0; i < ranking.size(); i++) {
            if (ranking.get(i).getName().equals(houseNameRaw)) {
                posicao = i + 1;
                break;
            }
        }
    }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Home do Aluno</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/students/home.css">
    <style>
        .logo {
            width: 190px;
            margin-bottom: -35px;
            justify-content: center;
        }
    </style>
</head>

<body class="<%=houseName%>">

<header>

    <div class="nav-links">

        <form action="${pageContext.request.contextPath}/aluno/home" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <button type="submit">Início</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/grades" method="post">
            <input type="hidden" name="id" value="<%=id%>">
                  <input type="hidden" name="name" value="<%=nome%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit">Boletim</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/subjects" method="post">
            <input type="hidden" name="id" value="<%=id%>">
                  <input type="hidden" name="name" value="<%=nome%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit">Matérias</button>
        </form>

    </div>

    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div id="aluno-perfil">

        <form action="${pageContext.request.contextPath}/aluno/profile" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit" style="all:unset; cursor:pointer; display:flex; align-items:center; gap:10px;">
                <%=nome%>
            </button>

        </form>

    </div>

</header>

<main>

    <h2>Bem-vindo a plataforma Minerva!</h2>
    <h1><%=nome%></h1>

    <div class="destaque-casa">

        <img src="${pageContext.request.contextPath}/assets/<%=houseName%>.png"
             class="brasao-grande">

        <div class="texto-casa">

            <h2>Sua casa está em:</h2>
            <h3 id="posicao"><%=posicao%>º Lugar</h3>
            <p>na taça das casas!</p>

        </div>
    </div>

    <div class="separador-titulo">
        <hr><p>Placar Geral</p><hr>
    </div>

    <%
        if (ranking != null) {
            for (int i = 0; i < ranking.size(); i++) {

                House casa = ranking.get(i);
                String cssCasa = casa.getName().equals("Grifinória") ? "grifinoria" :
                        casa.getName().equals("Lufa-Lufa") ? "lufa-lufa" :
                                casa.getName().equals("Sonserina") ? "sonserina" : "corvinal";
    %>

    <p class="rank"><%=i+1%>º</p>

    <div class="barra <%=cssCasa%>">

        <img src="${pageContext.request.contextPath}/assets/casas/<%=cssCasa%>.png">

        <span><%=casa.getName()%></span>

        <span><%=casa.getPoints()%></span>

    </div>

    <%
            }
        }
    %>

</main>
<br>
<br>
<br>
</body>
</html>