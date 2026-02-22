<%@ page import="com.example.minerva.model.Teacher" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 22/02/2026
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Perfil</title>
</head>
<body>

<h2>Informações Pessoais</h2>
        <%
            Teacher teachers = (Teacher) request.getParameter("teacher");

            for (Teacher teacher: teachers){
        %>
<table border="1">

    <tr>
        <td><b>Nome Completo:</b></td>
        <td><%= teacher.get %>></td>
    </tr>

    <tr>
        <td><b>Número de Registro Docente:</b></td>
        <td>${usuario.registro}</td>
    </tr>

    <tr>
        <td><b>Casa de Origem:</b></td>
        <td>${usuario.casaOrigem}</td>
    </tr>

    <tr>
        <td><b>Título:</b></td>
        <td>${usuario.titulo}</td>
    </tr>

    <tr>
        <td><b>Chefe da Casa:</b></td>
        <td>${usuario.chefeCasa}</td>
    </tr>

    <tr>
        <td><b>Casa Atual:</b></td>
        <td>${usuario.casaAtual}</td>
    </tr>

    <%}%>
</table>

<br>

<h2>Identificação da Varinha</h2>

<table border="1">

    <tr>
        <td><b>Madeira:</b></td>
        <td>${usuario.madeira}</td>
    </tr>

    <tr>
        <td><b>Núcleo:</b></td>
        <td>${usuario.nucleo}</td>
    </tr>

    <tr>
        <td><b>Flexibilidade:</b></td>
        <td>${usuario.flexibilidade}</td>
    </tr>

</table>

<br>

<h2>Currículo</h2>

<table border="1">
    <tr>
        <td>${usuario.curriculo}</td>
    </tr>
</table>

</body>
</html>