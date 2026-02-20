<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="com.example.minerva.dto.ProfileDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 19/02/2026
  Time: 18:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");
  ProfileDTO profile = (ProfileDTO) request.getAttribute("profile");
  List<SubjectDTO> grades = profile.getGrades();
%>
<h1>Perfil do Aluno: <%= profile.getName() %></h1>
<img src="<%= profile.getImageUrl() %>"  style="max-width: 150px; max-height: 150px;">


<p><strong>Data de Nascimento:</strong> <%= profile.getBirthDate() %></p>
<p><strong>Status de Sangue:</strong> <%= profile.getBloodStatus() %></p>
<p><strong>Matrícula:</strong> <%= profile.getRegistration() %></p>
<p><strong>Endereço:</strong> <%= profile.getResidenceAddress() %></p>
<p><strong>Responsável:</strong> <%= profile.getGuardianName() %></p>
<p><strong>Ano Escolar:</strong> <%= profile.getSchoolYear() %></p>
<p><strong>Casa:</strong> <%= profile.getHouseName() %></p>

<h2>Varinha</h2>
<p><strong>Madeira:</strong> <%= profile.getWandWood() %></p>
<p><strong>Núcleo:</strong> <%= profile.getWandCore() %></p>
<p><strong>Flexibilidade:</strong> <%= profile.getWandFlexibility() %></p>

<h2>Outras Informações</h2>
<p><strong>Animal de Estimação:</strong> <%= profile.getPetType() %></p>
<p><strong>Voo Autorizado:</strong> <%= profile.isFlightFitness() ? "Sim" : "Não" %></p>
<p><strong>Kit Básico:</strong> <%= profile.isBasicKit() ? "Sim" : "Não" %></p>
<p><strong>Alergias:</strong> <%= profile.getAllergies().equals("") ? "Nenhuma alergia registrada!" : profile.getAllergies()%></p>
<p><strong>Permissão do Responsável:</strong> <%= profile.isGuardianPermission() ? "Sim" : "Não" %></p>

<h2>Matérias</h2>
<ul>
  <%
    for (SubjectDTO grade : grades) {
  %>
  <li><%= grade.getSubjectName() %></li>
  <%
    }
  %>
</ul>

<hr><br>
<form action="<%= request.getContextPath() %>/professor/home" method="post">
  <input type="hidden" name="teacherId" value="<%= teacherId %>">
  <button type="submit">Voltar</button>
</form>
</body>
</html>
