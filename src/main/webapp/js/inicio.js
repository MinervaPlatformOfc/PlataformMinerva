document.addEventListener("DOMContentLoaded", function () {

    // Variáveis
    const tabela = document.querySelector("#tabela table");
    const divUpdate = document.querySelector(".div-update");
    const overlay = document.querySelector(".overlay");
    const idUpdateSpan = document.querySelector(".id-update span");
    const inputUpdateId = document.querySelector(".div-update input[type='hidden'][name='id']");
    const inputNome = document.querySelector("#nome");
    const inputEmail = document.querySelector("#email");
    const inputRole = document.querySelector("#role");
    const fotoPreview = document.querySelector("#foto-preview");

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
        if (inputRole) inputRole.value = "";
        if (fotoPreview) fotoPreview.src = "";
    }

    // Verificando se existem os elementos necessários para adicionar os eventos
    if (tabela) {
        tabela.addEventListener("click", function (event) {
            const botaoEditar = event.target.closest(".editar");

            if (!botaoEditar) return;

            const linha = event.target.closest("tr");
            if (!linha) return;

            const id = linha.dataset.id;
            if (!id) return;

            const foto = linha.dataset.foto || "";
            const nome = linha.dataset.nome || "";
            const email = linha.dataset.email || "";
            const role = linha.dataset.role || "";

            if (botaoEditar) {
                if (idUpdateSpan) idUpdateSpan.textContent = id;
                if (inputUpdateId) inputUpdateId.value = id;
                if (inputNome) inputNome.value = nome;
                if (inputEmail) inputEmail.value = email;
                if (fotoPreview) fotoPreview.src = foto;
                if (inputRole) {
                    // Selecionar a opção correta no select
                    Array.from(inputRole.options).forEach(option => {
                        if (option.value.toLowerCase() === role.toLowerCase()) {
                            option.selected = true;
                        }
                    });
                }
                abrirModal(divUpdate);
            }
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