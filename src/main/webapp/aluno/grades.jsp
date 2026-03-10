<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int id = (int) request.getAttribute("id");
    String houseName = (String) request.getAttribute("houseName");
    List<StudentGradeDTO> grades = (List<StudentGradeDTO>) request.getAttribute("grades");

    String name =  (String) request.getAttribute("name");
%>

<!DOCTYPE html>
<html lang="pt-br">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Boletim</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/students/grades.css">

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
            <button type="submit" id="atual">Boletim</button>
        </form>

        <form action="${pageContext.request.contextPath}/aluno/subjects" method="post">
            <input type="hidden" name="id" value="<%=id%>">
                  <input type="hidden" name="name" value="<%=name%>">
            <input type="hidden" name="houseName" value="<%=houseName%>">
            <button type="submit">Matérias</button>
        </form>
    </div>

    <img src="${pageContext.request.contextPath}/images/logo.png" alt="logo-Minerva">

    <div id="aluno-perfil">
    <form action="${pageContext.request.contextPath}/aluno/profile" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="houseName" value="<%=houseName%>">
        <button type="submit"><%=name%><svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960" fill="#FFFFFF">
            <path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Z"/>
        </svg></button>
    </form>
    </div>

</header>


<main>

    <div id="boletim">

        <div id="boletim-header">

            <span class="col-materia">Boletim</span>
            <span class="col-n">N1</span>
            <span class="col-n">N2</span>
            <span class="col-n">Média</span>

        </div>

        <%
            if (grades != null && !grades.isEmpty()) {

                for (StudentGradeDTO grade : grades) {
        %>

        <div class="barra">

            <span class="materia"><%= grade.getSubjectName() %></span>

            <div class="notas">

<span class="notaN1">
<%= grade.getN1() != null ? grade.getN1() : "-" %>
</span>

<span class="notaN2">
<%= grade.getN2() != null ? grade.getN2() : "-" %>
</span>

<span class="notaMedia">
<%= grade.getMedia() != null ? String.format("%.2f", grade.getMedia()) : "-" %>
</span>

            </div>

        </div>

        <%
            }
        } else {
        %>

        <div class="barra">
            <span>Nenhuma nota disponível</span>
        </div>

        <%
            }
        %>

    </div>

</main>

</body>
</html>