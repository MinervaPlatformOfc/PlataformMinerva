<%@ page import="com.example.minerva.dto.StudentHomeDTO" %>
<%@ page import="com.example.minerva.model.House" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home do Aluno</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        body { font-family: Arial, sans-serif; background-color: #f5f5f5; padding: 20px; }
        h1, h2 { color: #333; }
        table { border-collapse: collapse; width: 50%; margin-bottom: 20px; }
        th, td { border: 1px solid #999; padding: 8px; text-align: left; }
        th { background-color: #ddd; }
    </style>
</head>
<body>
<header style="display: flex;justify-content: space-around">
    <%int id = ((StudentHomeDTO)request.getAttribute("homeDto")).getId();%>
    <%String houseNameRaw = ((StudentHomeDTO)request.getAttribute("homeDto")).getHouseName();

    String houseName = houseNameRaw.equals("Grifinória") ? "grifinoria" :
                houseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                        houseNameRaw.equals("Sonserina") ? "sonserina" : "corvinal"%>
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
<div class="home-wrapper">
    <h1>Bem-vindo, <%= ((StudentHomeDTO)request.getAttribute("homeDto")).getName() %></h1>
    <p>Casa: <%= houseNameRaw %></p>

    <h2>Ranking das Casas</h2>
    <table>
        <tr>
            <th>Casa</th>
            <th>Pontos</th>
        </tr>
        <%
            List<House> ranking = (List<House>) request.getAttribute("ranking");
            if (ranking != null && !ranking.isEmpty()) {
                for (House house : ranking) {
        %>
        <tr>
            <td><%= house.getName() %></td>
            <td><%= house.getPoints() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="2">Nenhum dado disponível</td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
