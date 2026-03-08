 // Logo que volta para o início (efeito tremendo)
    const voltarInicioImg = document.querySelector('.logo-central');

    voltarInicioImg.addEventListener('click', () => {
    if (document.title !== "Página Professor") {
    window.location.href = "${pageContext.request.contextPath}/";
} else {
    voltarInicioImg.classList.remove("tremendo");
    void voltarInicioImg.offsetWidth;
    voltarInicioImg.classList.add("tremendo");
}
});

    // Funcionalidade para adicionar observação
    const btnAddObservacao = document.getElementById('btnAddObservacao');
    const divAdd = document.querySelector('.div-add');
    const overlay = document.querySelector('.overlay');
    const btnSubmit = document.getElementById('btnSubmitComment');

    if (btnAddObservacao) {
    btnAddObservacao.addEventListener('click', () => {
        divAdd.classList.remove('escondido');
        overlay.classList.remove('escondido');
    });
}

    if (btnSubmit) {
    btnSubmit.addEventListener('click', (e) => {
        // O formulário será submetido normalmente
        // O overlay será fechado após o submit
        setTimeout(() => {
            divAdd.classList.add('escondido');
            overlay.classList.add('escondido');
        }, 100);
    });
}

    if (overlay) {
    overlay.addEventListener('click', () => {
        divAdd.classList.add('escondido');
        overlay.classList.add('escondido');
    });
}

    // Prevenir que o clique no formulário feche o overlay
    const form = document.querySelector('.div-add form');
    if (form) {
    form.addEventListener('click', (e) => {
        e.stopPropagation();
    });
}
