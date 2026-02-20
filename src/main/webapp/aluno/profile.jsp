<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.minerva.dto.ProfileDTO" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%
    ProfileDTO profile = (ProfileDTO) request.getAttribute("profile");
    List<SubjectDTO> grades = profile.getGrades();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Perfil do Aluno</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 20px; }
        h1 { color: #333; }
        ul { margin-top: 10px; }
        li { padding: 4px 0; }
    </style>
</head>
<body>
<header style="display: flex;justify-content: space-around">
    <%int id = (int) request.getAttribute("id");%>
    <%String houseName = (String) request.getAttribute("houseName");%>

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
<form action="logout" method="post">
    <button type="submit">Sair</button>
</form>
</body>
</html>
