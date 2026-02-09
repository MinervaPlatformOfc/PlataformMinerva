<%--
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
    <h1>NOTAS DOS ALUNOS</h1>
    <input id="n1" type="number" oninput="calcularMedia()">
    <input id="n2" type="number" oninput="calcularMedia()">

    <p>Média: <span id="media">0</span></p>

    <br>
    <br>
    <h1>CAÇAR OS ALUNOS</h1>
    <form action="">
        <label for="">digite a matricula do aluno</label>
        <input type="text">
        <button type="submit"> procurar </button>
    </form>
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
