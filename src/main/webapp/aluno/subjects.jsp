<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = (int) request.getAttribute("id");
    String houseName = (String) request.getAttribute("houseName");
    String name = (String) request.getAttribute("name");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Matérias</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Almendra:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/students/subjects.css">
    <style>
        .logo {
            width: 190px;
            margin-bottom: -35px;
            justify-content: center;
        }
    </style>
</head>

<body class="<%= houseName %>">

<header>

    <div class="nav-links">

        <form action="${pageContext.request.contextPath}/aluno/home" method="post">
            <input type="hidden" name="id" value="<%=id%>">
            <button type="submit">Início</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/grades" method="post">
            <input type="hidden" name="id" value="<%=id%>">
                  <input type="hidden" name="name" value="<%=name%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit">Boletim</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/subjects" method="post">
            <input type="hidden" name="id" value="<%=id%>">
                  <input type="hidden" name="name" value="<%=name%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit" id="atual">Matérias</button>
        </form>

    </div>

    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div id="aluno-perfil">

        <form action="${pageContext.request.contextPath}/aluno/profile" method="post">

            <input type="hidden" name="id" value="<%=id%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">

            <button type="submit" style="all:unset; cursor:pointer; display:flex; align-items:center; gap:10px;">
                <%=name%>
            </button>

        </form>

    </div>

</header>

<main>

    <div class="container-grid">

        <%
            List<SubjectDTO> subjects = (List<SubjectDTO>) request.getAttribute("subjects");
            if(subjects != null && !subjects.isEmpty()){
                for(SubjectDTO subject : subjects){
        %>

        <form class="materia"
              action="${pageContext.request.contextPath}/aluno/subject"
              method="post">

            <input type="hidden" name="id" value="<%= id %>">
            <input type="hidden" name="subjectId" value="<%= subject.getSubjectId() %>">
            <input type="hidden" name="subjectName" value="<%= subject.getSubjectName() %>">
            <input type="hidden" name="houseName" value="<%= houseName %>">
                  <input type="hidden" name="name" value="<%=name%>">

            <button type="submit" style="all:unset; width:100%; height:100%; cursor:pointer;">

                <img src="${pageContext.request.contextPath}/images/materia.png" alt="A IMAGEM JOAO">

                <div class="nome-Materia">

<span>
<%= subject.getSubjectName() %>
</span>

                </div>

            </button>

        </form>

        <%
            }
        }else{
        %>

        <p>Nenhuma matéria encontrada.</p>

        <%
            }
        %>

    </div>

</main>

</body>
</html>