<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="login" method="POST">
    <div> <label>Email:</label> <input type="email" name="email" required> </div>
    <br>
    <div> <label>Senha:</label> <input type="password" name="senha" required> </div>
    <br>
    <hr>
    <button type="submit">Entrar</button>
</form>
<form action="${pageContext.request.contextPath}/notes/insert" method="post">

    <input name="n1" id="n1" type="number" oninput="calcularMedia()">
    <input name="n2" id="n2" type="number" oninput="calcularMedia()">

    <input name="id" id="student_id" type="hidden" value="1">
    <input name="subject_id" id="subject_id" type="hidden" value="2">

    <button type="submit">salvar nota</button>

</form>


<p>Média: <span id="media">0</span></p>

</body>
<script>
    function calcularMedia() {
        let n1 = parseFloat(document.getElementById("n1").value) || 0;
        let n2 = parseFloat(document.getElementById("n2").value) || 0;

        let media = (n1 + n2) / 2;

        document.getElementById("media").innerText = media.toFixed(2);
    }

</script>
</html>