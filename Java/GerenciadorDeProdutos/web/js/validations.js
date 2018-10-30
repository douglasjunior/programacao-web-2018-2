
function validaString(valor, minimo) {
//    if (typeof valor === 'string' && valor.length >= minimo) {
//        return true;
//    }
//    return false;

    return typeof valor === 'string' && valor.length >= minimo;
}

function validaNumber(valor, minimo, maximo) {
    return typeof valor === 'number'
            && valor >= minimo && valor <= maximo;
}

function validaData(valor, minimo, maximo) {
    var data = moment(valor, 'YYYY-MM-DD', true);
    return data.isValid() && data.isBetween(minimo, maximo);
}

