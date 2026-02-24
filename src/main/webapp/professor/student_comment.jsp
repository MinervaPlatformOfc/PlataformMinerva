<%@ page import="com.example.minerva.view.StudentForTeacherView" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 20/02/2026
  Time: 00:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--ERICK E FARIAS PONHAM O FILTRO DE JAVA SCRIPTTTTTTT--%>
<header>
    <a href="home.jsp">inicio</a>
    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=comment">observacoes</a>
    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=grade">notas</a>
    <a href="${pageContext.request.contextPath}/professor/teacher_profile.jsp">perfil professor</a>
</header>
<%

    List<StudentForTeacherView> alunos =
            (List<StudentForTeacherView>) request.getAttribute("list");

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
            <form action="${pageContext.request.contextPath}/teacher/ListComment" method="get">
                <input type="hidden" name="id" value="<%= aluno.getId_user()%>">
                <input type="hidden" name="house_id" value="<%= aluno.getId_house()%>">
                <input type="hidden" name="student_id" value="<%= aluno.getId_student()%>">
                <input type="hidden" name="path" value="/professor/comment.jsp">
                <button type="submit"> adicionar comentario!!! </button>
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
