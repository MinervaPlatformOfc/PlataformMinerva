<%@ page import="com.example.minerva.model.Teacher" %>
<%@ page import="com.example.minerva.model.User" %>
<%@ page import="com.example.minerva.dao.HouseDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Perfil do Professor</title>
</head>
<body>
<header>
    <a href="home.jsp">inicio</a>
    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=comment">observacoes</a>

    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=grade">notas</a>
    <a href="${pageContext.request.contextPath}/professor/teacher_profile.jsp">perfil professor</a>
</header>
<%
    Teacher teacher = (Teacher) session.getAttribute("teacher");
    User user = (User) session.getAttribute("user");
    HouseDAO houseDAO = new HouseDAO();

    String wand = teacher.getWand();
    String[] parts = wand.split("-");

    String wandWood = parts[0].trim();
    String wandCore = parts[1].trim();
    String wandFlexibility = parts[2].trim();

    String houseName = houseDAO.getHouseName(teacher.getHouseId());
%>

    <img src="<%= user.getImageUrl() %>" width="200">

    <h3>Matérias</h3>

    <ul>
        <%
            List<String> subjects = (List<String>) session.getAttribute("subjects");

            for(String s : subjects){
        %>

        <li><%= s %></li>

        <%
            }
        %>
    </ul>

    <h2>Informações pessoais</h2>

    <p><b>Nome Completo:</b> <%= user.getName() %></p>

    <p><b>Número de Registro Docente:</b> <%= teacher.getId() %></p>

    <p><b>Casa de Origem:</b> <%= houseName %></p>

    <p><b>Título Bruxo:</b> <%= teacher.getWizardTitle() %></p>

    <p><b>Chefe da casa:</b>
        <%= teacher.isHeadHouse() ? "Sim" : "Não" %>
    </p>

    <p><b>Casa:</b> <%= houseName %></p>

    <h2>Equipamento e varinha</h2>

    <p><b>Identificação da Varinha</b></p>

    <p><b>Madeira:</b> <%= wandWood %></p>

    <p><b>Núcleo:</b> <%= wandCore %></p>

    <p><b>Flexibilidade:</b> <%= wandFlexibility %></p>

    <h2>Currículo</h2>

    <p>
        <%= teacher.getPastExperiences() %>
    </p>

</body>
</html>