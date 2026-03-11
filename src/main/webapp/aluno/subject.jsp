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
    <style>
        .logo {
            width: 190px;
            margin-bottom: -35px;
            justify-content: center;
        }
    </style>
</head>

<body class="<%=houseName.toLowerCase()%>">
<header>

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

                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960" fill="#FFFFFF">
                    <path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Z"/>
                </svg>

            </button>

        </form>

    </div>

</header>

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

</body>
</html>
