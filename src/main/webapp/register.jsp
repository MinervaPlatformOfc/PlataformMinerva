<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/generic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/recoverPassword/register.css">
    <link href="https://fonts.googleapis.com/css2?family=Almendra&display=swap" rel="stylesheet">
    <title>Cadastro</title>
</head>

<%
    String msgBruta = request.getAttribute("msg") != null ? (String) request.getAttribute("msg") : "";
    String msg = !(msgBruta.equals("")||msgBruta.isEmpty())?msgBruta:" ";
%>
<body>
<div class="page">

    <img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

    <div class="title-container">

        <img class="ornamento-esq" src="${pageContext.request.contextPath}/assets/ornamento-esq.png">

        <h1 class="title">CADASTRE-SE</h1>

        <img class="ornamento-dir" src="${pageContext.request.contextPath}/assets/ornamento-esq.png">

    </div>
    <form action="${pageContext.request.contextPath}/register" method="post" id="registerForm" enctype="multipart/form-data">


        <div class="form-container">
            <h2>informações pessoais</h2>
            <!-- Nome -->
            <label CLASS="label">Nome:</label><br>
            <input class="input" type="text" name="name" required><br><br>

            <!-- Email -->
            <label CLASS="label">Email:</label><br>
            <input class="input" type="email" name="email" id="email" required><br><br>

            <!-- Senha -->
            <label CLASS="label">Senha:</label><br>
            <input class="input" type="password" name="password" id="password" required minlength="8"
                   pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\-=\[\]{};':&quot;\\|,.<>\/?]).+$">
            <br>
            <small>
                A senha deve conter:
                <br>
                No mínimo 8 caracteres, 1 maiúscula, 1 minúscula e 1 especial.
            </small>
            <br><br>
            <!-- Ano escolar -->
            <label CLASS="label">Ano escolar:</label><br>
            <select name="schoolYear" required>
                <option value="1">1º ano</option>
                <option value="2">2º ano</option>
                <option value="3">3º ano</option>
            </select><br><br>

            <!-- Responsável -->
            <label CLASS="label">Nome do responsável:</label><br>
            <input class="input" type="text" name="legalGuardianName" required><br><br>

            <!-- Endereço -->
            <label CLASS="label">Endereço:</label><br>
            <input class="input" type="text" name="residenceAdress" required><br><br>


            <label CLASS="label">Foto de perfil:</label><br>
            <input class="input" type="file" name="image"
                   accept="image/jpeg, image/png, image/webp"
                   required>
            <br><br>

            <!-- Matrícula -->
            <label CLASS="label">Código de Matrícula:</label><br>
            <input class="input" type="text" name="registration"><br><br>
            <!-- Blood status -->
            <label CLASS="label">Status sanguíneo:</label><br>
            <select name="blood" required>
                <option value="Pure-blood">Pure-blood</option>
                <option value="Half-blood">Half-blood</option>
                <option value="Muggle-born">Muggle-born</option>
                <option value="Squib">Squib</option>
            </select><br><br>

            <!-- Data nascimento -->
            <label CLASS="label">Data de nascimento:</label><br>
            <input class="input" type="date" name="birthDate" required><br><br>

            <h2>Materiais</h2>
            <!-- Pet -->
            <label CLASS="label">Animal de estimação:</label><br>
            <select name="pet" required>
                <option value="coruja">Coruja</option>
                <option value="sapo">Sapo</option>
                <option value="gato">Gato</option>
            </select><br><br>

            <!-- Alergias -->
            <label CLASS="label">Alergias:</label><br>
            <input class="input" type="text" name="allergies"><br><br>

            <h2>Varinha</h2>
            <!-- Varinha -->
            <label CLASS="label">Madeira da varinha:</label><br>
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

            <label CLASS="label">Núcleo da varinha:</label><br>
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

            <label CLASS="label">Flexibilidade:</label><br>
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

            <!-- Permissões -->
            <div class="checkbox-group">
                <input type="checkbox" name="guardianPermission" value="true">
                <label>Tenho Permissão do responsável</label>
            </div>

            <div class="checkbox-group">
                <input type="checkbox" name="BasicKit" value="true">
                <label>Tenho kit básico</label>
            </div>

            <button class="button" type="submit">Cadastrar</button>
        </div>
    </form>
</div>
</body>
</html>