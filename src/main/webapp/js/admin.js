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
    const inputNomeUpdate = divAtualizar?.querySelector('input[name="nome"]');
    const inputEmailUpdate = divAtualizar?.querySelector('input[name="email"]');
    const fotoPreviewUpdate = document.querySelector('#foto-preview-update');
    const inputFotoUpdate = document.querySelector('#foto-update');
    const inputUpdateId = document.querySelector('#id-update');

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

    /* Função de ordenação da tabela */
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
                    // Assume que IDs maiores são mais recentes
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

            const colunas = linha.querySelectorAll('td');
            if (colunas.length < 4) return;

            const id = linha.dataset.id;
            const foto = colunas[1]?.querySelector('img')?.src || '';
            const nome = colunas[2]?.textContent.trim() || '';
            const email = colunas[3]?.textContent.trim() || '';

            // Editar
            const botaoEditar = event.target.closest('.editar');
            if (botaoEditar) {

                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) inputUpdateId.value = id;
                if (inputNomeUpdate) inputNomeUpdate.value = nome;
                if (inputEmailUpdate) inputEmailUpdate.value = email;
                if (fotoPreviewUpdate) fotoPreviewUpdate.src = foto;

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