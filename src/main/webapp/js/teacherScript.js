    // Redirecionar para perfil
    document.getElementById('perfil').addEventListener('click', function(e) {
    // O formulário já vai fazer o redirecionamento
});

    // Logo que volta para o início
    const voltarInicioImg = document.querySelector('.logo-central');

    voltarInicioImg.addEventListener('click', () => {
    voltarInicioImg.classList.remove("tremendo");
    void voltarInicioImg.offsetWidth;
    voltarInicioImg.classList.add("tremendo");
});

    // Filtro de busca de alunos
    document.getElementById('busca-aluno').addEventListener('input', function(e) {
    const searchTerm = e.target.value.toLowerCase();
    const barras = document.querySelectorAll('.barra');

    barras.forEach(barra => {
    const nomeAluno = barra.querySelector('span').textContent.toLowerCase();
    if (nomeAluno.includes(searchTerm)) {
    barra.style.display = 'flex';
} else {
    barra.style.display = 'none';
}
});
});
