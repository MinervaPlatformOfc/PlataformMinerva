const sidebar = document.querySelector('.sidebar');
const menu = document.querySelector('#menu-sandwich');
const main = document.querySelector('main')

menu.addEventListener('click', ()=>{
    sidebar.classList.add('aberta');
})

main.addEventListener('click', ()=>{
    sidebar.classList.remove('aberta')
})
