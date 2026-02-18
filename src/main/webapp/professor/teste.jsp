<%@ page import="com.example.minerva.view.StudentForTeacherView" %><%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 16/02/2026
  Time: 12:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

        th {
            font-size: 14px;
            letter-spacing: 0.5px;
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
    </style>

</head>
<body>
    <h1>Alunos</h1>

    <!-- TABELA RESULTADO -->
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
            <th> ações  </th>
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
            <td>
            <form action="${pageContext.request.contextPath}/teacher/ListComment">
                <input type="hidden" id="student_id" name="student_id" value="<%=student.getId_student()%>">
                <input type="hidden" name="teacher">
                <td> <button type="submit"> adicionar comentario </button></td>
            </form>
                <form action="${pageContext.request.contextPath}/aluno/grades">
                    <input type="text">
                    <button type="submit"> ver boletim </button>
                </form>
            </td>
        </tr>
        <%
        } else {
        %>
        <tr>
            <td colspan="10" class="empty">Insira uma matrícula existente</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
    <br>
    <br>
    <br><br>
    <h1>CSS PROVISORIO PARA NN FICAR PESSIMA A VISUALIZAÇÃO</h1>
</body>
</html>
