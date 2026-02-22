<%@ page import="javax.xml.stream.events.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.view.CommentView" %><%--
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
<%--ISSO SERA UM POP UPPPPPP--%>
    <%String studentName = (String) request.getAttribute("studentName");%>
    <%int houseId = Integer.parseInt(request.getParameter("house_id"));%>
    <h1> escreva o comentario para o aluno: <%= studentName%></h1>
    <br>
    <%
        Integer studentId = (Integer) request.getAttribute("studentId");
        Integer teacherId = (Integer) request.getAttribute("teacherId");
        Integer subjectId = (Integer) request.getAttribute("subjectId");

    %>

    <form action="${pageContext.request.contextPath}/teacher/insertComment" method="post">

        <input type="hidden" name="student_id" value="<%= studentId %>">
        <input type="hidden" name="teacher_id" value="<%= teacherId %>">
        <input type="hidden" name="subject_id" value="<%= subjectId %>">
        <input type="hidden" name="house_id" value="<%=houseId%>">

        <label for="">conteudo</label>
        <input type="text" id="comment" name="comment">

        <label for="">pontos</label>
        <input type="number" id="score" name="score">

        <button type="submit">Enviar comentario</button>

    </form>

    <hr>

    <h3>Histórico de Comentários</h3>

    <tr>
        <th>Comentário</th>
        <th>Aluno</th>
        <th>Professor</th>
        <th>Matéria</th>
        <th>Pontos</th>
        <th>Data</th>
    </tr>
    <br>
    <%
        List<CommentView> commentList = (List<CommentView>) request.getAttribute("listComments");
        if (commentList != null && !commentList.isEmpty()){
            for (CommentView commentView : commentList){
    %>

    <tr>
        <td style="display:none;">
            <input type="hidden" name="idStudent" value="<%= commentView.getIdStudent() %>">
            <input type="hidden" name="idTeacher" value="<%= commentView.getIdTeacher() %>">
            <input type="hidden" name="subjectId" value="<%= commentView.getSubjectId() %>">
        </td>

        <td><%= commentView.getContent() %></td>
        <td><%= commentView.getStudent() %></td>
        <td><%= commentView.getTeacher() %></td>
        <td><%= commentView.getSubjectName() %></td>
        <td><%= commentView.getScore() %></td>
        <td><%= commentView.getDateTime() %></td>

    </tr>
    <hr>
    <%
            }
        } else { %>
    <td> aluno sem comentarios </td> <% }
    %>

</body>
</html>
