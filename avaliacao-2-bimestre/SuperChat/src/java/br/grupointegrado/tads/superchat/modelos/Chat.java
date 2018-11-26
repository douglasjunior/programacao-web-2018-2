package br.grupointegrado.tads.superchat.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dougl
 */
public class Chat {

    private Chat() {
    }

    private static final List<Mensagem> MENSAGENS = new ArrayList<>();
    private static long MENSAGEM_ID = 0;

    public static void addMensagem(String autor, String texto) {
        Mensagem msg = new Mensagem(
                MENSAGEM_ID++,
                autor, texto,
                new Date()
        );

        MENSAGENS.add(msg);
    }

    public static List<Mensagem> getMensagens() {
        return MENSAGENS;
    }

}
