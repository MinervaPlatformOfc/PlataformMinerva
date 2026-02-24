<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.view.StudentForTeacherView" %>
<%@ page import="static jdk.internal.org.jline.utils.Colors.h" %>
<%@ page import="com.example.minerva.model.User" %><%--
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
</head>
<>
<header>
    <a href="home.jsp">inicio</a>
    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=comment">observacoes</a>
    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=grade">notas</a>
    <a href="${pageContext.request.contextPath}/professorteacher_profile.jsp">perfil professor</a>
</header>
<%--ERICK E FARIAS PONHAM O FILTRO DE JAVA SCRIPTTTTTTT--%>
<br>
<% User user = (User) session.getAttribute("user");%>
<h1>SEJA BEM VINDO <%= user.getName() %></h1>
<h5>filtro js</h5>
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



