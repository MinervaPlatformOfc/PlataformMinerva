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
<header>
    <%String name = (String) request.getAttribute("name");%>
    <%String url = (String) request.getAttribute("url");%>
    <form action="${pageContext.request.contextPath}/admin/users" method="post">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">HOME</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewAdmins" method="get">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Admins</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewTeachers" method="get">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Teachers</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewSubjects" method="post">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Subjects</button>
    </form>

    <form action="${pageContext.request.contextPath}/admin/ViewStudents" method="get">
        <input type="hidden" name="name" value="<%=name%>">
        <input type="hidden" name="url" value="<%=url%>">
        <button type="submit">CRUD Subjects</button>
    </form>

    <form action="${pageContext.request.contextPath}/recharge" method="post">
        <button type="submit">Atualizar dados</button>
    </form>

    <form action="${pageContext.request.contextPath}/logout" method="post">
        <button type="submit">Sair</button>
    </form>
</header>
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
