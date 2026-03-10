document.addEventListener("DOMContentLoaded", function () {

    // Variáveis
    const overlay = document.querySelector('.overlay');

    // Elementos do Insert (Envio de Matrícula)
    let botaoInserir = document.querySelector('.btn-amarelo');
    let botaoFecharInsert = document.querySelector('.div-insert .fechar');
    let botaoInsere = document.querySelector('.div-insert input[type="submit"]');
    let divInsere = document.querySelector('.div-insert');

    // Elementos do Update
    const divAtualizar = document.querySelector('.div-update');
    const idUpdateSpan = document.querySelector('.id-update span');
    const inputUpdateId = document.querySelector('#id-update');

// Campos do Update
    const inputSchoolYearUpdate = document.querySelector('#schoolYear-update');
    const inputGuardianNameUpdate = document.querySelector('#guardianName-update');
    const inputAddressUpdate = document.querySelector('#address-update');
    const inputWandUpdate = document.querySelector('#wand-update');
    const inputPetUpdate = document.querySelector('#pet-update');
    const inputAllergiesUpdate = document.querySelector('#allergies-update');
    const inputBloodUpdate = document.querySelector('#blood-update');
    const inputRegistrationUpdate = document.querySelector('#registration-update');
    const inputBasicKitUpdate = document.querySelector('#basicKit-update');
    const inputGuardianPermissionUpdate = document.querySelector('#guardianPermission-update');
    const inputFlightFitnessUpdate = document.querySelector('#flightFitness-update'); // NOVO

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
        document.querySelector('#email-insert').value = "";
    }

    function limparModalUpdate() {
        if (idUpdateSpan) idUpdateSpan.textContent = "";
        if (inputUpdateId) inputUpdateId.value = "";
        if (inputSchoolYearUpdate) inputSchoolYearUpdate.value = "";
        if (inputGuardianNameUpdate) inputGuardianNameUpdate.value = "";
        if (inputAddressUpdate) inputAddressUpdate.value = "";
        if (inputWandUpdate) inputWandUpdate.value = "";
        if (inputPetUpdate) inputPetUpdate.value = "";
        if (inputAllergiesUpdate) inputAllergiesUpdate.value = "";
        if (inputBloodUpdate) inputBloodUpdate.value = "";
        if (inputRegistrationUpdate) inputRegistrationUpdate.value = "";
        if (inputBasicKitUpdate) inputBasicKitUpdate.checked = false;
        if (inputGuardianPermissionUpdate) inputGuardianPermissionUpdate.checked = false;
        if (inputFlightFitnessUpdate) inputFlightFitnessUpdate.checked = false; // NOVO

        // Limpar campos de visualização
        const birthDateDisplay = document.querySelector('#birthDate-update-display');
        if (birthDateDisplay) birthDateDisplay.value = "";

        const birthDateHidden = document.querySelector('#birthDate-update');
        if (birthDateHidden) birthDateHidden.value = "";
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

    // Atualizar e Excluir na tabela
    if (tabela) {
        tabela.addEventListener('click', function (event) {
            const linha = event.target.closest('tr');
            if (!linha) return;

            // Verifica se é a linha de "nenhum registro"
            if (linha.querySelector('td[colspan]')) return;

            // Pegar todos os dados dos atributos data-*
            const id = linha.dataset.id;
            const nome = linha.dataset.nome || '';
            const registration = linha.dataset.registration || '';
            const schoolYear = linha.dataset.schoolYear || '';
            const guardianName = linha.dataset.guardianName || '';
            const residenceAddress = linha.dataset.residenceAddress || '';
            const wand = linha.dataset.wand || '';
            const petType = linha.dataset.petType || '';
            const allergies = linha.dataset.allergies || '';
            const blood = linha.dataset.blood || '';
            const birthDate = linha.dataset.birthDate || '';
            const basicKit = linha.dataset.basicKit === 'true';
            const guardianPermission = linha.dataset.guardianPermission === 'true';
            const flightFitness = linha.dataset.flightFitness === 'true';

            // Editar
            const botaoEditar = event.target.closest('.editar');
            if (botaoEditar) {
                // Preencher campos de visualização/edição
                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) inputUpdateId.value = id;

                // Campos que podem ser visualizados ou editados
                if (inputRegistrationUpdate) inputRegistrationUpdate.value = registration;
                if (inputSchoolYearUpdate) inputSchoolYearUpdate.value = schoolYear;
                if (inputGuardianNameUpdate) inputGuardianNameUpdate.value = guardianName;
                if (inputAddressUpdate) inputAddressUpdate.value = residenceAddress;
                if (inputWandUpdate) inputWandUpdate.value = wand;
                if (inputPetUpdate) inputPetUpdate.value = petType;
                if (inputAllergiesUpdate) inputAllergiesUpdate.value = allergies;

                // Campo de tipo sanguíneo (agora editável também)
                if (inputBloodUpdate) inputBloodUpdate.value = blood;

                // Checkboxes
                if (inputBasicKitUpdate) inputBasicKitUpdate.checked = basicKit;
                if (inputGuardianPermissionUpdate) inputGuardianPermissionUpdate.checked = guardianPermission;
                if (inputFlightFitnessUpdate) inputFlightFitnessUpdate.checked = flightFitness;

                // Campo de data de nascimento (apenas visualização)
                const birthDateDisplay = document.querySelector('#birthDate-update-display');
                if (birthDateDisplay) {
                    // Formatar data para exibição (dd/mm/aaaa)
                    if (birthDate) {
                        const dateParts = birthDate.split('-');
                        if (dateParts.length === 3) {
                            birthDateDisplay.value = `${dateParts[2]}/${dateParts[1]}/${dateParts[0]}`;
                        } else {
                            birthDateDisplay.value = birthDate;
                        }
                    } else {
                        birthDateDisplay.value = '';
                    }
                }

                // Hidden field para birthDate (necessário para o servidor)
                const birthDateHidden = document.querySelector('#birthDate-update');
                if (birthDateHidden) birthDateHidden.value = birthDate;

                abrirModal(divAtualizar);
                return;
            }

            // Excluir (mantém como está)
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