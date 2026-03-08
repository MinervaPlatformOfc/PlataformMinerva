// para ir para página de aluno

const perfilAluno = document.querySelector('#aluno-perfil');

perfilAluno.addEventListener('click', ()=>{
    window.location.href = "/pags/aluno.html";
});


// Lógica pra clicar na logo = voltar inicio
const voltarInicioImg = document.querySelector('.logo-central');

voltarInicioImg.addEventListener('click', ()=>{
    window.location.href = "../index.html";
});