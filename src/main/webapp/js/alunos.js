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
            const nome = colunas[1]?.textContent.trim() || '';

            // Dados completos não estão na tabela, mas pegamos o que temos
            const registration = colunas[2]?.textContent.trim() === '-' ? '' : colunas[2]?.textContent.trim() || '';
            const schoolYear = colunas[3]?.textContent.trim() === '-' ? '' : colunas[3]?.textContent.trim() || '';
            const guardianName = colunas[4]?.textContent.trim() === '-' ? '' : colunas[4]?.textContent.trim() || '';
            const wand = colunas[5]?.textContent.trim() === '-' ? '' : colunas[5]?.textContent.trim() || '';
            const pet = colunas[6]?.textContent.trim() === '-' ? '' : colunas[6]?.textContent.trim() || '';

            // Editar
            const botaoEditar = event.target.closest('.editar');
            if (botaoEditar) {

                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) inputUpdateId.value = id;
                if (inputRegistrationUpdate) inputRegistrationUpdate.value = registration;
                if (inputSchoolYearUpdate) inputSchoolYearUpdate.value = schoolYear;
                if (inputGuardianNameUpdate) inputGuardianNameUpdate.value = guardianName;
                if (inputWandUpdate) inputWandUpdate.value = wand;
                if (inputPetUpdate) inputPetUpdate.value = pet;

                // Campos que não estão na tabela (deixamos vazios para o usuário preencher)
                if (inputAddressUpdate) inputAddressUpdate.value = "";
                if (inputAllergiesUpdate) inputAllergiesUpdate.value = "";
                if (inputBloodUpdate) inputBloodUpdate.value = "";
                if (inputBasicKitUpdate) inputBasicKitUpdate.checked = false;
                if (inputGuardianPermissionUpdate) inputGuardianPermissionUpdate.checked = false;

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