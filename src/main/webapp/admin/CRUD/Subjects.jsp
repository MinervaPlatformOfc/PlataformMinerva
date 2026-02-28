<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 25/02/2026
  Time: 08:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Lista de Disciplinas</h2>

<%
    List<SubjectDTO> subjects = (List<SubjectDTO>) request.getAttribute("subjects");

    if (subjects == null || subjects.isEmpty()) {
%>
<p>Nenhuma disciplina encontrada.</p>
<%
} else {
%>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Nome</th>
    </tr>

    <%
        for (SubjectDTO subject : subjects) {
    %>
    <tr>
        <td><%= subject.getSubjectId() %></td>
        <td><%= subject.getSubjectName() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
