<%--
  Created by IntelliJ IDEA.
  User: erickbarbosa-ieg
  Date: 05/02/2026
  Time: 22:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cadastro</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/register" method="post" id="registerForm">

    <h2>Registro em Hogwarts</h2>

    <!-- Nome -->
    <label>Nome:</label><br>
    <input type="text" name="name" required><br><br>

    <!-- Email -->
    <label>Email:</label><br>
    <input type="email" name="email" id="email" required><br><br>

    <!-- Senha -->
    <label>Senha:</label><br>
    <input type="password" name="password" id="password" required minlength="8"
           pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':&quot;\\|,.<>\/?]).+$">
    <br>
    <small>
        A senha deve conter:
        <br>
        No mínimo 8 caracteres, 1 maiúscula, 1 minúscula e 1 especial.
    </small>
    <br><br>

    <!-- Pet -->
    <label>Animal de estimação:</label><br>
    <select name="pet" required>
        <option value="Coruja">Coruja</option>
        <option value="Sapo">Sapo</option>
        <option value="Gato">Gato</option>
    </select><br><br>

    <!-- Alergias -->
    <label>Alergias:</label><br>
    <input type="text" name="allergies"><br><br>

    <!-- Blood status -->
    <label>Status sanguíneo:</label><br>
    <select name="blood" required>
        <option value="Pure-blood">Pure-blood</option>
        <option value="Half-blood">Half-blood</option>
        <option value="Muggle-born">Muggle-born</option>
        <option value="Squib">Squib</option>
    </select><br><br>

    <!-- Data nascimento -->
    <label>Data de nascimento:</label><br>
    <input type="date" name="birthDate" required><br><br>

    <!-- Varinha -->
    <label>Madeira da varinha:</label><br>
    <select name="wood" required>
        <option>Holly</option>
        <option>Yew</option>
        <option>Oak</option>
        <option>Willow</option>
        <option>Mahogany</option>
        <option>Cherry</option>
        <option>Walnut</option>
        <option>Ebony</option>
        <option>Vine</option>
        <option>Ash</option>
        <option>Cedar</option>
        <option>Fir</option>
        <option>Acacia</option>
        <option>Beech</option>
        <option>Elm</option>
        <option>Larch</option>
        <option>Maple</option>
        <option>Pear</option>
        <option>Pine</option>
        <option>Poplar</option>
        <option>Redwood</option>
        <option>Rowan</option>
        <option>Spruce</option>
        <option>Sycamore</option>
        <option>Hazel</option>
        <option>Blackthorn</option>
    </select><br><br>

    <label>Núcleo da varinha:</label><br>
    <select name="core" required>
        <option>Phoenix Feather</option>
        <option>Dragon Heartstring</option>
        <option>Unicorn Hair</option>
        <option>Veela Hair</option>
        <option>Thestral Tail Hair</option>
        <option>Basilisk Horn</option>
        <option>Troll Whisker</option>
        <option>Kneazle Whisker</option>
    </select><br><br>

    <label>Flexibilidade:</label><br>
    <select name="flexibility" required>
        <option>Rigid</option>
        <option>Unyielding</option>
        <option>Solid</option>
        <option>Stiff</option>
        <option>Brittle</option>
        <option>Hard</option>
        <option>Supple</option>
        <option>Flexible</option>
        <option>Swishy</option>
        <option>Springy</option>
        <option>Whippy</option>
    </select><br><br>

    <!-- Ano escolar -->
    <label>Ano escolar:</label><br>
    <select name="schoolYear" required>
        <option value="1">1º ano</option>
        <option value="2">2º ano</option>
        <option value="3">3º ano</option>
    </select><br><br>

    <!-- Responsável -->
    <label>Nome do responsável:</label><br>
    <input type="text" name="legalGuardianName" required><br><br>

    <!-- Endereço -->
    <label>Endereço:</label><br>
    <input type="text" name="residenceAdress" required><br><br>

    <!-- Permissões -->
    <label>
        <input type="checkbox" name="guardianPermission" value="true" >
        Permissão do responsável
    </label><br><br>

    <label>
        <input type="checkbox" name="BasicKit" value="true">
        Tenho kit básico
    </label><br><br>

    <button type="submit">Registrar</button>

</form>
</body>
</html>
