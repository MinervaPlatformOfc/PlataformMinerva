<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.StudentGradeDTO" %>
<%
  List<StudentGradeDTO> students = (List<StudentGradeDTO>) request.getAttribute("students");
  int year = (int) request.getAttribute("year");
  String subject = (String) request.getAttribute("subject");
  int teacherId = (int) request.getAttribute("teacherId");
  String houseName = (String) request.getAttribute("houseName");
  boolean showGrades = request.getAttribute("showGrades") != null && (boolean) request.getAttribute("showGrades");
  String teacherName = (String) request.getAttribute("teacherName");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title><%= showGrades ? "Notas" : "Observações" %> do Aluno - Minerva</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/teachers/grade.css">
  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Almendra:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&family=Hermeneus+One&display=swap" rel="stylesheet">
  <script src="${pageContext.request.contextPath}/js/searchGrade.js" defer></script>
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

    /* Ajustes para a barra de alunos */
    .barra {
      display: grid;
      grid-template-columns: 50px 1fr <%= showGrades ? "100px 100px 140px" : "" %>;
      align-items: center;
      width: 80%;
      margin: 0 auto 15px auto;
      padding: 12px 25px;
      border: 2px solid #d4af37;
      background: #2a3144;
      transition: 0.3s;
      cursor: pointer;
    }

    .barra:hover {
      background: #323a52;
      transform: scale(1.01);
    }

    .barra span:nth-of-type(1) {
      flex: 1;
      font-size: 1.4rem;
    }

    .notas {
      text-align: center;
      font-variant-numeric: tabular-nums;
      font-size: 2rem;
    }

    #boletim-header {
      display: grid;
      grid-template-columns: 50px 1fr <%= showGrades ? "120px 120px 150px" : "" %>;
      align-items: center;
      width: 80%;
      margin: 0 auto 20px auto;
    }

    #boletim-header h3 {
      grid-column: 2;
      font-size: 2rem;
    }

    .col-n {
      text-align: center;
      font-size: 1rem;
    }

    /* Classe para linhas buscáveis */
    .linhas {
      display: grid;
    }

    /* Esconder elementos de busca */
    .search-field {
      display: none;
    }
    .logo {
        width: 190px;
        margin-bottom: -35px;
        justify-content: center;
    }
  </style>
</head>
<body>
<!-- Botão de voltar no canto direito -->
<div class="btn-voltar">
  <form action="<%= request.getContextPath() %>/teacher/yearsSubjects" method="post">
    <input type="hidden" name="teacherId" value="<%= teacherId %>">
    <input type="hidden" name="houseName" value="<%= houseName %>">
    <input type="hidden" name="teacherName" value="<%=teacherName%>">
    <input type="hidden" name="showGrades" value="<%= showGrades %>">
    <button type="submit">
      Voltar
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 -960 960 960">
        <path d="M400-120 160-360l241-241 56 57-144 144h367v-400h80v480H313l144 143-57 57Z"/>
      </svg>
    </button>
  </form>
</div>

<!-- Logo central clicável -->
<img class="logo" src="${pageContext.request.contextPath}/assets/Plataforma_minerva_transparente%202.png" alt="IMAGEM JOAOOOO">

<main>
  <h1><%= year %>º Ano - <%= subject %></h1>

  <div id="buscar-alunos" class="boletim">
    <!-- Campo de busca -->
    <input type="search" name="busca-aluno" id="searchbar" placeholder="Digite o nome a ser pesquisado">

    <!-- Configuração das colunas para busca -->
    <input type="hidden" id="searchColumns" value="nome<%= showGrades ? ",n1,n2,media" : "" %>">

    <% if (showGrades) { %>
    <!-- Header do boletim (apenas para notas) -->
    <div id="boletim-header">
      <h3>Nome do Aluno</h3>
      <span class="col-n">N1</span>
      <span class="col-n">N2</span>
      <span class="col-n">Méd. Fin.</span>
    </div>
    <% } %>

    <% if (students != null && !students.isEmpty()) {
      for (StudentGradeDTO s : students) {
        // Determinar a classe da casa para a imagem
        String studentHouseNameRaw = s.getStudentHouseName();
        String studentHouseNameClass = studentHouseNameRaw != null ?
                (studentHouseNameRaw.equals("Grifinória") ? "grifinoria" :
                        studentHouseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                                studentHouseNameRaw.equals("Sonserina") ? "sonserina" : "corvinal") : "";
    %>

    <% if (showGrades) { %>
    <!-- Visualização de NOTAS -->
    <div class="barra <%= studentHouseNameClass %> linhas" data-student-id="<%= s.getStudentId() %>">
      <img src="${pageContext.request.contextPath}/imgs/brasao-<%= studentHouseNameClass %>.png"
           alt="<%= s.getStudentHouseName() %>"
           data-field="houseImage">

      <span data-field="nome" data-original="<%= s.getStudentName() %>"><%= s.getStudentName() %></span>

      <span class="notas" data-field="n1" data-original="<%= s.getN1() != null ? s.getN1() : "0" %>">
                    <%= s.getN1() != null ? String.format("%.2f", s.getN1()) : "-" %>
                </span>
      <span class="notas" data-field="n2" data-original="<%= s.getN2() != null ? s.getN2() : "0" %>">
                    <%= s.getN2() != null ? String.format("%.2f", s.getN2()) : "-" %>
                </span>
      <span class="notas" data-field="media" data-original="<%= s.getMedia() != null ? s.getMedia() : "0" %>">
                    <%= s.getMedia() != null ? String.format("%.2f", s.getMedia()) : "-" %>
                </span>

      <!-- Formulário oculto para edição de notas -->
      <form method="post" action="${pageContext.request.contextPath}/teacher/studentGrade" class="gradeForm" id="form-<%= s.getStudentId() %>" style="display: none;">
        <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
        <input type="hidden" name="teacherName" value="<%=teacherName%>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="n1Original" value="<%= s.getN1() != null ? s.getN1() : "" %>">
        <input type="hidden" name="n2Original" value="<%= s.getN2() != null ? s.getN2() : "" %>">
      </form>
    </div>

    <% } else { %>
    <!-- Visualização de OBSERVAÇÕES -->
    <div class="barra <%= studentHouseNameClass %> linhas" data-student-id="<%= s.getStudentId() %>">
      <img src="${pageContext.request.contextPath}/imgs/brasao-<%= studentHouseNameClass %>.png"
           alt="<%= s.getStudentHouseName() %>"
           data-field="houseImage">

      <span data-field="nome" data-original="<%= s.getStudentName() %>"><%= s.getStudentName() %></span>

      <!-- Formulário para observações -->
      <form method="post" action="${pageContext.request.contextPath}/teacher/studentComments" style="display: none;" id="form-<%= s.getStudentId() %>">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="teacherName" value="<%=teacherName%>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <input type="hidden" name="studentId" value="<%= s.getStudentId() %>">
      </form>
    </div>
    <% } %>

    <%   }
    } else { %>
    <div style="text-align: center; color: red; padding: 40px; grid-column: 1/-1;">
      Nenhum aluno encontrado para esta turma.
    </div>
    <% } %>
  </div>

  <!-- Overlay e formulário de adição de notas (apenas para modo notas) -->
  <% if (showGrades) { %>
  <div class="overlay escondido"></div>
  <div class="escondido div-add">
    <h1>Adicionar Notas</h1>
    <form action="${pageContext.request.contextPath}/teacher/studentGrade" method="post" id="gradeEditForm">
      <div class="add-notas">
        <div class="campo">
          <label for="n1">N1</label>
          <input type="number" step="0.01" name="n1" id="n1" min="0" max="10">
        </div>
        <div class="campo">
          <label for="n2">N2</label>
          <input type="number" step="0.01" name="n2" id="n2" min="0" max="10">
        </div>
      </div>
      <input type="button" value="Salvar" class="btn-adicionar" id="btnSalvarNotas">
    </form>
  </div>
  <% } %>
</main>

<script>// Aguardar o DOM carregar completamente
// Aguardar o DOM carregar completamente
document.addEventListener('DOMContentLoaded', function() {

  // CORREÇÃO: Selecionar a imagem pela classe correta 'logo'
  const voltarInicioImg = document.querySelector('.logo');

  if (voltarInicioImg) {
    console.log('Logo encontrada!');

    voltarInicioImg.addEventListener('click', function() {
      console.log('Logo clicada!');
      window.location.href = "${pageContext.request.contextPath}/";
    });

    voltarInicioImg.style.cursor = 'pointer';
  } else {
    console.log('Logo NÃO encontrada! Verifique a classe CSS.');
  }

  // Declaração ÚNICA de barras - fora dos condicionais
  const barras = document.querySelectorAll('.barra');
  console.log('Total de barras encontradas:', barras.length);

  <% if (showGrades) { %>
  // MODO NOTAS - Funcionalidade para abrir formulário de edição

  const divAdd = document.querySelector('.div-add');
  const overlay = document.querySelector('.overlay');
  const btnSalvar = document.querySelector('#btnSalvarNotas');
  const inputN1 = document.getElementById('n1');
  const inputN2 = document.getElementById('n2');
  let currentStudentId = null;

  if (barras.length > 0 && divAdd && overlay) {
    barras.forEach(barra => {
      barra.addEventListener('click', (e) => {
        console.log('Barra de notas clicada!');

        // Não abrir se clicou em algum elemento de formulário
        if (e.target.tagName === 'INPUT' || e.target.tagName === 'BUTTON' || e.target.tagName === 'FORM') {
          return;
        }

        currentStudentId = barra.dataset.studentId;
        console.log('Student ID:', currentStudentId);

        // Pegar valores atuais das notas
        const n1Span = barra.querySelector('[data-field="n1"]');
        const n2Span = barra.querySelector('[data-field="n2"]');

        if (n1Span && n2Span) {
          inputN1.value = n1Span.dataset.original !== "0" ? n1Span.dataset.original : '';
          inputN2.value = n2Span.dataset.original !== "0" ? n2Span.dataset.original : '';

          divAdd.classList.remove('escondido');
          overlay.classList.remove('escondido');
          console.log('Popup aberto!');
        }
      });
    });

    overlay.addEventListener('click', () => {
      divAdd.classList.add('escondido');
      overlay.classList.add('escondido');
    });

    btnSalvar.addEventListener('click', () => {
      if (currentStudentId) {
        const form = document.getElementById('form-' + currentStudentId);

        if (form) {
          // Limpar inputs anteriores para não duplicar
          const inputsAntigos = form.querySelectorAll('input[name="n1"], input[name="n2"]');
          inputsAntigos.forEach(input => input.remove());

          // Adicionar novos inputs
          const n1Input = document.createElement('input');
          n1Input.type = 'hidden';
          n1Input.name = 'n1';
          n1Input.value = inputN1.value;

          const n2Input = document.createElement('input');
          n2Input.type = 'hidden';
          n2Input.name = 'n2';
          n2Input.value = inputN2.value;

          form.appendChild(n1Input);
          form.appendChild(n2Input);

          console.log('Submetendo formulário...');
          form.submit();
        }
      }

      divAdd.classList.add('escondido');
      overlay.classList.add('escondido');
    });
  }

  <% } else { %>
  // MODO OBSERVAÇÕES - Funcionalidade para redirecionar

  if (barras.length > 0) {
    barras.forEach(barra => {
      barra.addEventListener('click', (e) => {
        console.log('Barra de observação clicada!');

        // Não redirecionar se clicou em algum elemento de formulário
        if (e.target.tagName === 'INPUT' || e.target.tagName === 'BUTTON' || e.target.tagName === 'FORM') {
          return;
        }

        const studentId = barra.dataset.studentId;
        const form = document.getElementById('form-' + studentId);

        if (form) {
          console.log('Redirecionando para observações do aluno:', studentId);
          form.submit();
        }
      });
    });
  }
  <% } %>
});
</script>
</body>
</html>