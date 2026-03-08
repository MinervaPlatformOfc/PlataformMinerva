const apresentacao = document.querySelector('#apresentacao');
const botaoInicar = document.querySelector('#botaoIniciar');

let indicePergunta = 0;

const perguntas = document.querySelector('#perguntas');
const pergunta = document.querySelector('#textoPergunta');
const botoesPerguntas = document.querySelectorAll('#perguntas .input-group-resposta input');
const resposta = document.querySelector("#resposta");
const imgResposta = document.querySelector('#imgCasa');
const h2Resposta = document.querySelector('#casaResultado');
const pResposta = document.querySelector('#textoResultado');

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
    "Como um herói cujos feitos serão contados em histórias"
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
    "Como um gênio que descobriu algo revolucionário"
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

        }

        else if (indicePergunta === 2) {

            pergunta.innerText = 'Se você encontrasse um baú trancado que pertenceu a um grande bruxo, qual seria sua reação?';

            botoesPerguntas[0].value = 'Tentaria decifrar os enigmas da fechadura por horas';
            botoesPerguntas[1].value = 'Procuraria uma forma de usá-lo para ganhar prestígio ou influência';
            botoesPerguntas[2].value = 'Verificaria se o baú tem um dono para devolvê-lo com segurança';
            botoesPerguntas[3].value = 'Abriria na hora, pronto para qualquer perigo que estivesse dentro';

        }

        else if (indicePergunta === 3) {

            pergunta.innerText = 'Qual é o seu maior medo?';

            botoesPerguntas[0].value = 'Ser visto como covarde';
            botoesPerguntas[1].value = 'Ser considerado ignorante ou comum';
            botoesPerguntas[2].value = 'O fracasso e a perda de controle';
            botoesPerguntas[3].value = 'Decepcionar meus amigos ou ser isolado';

        }

        else if (indicePergunta === 4) {

            pergunta.innerText = 'Você está em um duelo mágico e as coisas estão ficando feias. Qual sua estratégia?';

            botoesPerguntas[0].value = 'Defendo-me e espero o momento certo de cansar o adversário';
            botoesPerguntas[1].value = 'Observo os padrões do oponente para encontrar uma falha lógica';
            botoesPerguntas[2].value = 'Uso qualquer truque, mesmo os proibidos, para garantir a vitória';
            botoesPerguntas[3].value = 'Ataco com determinação, indo direto para o combate frontal';

        }

        else if (indicePergunta === 5) {

            pergunta.innerText = 'Como você gostaria de ser lembrado após a morte?';

            botoesPerguntas[0].value = 'Como um herói cujos feitos serão contados em histórias';
            botoesPerguntas[1].value = 'Como um líder poderoso que mudou o curso do mundo.';
            botoesPerguntas[2].value = 'Como um gênio que descobriu algo revolucionário';
            botoesPerguntas[3].value = 'Como um amigo fiel que nunca abandonou ninguém';

        }

        else if (indicePergunta === 6) {

            pergunta.innerText = 'Se você pudesse criar uma nova poção, ela serviria para:';

            botoesPerguntas[0].value = 'Dar coragem instantânea para enfrentar desafios';
            botoesPerguntas[1].value = 'Expandir a capacidade cerebral e a memória';
            botoesPerguntas[2].value = 'Garantir que as pessoas façam o que você quer';
            botoesPerguntas[3].value = 'Curar qualquer dor e trazer conforto';

        }

        else if (indicePergunta === 7) {

            pergunta.innerText = 'Qual caminho você escolheria em uma floresta desconhecida?';

            botoesPerguntas[0].value = 'O caminho mais perigoso e emocionante';
            botoesPerguntas[1].value = 'O caminho que parece esconder segredos antigos';
            botoesPerguntas[2].value = 'O caminho que leva mais rápido ao topo da montanha.';
            botoesPerguntas[3].value = 'O caminho mais florido e acolhedor';

        }

        else if (indicePergunta === 8) {

            pergunta.innerText = 'O que mais te irrita em uma pessoa?';

            botoesPerguntas[0].value = 'Fraqueza e falta de ambição';
            botoesPerguntas[1].value = 'Arrogância e deslealdade';
            botoesPerguntas[2].value = 'Egoísmo e falta de iniciativa';
            botoesPerguntas[3].value = 'Estupidez e falta de curiosidade';

        }

        else if (indicePergunta === 9) {

            pergunta.innerText = 'Se você ganhasse 1.000 galeões, o que faria?';

            botoesPerguntas[0].value = 'Gastaria em uma grande aventura ou doaria para uma causa nobre';
            botoesPerguntas[1].value = 'Compraria livros raros e instrumentos de pesquisa.';
            botoesPerguntas[2].value = 'Dividiria com minha família e amigos próximos.';
            botoesPerguntas[3].value = 'Investiria para transformar esse dinheiro em uma fortuna maior.';

        }

        else if (indicePergunta === 10) {

            perguntas.classList.add('escondido');
            resposta.classList.remove('escondido');

            grifConta = preferencia.filter(e => grif.includes(e)).length;
            lufaConta = preferencia.filter(e => lufa.includes(e)).length;
            corvConta = preferencia.filter(e => corv.includes(e)).length;
            sonsConta = preferencia.filter(e => sons.includes(e)).length;

            const casa = Math.max(grifConta, lufaConta, corvConta, sonsConta);

            let houseName;

            if (grifConta === casa) {

                h2Resposta.innerText = "Grifinória";
                houseName = "Grifinória";
                h2Resposta.style.color = "red";
                imgResposta.src = contextPath + "/aluno/quiz/imgs/grifinoria.jpg";

                pResposta.innerText = "Seja muito bem-vindo à Grifinória, pois esta é a casa dos corajosos, dos audazes e daqueles que possuem a ousadia no sangue. Aqui, o vermelho e o dourado representam a chama que arde no peito de quem não recua diante de um desafio e coloca a honra acima de tudo. Você foi escolhido para caminhar entre os bravos, onde a amizade é um pacto de lealdade e cada dia é uma oportunidade de provar o seu valor. Prepare-se para viver com intensidade, pois agora você faz parte da linhagem dos leões de Hogwarts.";

            }

            else if (sonsConta === casa) {

                h2Resposta.innerText = "Sonserina";
                houseName = "Sonserina";
                h2Resposta.style.color = "green";
                imgResposta.src = contextPath + "/aluno/quiz/imgs/sonserina.jpg";

                pResposta.innerText = "Seja muito bem-vindo à Sonserina, pois esta é a casa dos ambiciosos, dos astutos e daqueles que nasceram para alcançar a grandeza. Aqui, sob o brilho do verde e da prata, valorizamos a inteligência estratégica e o desejo de chegar ao topo, custe o que custar. Você foi escolhido porque o Chapéu Seletor viu em você o potencial de um líder que sabe usar o poder com sabedoria e mantém a lealdade aos seus acima de qualquer coisa. Entre com orgulho em nossas masmorras, pois o seu caminho para o sucesso começa agora.";

            }

            else if (corvConta === casa) {

                h2Resposta.innerText = "Corvinal";
                houseName = "Corvinal";
                h2Resposta.style.color = "blue";
                imgResposta.src = contextPath + "/aluno/quiz/imgs/corvinal.jpg";

                pResposta.innerText = "Seja muito bem-vindo à Corvinal, pois esta é a casa dos sábios, dos criativos e daqueles que possuem uma mente sem limites. Aqui, as cores azul e bronze envolvem aqueles que buscam o conhecimento como o maior dos tesouros e não têm medo de pensar de forma diferente do resto. Você foi escolhido porque sua inteligência e sua curiosidade brilham mais forte, encontrando morada na torre mais alta do castelo. Prepare-se para abrir seus horizontes, pois entre os corvos, o aprendizado é a nossa maior aventura.";

            }

            else {

                h2Resposta.innerText = "Lufa-Lufa";
                houseName = "Lufa-Lufa";
                h2Resposta.style.color = "yellow";
                imgResposta.src = contextPath + "/aluno/quiz/imgs/lufalufa.jpg";

                pResposta.innerText = "Seja muito bem-vindo à Lufa-Lufa, pois esta é a casa dos justos, dos leais e daqueles que acreditam no valor do trabalho árduo. Aqui, o amarelo e o preto protegem quem planta a bondade e colhe amizades verdadeiras, sem nunca deixar um companheiro para trás. Você foi escolhido porque seu coração é honesto e sua paciência é sua maior força, fazendo de você uma peça fundamental na família mais acolhedora de Hogwarts. Sinta-se em casa entre os texugos, pois aqui a sua \n" +
                    "dedicação sempre será reconhecida e celebrada.";

            }

            document.getElementById("houseName").value = houseName;

            document.querySelector('#botoesResultado').classList.remove('escondido');

        }

    });

});