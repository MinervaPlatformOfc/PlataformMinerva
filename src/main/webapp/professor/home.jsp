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
</head>
<body>
    <form action="">
        <label for="registro">Insira a matricula do aluno</label>
        <input type="text" id="registro" name="registro">
        <button type="submit"> Procurar </button>
    </form>
    <table>
        <thead>
        <tr>
            <th>Nome </th>
            <th>Email </th>
            <th>Matricula</th>
            <th>Ano Escolar</th>
            <th>Responsável Legal</th>
            <th>Varinha</th>
            <th>Tipo de Pet</th>
            <th>Alergias</th>
            <th>Tipo Sanguíneo</th>
            <th>Kit Básico</th>
            <th>Ações</th>
        </tr>
        </thead>

        <tbody>
        <%
            StudentForTeacherView student =
                    (StudentForTeacherView) request.getAttribute("student");

            if (student != null) {
        %>
        <tr>
            <td><%= student.getName() %></td>
            <td><%= student.getEmail() %></td>
            <td><%= student.getRegistration() %></td>
            <td><%= student.getSchoolYear() %></td>
            <td><%= student.getLegalGuardianName() %></td>
            <td><%= student.getWand() %></td>
            <td><%= student.getPetType() %></td>
            <td><%= student.getAllergies() != null ? student.getAllergies() : "" %></td>
            <td><%= student.getBlood() %></td>
            <td><%= student.getBasicKit() %></td>
        </tr>
        <%
            } else {
        %> <h3> insira uma matricula existente </h3> <%
            }%>

        </tbody>

    </table>

</body>
</html>
