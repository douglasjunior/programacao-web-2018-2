// Permite exibir o nome do arquivo selecionado no formulário.
$('.custom-file-input').on('change', function () {
    var fileName = $(this).val();
    var fileLabelElement = $(this).parent().children('.custom-file-label');
    if (fileName) {
        fileLabelElement.html(fileName);
    } else {
        fileLabelElement.html('Selecionar arquivo');
    }
})

