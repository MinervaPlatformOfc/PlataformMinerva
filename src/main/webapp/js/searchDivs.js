function search() {
    let input = document.getElementById("searchbar").value.toLowerCase().trim();
    let columnsValue = document.getElementById("searchColumns").value;
    let searchColumns = columnsValue.split(',').map(c => c.trim());

    let linhas = document.getElementsByClassName("linhas");

    for (let i = 0; i < linhas.length; i++) {
        let mostrarLinha = false;

        // Percorre todas as colunas configuradas
        for (let col of searchColumns) {
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
        linhas[i].style.display = mostrarLinha ? "flex" : "none";
    }
}

// Adiciona evento input também (alternativa ao oninput)
document.addEventListener('DOMContentLoaded', function() {
    const searchbar = document.getElementById("searchbar");
    if (searchbar) {
        searchbar.addEventListener('input', search);
    }
});