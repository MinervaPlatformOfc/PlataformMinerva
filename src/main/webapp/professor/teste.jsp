<%@ page import="com.example.minerva.view.StudentForTeacherView" %>
<%@ page import="com.example.minerva.dao.HouseDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Detalhes do Aluno</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f9;
            margin: 40px;
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
            border-radius: 8px;
            overflow: hidden;
        }

        thead {
            background-color: #2c3e50;
            color: white;
        }

        th, td {
            padding: 12px 10px;
            text-align: left;
        }

        tbody tr {
            border-bottom: 1px solid #eee;
        }

        tbody tr:nth-child(even) {
            background-color: #f9fafb;
        }

        tbody tr:hover {
            background-color: #eef3f8;
            transition: 0.2s;
        }

        .empty {
            text-align: center;
            padding: 20px;
            color: #777;
            font-style: italic;
        }

        .actions {
            display: flex;
            gap: 10px;
        }

        .actions form {
            margin: 0;
        }

        button {
            padding: 6px 10px;
            border: none;
            background-color: #2c3e50;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color: #1a252f;
        }
    </style>
</head>

<body>

<h1>Aluno</h1>

<table>
    <thead>
    <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Matrícula</th>
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
        HouseDAO houseDAO = new HouseDAO();
        String house = houseDAO.getHouseName(student.getId_house());
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

        <td class="actions">
            <!-- Ver boletim -->
            <form action="${pageContext.request.contextPath}/aluno/grades" method="post" >
                <input type="hidden"  name="id" value="<%= student.getId_student() %>">
                <input type="hidden"  name="houseName" value="<%= house %>">
                <button type="submit">Boletim</button>
            </form>
        </td>
    </tr>

    <%
    } else {
    %>

    <tr>
        <td colspan="11" class="empty">
            Insira uma matrícula existente
        </td>
    </tr>

    <%
        }
    %>

    </tbody>
</table>

</body>
</html>