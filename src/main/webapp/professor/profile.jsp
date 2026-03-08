<%@ page import="com.example.minerva.dto.TeacherProfileDTO" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%@ page import="com.example.minerva.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Perfil Professor - Minerva</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/teachers/perfil.css">
  <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&family=Hermeneus+One&display=swap" rel="stylesheet">
  <style>
    form#perfil {
      padding: 0;
      background: none;
    }

    /* Aplica os estilos visuais apenas no botão */
    form#perfil button {
      background: rgba(0, 0, 0, 0.5);
      border: 2px solid #d4af37;
      border-radius: 50px;
      padding: 8px 20px;
      color: white;
      font-family: 'Almendra', serif;
      font-size: 1rem;
      display: flex;
      align-items: center;
      gap: 10px;
      cursor: pointer;
      transition: 0.3s;
      width: 100%;
    }

    form#perfil button:hover {
      background: rgba(212, 175, 55, 0.2);
      transform: scale(1.05);
    }

    form#perfil button svg {
      width: 24px;
      height: 24px;
      fill: #fff;
    }
  </style>
</head>
<body>
<%
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");
  TeacherProfileDTO profile = (TeacherProfileDTO) request.getAttribute("profile");
  List<String> subjects = profile.getSubjects();
  String name = profile.getName();
%>

<header>
  <div class="nav-links">
    <form action="${pageContext.request.contextPath}/professor/home" method="post" style="display: inline;">
      <input type="hidden" name="teacherId" value="<%=teacherId%>">
      <button type="submit" class="nav-button">Início</button>
    </form>
    <form action="${pageContext.request.contextPath}/teacher/yearsSubjects" method="post" style="display: inline;">
      <input type="hidden" name="teacherId" value="<%=teacherId%>">
      <input type="hidden" name="teacherName" value="<%=name%>">
      <input type="hidden" name="houseName" value="<%=houseName%>">
      <button type="submit" class="nav-button">Observações</button>
    </form>
    <form action="${pageContext.request.contextPath}/teacher/yearsSubjects" method="post" style="display: inline;">
      <input type="hidden" name="showGrades" value="true">
      <input type="hidden" name="teacherId" value="<%=teacherId%>">
      <input type="hidden" name="teacherName" value="<%=name%>">
      <input type="hidden" name="houseName" value="<%=houseName%>">
      <button type="submit" class="nav-button">Notas</button>
    </form>
  </div>

  <img src="${pageContext.request.contextPath}/imgs/logo-minerva.png" alt="logo-Minerva" class="logo-central">

  <form action="${pageContext.request.contextPath}/teacher/profile" method="post" id="perfil">
    <input type="hidden" name="teacherId" value="<%=teacherId%>">
    <input type="hidden" name="houseName" value="<%=houseName%>">
    <button type="submit" style="background: none; border: none; color: white; font-family: 'Almendra', serif; font-size: 1rem; cursor: pointer; display: flex; align-items: center; gap: 5px;">
      <%= name %>
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960" fill="#FFFFFF" width="24" height="24">
        <path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/>
      </svg>
    </button>
  </form>
</header>

<main>
  <div class="conteudo-principal">
    <aside class="materias">
      <img src="<%= profile.getImageUrl() != null ? profile.getImageUrl() : "/imgs/default-teacher.png" %>" alt="img-professor">

      <h1>Matérias</h1>

      <% if (subjects != null && !subjects.isEmpty()) {
        for (String subject : subjects) { %>
      <div class="barra">
        <svg width="48" height="43" viewBox="0 0 48 43" fill="none" xmlns="http://www.w3.org/2000/svg">
          <path d="M46.3817 34.3761C49.7409 37.41 47.5094 43 42.9984 43H21.7392C14.9967 43 9.59787 37.625 9.59787 30.9122V30.7928C4.07909 29.67 0 24.8683 0 19.1111C0 12.5417 5.32682 7.16667 11.9973 7.16667H17.996C18.7158 7.16667 19.1957 6.61722 19.1957 5.97222C19.1957 5.32722 18.7158 4.77778 17.996 4.77778H11.9973V0H17.996C21.3073 0 23.9947 2.69944 23.9947 5.97222C23.9947 9.29278 21.3073 11.9444 17.996 11.9444H11.9973C8.01422 11.9444 4.79893 15.1217 4.79893 19.1111C4.79893 22.3839 7.00644 25.0833 9.93379 25.9672C11.2775 20.7117 16.0044 16.7222 21.7392 16.7222C23.6587 16.7222 25.5783 17.2478 27.2579 18.0361C23.8747 19.9233 21.5952 23.4111 21.5952 27.4722C21.5952 30.4583 22.7949 33.1339 24.7865 35.045L26.4661 33.3728C24.9065 31.9156 23.9947 29.7894 23.9947 27.4722C23.9947 21.285 29.6094 19.1111 32.3928 19.1111C37.3837 19.1111 41.8707 23.6261 40.647 29.2161L46.3817 34.3761ZM38.3915 38.2222C39.7352 38.2222 40.7909 37.1711 40.7909 35.8333C40.7909 34.4956 39.7352 33.4444 38.3915 33.4444C37.0478 33.4444 35.992 34.4956 35.992 35.8333C35.992 37.1711 37.0478 38.2222 38.3915 38.2222Z" fill="#B6B8BA"/>
        </svg>
        <span><%= subject %></span>
      </div>
      <%      }
      } else { %>
      <div class="barra">
        <span>Nenhuma matéria atribuída</span>
      </div>
      <% } %>

      <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="submit" value="Sair" id="button-sair">
      </form>
    </aside>

    <aside class="informacoes">
      <div class="separador-titulo">
        <hr><p>Informações Pessoais</p><hr>
      </div>

      <p><strong>Nome Completo:</strong> <%= name %></p>
      <p><strong>Número de Registro Docente:</strong> <%= profile.getTeacherRegistrationCode() %></p>
      <p><strong>Casa de Origem:</strong> <%= profile.getHouseName() %></p>
      <p><strong>Título Bruxo:</strong> <%= profile.getWizardTitle() %></p>
      <p><strong>Chefe da Casa:</strong> <%= profile.isHeadHouse() ? "Sim" : "Não" %></p>
      <p><strong>Casa:</strong> <%= profile.getHouseName() %></p>

      <div class="separador-titulo">
        <hr><p>Equipamento e Varinha</p><hr>
      </div>

      <p><strong>Identificação da Varinha</strong></p>
      <p><strong>Madeira:</strong> <%= profile.getWandWood() != null ? profile.getWandWood() : "-" %></p>
      <p><strong>Núcleo:</strong> <%= profile.getWandCore() != null ? profile.getWandCore() : "-" %></p>
      <p><strong>Flexibilidade:</strong> <%= profile.getWandFlexibility() != null ? profile.getWandFlexibility() : "-" %></p>
    </aside>
  </div>

  <div class="separador-titulo curriculo">
    <hr><p>Currículo</p><hr>
  </div>
  <div class="texto-curriculo">
    <p><%= profile.getPastExperiences() != null ? profile.getPastExperiences() : "Nenhuma experiência registrada." %></p>
  </div>

</main>

<script>
  // Logo que volta para o início (efeito tremendo)
  const voltarInicioImg = document.querySelector('.logo-central');

  voltarInicioImg.addEventListener('click', () => {
    voltarInicioImg.classList.remove("tremendo");
    void voltarInicioImg.offsetWidth;
    voltarInicioImg.classList.add("tremendo");
  });
</script>
</body>
</html>