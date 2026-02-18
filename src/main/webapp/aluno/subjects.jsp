<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Matérias do Aluno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }
        h1 { color: #333; margin-bottom: 20px; }
        ul { list-style-type: none; padding: 0; }
        li { background-color: #fff; margin-bottom: 8px; padding: 10px; border: 1px solid #999; border-radius: 4px; }
        li:hover { background-color: #e0f0ff; cursor: pointer; }
        a { text-decoration: none; color: #333; display: block; }
    </style>
</head>
<body>
<header style="display: flex;justify-content: space-around">
    <%int id = (int) request.getAttribute("id");%>
    <%String houseName = request.getAttribute("houseName");%>

    <form action="${pageContext.request.contextPath}/aluno/home" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Home</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/grades" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <input type="hidden"  name="houseName" value="<%=houseName%>">
        <button type="submit">Boletim</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/subjects" method="post" >
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
<h1>Matérias do Aluno</h1>

<ul>
    <%
        List<SubjectDTO> subjects = (List<SubjectDTO>) request.getAttribute("subjects");
        if (subjects != null && !subjects.isEmpty()) {
            for (SubjectDTO subject : subjects) {
    %>
    <li>
        <form action="${pageContext.request.contextPath}/aluno/subject" method="post" style="margin:0; padding:0;">
            <input type="hidden" name="id" value="<%= id %>">
            <input type="hidden" name="subjectId" value="<%= subject.getSubjectId() %>">
            <input type="hidden"  name="houseName" value="<%=houseName%>">
            <button type="submit" style="all: unset; cursor: pointer;">
                <%= subject.getSubjectName() %>
            </button>
        </form>
    </li>
    <%
        }
    } else {
    %>
    <li>Nenhuma matéria disponível</li>
    <%
        }
    %>
</ul>
</body>
</html>
