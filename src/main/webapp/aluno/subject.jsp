<%@ page import="com.example.minerva.dto.CommentDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalhes da Matéria</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }
        h1 { color: #333; margin-bottom: 10px; }
        h2 { color: #555; margin-top: 20px; }
        ul { list-style-type: none; padding: 0; }
        li { background-color: #fff; margin-bottom: 8px; padding: 10px; border: 1px solid #999; border-radius: 4px; }
        .score { font-weight: bold; color: #0077cc; }
        .date { font-size: 0.9em; color: #999; }
    </style>
</head>
<body>
<header style="display: flex;justify-content: space-around">
    <%int id = (int) request.getAttribute("id");%>
    <form action="${pageContext.request.contextPath}/aluno/home" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Home</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/grades" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Boletim</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/subjects" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Matérias</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/profile" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Perfil</button>
    </form>
</header>
<%
    SubjectDTO subject = (SubjectDTO) request.getAttribute("subject");
    if (subject != null) {
%>
<h1>Matéria: <%= subject.getSubjectName() %></h1>
<h2>Comentários:</h2>

<ul>
    <%
        ArrayList<CommentDTO> comments = subject.getComments();
        if (comments != null && !comments.isEmpty()) {
            for (CommentDTO comment : comments) {
    %>
    <li>
        <p><%= comment.getContent() %></p>
        <p class="score">Nota: <%= comment.getScore() != null ? comment.getScore() : "-" %></p>
        <p class="date">Data: <%= comment.getCreatedAt() != null ? comment.getCreatedAt() : "-" %></p>
    </li>
    <%
        }
    } else {
    %>
    <li>Nenhum comentário disponível</li>
    <%
        }
    %>
</ul>
<%
} else {
%>
<p>Matéria não encontrada.</p>
<%
    }
%>
</body>
</html>
