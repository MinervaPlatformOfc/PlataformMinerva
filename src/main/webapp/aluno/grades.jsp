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

    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="54px" fill="#C7D9E5" class="escondido" id="menu-sandwich"><path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/></svg>

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

    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div id="aluno-perfil">
    <form action="${pageContext.request.contextPath}/aluno/profile" method="post">
        <input type="hidden" name="id" value="<%=id%>">
        <input type="hidden" name="houseName" value="<%=houseName%>">
        <button type="submit"><%=name%> </button>
    </form>
        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#C7D9E5"><path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/></svg>
    </div>

</header>


<aside class="sidebar">
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
</aside>

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
<script src="${pageContext.request.contextPath}/js/sidebar.js"></script>
</body>
</html>