<%@ page import="com.example.minerva.dto.CommentDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = (int) request.getAttribute("id");
    String houseName = (String) request.getAttribute("houseName");
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

    <div>
        <p id="materia-nome">
            <%= subject != null ? subject.getSubjectName() : "Matéria" %>
        </p>
    </div>

    <img src="" alt="logo-Minerva" class="logo-central">

    <div id="aluno-perfil">

        <form action="${pageContext.request.contextPath}/aluno/subjects" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">

            <button type="submit" style="all:unset; cursor:pointer;">
                Voltar
            </button>
        </form>

        <svg xmlns="http://www.w3.org/2000/svg" height="24px"
             viewBox="0 -960 960 960" width="24px" fill="#FFFFFF">
            <path d="M400-120 160-360l241-241 56 57-144 144h367v-400h80v480H313l144 143-57 57Z"/>
        </svg>

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