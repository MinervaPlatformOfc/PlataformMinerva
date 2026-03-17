<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Frame room</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/students/frames.css">
    <script src="../js/chatbot.js"></script>
    <script>const contextPath = "${pageContext.request.contextPath}";</script>
</head>
<body>
    <div id="chat">
    </div>

    <div id="input-area">
        <input type="text" id="msg" placeholder="Digite sua mensagem">
        <button onclick="sendMessage()">Enviar</button>
    </div>
</body>
</html>
