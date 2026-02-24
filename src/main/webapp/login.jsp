<%@ page import="com.example.minerva.view.StudentForTeacherView" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<h1>Hello World!</h1>

<br/>

<!-- FORM LOGIN -->
<form action="login" method="POST">
    <div>
        <label>Email:</label>
        <input type="email" name="email" required>
    </div>

    <br>

    <div>
        <label>Senha:</label>
        <input type="password" name="senha" required>
    </div>

    <br>

    <button type="submit">Entrar</button>
</form>

<hr>

<br><br><br>

<a href="register.jsp">Cadastre-se</a>
<br>

<br><br><br>
<!--
 FORM NOTAS
<form action="${pageContext.request.contextPath}/teacher/subject" method="post">

    <label>Nota 1:</label>
    <input type="number" name="n1" id="n1" oninput="calcularMedia()">

    <label>Nota 2:</label>
    <input type="number" name="n2" id="n2" oninput="calcularMedia()">

    <input type="hidden" name="student_id" value="3">
    <input type="hidden" name="subject_id" value="3">

    <h3>Média: <span id="media">0</span></h3>

    <button type="submit">Salvar nota</button>
</form>

<br><br><br>

 FORM BUSCAR ALUNO
<form action="${pageContext.request.contextPath}/teacher/findStudent" method="get">

    <label for="registro">Insira a matrícula do aluno</label>
    <input type="text" id="registro" name="registro">
    <button type="submit">Procurar</button>
</form>
-->
<script>
    function calcularMedia() {
        let n1 = Number(document.getElementById("n1").value);
        let n2 = Number(document.getElementById("n2").value);

        let media = (n1 + n2) / 2;

        document.getElementById("media").innerText = media.toFixed(2);
    }
</script>

</body>
</html>
