package br.grupointegrado.ads.gerenciadorDeProdutos.utils;

import java.util.Date;

/**
 *
 * @author dougl
 */
public class Validations {

    public static boolean validaString(String valor, int minimo) {
        //        if (valor == null || valor.length() < minimo) {
        //            return false;
        //        }
        //        return true;

        return valor != null && valor.length() >= minimo;
    }

    public static boolean validaDouble(String valor, double minimo, double maximo) {
        double valorNumerico = Formatter.stringParaDouble(valor);

        return valorNumerico >= minimo && valorNumerico <= maximo;
    }

    public static boolean validaLong(String valor, long minimo, long maximo) {
        long valorNumero = Formatter.stringParaLong(valor);

        return valorNumero >= minimo && valorNumero <= maximo;
    }

    public static boolean validaData(String data, Date minimo, Date maximo) {
        Date dataDate = Formatter.stringParaData(data);

        return dataDate != null
                && (dataDate.after(minimo) || dataDate.equals(minimo))
                && (dataDate.before(maximo) || dataDate.equals(maximo));
    }

}
