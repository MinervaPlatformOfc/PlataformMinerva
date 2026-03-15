<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.minerva.dto.AdminDTO" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CRUD - Administradores</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/crud/inicio.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/crud/admin.css">
    <script src="${pageContext.request.contextPath}/js/admin.js" defer></script>
    <script src="${pageContext.request.contextPath}/js/search.js" defer></script>
</head>
<body>
<%
    // Recuperando atributos do request
    String name = (String) request.getAttribute("name");
    String url = (String) request.getAttribute("url");
    List<AdminDTO> admins = (List<AdminDTO>) request.getAttribute("adminList");
%>

<header>
    <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="54px" fill="#C7D9E5" class="escondido" id="menu-sandwich"><path d="M120-240v-80h720v80H120Zm0-200v-80h720v80H120Zm0-200v-80h720v80H120Z"/></svg>
    <div class="search-container">
        <input type="search" name="pesquisar-registros" id="searchbar"
               placeholder="Pesquisar Administradores" oninput="search()">
        <input type="hidden" id="searchColumns" value="nome,email">

        <form action="${pageContext.request.contextPath}/recharge" method="post" class="recharge-form">
            <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
            <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
            <input type="hidden" name="endpoint" value="/admin/ViewAdmins">
            <button type="submit" class="recharge-btn" title="Atualizar dados">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#fccb4f">
                    <path d="M480-160q-134 0-227-93t-93-227q0-134 93-227t227-93q69 0 132 28.5T720-690v-110h80v280H520v-80h168q-32-56-87.5-88T480-720q-100 0-170 70t-70 170q0 100 70 170t170 70q77 0 139-44t87-116h84q-28 106-114 173t-196 67Z"/>
                </svg>
            </button>
        </form>
    </div>

    <button class="user-profile">
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

            <!-- PROFESSOR -->
            <li>
                <form action="${pageContext.request.contextPath}/admin/ViewTeachers" method="get">
                    <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                    <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                    <button type="submit" class="nav-button">
                        <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3"><path d="M260-320q47 0 91.5 10.5T440-278v-394q-41-24-87-36t-93-12q-36 0-71.5 7T120-692v396q35-12 69.5-18t70.5-6Zm260 42q44-21 88.5-31.5T700-320q36 0 70.5 6t69.5 18v-396q-33-14-68.5-21t-71.5-7q-47 0-93 12t-87 36v394Zm-40 118q-48-38-104-59t-116-21q-42 0-82.5 11T100-198q-21 11-40.5-1T40-234v-482q0-11 5.5-21T62-752q46-24 96-36t102-12q58 0 113.5 15T480-740q51-30 106.5-45T700-800q52 0 102 12t96 36q11 5 16.5 15t5.5 21v482q0 23-19.5 35t-40.5 1q-37-20-77.5-31T700-240q-60 0-116 21t-104 59ZM280-494Z"/></svg>
                        <span>Professor</span>
                    </button>
                </form>
            </li>

            <!-- ADMIN (ATUAL) -->
            <li id="guia-aberta">
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

<%
    String msgBruta = request.getAttribute("msg") != null ? (String) request.getAttribute("msg") : "";
    String msg = !(msgBruta.equals("")||msgBruta.isEmpty())?msgBruta:"Gerencie os registros dos administradores";
%>

<main>
    <div class="content-wrapper">
        <h1>Administradores</h1>
        <p><%=msg%></p>

        <div id="div-crud">
            <button class="btn-amarelo" id="btn-inserir">
                <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#151a2e">
                    <path d="M440-440H200v-80h240v-240h80v240h240v80H520v240h-80v-240Z"/>
                </svg>
                Inserir novo registro
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
            <h2>Inserir Administrador</h2>

            <form action="${pageContext.request.contextPath}/admin/insertAdmin" method="post" enctype="multipart/form-data">
                <div class="foto-atual" style="width: 100%; display: flex;flex-direction: column;justify-content: center;align-items: center">
                    <label>Foto do Perfil:</label>
                    <img src="" alt="foto-preview" id="foto-preview-insert" style="display: none;">
                </div>

                <label for="nome-insert">Nome</label>
                <input type="text" name="nameInsert" id="nome-insert" required>

                <label for="email-insert">Email</label>
                <input type="email" name="emailInsert" id="email-insert" required>

                <label for="senha-insert">Senha</label>
                <input type="password" name="passwordInsert" id="senha-insert" required>

                <label for="foto-insert">Foto</label>
                <input type="file" name="imageInsert" id="foto-insert" accept="image/*" required>

                <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                <input type="submit" value="Inserir administrador">
            </form>
        </div>

        <!-- Modal de Atualização -->
        <div class="escondido div-update">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3" class="fechar"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg>
            <h2>Atualizar Administrador</h2>
            <p class="id-update">ID: <span></span></p>

            <form action="${pageContext.request.contextPath}/admin/updateAdmin" method="post" enctype="multipart/form-data">
                <div class="foto-atual">
                    <label>Foto Atual:</label>
                    <img src="" alt="foto-perfil" id="foto-preview-update">
                </div>

                <label for="nome-update">Nome</label>
                <input type="text" name="nameInput" id="nome-update" required>

                <label for="email-update">Email</label>
                <input type="email" name="emailInput" id="email-update" required>

                <label for="foto-update">Nova Foto</label>
                <input type="file" name="imageInput" id="foto-update" accept="image/*">

                <input type="hidden" name="id" id="id-update" value="">
                <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                <input type="hidden" name="url" value="<%= url != null ? url : "" %>">

                <input type="hidden" name="emailOriginal" id="email-original" value="">
                <input type="hidden" name="fotoOriginal" id="foto-original" value="">
                <input type="submit" value="Atualizar administrador">
            </form>
        </div>

        <!-- Modal de Exclusão -->
        <div class="escondido div-delete">
            <svg xmlns="http://www.w3.org/2000/svg" height="24px" viewBox="0 -960 960 960" width="24px" fill="#e3e3e3" class="fechar"><path d="m256-200-56-56 224-224-224-224 56-56 224 224 224-224 56 56-224 224 224 224-56 56-224-224-224 224Z"/></svg>
            <h2>Confirmar Exclusão</h2>
            <div>
                <img src="${pageContext.request.contextPath}/Icon.svg" alt="">
                <p>Tem certeza que deseja excluir permanentemente o administrador:</p>
            </div>
            <p class="nome-delete"></p>

            <div>Atenção! Esta ação não poderá ser revertida e o administrador será removido completamente do sistema!</div>

            <form action="${pageContext.request.contextPath}/admin/deleteAdmin" method="post">
                <input type="hidden" name="id" class="id-delete" value="">
                <input type="hidden" name="name" value="<%= name != null ? name : "" %>">
                <input type="hidden" name="url" value="<%= url != null ? url : "" %>">
                <input type="submit" value="Confirmar exclusão">
            </form>
        </div>

        <section id="tabela">
            <h2>Lista de Administradores</h2>
            <div class="table-container">
                <table>
                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Foto</th>
                        <th>Nome</th>
                        <th>Email</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% if (admins != null && !admins.isEmpty()) { %>
                    <% for (AdminDTO admin : admins) { %>
                    <tr class="linhas" data-id="<%= admin.getId() %>"
                        data-foto="<%= admin.getImageUrl() != null ? admin.getImageUrl() : "" %>">
                        <td data-field="id"><%= admin.getId() %></td>
                        <td data-field="foto">
                            <img src="<%= admin.getImageUrl() != null ? admin.getImageUrl() : "" %>"
                                 alt="foto"
                                 class="foto-perfil-tabela">
                        </td>
                        <td data-field="nome" data-original="<%= admin.getName() %>"><%= admin.getName() %></td>
                        <td data-field="email" data-original="<%= admin.getEmail() %>"><%= admin.getEmail() %></td>
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
                    <% } %>
                    <% } else { %>
                    <tr>
                        <td colspan="6" style="text-align: center;">Nenhum administrador encontrado</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </section>
    </div>

    <div class="overlay escondido"></div>
</main>
</body>
</html>