<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
  List<String> yearsSubjects = (List<String>) request.getAttribute("yearsSubjects");
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");%>
%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Anos e Matérias</title>
</head>
<body>
<header style="display: flex;justify-content: space-around">
  <form action="${pageContext.request.contextPath}/professor/home" method="post" >
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <button type="submit">Home</button>
  </form>
  <form action="${pageContext.request.contextPath}/professor/yearsSubjects" method="post" >
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <input type="hidden"  name="houseName" value="<%=houseName%>">
    <button type="submit">Observações</button>
  </form>
  <form action="${pageContext.request.contextPath}/professor/yearsSubjects" method="post" >
    <input type="hidden" name="showGrades" value="true">
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <input type="hidden"  name="houseName" value="<%=houseName%>">
    <button type="submit">Notas</button>
  </form>
  <form action="${pageContext.request.contextPath}/professor/profile" method="post" >
    <input type="hidden"  name="teacherId" value="<%=teacherId%>">
    <input type="hidden"  name="houseName" value="<%=houseName%>">
    <button type="submit">Perfil</button>
  </form>
</header>
<h1>Anos e Matérias</h1>
<ul>
  <% if (yearsSubjects != null && !yearsSubjects.isEmpty()) {
    for (String ys : yearsSubjects) {
      int idx = ys.indexOf("º Ano");
      String yearStr = ys.substring(0, idx).trim();
      String subject = ys.substring(ys.indexOf("(")+1, ys.indexOf(")"));
  %>
  <li>
    <form method="post" action="<%=request.getContextPath()%>/teacher/students">
      <input type="hidden" name="teacherId" value="<%= teacherId %>">
      <input type="hidden" name="year" value="<%= yearStr %>">
      <input type="hidden" name="subject" value="<%= subject %>">
      <button type="submit" style="border:none; background:none; color:blue; text-decoration:underline; cursor:pointer;">
        <%= ys %>
      </button>
    </form>
  </li>
  <%     }
  } else { %>
  <li>Nenhum aluno encontrado para este professor.</li>
  <% } %>
</ul>
</body>
</html>
