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

    public static boolean validaDouble(double valor, double minimo, double maximo) {
        return valor >= minimo && valor <= maximo;
    }

    public static boolean validaLong(long valor, long minimo, long maximo) {
        return valor >= minimo && valor <= maximo;
    }

    public static boolean validaData(Date data, Date minimo, Date maximo) {
        return data != null
                && (data.after(minimo) || data.equals(minimo))
                && (data.before(maximo) || data.equals(maximo));
    }

}
