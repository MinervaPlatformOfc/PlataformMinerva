<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.view.StudentForTeacherView" %>
<%@ page import="static jdk.internal.org.jline.utils.Colors.h" %><%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 05/02/2026
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .link-student {
            text-decoration: none;  /* tira o sublinhado */
            color: black;           /* tira o azul */
        }
    </style>
</head>
<body>
<header>
    <a href="home.jsp">inicio</a>
    <a href="comment.jsp">observacoes</a>
    <a href="home.jsp">notas</a>
    <a href="">perfil professor</a>
</header>
<br>
<% String name = (String) session.getAttribute("name");%>
<h1>SEJA BEM VINDO <%= name %></h1>
<%
    List<StudentForTeacherView> alunos =
            (List<StudentForTeacherView>) session.getAttribute("studentList");

    if (alunos != null && !alunos.isEmpty()) {
%>
<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>acao</th>
    </tr>
    </thead>
    <tbody>
    <%
        for (StudentForTeacherView aluno : alunos) {
    %>
    <tr>

        <td>
            <%= aluno.getName() %>
        </td>
        <td>
            <form action="${pageContext.request.contextPath}/teacher/findStudent">
                <input type="hidden" name="id" value="<%= aluno.getId_user()%>">
                <button type="submit">ver mais</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>

    </tbody>
</table>

<%
} else {
%>
<h3>Sem alunos no sistema</h3>
<%
    }
%>

</body>
</html>



