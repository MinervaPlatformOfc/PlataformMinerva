function search() {

    let input = document.getElementById("searchbar").value.toLowerCase().trim();

    // Pega as colunas a serem pesquisadas do input hidden
    let columnsValue = document.getElementById("searchColumns").value;
    let searchColumns = columnsValue.split(',').map(c => c.trim());

    let linhas = document.getElementsByClassName("linhas");

    for (let i = 0; i < linhas.length; i++) {

        let mostrarLinha = false;

        // Percorre todas as colunas configuradas
        for (let col of searchColumns) {

            let celula = linhas[i].querySelector(`td[data-field="${col}"]`);

            if (celula) {

                let valor = celula.dataset.original || celula.textContent.trim();

                if (valor.toLowerCase().includes(input)) {
                    mostrarLinha = true;
                    break;
                }
            }
        }
        linhas[i].style.display = mostrarLinha ? "table-row" : "none";
    }
}

// EXEMPLO DE COMO VAI FUNCIONAR NAS JSP
// use no head <script src="${pageContext.request.contextPath}/js/search.js" defer ></script> para chamar o arquivo js
// aqui é onde vai ser procurado os valores a serem filtrados, oninput para chamar a função de search em tempo real -> <input type="text" id="searchbar" oninput="search()">
// <input type="hidden" id="searchColumns" value="nome,email,matricula">
//
//        <table>
//             <tr className="linhas">
//                 <td data-field="nome">Marcos</td> como a coluna nome vai ser procurada de acordo com o input hidden searchColumns, ela precisa conter o data-field de nome
//                 <td data-field="email">marcos@email.com</td>
//                 <td data-field="status">Ativo</td>
//                 <td data-field="matricula">20109010</td>
//             </tr>
//         </table>