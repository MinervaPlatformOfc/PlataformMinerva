<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.minerva.dto.ProfileDTO" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %>

<%
    ProfileDTO profile = (ProfileDTO) request.getAttribute("profile");
    List<SubjectDTO> grades = profile.getGrades();

    int id = (int) request.getAttribute("id");
    String houseName = (String) request.getAttribute("houseName");

    String houseClass = houseName.toLowerCase();
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Perfil do Aluno</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/profile.css">

    <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&family=Hermeneus+One&display=swap" rel="stylesheet">

</head>

<body class="<%= houseClass %>">

<header>

    <div>
        <p>Perfil Aluno</p>
    </div>

    <img src="/images/logo.png" alt="logo-Minerva" class="logo-central">

    <div id="aluno-perfil">

        <form action="${pageContext.request.contextPath}/aluno/home" method="post">

            <input type="hidden" name="id" value="<%=id%>">

            <button type="submit" style="background:none;border:none;color:white;font-family:Almendra;cursor:pointer;">
                Voltar ao Início
            </button>
        </form>
    </div>
</header>


<main>

    <aside class="materias">

        <img src="<%= profile.getImageUrl() %>" alt="img-aluno">

        <h1>Matérias</h1>

        <%
            for (SubjectDTO grade : grades) {
                String subjectName = grade.getSubjectName();
        %>

        <form action="${pageContext.request.contextPath}/aluno/subject" method="post">

            <input type="hidden" name="id" value="<%= id %>">
            <input type="hidden" name="subjectId" value="<%= grade.getSubjectId() %>">
            <input type="hidden" name="subjectName" value="<%= subjectName %>">
            <input type="hidden" name="houseName" value="<%= houseName %>">

            <div class="barra" onclick="this.closest('form').submit()">

                <span><%= subjectName %></span>
                <span class="nota"></span>

            </div>

        </form>

        <%
            }
        %>
        <form action="${pageContext.request.contextPath}/logout" method="post">
            <input type="submit" value="Sair">
        </form>
    </aside>

    <aside class="informacoes">

        <div class="separador-titulo">
            <hr><p>Informações Pessoais</p><hr>
        </div>

        <p>Nome Completo: <%= profile.getName() %></p>

        <p>Data de Nascimento: <%= profile.getBirthDate() %></p>

        <p>Status de Sangue: <%= profile.getBloodStatus() %></p>

        <p>Número de Matrícula: <%= profile.getRegistration() %></p>

        <p>Endereço de Residência: <%= profile.getResidenceAddress() %></p>

        <p>Responsável Legal: <%= profile.getGuardianName() %></p>

        <p>Ano Letivo Atual: <%= profile.getSchoolYear() %></p>

        <p>Casa: <%= profile.getHouseName() %></p>


        <div class="separador-titulo">
            <hr><p>Equipamento e Varinha</p><hr>
        </div>

        <p>Madeira: <%= profile.getWandWood() %></p>

        <p>Núcleo: <%= profile.getWandCore() %></p>

        <p>Flexibilidade: <%= profile.getWandFlexibility() %></p>

        <p>Animal de Estimação: <%= profile.getPetType() %></p>

        <p>Kit Básico: <%= profile.isBasicKit() ? "Sim" : "Não" %></p>


        <div class="separador-titulo">
            <hr><p>Vida Escolar</p><hr>
        </div>

        <p>Aptidão de Voo: <%= profile.isFlightFitness() ? "Sim" : "Não" %></p>

        <p>Alergias e Restrições:
            <%= profile.getAllergies().equals("") ? "Nenhuma alergia registrada!" : profile.getAllergies() %>
        </p>

        <p>Autorização de Saída: <%= profile.isGuardianPermission() ? "Sim" : "Não" %></p>

    </aside>

</main>

<script src="/js/aluno.js"></script>

</body>
</html>