// const alohomora = document.querySelector('#alohomora-link')
const cadastrar = document.querySelector('#cadastrar')
const entrar = document.querySelector('#entrar')
const efeito = document.querySelector('#efeito')

// alohomora.addEventListener("click", ()=>{
//     cadastrar.classList.add('leviowwsaah')
//     setTimeout(()=>{
//         cadastrar.classList.remove('leviowwsaah')
//     }, 14000)
// });

//lógica para alohomora
cadastrar.addEventListener("click", (e)=>{

    efeito.style.left = e.clientX + "px"
    efeito.style.top = e.clientY + "px"

    efeito.style.transition = "transform 5s ease-in-out"
    efeito.style.transform = "scale(200)"

    setTimeout(() =>{
        window.location.href = "register.jsp"
    }, 7000)
})

entrar.addEventListener("click", (e)=>{
    window.location.href = "login.jsp"
})




