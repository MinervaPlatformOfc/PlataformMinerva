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

    // Campos do Update - Visualização
    const nomeDisplay = document.querySelector('#nome-display');
    const emailDisplay = document.querySelector('#email-display');
    const casaDisplay = document.querySelector('#casa-display');
    const fotoPreviewUpdate = document.querySelector('#foto-preview-update');

    // Campos do Update - Editáveis
    const woodUpdate = document.querySelector('#wood-update');
    const coreUpdate = document.querySelector('#core-update');
    const flexibilityUpdate = document.querySelector('#flexibility-update');
    const inputExperiencesUpdate = document.querySelector('#experiences-update');
    const inputTitleUpdate = document.querySelector('#title-update');
    const headHouseUpdate = document.querySelector('#headHouse-update');
    const inputFotoUpdate = document.querySelector('#foto-update');
    const originalSubjectsContainer = document.querySelector('#original-subjects-container');

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
        document.querySelector('#experiences-insert').value = "";
        document.querySelector('#title-insert').value = "";
        document.querySelector('#foto-insert').value = "";

        // Limpar selects da varinha
        document.querySelector('select[name="wood"]').value = "";
        document.querySelector('select[name="core"]').value = "";
        document.querySelector('select[name="flexibility"]').value = "";

        // Desmarcar checkbox de chefe de casa
        const headHouseCheckbox = document.querySelector('#headHouse-insert');
        if (headHouseCheckbox) headHouseCheckbox.checked = false;

        // Desmarcar todos os checkboxes de matérias
        document.querySelectorAll('.div-insert input[name="newSubjects"]').forEach(cb => {
            cb.checked = false;
        });

        if (fotoPreviewInsert) {
            fotoPreviewInsert.src = "";
            fotoPreviewInsert.style.display = "none";
        }
    }

    function limparModalUpdate() {
        if (idUpdateSpan) idUpdateSpan.textContent = "";
        if (inputUpdateId) inputUpdateId.value = "";

        // Limpar visualização
        if (nomeDisplay) nomeDisplay.textContent = "";
        if (emailDisplay) emailDisplay.textContent = "";
        if (casaDisplay) casaDisplay.textContent = "";
        if (fotoPreviewUpdate) fotoPreviewUpdate.src = "";

        // Limpar campos editáveis
        if (woodUpdate) woodUpdate.value = "";
        if (coreUpdate) coreUpdate.value = "";
        if (flexibilityUpdate) flexibilityUpdate.value = "";
        if (inputExperiencesUpdate) inputExperiencesUpdate.value = "";
        if (inputTitleUpdate) inputTitleUpdate.value = "";
        if (headHouseUpdate) headHouseUpdate.checked = false;
        if (inputFotoUpdate) inputFotoUpdate.value = "";

        // Desmarcar todos os checkboxes de matérias
        document.querySelectorAll('input[name="newSubjects"]').forEach(cb => cb.checked = false);

        // Limpar container de matérias originais
        if (originalSubjectsContainer) originalSubjectsContainer.innerHTML = "";
    }

    function limparModalDelete() {
        if (idDeleteInput) idDeleteInput.value = "";
        if (nomeDelete) nomeDelete.textContent = "";
    }

    /* Função de ordenação da tabela */
    function ordenarTabela(criterio) {
        if (!tbody) return;

        const linhas = Array.from(tbody.querySelectorAll('tr'));
        if (linhas.length === 0) return;

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

        linhasOrdenadas.forEach(linha => tbody.appendChild(linha));
    }

    /* Event listener para o formulário de filtros */
    if (filtroForm) {
        filtroForm.addEventListener('submit', (e) => {
            e.preventDefault();
            const criterio = orderBySelect.value;
            ordenarTabela(criterio);

            divEscondida.classList.add('escondido');
            filtros.style.color = 'white';
            filtro.style.fill = '#e3e3e3';
            btnFiltro.style.border = 'none';
            btnFiltro.classList.add('fechado');
        });

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
            if (linha.querySelector('td[colspan]')) return;

            const colunas = linha.querySelectorAll('td');
            if (colunas.length < 9) return;

            const id = linha.dataset.id;
            const foto = colunas[1]?.querySelector('img')?.src || '';
            const nome = colunas[2]?.textContent.trim() || '';
            const email = colunas[3]?.textContent.trim() || '';
            const casa = colunas[4]?.textContent.trim() === '-' ? '' : colunas[4]?.textContent.trim() || '';

            // Pegar dados dos atributos data-*
            const pastExperiences = linha.dataset.experiences || '';
            const wizardTitle = linha.dataset.title || '';
            const headHouse = linha.dataset.headHouse === 'true';
            const wood = linha.dataset.wood || '';
            const core = linha.dataset.core || '';
            const flexibility = linha.dataset.flexibility || '';

            // Pegar matérias do professor
            let teacherSubjects = [];
            if (linha.dataset.subjects) {
                try {
                    teacherSubjects = JSON.parse(linha.dataset.subjects);
                } catch (e) {
                    console.error('Erro ao parsear matérias:', e);
                }
            }

            // Editar
            const botaoEditar = event.target.closest('.editar');
            if (botaoEditar) {
                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) {
                    inputUpdateId.value = id;
                    inputUpdateId.setAttribute('value', id);
                }

                // Preencher visualização
                if (nomeDisplay) nomeDisplay.textContent = nome;
                if (emailDisplay) emailDisplay.textContent = email;
                if (casaDisplay) casaDisplay.textContent = casa;
                if (fotoPreviewUpdate) fotoPreviewUpdate.src = foto;

                // Preencher campos editáveis
                if (woodUpdate) woodUpdate.value = wood;
                if (coreUpdate) coreUpdate.value = core;
                if (flexibilityUpdate) flexibilityUpdate.value = flexibility;
                if (inputExperiencesUpdate) inputExperiencesUpdate.value = pastExperiences;
                if (inputTitleUpdate) inputTitleUpdate.value = wizardTitle;
                if (headHouseUpdate) headHouseUpdate.checked = headHouse;

                // Marcar checkboxes das matérias do professor
                document.querySelectorAll('input[name="newSubjects"]').forEach(cb => {
                    const subjectName = cb.nextElementSibling.textContent.trim(); // Pega o nome da matéria
                    cb.checked = teacherSubjects.includes(subjectName);
                });

                // Criar hidden inputs para as matérias originais
                if (originalSubjectsContainer) {
                    originalSubjectsContainer.innerHTML = '';
                    document.querySelectorAll('input[name="newSubjects"]:checked').forEach(cb => {
                        const hidden = document.createElement('input');
                        hidden.type = 'hidden';
                        hidden.name = 'originalSubjects';
                        hidden.value = cb.value; // value é o ID
                        originalSubjectsContainer.appendChild(hidden);
                    });
                }

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