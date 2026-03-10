<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.TeacherDTO" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.example.minerva.dto.SubjectDTO" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD - Professores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/crud/inicio.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/crud/admin.css">
    <style>
        .info-group {
            margin-bottom: 15px;
        }
        .info-group label {
            font-weight: bold;
            display: block;
            margin-bottom: 5px;
            color: #fccb4f;
        }
        .info-text {
            padding: 8px;
            background-color: #2a2f45;
            border-radius: 4px;
            margin: 0;
        }
        .subjects-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));
            gap: 10px;
            margin-top: 10px;
            max-height: 200px;
            overflow-y: auto;
            padding: 10px;
            background-color: #2a2f45;
            border-radius: 4px;
        }
        .subject-checkbox {
            display: flex;
            align-items: center;
            gap: 8px;
        }
        .subject-checkbox input[type="checkbox"] {
            width: auto;
            margin: 0;
        }
        .subject-checkbox label {
            color: #e3e3e3;
            font-size: 14px;
        }
    </style>
    <script src="${pageContext.request.contextPath}/js/teachers.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/search.js" defer></script>
</head>
<body>
<%
    // Recuperando atributos do request
    String name = (String) request.getAttribute("name");
    String url = (String) request.getAttribute("url");
    List<TeacherDTO> teachers = (List<TeacherDTO>) request.getAttribute("teacherList");
    HashMap<String, Integer> hash = (HashMap<String, Integer>) request.getAttribute("house");
    List<SubjectDTO> subjects = (List<SubjectDTO>) request.getAttribute("subjects");
%>

<header>
    <div class="search-container">
        <input type="search" name="pesquisar-registros" id="searchbar"
               placeholder="Pesquisar Professores" oninput="search()">
        <input type="hidden" id="searchColumns" value="nome,email,house,wizardTitle">

        <form action="${pageContext.request.contextPath}/recharge" method="post" class="recharge-form">
            <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
            <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
            <input type="hidden" name="endpoint" value="/admin/ViewTeachers">
            <button type="submit" class="recharge-btn" title="Atualizar dados">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fccb4f">
                    <path d="M480-160q-134 0-227-93t-93-227q0-134 93-227t227-93q69 0 132 28.5T720-690v-110h80v280H520v-80h168q-32-56-87.5-88T480-720q-100 0-170 70t-70 170q0 100 70 170t170 70q77 0 139-44t87-116h84q-28 106-114 173t-196 67Z"/>
                </svg>
            </button>
        </form>
    </div>

    <button class="user-profile" style="pointer-events: none;">
        <img src="<%= url != null ? url : "" %>" alt="foto-perfil">
        <%= name != null ? name : "Admin" %>
    </button>
</header>

<aside class="sidebar">
    <img src="" alt="minerva-logo" class="logo">
    <nav>
        <ul>
            <!-- HOME -->
            <li>
                <form action="${pageContext.request.contextPath}/admin/users" method="post">
                    <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                    <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M240-200h120v-240h240v240h120v-360L480-740 240-560v360Zm-80 80v-480l320-240 320 240v480H520v-240h-80v240H160Zm320-350Z"/></svg>
                        <span>Inicio</span>
                    </button>
                </form>
            </li>

            <!-- PROFESSOR (ATUAL) -->
            <li id="guia-aberta">
                <form action="${pageContext.request.contextPath}/admin/ViewTeachers" method="get">
                    <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                    <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M260-320q47 0 91.5 10.5T440-278v-394q-41-24-87-36t-93-12q-36 0-71.5 7T120-692v396q35-12 69.5-18t70.5-6Zm260 42q44-21 88.5-31.5T700-320q36 0 70.5 6t69.5 18v-396q-33-14-68.5-21t-71.5-7q-47 0-93 12t-87 36v394Zm-40 118q-48-38-104-59t-116-21q-42 0-82.5 11T100-198q-21 11-40.5-1T40-234v-482q0-11 5.5-21T62-752q46-24 96-36t102-12q58 0 113.5 15T480-740q51-30 106.5-45T700-800q52 0 102 12t96 36q11 5 16.5 15t5.5 21v482q0 23-19.5 35t-40.5 1q-37-20-77.5-31T700-240q-60 0-116 21t-104 59ZM280-494Z"/></svg>
                        <span>Professor</span>
                    </button>
                </form>
            </li>

            <!-- ADMIN -->
            <li>
                <form action="${pageContext.request.contextPath}/admin/ViewAdmins" method="get">
                    <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                    <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M367-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47ZM160-160v-112q0-34 17.5-62.5T224-378q62-31 126-46.5T480-440q66 0 130 15.5T736-378q29 15 46.5 43.5T800-272v112H160Zm80-80h480v-32q0-11-5.5-20T700-306q-54-27-109-40.5T480-360q-56 0-111 13.5T260-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q560-607 560-640t-23.5-56.5Q513-720 480-720t-56.5 23.5Q400-673 400-640t23.5 56.5Q447-560 480-560t56.5-23.5ZM480-640Zm0 400Z"/></svg>
                        <span>Admin</span>
                    </button>
                </form>
            </li>

            <!-- ALUNO -->
            <li>
                <form action="${pageContext.request.contextPath}/admin/ViewStudents" method="get">
                    <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                    <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M40-160v-112q0-34 17.5-62.5T104-378q62-31 126-46.5T360-440q66 0 130 15.5T616-378q29 15 46.5 43.5T680-272v112H40Zm720 0v-120q0-44-24.5-84.5T666-434q51 6 96 20.5t84 35.5q36 20 55 44.5t19 53.5v120H760ZM247-527q-47-47-47-113t47-113q47-47 113-47t113 47q47 47 47 113t-47 113q-47 47-113 47t-113-47Zm466 0q-47 47-113 47-11 0-28-2.5t-28-5.5q27-32 41.5-71t14.5-81q0-42-14.5-81T544-792q14-5 28-6.5t28-1.5q66 0 113 47t47 113q0 66-47 113ZM120-240h480v-32q0-11-5.5-20T580-306q-54-27-109-40.5T360-360q-56 0-111 13.5T140-306q-9 5-14.5 14t-5.5 20v32Zm296.5-343.5Q440-607 440-640t-23.5-56.5Q393-720 360-720t-56.5 23.5Q280-673 280-640t23.5 56.5Q327-560 360-560t56.5-23.5ZM360-240Zm0-400Z"/></svg>
                        <span>Aluno</span>
                    </button>
                </form>
            </li>

            <!-- MATÉRIAS -->
            <li>
                <form action="${pageContext.request.contextPath}/admin/ViewSubjects" method="post">
                    <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                    <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M240-320h320v-80H240v80Zm0-160h480v-80H240v80Zm-80 320q-33 0-56.5-23.5T80-240v-480q0-33 23.5-56.5T160-800h240l80 80h320q33 0 56.5 23.5T880-640v400q0 33-23.5 56.5T800-160H160Zm0-80h640v-400H447l-80-80H160v480Zm0 0v-480 480Z"/></svg>
                        <span>Matérias</span>
                    </button>
                </form>
            </li>

            <!-- SAIR -->
            <li id="sair">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M200-120q-33 0-56.5-23.5T120-200v-160h80v160h560v-560H200v160h-80v-160q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm220-160-56-58 102-102H120v-80h346L364-622l56-58 200 200-200 200Z"/></svg>
                        <span>Sair</span>
                    </button>
                </form>
            </li>
        </ul>
    </nav>
</aside>

<main>
    <div class="content-wrapper">
        <h1>Professores</h1>
        <p>Gerencie os registros dos professores</p>

        <div id="div-crud">
            <button class="btn-amarelo" id="btn-inserir">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#151a2e">
                    <path d="M440-440H200v-80h240v-240h80v240h240v80H520v240h-80v-240Z"/>
                </svg>
                Inserir novo professor
            </button>

            <div class="btn-filtro fechado">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3" id="filtro">
                    <path d="M440-160q-17 0-28.5-11.5T400-200v-240L168-736q-15-20-4.5-42t36.5-22h560q26 0 36.5 22t-4.5 42L560-440v240q0 17-11.5 28.5T520-160h-80Zm40-308 198-252H282l198 252Zm0 0Z"/>
                </svg>
                <span id="filtros">Filtros</span>
            </div>

            <div class="escondido" id="divEscondida">
                <form action="" id="filtro-form">
                    <label for="order-by">Ordenar Por</label>
                    <select name="order-by" id="order-by">
                        <option value="recente">Mais recente</option>
                        <option value="nome-desc">Nome [A-Z]</option>
                        <option value="nome-asc">Nome [Z-A]</option>
                    </select>

                    <input type="submit" value="Aplicar Filtros">
                    <input type="reset" value="Limpar">
                </form>
            </div>
        </div>

        <!-- Modal de Inserção -->
        <div class="escondido div-insert">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3" class="fechar"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg>
            <h2>Inserir Professor</h2>

            <form action="${pageContext.request.contextPath}/admin/insertTeacher" method="post" enctype="multipart/form-data">
                <label for="nome-insert">Nome</label>
                <input type="text" name="nome" id="nome-insert" required>

                <label for="email-insert">Email</label>
                <input type="email" name="email" id="email-insert" required>

                <label for="senha-insert">Senha</label>
                <input type="password" name="senha" id="senha-insert" required>

                <label for="house-insert">Casa</label>
                <select name="houseIdInput" id="house-insert">
                    <option value="" disabled selected>Selecione uma casa</option>
                    <%
                        if (hash != null) {
                            for (Map.Entry<String, Integer> entry : hash.entrySet()) {
                    %>
                    <option value="<%= entry.getValue() %>"><%= entry.getKey() %></option>
                    <%
                            }
                        }
                    %>
                </select>

                <label class="label">Madeira da varinha:</label>
                <select name="wood" required>
                    <option value="" disabled selected>Selecione uma madeira</option>
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
                </select>

                <label class="label">Núcleo da varinha:</label>
                <select name="core" required>
                    <option value="" disabled selected>Selecione um núcleo</option>
                    <option>Phoenix Feather</option>
                    <option>Dragon Heartstring</option>
                    <option>Unicorn Hair</option>
                    <option>Veela Hair</option>
                    <option>Thestral Tail Hair</option>
                    <option>Basilisk Horn</option>
                    <option>Troll Whisker</option>
                    <option>Kneazle Whisker</option>
                </select>

                <label class="label">Flexibilidade da varinha:</label>
                <select name="flexibility" required>
                    <option value="" disabled selected>Selecione uma flexibilidade</option>
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
                </select>

                <label for="experiences-insert">Experiências Passadas</label>
                <textarea name="pastExperiences" id="experiences-insert" rows="3" placeholder="Descreva as experiências do professor..."></textarea>

                <label for="title-insert">Título</label>
                <input type="text" name="wizardTitle" id="title-insert" placeholder="Ex: Professor de Poções, Mestre em Transfiguração">

                <label><input type="checkbox" name="headHouse" id="headHouse-insert"> Chefe de casa</label>

                <label for="foto-insert">Foto</label>
                <input type="file" name="foto" id="foto-insert" accept="image/*">

                <!-- Matérias no Insert -->
                <div class="subjects-container">
                    <label>Matérias que vai lecionar:</label>
                    <div class="subjects-grid">
                        <% if (subjects != null) { %>
                        <% for (SubjectDTO subject : subjects) { %>
                        <div class="subject-checkbox">
                            <input type="checkbox" name="newSubjects" value="<%= subject.getSubjectId() %>" id="insert-subject-<%= subject.getSubjectId() %>">
                            <label for="insert-subject-<%= subject.getSubjectId() %>"><%= subject.getSubjectName() %></label>
                        </div>
                        <% } %>
                        <% } %>
                    </div>
                </div>

                <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                <input type="submit" value="Inserir professor">
            </form>
        </div>

        <!-- Modal de Atualização -->
        <div class="escondido div-update">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3" class="fechar"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg>
            <h2>Atualizar Professor</h2>
            <p class="id-update">ID: <span></span></p>

            <form action="${pageContext.request.contextPath}/admin/updateTeacher" method="post" enctype="multipart/form-data">
                <!-- Apenas visualização dos dados (não editáveis) -->
                <div class="foto-atual">
                    <label>Foto Atual:</label>
                    <img src="" alt="foto-perfil" id="foto-preview-update">
                </div>

                <div class="info-group">
                    <label>Nome:</label>
                    <p class="info-text" id="nome-display"></p>
                </div>

                <div class="info-group">
                    <label>Email:</label>
                    <p class="info-text" id="email-display"></p>
                </div>

                <div class="info-group">
                    <label>Casa:</label>
                    <p class="info-text" id="casa-display"></p>
                </div>

                <!-- Campos editáveis -->
                <label CLASS="label">Madeira da varinha:</label>
                <select name="wood" id="wood-update">
                    <option value="" disabled selected>Selecione uma madeira</option>
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
                </select>

                <label CLASS="label">Núcleo da varinha:</label>
                <select name="core" id="core-update">
                    <option value="" disabled selected>Selecione um núcleo</option>
                    <option>Phoenix Feather</option>
                    <option>Dragon Heartstring</option>
                    <option>Unicorn Hair</option>
                    <option>Veela Hair</option>
                    <option>Thestral Tail Hair</option>
                    <option>Basilisk Horn</option>
                    <option>Troll Whisker</option>
                    <option>Kneazle Whisker</option>
                </select>

                <label CLASS="label">Flexibilidade da varinha:</label>
                <select name="flexibility" id="flexibility-update">
                    <option value="" disabled selected>Selecione uma flexibilidade</option>
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
                </select>

                <label for="experiences-update">Experiências Passadas</label>
                <textarea name="pastExperiences" id="experiences-update" rows="3"></textarea>

                <label for="title-update">Título</label>
                <input type="text" name="wizardTitle" id="title-update">

                <label><input type="checkbox" name="headHouse" id="headHouse-update"> Chefe de casa</label>

                <!-- Matérias -->
                <div class="subjects-container">
                    <label>Matérias que leciona:</label>
                    <div class="subjects-grid">
                        <% if (subjects != null) { %>
                        <% for (SubjectDTO subject : subjects) { %>
                        <div class="subject-checkbox">
                            <input type="checkbox" name="newSubjects" value="<%= subject.getSubjectId() %>" id="subject-<%= subject.getSubjectId() %>">
                            <label for="subject-<%= subject.getSubjectId() %>"><%= subject.getSubjectName() %></label>
                        </div>
                        <% } %>
                        <% } %>
                    </div>
                </div>

                <!-- Hidden fields -->
                <input type="hidden" name="id" id="id-update">
                <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                <input type="hidden" name="url" value="<%= url != null ? url : "" %>">

                <!-- Matérias originais (para o servidor saber quais foram removidas) -->
                <div id="original-subjects-container"></div>

                <input type="submit" value="Atualizar professor">
            </form>
        </div>

        <!-- Modal de Exclusão -->
        <div class="escondido div-delete">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3" class="fechar"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg>
            <h2>Confirmar Exclusão</h2>
            <div>
                <img src="${pageContext.request.contextPath}/Icon.svg" alt="">
                <p>Tem certeza que deseja excluir permanentemente o professor:</p>
            </div>
            <p class="nome-delete"></p>

            <div>Atenção! Esta ação não poderá ser revertida e o professor será removido completamente do sistema!</div>

            <form action="${pageContext.request.contextPath}/admin/deleteTeacher" method="post">
                <input type="hidden" name="id" class="id-delete" value="">
                <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                <input type="submit" value="Confirmar exclusão">
            </form>
        </div>

        <section id="tabela">
            <h2>Lista de Professores</h2>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Foto</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Casa</th>
                        <th>Varinha</th>
                        <th>Título</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (teachers != null && !teachers.isEmpty()) {
                            for (TeacherDTO teacher : teachers) {
                                String wand = teacher.getWand();
                                String wood = "";
                                String core = "";
                                String flexibility = "";

                                if (wand != null && !wand.isEmpty()) {
                                    String[] parts = wand.split(" - ");
                                    if (parts.length >= 1) wood = parts[0];
                                    if (parts.length >= 2) core = parts[1];
                                    if (parts.length >= 3) flexibility = parts[2];
                                }

                                String subjectsJson = "[]";
                                if (teacher.getSubjects() != null && !teacher.getSubjects().isEmpty()) {
                                    StringBuilder sb = new StringBuilder("[");
                                    for (int i = 0; i < teacher.getSubjects().size(); i++) {
                                        if (i > 0) sb.append(",");
                                        // ADICIONA ASPAS DUPLAS AO REDOR DO NOME
                                        sb.append("\"").append(teacher.getSubjects().get(i)).append("\"");
                                    }
                                    sb.append("]");
                                    subjectsJson = sb.toString();

                                    // DEBUG
//                                    System.out.println("subjectsJson: " + subjectsJson);
                                }
                    %>
                    <tr class="linhas"
                        data-id="<%= teacher.getId() %>"
                        data-foto="<%= teacher.getImageUrl() != null ? teacher.getImageUrl() : "" %>"
                        data-experiences="<%= teacher.getPastExperiences() != null ? teacher.getPastExperiences().replace("\"", "&quot;") : "" %>"
                        data-title="<%= teacher.getWizardTitle() != null ? teacher.getWizardTitle() : "" %>"
                        data-head-house="<%= teacher.getHeadHouse() %>"
                        data-wood="<%= wood %>"
                        data-core="<%= core %>"
                        data-flexibility="<%= flexibility %>"
                        data-subjects='<%= subjectsJson %>'>
                        <td data-field="id"><%= teacher.getId() %></td>
                        <td data-field="foto">
                            <img src="<%= teacher.getImageUrl() != null ? teacher.getImageUrl() : "" %>"
                                 alt="foto"
                                 class="foto-perfil-tabela">
                        </td>
                        <td data-field="nome" data-original="<%= teacher.getName() %>"><%= teacher.getName() %></td>
                        <td data-field="email" data-original="<%= teacher.getEmail() %>"><%= teacher.getEmail() %></td>
                        <td data-field="house" data-original="<%= teacher.getHouse() != null ? teacher.getHouse() : "" %>"><%= teacher.getHouse() != null ? teacher.getHouse() : "-" %></td>
                        <td data-field="wand" data-original="<%= teacher.getWand() != null ? teacher.getWand() : "" %>"><%= teacher.getWand() != null ? teacher.getWand() : "-" %></td>
                        <td data-field="title" data-original="<%= teacher.getWizardTitle() != null ? teacher.getWizardTitle() : "" %>"><%= teacher.getWizardTitle() != null ? teacher.getWizardTitle() : "-" %></td>
                        <td>
                            <button class="editar">
                                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fccb4f">
                                    <path d="M200-200h57l391-391-57-57-391 391v57Zm-80 80v-170l528-527q12-11 26.5-17t30.5-6q16 0 31 6t26 18l55 56q12 11 17.5 26t5.5 30q0 16-5.5 30.5T817-647L290-120H120Zm640-584-56-56 56 56Zm-141 85-28-29 57 57-29-28Z"/>
                                </svg>
                            </button>
                        </td>
                        <td>
                            <button class="excluir">
                                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#EA3323">
                                    <path d="M280-120q-33 0-56.5-23.5T200-200v-520h-40v-80h200v-40h240v40h200v80h-40v520q0 33-23.5 56.5T680-120H280Zm400-600H280v520h400v-520ZM360-280h80v-360h-80v360Zm160 0h80v-360h-80v360ZM280-720v520-520Z"/>
                                </svg>
                            </button>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="9" style="text-align: center;">Nenhum professor encontrado</td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </section>
    </div>

    <div class="overlay escondido"></div>
</main>
</body>
</html>