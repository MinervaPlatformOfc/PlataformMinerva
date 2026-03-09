document.addEventListener("DOMContentLoaded", function () {

    // Variáveis
    const tabela = document.querySelector("#tabela table");
    const divUpdate = document.querySelector(".div-update");
    const overlay = document.querySelector(".overlay");
    const idUpdateSpan = document.querySelector(".id-update span");
    const inputUpdateId = document.querySelector(".div-update input[type='hidden'][name='id']");
    const inputNome = document.querySelector("#nome");
    const inputEmail = document.querySelector("#email");
    const fotoPreview = document.querySelector("#foto-preview");

    const inputEmailOriginal = document.querySelector("input[name='emailOriginal']");
    const inputCurrentImageUrl = document.querySelector("input[name='currentImageUrl']");

    // Functions
    function abrirModal(modal) {
        modal.classList.remove("escondido");
        overlay.classList.remove("escondido");
        document.body.style.overflow = "hidden";
    }

    function fecharModal(modal) {
        modal.classList.add("escondido");
        overlay.classList.add("escondido");
        document.body.style.overflow = "auto";
        limparModal();
    }

    function limparModal() {
        if (idUpdateSpan) idUpdateSpan.textContent = "";
        if (inputUpdateId) inputUpdateId.value = "";
        if (inputNome) inputNome.value = "";
        if (inputEmail) inputEmail.value = "";
        if (fotoPreview) fotoPreview.src = "";
        if (inputEmailOriginal) inputEmailOriginal.value = "";
        if (inputCurrentImageUrl) inputCurrentImageUrl.value = "";
    }

    // Verificando se existem os elementos necessários para adicionar os eventos
    if (tabela) {
        tabela.addEventListener('click', function (event) {
            const botaoEditar = event.target.closest('.editar');

            if (!botaoEditar) return;

            const linha = event.target.closest('tr');
            if (!linha) return;

            // Ignorar linha de "nenhum registro"
            if (linha.querySelector('td[colspan]')) return;

            // Pegar dados da linha
            const id = linha.dataset.id;

            // Pegar a foto
            const imgElement = linha.querySelector('td[data-field="foto"] img');
            const foto = imgElement ? imgElement.src : '';

            // PEGAR NOME E EMAIL USANDO DATA-FIELD
            const nomeElement = linha.querySelector('td[data-field="nome"]');
            const emailElement = linha.querySelector('td[data-field="email"]');

            // Usar data-original se existir, senão usar textContent
            const nome = nomeElement ? (nomeElement.dataset.original || nomeElement.textContent.trim()) : '';
            const email = emailElement ? (emailElement.dataset.original || emailElement.textContent.trim()) : '';

            // Preencher o modal
            if (idUpdateSpan) idUpdateSpan.textContent = id;
            if (inputUpdateId) inputUpdateId.value = id;
            if (inputEmailOriginal) inputEmailOriginal.value = email;
            if (inputCurrentImageUrl) inputCurrentImageUrl.value = foto;
            if (inputNome) inputNome.value = nome;
            if (inputEmail) inputEmail.value = email;
            if (fotoPreview) fotoPreview.src = foto;

            // Abrir o modal
            abrirModal(divUpdate);
        });
    }

    // Eventos para fechar os modais
    document.querySelectorAll(".fechar").forEach(botao => {
        botao.addEventListener("click", () => {
            fecharModal(divUpdate);
        });
    });

    if (overlay) {
        overlay.addEventListener("click", () => {
            fecharModal(divUpdate);
        });
    }

    // Preview da nova foto quando selecionada
    const inputNovaFoto = document.querySelector("#nova_foto");
    if (inputNovaFoto) {
        inputNovaFoto.addEventListener("change", function() {
            if (this.files && this.files[0]) {
                const reader = new FileReader();
                reader.onload = function(e) {
                    fotoPreview.src = e.target.result;
                };
                reader.readAsDataURL(this.files[0]);
            }
        });
    }
});