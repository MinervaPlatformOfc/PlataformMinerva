function adicionarMensagemUsuario(texto){

    let chat = document.getElementById("chat");

    let msg = document.createElement("div");
    msg.className = "msg-user";
    msg.innerText = texto;

    chat.appendChild(msg);

    chat.scrollTop = chat.scrollHeight;
}

function adicionarMensagemIA(texto){

    let chat = document.getElementById("chat");

    let msg = document.createElement("div");
    msg.className = "msg-ia";
    msg.innerText = texto;

    chat.appendChild(msg);

    chat.scrollTop = chat.scrollHeight;
}

function adicionarTyping(){

    let chat = document.getElementById("chat");

    let div = document.createElement("div");
    div.className = "typing";
    div.innerText = "IA está digitando...";

    chat.appendChild(div);

    return div;
}

function removerTyping(div){
    div.remove();
}

async function sendMessage(){
    let input = document.getElementById("msg");
    let msg = input.value;

    if (msg.trim() === "") return;

    adicionarMensagemUsuario(msg)
    input.value = ""

    let typing = adicionarTyping();

    let response = await fetch(contextPath+"/ia/chatbot", {
        method: "POST",
        headers: {
            "Content-Type":"application/x-www-form-urlencoded"
        },
        body: "msg="+encodeURIComponent(msg)
    });

    if (!response.ok){
        throw new Error("Erro HTTP: "+response.status)
    }

    let data = await response.json();

    removerTyping(typing)

    adicionarMensagemIA(data.msg);
}