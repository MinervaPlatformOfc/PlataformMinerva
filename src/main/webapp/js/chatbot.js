function adicionarMensagemUsuario(texto){

    let chat = document.getElementById("chat");

    let msg = document.createElement("div");

    let p = document.createElement("p");


// Na função adicionarMensagemUsuario:
    msg.className = "msg-user"; // Já está assim, mantenha.
    p.innerText = texto;

    chat.appendChild(msg);
    msg.appendChild(p);

    chat.scrollTop = chat.scrollHeight;
}

function adicionarMensagemIA(texto){

    let chat = document.getElementById("chat");

    let msg = document.createElement("div");
    let  p = document.createElement("p");

    // Na função adicionarMensagemIA:
    msg.className = "msg-ia"; // Adicione a classe na DIV, não no P
    p.innerText = texto;

    chat.appendChild(msg);
    msg.appendChild(p);

    chat.scrollTop = chat.scrollHeight;
}

function adicionarTyping(text){

    let chat = document.getElementById("chat");

    let div = document.createElement("div");
    div.className = "typing";
    div.innerText = text;

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

    let typing = adicionarTyping("Quadro digitando...");

    try{
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
        console.log(data)

        removerTyping(typing)

        adicionarMensagemIA(data.msg);
    }catch (e) {
        removerTyping(typing)
        adicionarTyping("Ocorreu um erro!")
    }
}