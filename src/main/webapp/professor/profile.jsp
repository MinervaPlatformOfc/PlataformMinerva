<%@ page import="com.example.minerva.dto.TeacherProfileDTO" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%@ page import="com.example.minerva.model.User" %><%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 19/02/2026
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");%>
<header style="display: flex;justify-content: space-around">
  <form action="${pageContext.request.contextPath}/professor/home" method="post" >
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <button type="submit">Home</button>
  </form>
  <form action="${pageContext.request.contextPath}/professor/yearsSubjects" method="post" >
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <input type="hidden"  name="houseName" value="<%=houseName%>">
    <button type="submit">Observações</button>
  </form>
  <form action="${pageContext.request.contextPath}/professor/yearsSubjects" method="post" >
    <input type="hidden" name="showGrades" value="true">
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <input type="hidden"  name="houseName" value="<%=houseName%>">
    <button type="submit">Notas</button>
  </form>
  <form action="${pageContext.request.contextPath}/professor/profile" method="post" >
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <input type="hidden"  name="houseName" value="<%=houseName%>">
    <button type="submit">Perfil</button>
  </form>
</header>
<%
  TeacherProfileDTO profile = (TeacherProfileDTO) request.getAttribute("profile");
  List<String> subjects = profile.getSubjects();
%>
<h1>Perfil do Professor: <%= profile.getName() %> (<%= profile.getWizardTitle() %>)</h1>
<img src="<%= profile.getImageUrl() %>" style="max-width: 150px; max-height: 150px;">

<p><strong>Casa:</strong> <%= profile.getHouseName() %> <%= profile.isHeadHouse() ? "(Chefe da casa)" : "" %></p>

<h2>Varinha</h2>
<p><strong>Madeira:</strong> <%= profile.getWandWood() %></p>
<p><strong>Núcleo:</strong> <%= profile.getWandCore() %></p>
<p><strong>Flexibilidade:</strong> <%= profile.getWandFlexibility() %></p>

<h2>Experiência e Registro</h2>
<p><strong>Experiências Passadas:</strong> <%= profile.getPastExperiences() != null ? profile.getPastExperiences() : "-" %></p>
<p><strong>Código de Registro:</strong> <%= profile.getTeacherRegistrationCode() %></p>

<h2>Matérias</h2>
<ul>
  <%
    for (String subject : subjects) {
  %>
  <li><%=subject%></li>
  <%
    }
  %>
</ul>

<form action="logout" method="post">
  <button type="submit">Sair</button>
</form>
</body>
</html>
