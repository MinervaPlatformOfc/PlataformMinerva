<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%
  List<String> yearsSubjects = (List<String>) request.getAttribute("yearsSubjects");
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");
  Boolean showGrades = (Boolean) request.getAttribute("showGrades");
  boolean isNotasAtivo = showGrades != null && showGrades;
  boolean isObservacoesAtivo = !isNotasAtivo;
  String teacherName = (String) request.getAttribute("teacherName");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><%= isNotasAtivo ? "Notas" : "Observações" %> - Minerva</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/teachers/yearsSubjects.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Almendra:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
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
<header>
  <div class="nav-links">
    <form action="${pageContext.request.contextPath}/professor/home" method="post" style="display: inline;">
      <input type="hidden" name="teacherId" value="<%=teacherId%>">
      <button type="submit" class="nav-button <%= !isNotasAtivo && !isObservacoesAtivo ? "ativo" : "" %>">Início</button>
    </form>

    <form action="${pageContext.request.contextPath}/teacher/yearsSubjects" method="post" style="display: inline;">
      <input type="hidden" name="teacherId" value="<%=teacherId%>">
      <input type="hidden" name="houseName" value="<%=houseName%>">
      <input type="hidden" name="teacherName" value="<%=teacherName%>">
      <button type="submit" class="nav-button <%= isObservacoesAtivo ? "ativo" : "" %>">Observações</button>
    </form>

    <form action="${pageContext.request.contextPath}/teacher/yearsSubjects" method="post" style="display: inline;">
      <input type="hidden" name="showGrades" value="true">
      <input type="hidden" name="teacherName" value="<%=teacherName%>">
      <input type="hidden" name="teacherId" value="<%=teacherId%>">
      <input type="hidden" name="houseName" value="<%=houseName%>">
      <button type="submit" class="nav-button <%= isNotasAtivo ? "ativo" : "" %>">Notas</button>
    </form>
  </div>

  <img src="${pageContext.request.contextPath}/imgs/logo-minerva.png" alt="logo-Minerva" class="logo-central">

  <form action="${pageContext.request.contextPath}/teacher/profile" method="post" id="perfil">
    <input type="hidden" name="teacherId" value="<%=teacherId%>">
    <input type="hidden" name="houseName" value="<%=houseName%>">
    <button type="submit" style="background: none; border: none; color: white; font-family: 'Almendra', serif; font-size: 1rem; cursor: pointer; display: flex; align-items: center; gap: 5px;">
        <%=teacherName%>
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960" fill="#FFFFFF" width="24" height="24">
        <path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/>
      </svg>
    </button>
  </form>
</header>

<main>
  <div class="div-grid">
    <% if (yearsSubjects != null && !yearsSubjects.isEmpty()) {
      for (String ys : yearsSubjects) {
        int idx = ys.indexOf("º Ano");
        String yearStr = ys.substring(0, idx).trim();
        String subject = ys.substring(ys.indexOf("(")+1, ys.indexOf(")"));
    %>
    <div class="card">
      <form method="post" action="<%=request.getContextPath()%>/teacher/students" style="width: 100%; height: 100%; display: flex; align-items: center; justify-content: center;">
        <input type="hidden" name="teacherName" value="<%=teacherName%>">
        <input type="hidden" name="showGrades" value="<%= showGrades %>">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="year" value="<%= yearStr %>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <button type="submit" style="width: 100%; height: 100%; border: none; background: none; color: inherit; font-family: 'Almendra', serif; font-size: 1.2rem; cursor: pointer; display: flex; align-items: center; justify-content: center;">
          <%= ys %>
        </button>
      </form>
    </div>
    <%      }
    } else { %>
    <div style="grid-column: 1/-1; text-align: center; color: red; padding: 40px;">
      Nenhum <%= isNotasAtivo ? "ano/matéria" : "ano/matéria" %> encontrado para este professor.
    </div>
    <% } %>
  </div>
</main>
<script>
  // Tornar os cards inteiros clicáveis
  document.addEventListener('DOMContentLoaded', function() {
    const cards = document.querySelectorAll('.card');

    cards.forEach(card => {
      card.addEventListener('click', function() {
        const form = this.querySelector('form');
        if (form) {
          form.submit();
        }
      });

      // Adiciona estilo de cursor pointer para indicar que é clicável
      card.style.cursor = 'pointer';
    });
  });
</script>
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