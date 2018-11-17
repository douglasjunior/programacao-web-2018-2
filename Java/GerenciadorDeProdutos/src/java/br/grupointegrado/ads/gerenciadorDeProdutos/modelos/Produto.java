package br.grupointegrado.ads.gerenciadorDeProdutos.modelos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Produto {

    /*
    CREATE TABLE `produtos` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `nome` VARCHAR(50) NOT NULL,
    `descricao` VARCHAR(255) NULL,
    `preco` DECIMAL(10,2) NOT NULL DEFAULT 0,
    `quantidade` INT NOT NULL DEFAULT 0,
    `validade` DATE NOT NULL,
    PRIMARY KEY (`id`));
     */
    private long id;
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private Date dataValidade;

    public Produto() {
        nome = "";
        descricao = "";
        dataValidade = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public String getDataValidadeString() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(this.dataValidade);
        } catch (Exception ex) {
            return "";
        }
    }

    public void setDataValidade(Date dataValidade) {
        this.dataValidade = dataValidade;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + (int) (this.id ^ (this.id >>> 32));
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
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
