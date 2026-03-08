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
    const inputUpdateId = document.querySelector('#id-update');

    // Elementos do Delete
    const divDelete = document.querySelector('.div-delete');
    const nomeDelete = document.querySelector('.nome-delete');
    const idDeleteInput = document.querySelector('.id-delete');

    // Elementos da Tabela
    const tabela = document.querySelector('#tabela table');

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
        const inputNome = document.querySelector('#nome-insert');
        if (inputNome) inputNome.value = "";
    }

    function limparModalUpdate() {
        if (idUpdateSpan) idUpdateSpan.textContent = "";
        if (inputUpdateId) inputUpdateId.value = "";
        if (inputNomeUpdate) inputNomeUpdate.value = "";
    }

    function limparModalDelete() {
        if (idDeleteInput) idDeleteInput.value = "";
        if (nomeDelete) nomeDelete.textContent = "";
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
            if (colunas.length < 4) return;

            const id = linha.dataset.id;
            const nome = colunas[1]?.textContent.trim() || '';

            // Editar
            const botaoEditar = event.target.closest('.editar');
            if (botaoEditar) {

                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) inputUpdateId.value = id;
                if (inputNomeUpdate) inputNomeUpdate.value = nome;

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