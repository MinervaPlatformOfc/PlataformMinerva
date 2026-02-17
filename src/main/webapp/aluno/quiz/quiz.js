const apresentacao = document.querySelector('#apresentacao');
const botaoInicar = document.querySelector('#apresentacao button');

let indicePergunta = 0;
const perguntas = document.querySelector('#perguntas');
const pergunta = document.querySelector('#perguntas p');
const botoesPerguntas = document.querySelectorAll('#perguntas input');

const resposta = document.querySelector("#resposta");
const imgResposta = document.querySelector('#resposta img');
const h2Resposta = document.querySelector('#resposta h2');
const pResposta = document.querySelector('#resposta p')

let preferencia = [];

let grifConta = 0;
let lufaConta = 0;
let corvConta = 0;
let sonsConta = 0;


let grif = [
      "Bravura", 
      "Confronto o colega na hora; não é justo com os outros", 
      "Abriria na hora, pronto para qualquer perigo que estivesse dentro",
      "Ser visto como covarde", 
      "Ataco com determinação, indo direto para o combate frontal", 
      "Dar coragem instantânea para enfrentar desafios", 
      "O caminho mais perigoso e emocionante", 
      "Egoísmo e falta de iniciativa",
      "Gastaria em uma grande aventura ou doaria para uma causa nobre",
      "Como um herói cujos feitos serão contados em histórias",
    ];

let lufa = [
      "Bondade", 
      "Sinto pena, mas não conto para o professor; não quero prejudicá-lo",
      "Verificaria se o baú tem um dono para devolvê-lo com segurança",
      "Decepcionar meus amigos ou ser isolado",
      "Defendo-me e espero o momento certo de cansar o adversário",
      "Curar qualquer dor e trazer conforto",
      "O caminho mais florido e acolhedor",
      "Arrogância e deslealdade",
      "Dividiria com minha família e amigos próximos",
      "Como um amigo fiel que nunca abandonou ninguém"
    ];
let corv = [
  "Inteligência", 
  "Fico curioso para saber que feitiço é esse e como ele funciona",
  "Tentaria decifrar os enigmas da fechadura por horas",
  "Ser considerado ignorante ou comum",
  "Observo os padrões do oponente para encontrar uma falha lógica",
  "Expandir a capacidade cerebral e a memória",
  "O caminho que parece esconder segredos antigos",
  "Estupidez e falta de curiosidade",
  "Compraria livros raros e instrumentos de pesquisa",
  "Como um gênio que descobriu algo revolucionário",
];
let sons = [
      "Poder", 
      "Não digo nada; cada um usa as armas que tem para vencer",
      "Procuraria uma forma de usá-lo para ganhar prestígio ou influência",
      "O fracasso e a perda de controle",
      "Uso qualquer truque, mesmo os proibidos, para garantir a vitória",
      "Garantir que as pessoas façam o que você quer",
      "O caminho que leva mais rápido ao topo da montanha",
      "Fraqueza e falta de ambição",
      "Investiria para transformar esse dinheiro em uma fortuna maior",
      "Como um líder poderoso que mudou o curso do mundo"
    ];

botaoInicar.addEventListener('click', () => {
    apresentacao.classList.add('escondido');
    perguntas.classList.remove('escondido');
});

botoesPerguntas.forEach(botao => {
  botao.addEventListener('click', () => {

    preferencia.push(botao.value);
    indicePergunta++;

    if (indicePergunta === 1) {
      pergunta.innerText = 'Qual dessas qualidades você mais gostaria que as pessoas vissem em você?';
      botoesPerguntas[0].value = 'Bravura';
      botoesPerguntas[1].value = 'Poder';
      botoesPerguntas[2].value = 'Bondade';
      botoesPerguntas[3].value = 'Inteligência';

    } else if (indicePergunta === 2) {
      pergunta.innerText = 'Se você encontrasse um baú trancado que pertenceu a um grande bruxo, qual seria sua reação?';
      botoesPerguntas[0].value = 'Tentaria decifrar os enigmas da fechadura por horas';
      botoesPerguntas[1].value = 'Procuraria uma forma de usá-lo para ganhar prestígio ou influência';
      botoesPerguntas[2].value = 'Verificaria se o baú tem um dono para devolvê-lo com segurança';
      botoesPerguntas[3].value = 'Abriria na hora, pronto para qualquer perigo que estivesse dentro';

    } else if (indicePergunta === 3) {
      pergunta.innerText = 'Qual é o seu maior medo?';
      botoesPerguntas[0].value = 'Ser visto como covarde';
      botoesPerguntas[1].value = 'Ser considerado ignorante ou comum';
      botoesPerguntas[2].value = 'O fracasso e a perda de controle';
      botoesPerguntas[3].value = 'Decepcionar meus amigos ou ser isolado';

    }else if (indicePergunta === 4) {
      pergunta.innerText = 'Você está em um duelo mágico e as coisas estão ficando feias. Qual sua estratégia?';
      botoesPerguntas[0].value = 'Defendo-me e espero o momento certo de cansar o adversário';
      botoesPerguntas[1].value = 'Observo os padrões do oponente para encontrar uma falha lógica';
      botoesPerguntas[2].value = 'Uso qualquer truque, mesmo os proibidos, para garantir a vitória';
      botoesPerguntas[3].value = 'Ataco com determinação, indo direto para o combate frontal';

    }else if (indicePergunta === 5) {
      pergunta.innerText = 'Como você gostaria de ser lembrado após a morte?';
      botoesPerguntas[0].value = 'Como um herói cujos feitos serão contados em histórias';
      botoesPerguntas[1].value = 'Como um líder poderoso que mudou o curso do mundo.';
      botoesPerguntas[2].value = 'Como um gênio que descobriu algo revolucionário';
      botoesPerguntas[3].value = 'Como um amigo fiel que nunca abandonou ninguém';

    }else if (indicePergunta === 6) {
      pergunta.innerText = 'Se você pudesse criar uma nova poção, ela serviria para:';
      botoesPerguntas[0].value = 'Dar coragem instantânea para enfrentar desafios';
      botoesPerguntas[1].value = 'Expandir a capacidade cerebral e a memória';
      botoesPerguntas[2].value = 'Garantir que as pessoas façam o que você quer';
      botoesPerguntas[3].value = 'Curar qualquer dor e trazer conforto';

    }else if (indicePergunta === 7) {
      pergunta.innerText = 'Qual caminho você escolheria em uma floresta desconhecida?';
      botoesPerguntas[0].value = 'O caminho mais perigoso e emocionante';
      botoesPerguntas[1].value = 'O caminho que parece esconder segredos antigos';
      botoesPerguntas[2].value = 'O caminho que leva mais rápido ao topo da montanha.';
      botoesPerguntas[3].value = 'O caminho mais florido e acolhedor';
    }else if (indicePergunta === 8) {
      pergunta.innerText = 'O que mais te irrita em uma pessoa?';
      botoesPerguntas[0].value = 'Fraqueza e falta de ambição';
      botoesPerguntas[1].value = 'Arrogância e deslealdade';
      botoesPerguntas[2].value = 'Egoísmo e falta de iniciativa';
      botoesPerguntas[3].value = 'Estupidez e falta de curiosidade';

    }else if (indicePergunta === 9) {
      pergunta.innerText = 'Se você ganhasse 1.000 galeões, o que faria?';
      botoesPerguntas[0].value = 'Gastaria em uma grande aventura ou doaria para uma causa nobre';
      botoesPerguntas[1].value = 'Compraria livros raros e instrumentos de pesquisa.';
      botoesPerguntas[2].value = 'Dividiria com minha família e amigos próximos.';
      botoesPerguntas[3].value = ' Investiria para transformar esse dinheiro em uma fortuna maior.';

    }else if (indicePergunta === 10) {
      perguntas.classList.add('escondido');
      resposta.classList.remove('escondido');

      grifConta = preferencia.filter(escolha => grif.includes(escolha)).length;
      lufaConta = preferencia.filter(escolha => lufa.includes(escolha)).length;
      corvConta = preferencia.filter(escolha => corv.includes(escolha)).length;
      sonsConta = preferencia.filter(escolha => sons.includes(escolha)).length;
    
      const casa = Math.max(grifConta, lufaConta, corvConta, sonsConta);
        let houseName;

        if (grifConta === casa) {
            h2Resposta.innerText = "Grifinória";
            houseName = "Grifinória";
            h2Resposta.style.color = "Red";
            pResposta.innerText = "Hum... vejo muita ousadia aqui. Não há medo de enfrentar o desconhecido e um fogo que queima forte no peito. Onde outros recuam, você avança com nobreza e bravura."
            imgResposta.src = contextPath+"/aluno/quiz/imgs/grifinoria.jpg";
        } else if (sonsConta === casa) {
            h2Resposta.innerText = "Sonserina!";
            houseName = "Sonserina";
            h2Resposta.style.color = "Green";
            imgResposta.src = contextPath+"/aluno/quiz/imgs/sonserina.jpg";
            pResposta.innerText = "Interessante... muito interessante. Vejo uma sede de provar seu valor e uma mente astuta que sabe exatamente onde quer chegar. Você fará grandes coisas, e nada impedirá sua ascensão rumo à grandeza."
        } else if (corvConta === casa) {
            h2Resposta.innerText = "Corvinal!";
            houseName = "Corvinal";
            h2Resposta.style.color = "Blue";
            imgResposta.src = contextPath+"/aluno/quiz/imgs/corvinal.jpg";
            pResposta.innerText = "Ah, veja só... Uma mente afiada e curiosa. Percebo uma criatividade única e um desejo insaciável de entender os mistérios do mundo. Onde o saber é o maior tesouro, você encontrará seus iguais."
        } else {
            h2Resposta.innerText = "Lufa-Lufa!";
            houseName = "Lufa-Lufa";
            h2Resposta.style.color = "Yellow";
            imgResposta.src = contextPath+"/aluno/quiz/imgs/lufalufa.jpg";
            pResposta.innerText = "Difícil... muito difícil. Mas vejo um coração justo e leal. Você não teme o trabalho árduo e valoriza a verdadeira amizade acima de qualquer glória passageira. A paciência e a bondade são suas maiores forças."
        }
        document.getElementById("houseName").value = houseName;
        document.querySelector('#botoesResultado').classList.remove('escondido');

        const btnRecomecar = document.querySelector('#recomecarQuiz');


        btnRecomecar.addEventListener('click', () => {
            preferencia = [];
            indicePergunta = 0;
            grifConta = 0;
            lufaConta = 0;
            corvConta = 0;
            sonsConta = 0;

            resposta.classList.add('escondido');
            apresentacao.classList.remove('escondido');
            document.querySelector('#botoesResultado').classList.add('escondido');

            // Resetar pergunta inicial
            pergunta.innerText = 'Em um exame difícil, você percebe que um colega está usando um feitiço de cola. O que você faz?';
            botoesPerguntas[0].value = 'Não digo nada; cada um usa as armas que tem para vencer';
            botoesPerguntas[1].value = 'Sinto pena, mas não conto para o professor; não quero prejudicá-lo';
            botoesPerguntas[2].value = 'Fico curioso para saber que feitiço é esse e como ele funciona';
            botoesPerguntas[3].value = 'Confronto o colega na hora; não é justo com os outros';
        });
    }
  });
});