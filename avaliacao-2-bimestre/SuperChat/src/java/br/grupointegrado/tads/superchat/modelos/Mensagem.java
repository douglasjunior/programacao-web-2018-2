package br.grupointegrado.tads.superchat.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Objeto que representa o modelo da mensagem.
 * @author dougl
 */
public class Mensagem {

    private final long id;
    private final String autor;
    private final String texto;
    private final Date dataHora;

    public Mensagem(long id, String autor, String texto, Date dataHora) {
        this.id = id;
        this.autor = autor;
        this.texto = texto;
        this.dataHora = dataHora;
    }

    public long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getTexto() {
        return texto;
    }

    public Date getDataHora() {
        return dataHora;
    }
    
    public String getDataHoraString() {
        // 11:01 AM | Today
        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm");
        return sdf.format(dataHora);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Mensagem other = (Mensagem) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
