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
<header style="display: flex;justify-content: space-around">
    <%int id = (int) request.getAttribute("id");%>
    <%String houseName = (String) request.getAttribute("houseName");%>
    <%String name = (String) request.getAttribute("name");%>

    <form action="${pageContext.request.contextPath}/aluno/home" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Home</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/grades" method="post" >
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden"  name="id" value="<%=id%>">
        <input type="hidden"  name="houseName" value="<%=houseName%>">
        <button type="submit">Boletim</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/subjects" method="post" >
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden"  name="id" value="<%=id%>">
        <input type="hidden"  name="houseName" value="<%=houseName%>">
        <button type="submit">Matérias</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/profile" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <input type="hidden"  name="houseName" value="<%=houseName%>">
        <button type="submit">Perfil</button>
    </form>
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
