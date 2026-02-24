<%@ page import="javax.xml.stream.events.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.ProfileDTO" %>
<%@ page import="com.example.minerva.dto.CommentDTO" %>

<%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 16/02/2026
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%ProfileDTO profile = (ProfileDTO) request.getAttribute("profile");%>
    <br>
    <%
        String houseName = (String) request.getAttribute("houseName");
        String subject = (String) request.getAttribute("subject");
        int year = (int) request.getAttribute("year");
        List<CommentDTO> commentList = (List<CommentDTO>) request.getAttribute("listComments");
        int studentId = (int) request.getAttribute("studentId");
        int teacherId = (int) request.getAttribute("teacherId");
        int subjectId = (int) request.getAttribute("subjectId");
    %>

    <!-- PERFIL DO CARAAAAA -->
    <div style="border:1px solid #ccc; padding:15px; width:400px;">

        <img src="<%= profile.getImageUrl()%>"
             alt="Foto de Perfil"
             width="120"
             height="120"
             style="border-radius:50%;">

        <p><strong>Nome:</strong> <%= profile.getName() %></p>
        <p><strong>Responsável Legal:</strong> <%= profile.getGuardianName() %></p>
        <p><strong>Ano Escolar:</strong> <%= year %>º Ano</p>
        <p><strong>Casa:</strong> <%= profile.getHouseName() %></p>
        <p><strong>Matéria:</strong> <%= subject %></p>

    </div>
    <hr>
    <!-- BOTÃO PARA MOSTRAR FORM DE COLOCAR COMENTARIOS -->
    <button onclick="document.getElementById('commentForm').style.display='block'; this.style.display='none';">
        Novo Comentário
    </button>
    <form id="commentForm" action="<%= request.getContextPath() %>/teacher/insertComment" method="post" style="display:none; margin-top:15px;">

        <input type="hidden" name="studentId" value="<%= studentId %>">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="subjectId" value="<%= subjectId %>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden" name="subject" value="<%= subject %>">

        <textarea name="comment" rows="3" cols="40" placeholder="Digite o comentário" required></textarea>
        <br><br>

        <input type="number" name="score" step="1" placeholder="Ex: 1 ou -1" required>
        <br><br>
        <button type="submit">Salvar Comentário</button>
    </form>

    <h3>Histórico de Comentários</h3>

    <table border="1" cellpadding="5">
        <thead>
        <tr>
            <th>Comentário</th>
            <th>Pontos</th>
            <th>Data</th>
        </tr>
        </thead>
        <tbody>
        <%
            if (commentList != null && !commentList.isEmpty()) {
                for (CommentDTO comment : commentList) {
                    int score = comment.getScore();
                    String color = "gray";
                    if (score < 0) {
                        color = "red";
                    } else if (score > 0) {
                        color = "green";
                    }

        %>
        <tr>
            <td><%= comment.getContent() %></td>
            <td style="color:<%= color %>;">
                <%= score > 0 ? "+" + score : score %>
            </td>
            <td><%= comment.getCreatedAt() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="3" style="text-align:center;">Aluno sem comentários</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <br>
<%--    BUTTON FOR BACK TO STUDENTS--%>
    <form action="<%= request.getContextPath() %>/teacher/students" method="post">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <button type="submit">Voltar</button>
    </form>
</body>
</html>
