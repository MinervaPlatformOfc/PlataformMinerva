<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.minerva.dto.ProfileDTO" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %>

<%
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");
  ProfileDTO profile = (ProfileDTO) request.getAttribute("profile");
  List<SubjectDTO> grades = profile.getGrades();

  // Determinar a classe da casa para o body
  String houseClass = "";
  if (profile.getHouseName() != null) {
    houseClass = profile.getHouseName().equals("Grifinória") ? "grifinoria" :
            profile.getHouseName().equals("Lufa-Lufa") ? "lufalufa" :
                    profile.getHouseName().equals("Sonserina") ? "sonserina" : "corvinal";
  }
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Perfil do Aluno - Minerva</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/students/profile.css">
  <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&family=Hermeneus+One&display=swap" rel="stylesheet">
  <style>
    /* Botão de voltar no canto direito */
    .btn-voltar {
      position: absolute;
      top: 20px;
      right: 20px;
      z-index: 100;
    }

    .btn-voltar form {
      margin: 0;
      padding: 0;
    }

    .btn-voltar button {
      background: rgba(0, 0, 0, 0.5);
      border: 1px solid #d4af37;
      border-radius: 50px;
      padding: 8px 20px;
      cursor: pointer;
      transition: 0.3s;
      color: white;
      font-family: 'Almendra', serif;
      font-size: 1rem;
      display: flex;
      align-items: center;
      gap: 10px;
      width: 100%;
      height: 100%;
    }

    .btn-voltar button:hover {
      background: rgba(212, 175, 55, 0.2);
      transform: scale(1.05);
    }

    .btn-voltar svg {
      width: 20px;
      height: 20px;
      fill: #ffffff;
    }

    /* Estilo para matérias não clicáveis */
    .materias .barra {
      cursor: default;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .materias .barra:hover {
      transform: none;
      background: #2a3144;
    }
    .materias .barra span {
      font-size: 1.2rem;
    }

    /* Ajuste para o header do perfil */
    header {
      display: flex;
      justify-content: space-between;
      align-items: center;
      padding: 30px 90px;
      background: rgba(0, 0, 0, 0.3);
      border-bottom: 1px solid #d4af37;
      box-shadow: 0 4px 15px rgba(0,0,0,0.5);
      position: relative;
    }

    header div:first-child p {
      color: white;
      font-size: 1.1rem;
      margin: 0;
    }

    .logo-central {
      position: absolute;
      left: 50%;
      transform: translateX(-50%);
      height: 60px;
      filter: drop-shadow(0 0 5px #d4af37);
      cursor: pointer;
    }
  </style>
</head>
<body class="<%= houseClass %>">

<header>
  <div>
    <p>Perfil do Aluno</p>
  </div>

  <img src="${pageContext.request.contextPath}/imgs/logo-minerva.png" alt="logo-Minerva" class="logo-central">

  <!-- Botão de voltar no canto direito -->
  <div class="btn-voltar">
    <form action="<%= request.getContextPath() %>/professor/home" method="post">
      <input type="hidden" name="teacherId" value="<%= teacherId %>">
      <button type="submit">
        Voltar
        <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960">
          <path d="M400-120 160-360l241-241 56 57-144 144h367v-400h80v480H313l144 143-57 57Z"/>
        </svg>
      </button>
    </form>
  </div>
</header>

<main>
  <aside class="materias">
    <img src="<%= profile.getImageUrl() != null ? profile.getImageUrl() : "/imgs/default-student.png" %>"
         alt="img-aluno">

    <h1>Matérias</h1>

    <% if (grades != null && !grades.isEmpty()) {
      for (SubjectDTO grade : grades) {
        String subjectName = grade.getSubjectName();
    %>
    <div class="barra">
      <span><%= subjectName %></span>
      <span class="nota"></span>
    </div>
    <%
      }
    } else { %>
    <div class="barra">
      <span>Nenhuma matéria</span>
    </div>
    <% } %>
  </aside>

  <aside class="informacoes">
    <div class="separador-titulo">
      <hr><p>Informações Pessoais</p><hr>
    </div>

    <p><strong>Nome Completo:</strong> <%= profile.getName() %></p>
    <p><strong>Data de Nascimento:</strong> <%= profile.getBirthDate() %></p>
    <p><strong>Status de Sangue:</strong> <%= profile.getBloodStatus() %></p>
    <p><strong>Número de Matrícula:</strong> <%= profile.getRegistration() %></p>
    <p><strong>Endereço de Residência:</strong> <%= profile.getResidenceAddress() %></p>
    <p><strong>Responsável Legal:</strong> <%= profile.getGuardianName() %></p>
    <p><strong>Ano Letivo Atual:</strong> <%= profile.getSchoolYear() %></p>
    <p><strong>Casa:</strong> <%= profile.getHouseName() %></p>

    <div class="separador-titulo">
      <hr><p>Equipamento e Varinha</p><hr>
    </div>

    <p><strong>Madeira:</strong> <%= profile.getWandWood() != null ? profile.getWandWood() : "-" %></p>
    <p><strong>Núcleo:</strong> <%= profile.getWandCore() != null ? profile.getWandCore() : "-" %></p>
    <p><strong>Flexibilidade:</strong> <%= profile.getWandFlexibility() != null ? profile.getWandFlexibility() : "-" %></p>
    <p><strong>Animal de Estimação:</strong> <%= profile.getPetType() != null ? profile.getPetType() : "-" %></p>
    <p><strong>Kit Básico:</strong> <%= profile.isBasicKit() ? "Sim" : "Não" %></p>

    <div class="separador-titulo">
      <hr><p>Vida Escolar</p><hr>
    </div>

    <p><strong>Aptidão de Voo:</strong> <%= profile.isFlightFitness() ? "Sim" : "Não" %></p>
    <p><strong>Alergias e Restrições:</strong>
      <%= profile.getAllergies() != null && !profile.getAllergies().isEmpty() ? profile.getAllergies() : "Nenhuma alergia registrada!" %>
    </p>
    <p><strong>Autorização de Saída:</strong> <%= profile.isGuardianPermission() ? "Sim" : "Não" %></p>
  </aside>
</main>

<script>
  // Logo que volta para o início (efeito tremendo)
  const voltarInicioImg = document.querySelector('.logo-central');

  voltarInicioImg.addEventListener('click', () => {
    if (document.title !== "Página Professor") {
      window.location.href = "${pageContext.request.contextPath}/";
    } else {
      voltarInicioImg.classList.remove("tremendo");
      void voltarInicioImg.offsetWidth;
      voltarInicioImg.classList.add("tremendo");
    }
  });
</script>

</body>
</html>