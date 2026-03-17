<%@ page import="com.example.minerva.dto.CommentDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = (int) request.getAttribute("id");
    String houseName = (String) request.getAttribute("houseName");
    String name = (String) request.getAttribute("name");
    SubjectDTO subject = (SubjectDTO) request.getAttribute("subject");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observações</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/students/subject.css">

    <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&family=Hermeneus+One&display=swap" rel="stylesheet">
</head>

<body class="<%=houseName.toLowerCase()%>">
<header>

    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="54px" fill="#C7D9E5" class="escondido" id="menu-sandwich"><path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/></svg>

    <div class="nav-links">

        <form action="${pageContext.request.contextPath}/aluno/home" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <button type="submit">Início</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/grades" method="post">
            <input type="hidden" name="name" value="<%=name%>">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit">Boletim</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/subjects" method="post">
            <input type="hidden" name="name" value="<%=name%>">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit">Matérias</button>
        </form>

    </div>


        <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png">

    <div id="aluno-perfil">

        <form action="${pageContext.request.contextPath}/aluno/profile" method="post">

            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">

            <button type="submit" style="all:unset; cursor:pointer; display:flex; align-items:center; gap:10px;">

                <%=name%>

                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#C7D9E5"><path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/></svg>

            </button>

        </form>

    </div>

</header>

<aside class="sidebar">
    <form action="${pageContext.request.contextPath}/aluno/home" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        <button type="submit">Início</button>
    </form>

    <form action="${pageContext.request.contextPath}/aluno/grades" method="post">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="houseName" value="<%=houseName%>">
        <button type="submit">Boletim</button>
    </form>

    <form action="${pageContext.request.contextPath}/aluno/subjects" method="post">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="houseName" value="<%=houseName%>">
        <button type="submit">Matérias</button>
    </form>
</aside>

<main>

    <aside class="conteudo">

        <h1>Conteúdos</h1>

        <%
            if(subject != null){
        %>

        <div class="barra">
            <span><%=subject.getSubjectName()%></span>
        </div>

        <%
            }
        %>

    </aside>


    <div class="divisor"></div>


    <aside class="observacoes">

        <h1>Observações</h1>

        <%
            if(subject != null){

                ArrayList<CommentDTO> comments = subject.getComments();

                if(comments != null && !comments.isEmpty()){

                    for(CommentDTO comment : comments){

                        Integer score = comment.getScore();
                        String color = "gray";

                        if(score != null){
                            if(score > 0){
                                color = "green";
                            }else if(score < 0){
                                color = "red";
                            }
                        }
        %>

        <div class="barra">

            <h1>Comentário</h1>

            <p>
                <%= comment.getContent() %>
            </p>

            <p class="pontos" style="color:<%=color%>">
                <%= score != null ? score : "-" %>
            </p>

        </div>

        <%
            }

        }else{
        %>

        <div class="barra">
            <p>Nenhum comentário disponível</p>
        </div>

        <%
            }

        }else{
        %>

        <div class="barra">
            <p>Matéria não encontrada</p>
        </div>

        <%
            }
        %>

    </aside>

</main>
<script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
</body>
</html>
