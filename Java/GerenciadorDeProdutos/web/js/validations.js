
function validaString(valor, minimo) {
    //    if (typeof valor === 'string' && valor.length >= minimo) {
    //        return true;
    //    }
    //    return false;
    
        return typeof valor === 'string' && valor.length >= minimo;
    }
    
    function validaNumber(valor, minimo, maximo) {
        var valorNumerico = Number(valor);
        return typeof valorNumerico === 'number'
                && valorNumerico >= minimo && valorNumerico <= maximo;
    }
    
    function validaData(valor, minimo, maximo) {
        var data = moment(valor, 'YYYY-MM-DD', true);
        return data.isValid() && data.isSameOrBefore(maximo)
                && data.isSameOrAfter(minimo);
    }
        