package br.grupointegrado.ads.gerenciadorDeProdutos.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dougl
 */
public class Formatter {

    public static Double stringParaDouble(String valor) {
        try {
            return Double.parseDouble(valor);
        } catch (Exception ex) {
            return 0D;
        }
    }

    public static Long stringParaLong(String valor) {
        try {
            return Long.parseLong(valor);
        } catch (Exception ex) {
            return 0L;
        }
    }

    public static Integer stringParaInt(String valor) {
        try {
            return Integer.parseInt(valor);
        } catch (Exception ex) {
            return 0;
        }
    }

    public static Date stringParaData(String data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(data);
        } catch (Exception ex) {
            return null;
        }
    }

    public static String dataParaString(Date data) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            return format.format(data);
        } catch (Exception ex) {
            return "";
        }
    }
}
