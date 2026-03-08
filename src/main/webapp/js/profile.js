const perfilAluno = document.querySelector('#aluno-perfil');

perfilAluno.addEventListener('click', ()=>{
    window.location.href = "../index.html";
});

const materias = document.querySelectorAll('.barra');

materias.forEach( materia => {
    materia.addEventListener('click', ()=>{
        window.location.href = "materias.html";
    })
});

// Lógica pra clicar na logo = voltar inicio
const voltarInicioImg = document.querySelector('.logo-central');

voltarInicioImg.addEventListener('click', ()=>{
    window.location.href = "../index.html";
});