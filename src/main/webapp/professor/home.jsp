<%@ page import="java.util.List" %>
<%@ page import="static jdk.internal.org.jline.utils.Colors.h" %>
<%@ page import="com.example.minerva.dto.TeacherHomeDTO" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %><%--

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
<%
    TeacherHomeDTO teacherDTO = (TeacherHomeDTO) request.getAttribute("teacherHomeDto");
%>
<%--PRO JOAO SABER COMO ESTILIZAR ( usa o sem Raw )--%>
<%
    String teacherHouseNameRaw = teacherDTO.getTeacherHouseName();
    String teacherHouseName = teacherHouseNameRaw.equals("Grifinória") ? "grifinoria" :
            teacherHouseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                    teacherHouseNameRaw.equals("Sonserina") ? "sonserina" : "corvinal";
%>
<header style="display: flex;justify-content: space-around">
    <form action="${pageContext.request.contextPath}/professor/home" method="post" >
        <input type="hidden"  name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
        <button type="submit">Home</button>
    </form>
    <form action="${pageContext.request.contextPath}/professor/yearsSubjects" method="post" >
        <input type="hidden"  name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
        <input type="hidden"  name="houseName" value="<%=teacherHouseName%>">
        <button type="submit">Observações</button>
    </form>
    <form action="${pageContext.request.contextPath}/professor/yearsSubjects" method="post" >
        <input type="hidden" name="showGrades" value="true">
        <input type="hidden"  name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
        <input type="hidden"  name="houseName" value="<%=teacherHouseName%>">
        <button type="submit">Notas</button>
    </form>
    <form action="${pageContext.request.contextPath}/professor/profile" method="post" >
        <input type="hidden"  name="teacherId" value="<%=teacherDTO.getTeacherId()%>">
        <input type="hidden"  name="houseName" value="<%=teacherHouseName%>">
        <button type="submit">Perfil</button>
    </form>
</header>


<br><br><br>
<h2>Professor: <%= teacherDTO.getTeacherName() %></h2>


<h3>Casa do professor: <%= teacherDTO.getTeacherHouseName() %></h3>

<table>
    <thead>
    <tr>
        <th>Casa</th>
        <th>Aluno</th>
        <th>Matéria</th>
        <th>Nota 1</th>
        <th>Nota 2</th>
    </tr>
    </thead>
    <tbody>

    <%
        List<StudentGradeDTO> students = teacherDTO.getStudents();
        if (students != null && !students.isEmpty()) {
            for ( StudentGradeDTO student : students) {
    %>
    <tr>
<%--        JOAO COLOCA A IMAGEM DA CASA COM O NOME DA CASA AQ DOIDO (dnv use sem o raw)--%>
    <%
        String studentHouseNameRaw = student.getStudentHouseName();
        String studentHouseName = studentHouseNameRaw.equals("Grifinória") ? "grifinoria" :
                studentHouseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                        studentHouseNameRaw.equals("Sonserina") ? "sonserina" : "corvinal";
    %><form method="post" action="${pageContext.request.contextPath}/teacher/studentProfile">
    <input type="hidden" name="teacherId" value="<%= teacherDTO.getTeacherId() %>">
    <input type="hidden" name="studentId" value="<%= student.getStudentId() %>">
    <input type="hidden" name="houseName" value="<%= studentHouseName %>">
    <button type="submit" style="width:100%; border:none; background:none; padding:0; cursor:pointer;">
    <td>IMAGEM JOAOOO</td>
        <td ><%= student.getStudentName() %></td>
        <td><%= student.getSubjectName() %></td>
        <td><%= student.getN1() != null ? student.getN1() : "-" %></td>
        <td><%= student.getN2() != null ? student.getN2() : "-" %></td>
    </button>
    </form>
    </tr>
    <%
        }
    } else {
    %>
    <tr>
        <td colspan="5" style="text-align:center; color:red;">Nenhum aluno encontrado para este professor.</td>
    </tr>
    <%
        }
    %>
    </tbody>
</table>
</body>
</html>
