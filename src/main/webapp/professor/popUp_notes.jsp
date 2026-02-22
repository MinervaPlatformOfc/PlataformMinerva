<%--
  Created by IntelliJ IDEA.
  User: brunajesus-ieg
  Date: 22/02/2026
  Time: 05:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="popup-nota" style="display:none; position:fixed; top:0; left:0;
width:100%; height:100%; background:rgba(0,0,0,0.5);
align-items:center; justify-content:center;">

    <div style="background:white; padding:20px; border-radius:10px">

        <h2 id="nome-aluno"></h2>

        <form action="<%=request.getContextPath()%>/teacher/insertNotes" method="post">

            <input type="hidden" name="student_id" id="student_id">
            <input type="hidden" name="subject_id" id="subject_id">
            <input type="hidden" name="school_year" id="school_year">
            <label>N1</label>
            <input type="number" step="0.01" name="n1" id="n1" oninput="calcularMedia()">

            <br><br>

            <label>N2</label>
            <input type="number" step="0.01" name="n2" id="n2" oninput="calcularMedia()">

            <br><br>

            <label>Média</label>
            <input type="text" id="media" readonly>

            <br><br>

            <button type="submit">Salvar</button>
            <button type="button" onclick="fecharPopup()">Cancelar</button>

        </form>

    </div>
</div>
</body>
</html>
