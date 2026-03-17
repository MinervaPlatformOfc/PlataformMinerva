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
</head>

<body class="<%=houseName%>">

<header>

    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="54px" fill="#C7D9E5" class="escondido" id="menu-sandwich"><path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/></svg>

    <div class="nav-links">

        <form action="${pageContext.request.contextPath}/aluno/home" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <button type="submit" id="atual">Início</button>
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

    <img class="logo-central" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div id="aluno-perfil">

        <form action="${pageContext.request.contextPath}/aluno/profile" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit" style="all:unset; cursor:pointer; display:flex; align-items:center; gap:10px;">
                <%=nome%>
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#C7D9E5"><path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/></svg>
            </button>
        </form>
    </div>
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960" id="chat" fill="#C7D9E5"><path d="M240-400h320v-80H240v80Zm0-120h480v-80H240v80Zm0-120h480v-80H240v80ZM80-80v-720q0-33 23.5-56.5T160-880h640q33 0 56.5 23.5T880-800v480q0 33-23.5 56.5T800-240H240L80-80Zm126-240h594v-480H160v525l46-45Zm-46 0v-480 480Z"/></svg>
</header>


<aside class="sidebar">

    <form action="${pageContext.request.contextPath}/aluno/home" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        <button type="submit" id="atual">Início</button>
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
</aside>

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
                        casa.getName().equals("Lufa-Lufa") ? "lufalufa" :
                                casa.getName().equals("Sonserina") ? "sonserina" : "corvinal";
    %>

    <p class="rank"><%=i+1%>º</p>

    <div class="barra <%=cssCasa%>">

        <img src="${pageContext.request.contextPath}/assets/<%=cssCasa%>.png">

        <span><%=casa.getName()%></span>

        <span><%=casa.getPoints()%></span>

    </div>

    <%
            }
        }
    %>

</main>
<script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
<script>
    const chat = document.querySelector('#chat');

    chat.addEventListener('click', ()=>{
        window.location.href = '${pageContext.request.contextPath}/aluno/frames.jsp';
    });
</script>
</body>
</html>