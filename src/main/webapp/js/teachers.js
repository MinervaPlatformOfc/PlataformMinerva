document.addEventListener("DOMContentLoaded", function () {

    // Variáveis
    const overlay = document.querySelector('.overlay');

    // Elementos do Insert
    let botaoInserir = document.querySelector('.btn-amarelo');
    let botaoFecharInsert = document.querySelector('.div-insert .fechar');
    let botaoInsere = document.querySelector('.div-insert input[type="submit"]');
    let divInsere = document.querySelector('.div-insert');

    // Elementos do Update
    const divAtualizar = document.querySelector('.div-update');
    const idUpdateSpan = document.querySelector('.id-update span');
    const inputUpdateId = document.querySelector('#id-update');

    // Campos do Update
    const inputNomeUpdate = document.querySelector('#nome-update');
    const inputEmailUpdate = document.querySelector('#email-update');
    const inputHouseUpdate = document.querySelector('#house-update');
    const inputWandUpdate = document.querySelector('#wand-update');
    const inputExperiencesUpdate = document.querySelector('#experiences-update');
    const inputTitleUpdate = document.querySelector('#title-update');
    const fotoPreviewUpdate = document.querySelector('#foto-preview-update');
    const inputFotoUpdate = document.querySelector('#foto-update');

    // Elementos do Delete
    const divDelete = document.querySelector('.div-delete');
    const nomeDelete = document.querySelector('.nome-delete');
    const idDeleteInput = document.querySelector('.id-delete');

    // Elementos dos Filtros
    let filtros = document.querySelector('#filtros');
    let divEscondida = document.querySelector('#divEscondida');
    let filtro = document.querySelector('#filtro');
    let btnFiltro = document.querySelector('.btn-filtro');
    const filtroForm = document.querySelector('#filtro-form');
    const orderBySelect = document.querySelector('#order-by');

    // Elementos da Tabela
    const tabela = document.querySelector('#tabela table');
    const tbody = document.querySelector('#tabela tbody');

    // Elementos da Sidebar
    const img = document.querySelector('aside img');
    const liS = document.querySelectorAll('nav li');

    // Preview da foto no Insert
    const inputFotoInsert = document.querySelector('#foto-insert');
    const fotoPreviewInsert = document.querySelector('#foto-preview-insert');

    /* Funções */
    function abrirModal(modal) {
        if (!modal) return;
        modal.classList.remove('escondido');
        overlay.classList.remove('escondido');
    }

    function fecharModal(modal) {
        if (!modal) return;

        overlay.classList.add("saindo");
        modal.classList.add("modal-saindo");

        setTimeout(() => {
            modal.classList.add("escondido");
            modal.classList.remove("modal-saindo");
            overlay.classList.add("escondido");
            overlay.classList.remove("saindo");
        }, 300);
    }

    function limparModalInsert() {
        document.querySelector('#nome-insert').value = "";
        document.querySelector('#email-insert').value = "";
        document.querySelector('#senha-insert').value = "";
        document.querySelector('#house-insert').value = "";
        document.querySelector('#wand-insert').value = "";
        document.querySelector('#experiences-insert').value = "";
        document.querySelector('#title-insert').value = "";
        document.querySelector('#foto-insert').value = "";
        if (fotoPreviewInsert) {
            fotoPreviewInsert.src = "";
            fotoPreviewInsert.style.display = "none";
        }
    }

    function limparModalUpdate() {
        if (idUpdateSpan) idUpdateSpan.textContent = "";
        if (inputUpdateId) inputUpdateId.value = "";
        if (inputNomeUpdate) inputNomeUpdate.value = "";
        if (inputEmailUpdate) inputEmailUpdate.value = "";
        if (inputHouseUpdate) inputHouseUpdate.value = "";
        if (inputWandUpdate) inputWandUpdate.value = "";
        if (inputExperiencesUpdate) inputExperiencesUpdate.value = "";
        if (inputTitleUpdate) inputTitleUpdate.value = "";
        if (fotoPreviewUpdate) fotoPreviewUpdate.src = "";
        if (inputFotoUpdate) inputFotoUpdate.value = "";
    }

    function limparModalDelete() {
        if (idDeleteInput) idDeleteInput.value = "";
        if (nomeDelete) nomeDelete.textContent = "";
    }

    /* Função de ordenação da tabela - SIMPLIFICADA */
    function ordenarTabela(criterio) {
        if (!tbody) return;

        const linhas = Array.from(tbody.querySelectorAll('tr'));
        if (linhas.length === 0) return;

        // Remove a linha de "Nenhum registro encontrado" se existir
        if (linhas.length === 1 && linhas[0].querySelector('td[colspan]')) return;

        const linhasOrdenadas = linhas.sort((a, b) => {
            const nomeA = a.querySelector('td[data-field="nome"]')?.textContent.trim() || '';
            const nomeB = b.querySelector('td[data-field="nome"]')?.textContent.trim() || '';

            switch(criterio) {
                case 'recente':
                    const idA = parseInt(a.dataset.id) || 0;
                    const idB = parseInt(b.dataset.id) || 0;
                    return idB - idA;

                case 'nome-desc':
                    return nomeA.localeCompare(nomeB);

                case 'nome-asc':
                    return nomeB.localeCompare(nomeA);

                default:
                    return 0;
            }
        });

        // Remove todas as linhas e adiciona ordenadas
        linhasOrdenadas.forEach(linha => tbody.appendChild(linha));
    }

    /* Event listener para o formulário de filtros */
    if (filtroForm) {
        filtroForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const criterio = orderBySelect.value;
            ordenarTabela(criterio);

            // Fecha o painel de filtros
            divEscondida.classList.add('escondido');
            filtros.style.color = 'white';
            filtro.style.fill = '#e3e3e3';
            btnFiltro.style.border = 'none';
            btnFiltro.classList.add('fechado');
        });

        // Botão Limpar
        const botaoLimpar = filtroForm.querySelector('input[type="reset"]');
        if (botaoLimpar) {
            botaoLimpar.addEventListener('click', (e) => {
                e.preventDefault();
                orderBySelect.value = 'recente';
                ordenarTabela('recente');
            });
        }
    }

    /* Preview de foto no Insert */
    if (inputFotoInsert) {
        inputFotoInsert.addEventListener('change', function() {
            if (this.files && this.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    if (fotoPreviewInsert) {
                        fotoPreviewInsert.src = e.target.result;
                        fotoPreviewInsert.style.display = 'block';
                    }
                };
                reader.readAsDataURL(this.files[0]);
            }
        });
    }

    /* Preview de foto no Update */
    if (inputFotoUpdate) {
        inputFotoUpdate.addEventListener('change', function() {
            if (this.files && this.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    if (fotoPreviewUpdate) {
                        fotoPreviewUpdate.src = e.target.result;
                    }
                };
                reader.readAsDataURL(this.files[0]);
            }
        });
    }

    /* Inserir */
    if (botaoInserir) {
        botaoInserir.addEventListener('click', () => {
            limparModalInsert();
            abrirModal(divInsere);
        });
    }

    if (botaoFecharInsert) {
        botaoFecharInsert.addEventListener('click', () => {
            fecharModal(divInsere);
        });
    }

    if (botaoInsere) {
        botaoInsere.addEventListener('click', () => {
            fecharModal(divInsere);
        });
    }

    /* Filtros - Abrir/Fechar */
    if (btnFiltro) {
        btnFiltro.addEventListener('click', () => {

            if (btnFiltro.classList.contains('fechado')) {

                divEscondida.classList.remove('escondido');
                filtros.style.color = '#fccb4f';
                filtro.style.fill = '#fccb4f';
                btnFiltro.style.border = '2px solid #fccb4f';
                btnFiltro.style.borderRadius = '12px';
                btnFiltro.classList.remove('fechado');

            } else {

                divEscondida.classList.add('escondido');
                filtros.style.color = 'white';
                filtro.style.fill = '#e3e3e3';
                btnFiltro.style.border = 'none';
                btnFiltro.classList.add('fechado');
            }
        });
    }

    /* Sidebar links */
    liS.forEach(li => {
        li.addEventListener('click', (event) => {
            if (!event.target.closest('button') && !event.target.closest('a')) {
                const form = li.querySelector('form');
                if (form) form.submit();
            }
        });
    });

    /* Logo voltando para a página inicial */
    if (img) {
        img.addEventListener('click', () => {
            window.location.href = "${pageContext.request.contextPath}/admin/users";
        });
    }

    /* Atualizar e Excluir na tabela */
    if (tabela) {
        tabela.addEventListener('click', function (event) {

            const linha = event.target.closest('tr');
            if (!linha) return;

            // Verifica se é a linha de "nenhum registro"
            if (linha.querySelector('td[colspan]')) return;

            const colunas = linha.querySelectorAll('td');
            if (colunas.length < 9) return;

            const id = linha.dataset.id;
            const foto = colunas[1]?.querySelector('img')?.src || '';
            const nome = colunas[2]?.textContent.trim() || '';
            const email = colunas[3]?.textContent.trim() || '';
            const casa = colunas[4]?.textContent.trim() === '-' ? '' : colunas[4]?.textContent.trim() || '';
            const varinha = colunas[5]?.textContent.trim() === '-' ? '' : colunas[5]?.textContent.trim() || '';
            const titulo = colunas[6]?.textContent.trim() === '-' ? '' : colunas[6]?.textContent.trim() || '';

            // Editar
            const botaoEditar = event.target.closest('.editar');
            if (botaoEditar) {

                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) inputUpdateId.value = id;
                if (inputNomeUpdate) inputNomeUpdate.value = nome;
                if (inputEmailUpdate) inputEmailUpdate.value = email;
                if (inputHouseUpdate) inputHouseUpdate.value = casa;
                if (inputWandUpdate) inputWandUpdate.value = varinha;
                if (inputTitleUpdate) inputTitleUpdate.value = titulo;
                if (fotoPreviewUpdate) fotoPreviewUpdate.src = foto;

                // Experiências não está na tabela, então não preenchemos
                if (inputExperiencesUpdate) inputExperiencesUpdate.value = "";

                abrirModal(divAtualizar);
                return;
            }

            // Excluir
            const botaoExcluir = event.target.closest('.excluir');
            if (botaoExcluir) {

                if (nomeDelete) nomeDelete.textContent = nome;
                if (idDeleteInput) idDeleteInput.value = id;

                abrirModal(divDelete);
                return;
            }
        });
    }

    /* Fechar Update */
    const botaoFecharUpdate = document.querySelector('.div-update .fechar');
    const botaoInsereUpd = document.querySelector('.div-update input[type="submit"]');

    if (botaoFecharUpdate) {
        botaoFecharUpdate.addEventListener('click', () => {
            fecharModal(divAtualizar);
        });
    }

    if (botaoInsereUpd) {
        botaoInsereUpd.addEventListener('click', () => {
            fecharModal(divAtualizar);
        });
    }

    /* Fechar Delete */
    const botaoFecharDelete = document.querySelector('.div-delete .fechar');

    if (botaoFecharDelete) {
        botaoFecharDelete.addEventListener('click', () => {
            fecharModal(divDelete);
        });
    }

    /* Overlay sair */
    if (overlay) {
        overlay.addEventListener('click', () => {
            fecharModal(divInsere);
            fecharModal(divAtualizar);
            fecharModal(divDelete);
        });
    }
});