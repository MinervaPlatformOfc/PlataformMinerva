// para ir para página de aluno

const perfilAluno = document.querySelector('#aluno-perfil');

perfilAluno.addEventListener('click', ()=>{
    window.location.href = "/aluno/home.jsp";
});

// Lógica pra clicar na logo = voltar inicio
const voltarInicioImg = document.querySelector('.logo-central');

voltarInicioImg.addEventListener('click', ()=>{
    window.location.href = "../index.html";
});

// Lógica para trocar as cores
const body = document.body
let casa = document.querySelector('input[name = "houseName"]').value;

    if (casa === "grifinoria") {
        body.classList.add('grifinoria')
    } else if (casa === "sonserina") {
        body.classList.add('sonserina')
    } else if (casa === "corvinal") {
        body.classList.add('corvinal')
    }
