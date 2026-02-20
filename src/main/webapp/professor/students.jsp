<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%
  List<StudentGradeDTO> students = (List<StudentGradeDTO>) request.getAttribute("students");
  int year = (int) request.getAttribute("year");
  String subject = (String) request.getAttribute("subject");
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");
  boolean showGrades = request.getAttribute("showGrades") != null && (boolean) request.getAttribute("showGrades");
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<h1>Alunos do <%= year %>º Ano - <%= subject %></h1>
<table border="1" cellpadding="5">
  <thead>
  <tr>
    <th>Aluno</th>
    <% if (showGrades) { %>
    <th>Nota 1</th>
    <th>Nota 2</th>
    <th>Média</th>
    <% } %>
  </tr>
  </thead>
  <tbody>
  <% if (students != null && !students.isEmpty()) {
    for (StudentGradeDTO s : students) { %>
  <tr>
    <td><% if (showGrades) { %>
      <form method="post" action="<%= request.getContextPath() %>/teacher/studentGrade" style="display:none;" class="gradeForm" id="form-<%= s.getStudentId() %>">
        <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden"  name="teacherId" value="<%=teacherId%>">
        <input type="hidden" name="n1Original" value="<%= s.getN1() != null ? s.getN1() : "" %>">
        <input type="hidden" name="n2Original" value="<%= s.getN2() != null ? s.getN2() : "" %>">
        <label>Nota 1: <input type="number" step="0.01" name="n1" value="<%= s.getN1() != null ? s.getN1() : "" %>" placeholder="N1" min="0" max="10"></label>
        <label>Nota 2: <input type="number" step="0.01" name="n2" value="<%= s.getN2() != null ? s.getN2() : "" %>" placeholder="N2" min="0" max="10"></label>
        <button type="submit">Salvar</button>
      </form>
      <button onclick="document.getElementById('form-<%= s.getStudentId() %>').style.display='block'; this.style.display='none';">
        <%= s.getStudentName() %>
      </button>
      <% } else { %>
      <!-- Leva para servlet de comentários -->
      <form method="post" action="<%= request.getContextPath() %>/teacher/studentComments">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
        <button type="submit" style="border:none; background:none; padding:0; cursor:pointer; font-size:inherit;">
          <%= s.getStudentName() %>
        </button>
      </form>
      <% } %>
    </td>
    <% if (showGrades) { %>
    <td><%= s.getN1() != null ? s.getN1() : "-" %></td>
    <td><%= s.getN2() != null ? s.getN2() : "-" %></td>
    <td><%= s.getMedia() != null ? s.getMedia() : "-" %></td>
    <% } %>
  </tr>
  <%   }
  } else { %>
  <tr>
    <td colspan="<%= showGrades ? 4 : 1 %>" style="text-align:center; color:red;">Nenhum aluno encontrado.</td>
  </tr>
  <% } %>
  </tbody>
</table>

<form action="<%= request.getContextPath() %>/teacher/yearsSubjects" method="post">
  <input type="hidden" name="teacherId" value="<%= teacherId %>">
  <input type="hidden" name="houseName" value="<%= houseName %>">
  <input type="hidden" name="showGrades" value="<%= showGrades %>">
  <button type="submit">Voltar</button>
</form>
</body>
</html>
