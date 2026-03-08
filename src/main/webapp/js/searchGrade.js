function search() {
    let input = document.getElementById("searchbar").value.toLowerCase().trim();
    let columnsValue = document.getElementById("searchColumns").value;
    let searchColumns = columnsValue.split(',').map(c => c.trim());

    let linhas = document.getElementsByClassName("linhas");

    for (let i = 0; i < linhas.length; i++) {
        let mostrarLinha = false;

        // Verificar se é modo notas ou observações baseado na estrutura
        const isGradesMode = linhas[i].querySelector('[data-field="n1"]') !== null;

        // Percorre todas as colunas configuradas
        for (let col of searchColumns) {
            // Para modo observações, procura apenas pelo nome
            if (!isGradesMode && col !== 'nome') {
                continue;
            }

            // Procura por elementos com data-field dentro da div
            let elemento = linhas[i].querySelector(`[data-field="${col}"]`);

            if (elemento) {
                let valor = elemento.dataset.original || elemento.textContent.trim();

                if (valor.toLowerCase().includes(input)) {
                    mostrarLinha = true;
                    break;
                }
            }
        }

        // Aplica o display correto baseado no modo
        linhas[i].style.display = mostrarLinha ? "grid" : "none";
    }
}

// Adiciona evento input
document.addEventListener('DOMContentLoaded', function() {
    const searchbar = document.getElementById("searchbar");
    if (searchbar) {
        searchbar.addEventListener('input', search);
    }
});