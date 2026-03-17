<%@ page import="javax.xml.stream.events.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.ProfileDTO" %>
<%@ page import="com.example.minerva.dto.CommentDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Observações do Aluno - Minerva</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/teachers/comment.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script src="${pageContext.request.contextPath}/js/comment.js" defer></script>
    <link href="https://fonts.googleapis.com/css2?family=Almendra:ital,wght@0,400;0,700;1,400;1,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Almendra:wght@400;700&family=Hermeneus+One&display=swap" rel="stylesheet">
</head>
<body>
<%
    ProfileDTO profile = (ProfileDTO) request.getAttribute("profile");
    String houseName = (String) request.getAttribute("houseName");
    String subject = (String) request.getAttribute("subject");
    int year = (int) request.getAttribute("year");
    List<CommentDTO> commentList = (List<CommentDTO>) request.getAttribute("listComments");
    int studentId = (int) request.getAttribute("studentId");
    int teacherId = (int) request.getAttribute("teacherId");
    int subjectId = (int) request.getAttribute("subjectId");
    String teacherName = (String) request.getAttribute("teacherName");

    // Determinar a classe da casa para a imagem
    String studentHouseNameRaw = profile.getHouseName();
    String studentHouseNameClass = studentHouseNameRaw != null ?
            (studentHouseNameRaw.equals("Grifinória") ? "grifinoria" :
                    studentHouseNameRaw.equals("Lufa-Lufa") ? "lufalufa" :
                            studentHouseNameRaw.equals("Sonserina") ? "sonserina" : "corvinal") : "";
%>

<!-- Botão de voltar no canto direito -->
<header>
<div class="btn-voltar">
    <form action="<%= request.getContextPath() %>/teacher/students" method="post">
        <input type="hidden" name="teacherId" value="<%= teacherId %>">
        <input type="hidden" name="year" value="<%= year %>">
        <input type="hidden" name="houseName" value="<%= houseName %>">
        <input type="hidden" name="teacherName" value="<%=teacherName%>">
        <input type="hidden" name="subject" value="<%= subject %>">
        <input type="hidden" name="showGrades" value="false">
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
</header>
<main>
    <aside class="informacoes">
        <h1><%= profile.getName() %></h1>

        <img src="<%= profile.getImageUrl() != null ? profile.getImageUrl() : "/imgs/default-student.png" %>"
             alt="Foto do Aluno">

        <div class="info-aluno">
            <p><strong>Nome Completo:</strong> <%= profile.getName() %></p>
            <p><strong>Responsável Legal:</strong> <%= profile.getGuardianName() != null ? profile.getGuardianName() : "-" %></p>
            <p><strong>Ano Letivo Atual:</strong> <%= year %>º Ano</p>
            <p><strong>Casa:</strong> <%= profile.getHouseName() %></p>
            <p><strong>Matéria:</strong> <%= subject %></p>
        </div>
    </aside>

    <aside class="observacoes">
        <div class="scroll">
            <% if (commentList != null && !commentList.isEmpty()) {
                for (CommentDTO comment : commentList) {
                    int score = comment.getScore();
                    String scoreClass = "neutro";
                    String scoreDisplay = String.valueOf(score);

                    if (score < 0) {
                        scoreClass = "negativo";
                    } else if (score > 0) {
                        scoreClass = "positivo";
                        scoreDisplay = "+" + score;
                    }
            %>
            <div class="card">
                <h1>Comentário</h1>
                <p><%= comment.getContent() %></p>
                <p class="pontos <%= scoreClass %>"><%= scoreDisplay %></p>
                <small style="display: block; text-align: right; color: #888; margin-top: 10px;">
                    <%= comment.getCreatedAt() %>
                </small>
            </div>
            <%   }
            } else { %>
            <div style="text-align: center; color: #888; padding: 40px;">
                Nenhum comentário para este aluno.
            </div>
            <% } %>
        </div>

        <!-- Botão para adicionar observação -->
        <input type="button" value="Adicionar Observação" id="btnAddObservacao">
    </aside>

    <!-- Overlay e formulário de adição de observação -->
    <div class="overlay escondido"></div>
    <div class="escondido div-add">
        <h1>Adicionar Observação</h1>
        <form action="<%= request.getContextPath() %>/teacher/insertComment" method="post" id="commentForm">
            <input type="hidden" name="studentId" value="<%= studentId %>">
            <input type="hidden" name="teacherId" value="<%= teacherId %>">
            <input type="hidden" name="subjectId" value="<%= subjectId %>">
            <input type="hidden" name="teacherName" value="<%=teacherName%>">
            <input type="hidden" name="year" value="<%= year %>">
            <input type="hidden" name="houseName" value="<%= houseName %>">
            <input type="hidden" name="subject" value="<%= subject %>">

            <label for="descricao-observacao">Descrição da Observação:</label>
            <textarea id="descricao-observacao" name="comment" placeholder="Digite a descrição da observação" required></textarea>

            <label for="pontos-observacao">Núm. pontos:</label>
            <input type="number" name="score" id="pontos-observacao" placeholder="Ex: 1 ou -1" required>

            <input type="submit" value="Adicionar" id="btnSubmitComment">
        </form>
    </div>
</main>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        console.log('DOM carregado!'); // Log para debug

        // CORREÇÃO: Logo que volta para o início
        const voltarInicioImg = document.querySelector('.logo');

        if (voltarInicioImg) {
            console.log('Logo encontrada!');
            voltarInicioImg.style.cursor = 'pointer';

            voltarInicioImg.addEventListener('click', () => {
                console.log('Logo clicada!');
                window.location.href = "${pageContext.request.contextPath}/";
            });
        } else {
            console.log('Logo NÃO encontrada!');
        }

        // Funcionalidade para adicionar observação
        const btnAddObservacao = document.getElementById('btnAddObservacao');
        const divAdd = document.querySelector('.div-add');
        const overlay = document.querySelector('.overlay');
        const btnSubmit = document.getElementById('btnSubmitComment');

        console.log('Elementos encontrados:', {
            btnAddObservacao: !!btnAddObservacao,
            divAdd: !!divAdd,
            overlay: !!overlay,
            btnSubmit: !!btnSubmit
        });

        if (btnAddObservacao && divAdd && overlay) {
            btnAddObservacao.addEventListener('click', (e) => {
                console.log('Botão de adicionar observação clicado!');
                e.preventDefault(); // Prevenir qualquer comportamento padrão
                divAdd.classList.remove('escondido');
                overlay.classList.remove('escondido');
                console.log('Popup aberto!');
            });
        } else {
            console.log('ERRO: Elementos necessários não encontrados!');
        }

        if (btnSubmit && overlay && divAdd) {
            btnSubmit.addEventListener('click', (e) => {
                console.log('Botão de submit clicado!');
                // O formulário será submetido normalmente
                // Não prevenir o comportamento padrão
            });
        }

        if (overlay && divAdd) {
            overlay.addEventListener('click', () => {
                console.log('Overlay clicado!');
                divAdd.classList.add('escondido');
                overlay.classList.add('escondido');
            });
        } else {
            console.log('Overlay ou divAdd não encontrados para o evento de clique');
        }

        // Prevenir que o clique no formulário feche o overlay
        const form = document.querySelector('.div-add form');
        if (form) {
            form.addEventListener('click', (e) => {
                e.stopPropagation();
                console.log('Clique no formulário - propagação parada');
            });
        } else {
            console.log('Formulário não encontrado!');
        }
    });
</script>
</body>
</html>