<%@ page import="com.example.minerva.dto.StudentByNotesDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Notas dos Alunos</title>
</head>
<body>
<header>
    <a href="home.jsp">inicio</a>
    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=comment">observacoes</a>

    <a href="${pageContext.request.contextPath}/professor/schoolYear.jsp?type=grade">notas</a>
    <a href="${pageContext.request.contextPath}/professor/teacher_profile.jsp">perfil professor</a>
</header>
<%
    List<StudentByNotesDTO> alunos =
            (List<StudentByNotesDTO>) request.getAttribute("list");

    if (alunos != null && !alunos.isEmpty()) {
%>

<table>
    <thead>
    <tr>
        <th>Aluno</th>
        <th>N1</th>
        <th>N2</th>
        <th>Média</th>
    </tr>
    </thead>
    <tbody>

    <%
        for (StudentByNotesDTO aluno : alunos) {

            double n1 =  aluno.getN1();
            double n2 =  aluno.getN2();

            double media = (n1 + n2) / 2;
    %>

    <tr onclick="abrirPopupNota(
        <%= aluno.getId_student() %>,
            '<%= aluno.getStudentName() %>',
        <%= n1 %>,
        <%= n2 %>,
        <%= aluno.getId_subject() %>,
        <%= aluno.getSchool_year() %>
            )">

        <td>
            <%= aluno.getStudentName() %>
        </td>

        <td>
            <%= String.format("%.2f", n1) %>
        </td>

        <td>
            <%= String.format("%.2f", n2) %>
        </td>

        <td>
            <%= String.format("%.2f", media) %>
        </td>

    </tr>

    <%
        }
    %>

    </tbody>
</table>

<%
} else {
%>

<h3>Sem notas cadastradas</h3>

<%
    }
%>

<jsp:include page="/professor/popUp_notes.jsp" />

</body>
<script>
    function abrirPopupNota(studentId, studentName, n1, n2, subjectId, school_year){

        document.getElementById("student_id").value = studentId;
        document.getElementById("subject_id").value = subjectId;
        document.getElementById("school_year").value = school_year;


        document.getElementById("nome-aluno").innerText = studentName;

        document.getElementById("n1").value = n1;
        document.getElementById("n2").value = n2;

        calcularMedia();

        document.getElementById("popup-nota").style.display = "flex";
    }

    function fecharPopup(){
        document.getElementById("popup-nota").style.display = "none";
    }

    function calcularMedia(){

        let n1 = parseFloat(document.getElementById("n1").value) || 0;
        let n2 = parseFloat(document.getElementById("n2").value) || 0;

        let media = (n1 + n2) / 2;

        document.getElementById("media").value = media.toFixed(2);
    }

</script>
</html>