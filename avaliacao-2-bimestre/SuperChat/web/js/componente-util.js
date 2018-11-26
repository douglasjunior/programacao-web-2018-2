/**
 * Script para ajustar o tamanho do TextArea automaticamente
 * @param {string} textAreaId
 */
function textAreaAutoAjustar(textAreaId) {
    var minRows = 5;
    var maxRows = 26;
    var textarea = document.getElementById(textAreaId);
    textarea.addEventListener('keydown', function () {
        if (textarea.scrollTop == 0)
            textarea.scrollTop = 1;
        while (textarea.scrollTop == 0) {
            if (textarea.rows > minRows)
                textarea.rows--;
            else
                break;
            textarea.scrollTop = 1;
            if (textarea.rows < maxRows)
                textarea.style.overflowY = "hidden";
            if (textarea.scrollTop > 0) {
                textarea.rows++;
                break;
            }
        }
        while (textarea.scrollTop > 0) {
            if (textarea.rows < maxRows) {
                textarea.rows++;
                if (textarea.scrollTop == 0)
                    textarea.scrollTop = 1;
            } else {
                textarea.style.overflowY = "auto";
                break;
            }
        }
    });
}

/**
 * Rola a barra para o final do elemento.
 * @param {string} elementId
 */
function rolarAoFinal(elementId) {
    var element = document.getElementById(elementId);
    element.scrollTop = element.scrollHeight - element.clientHeight;
}