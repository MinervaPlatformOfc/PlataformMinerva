<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Boletim do Aluno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }
        h1 { color: #333; margin-bottom: 20px; }
        table { border-collapse: collapse; width: 60%; margin-bottom: 20px; background-color: #fff; }
        th, td { border: 1px solid #999; padding: 10px; text-align: center; }
        th { background-color: #ddd; }
        td { font-weight: bold; }
        .nota-nula { color: #999; font-style: italic; }
    </style>
</head>
<body>
<header style="display: flex;justify-content: space-around">
    <%int id = (int) request.getAttribute("id");%>
    <form action="${pageContext.request.contextPath}/aluno/home" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Home</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/grades" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Boletim</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/subject" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Matérias</button>
    </form>
    <form action="${pageContext.request.contextPath}/aluno/profile" method="post" >
        <input type="hidden"  name="id" value="<%=id%>">
        <button type="submit">Perfil</button>
    </form>
</header>
<h1>Boletim do Aluno</h1>

<table>
    <tr>
        <th>Matéria</th>
        <th>Nota 1</th>
        <th>Nota 2</th>
        <th>Total</th>
    </tr>
    <%
        List<StudentGradeDTO> grades = (List<StudentGradeDTO>) request.getAttribute("grades");
        if (grades != null && !grades.isEmpty()) {
            for (StudentGradeDTO grade : grades) {
    %>
    <tr>
        <td><%= grade.getSubjectName() %></td>
        <td>
            <%= grade.getN1() != null ? grade.getN1() : "<span class='nota-nula'>-</span>" %>
        </td>
        <td>
            <%= grade.getN2() != null ? grade.getN2() : "<span class='nota-nula'>-</span>" %>
        </td>
        <td><%= grade.getTotal() %></td>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="4">Nenhuma nota disponível</td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>
