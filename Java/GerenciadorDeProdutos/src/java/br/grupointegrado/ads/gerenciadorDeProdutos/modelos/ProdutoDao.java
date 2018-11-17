package br.grupointegrado.ads.gerenciadorDeProdutos.modelos;

import br.grupointegrado.ads.gerenciadorDeProdutos.utils.Formatter;
import br.grupointegrado.ads.gerenciadorDeProdutos.utils.ServletUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.fileupload.FileUploadException;

public class ProdutoDao {

    private final Connection conexao;

    public ProdutoDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Produto produto) throws SQLException {
        // SQL: INSERT INTO PRODUTOS ...

        String sql = "INSERT INTO produtos "
                + " (nome, descricao, preco, quantidade, validade, imagem) "
                + " VALUES (?, ?, ?, ?, ?, ?) ";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, produto.getNome());
        statement.setString(2, produto.getDescricao());
        statement.setDouble(3, produto.getPreco());
        statement.setInt(4, produto.getQuantidade());
        long timeValidade = produto.getDataValidade().getTime();
        statement.setDate(5, new java.sql.Date(timeValidade));
        statement.setString(6, produto.getImagem());

        statement.execute();
    }

    public Produto buscaPorId(long id) throws SQLException {
        // SQL: SELECT * FROM PRODUTOS
        String sql = "SELECT * FROM produtos "
                + " WHERE id = ? ";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setLong(1, id);

        ResultSet result = statement.executeQuery();
        if (result.first()) {
            Produto prod = getProdutoByResultSet(result);
            return prod;
        }

        return null;
    }

    public void remover(long id) throws SQLException {
        // SQL: DELETE FROM PRODUTOS ...
        String sql = "DELETE FROM produtos WHERE id = ? ";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setLong(1, id);

        statement.execute();
    }

    public void atualizar(Produto produto) throws SQLException {
        // SQL: UPDATE PRODUTOS ...
        if (produto.getImagem() != null) {
            // Altera a imagem
            String sql = "UPDATE produtos SET "
                    + " nome = ?, descricao = ?, preco = ?, quantidade = ?, validade = ?, imagem = ? "
                    + " WHERE id = ? ";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setDouble(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidade());
            long timeValidade = produto.getDataValidade().getTime();
            statement.setDate(5, new java.sql.Date(timeValidade));
            statement.setString(6, produto.getImagem());
            statement.setLong(7, produto.getId());
            statement.execute();
        } else {
            // Não altera a imagem
            String sql = "UPDATE produtos SET "
                    + " nome = ?, descricao = ?, preco = ?, quantidade = ?, validade = ? "
                    + " WHERE id = ? ";

            PreparedStatement statement = conexao.prepareStatement(sql);
            statement.setString(1, produto.getNome());
            statement.setString(2, produto.getDescricao());
            statement.setDouble(3, produto.getPreco());
            statement.setInt(4, produto.getQuantidade());
            long timeValidade = produto.getDataValidade().getTime();
            statement.setDate(5, new java.sql.Date(timeValidade));
            statement.setLong(6, produto.getId());
            statement.execute();
        }

    }

    public List<Produto> buscaTodos(String termoBusca) throws SQLException {
        String parametroBusca = "%%";
        if (termoBusca != null) {
            parametroBusca = "%" + termoBusca + "%";
        }

        String sql = "SELECT * FROM produtos    "
                + " WHERE nome LIKE ?           "
                + "    OR descricao LIKE ?      ";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, parametroBusca);
        statement.setString(2, parametroBusca);

        List<Produto> produtos = new ArrayList<>();

        ResultSet result = statement.executeQuery();
        if (result.first()) {
            do {
                Produto prod = getProdutoByResultSet(result);
                produtos.add(prod);
            } while (result.next());
        }

        return produtos;
    }

    public static Produto getProdutoByResultSet(ResultSet result) throws SQLException {
        Produto prod = new Produto();
        prod.setId(result.getLong("id"));
        prod.setNome(result.getString("nome"));
        prod.setDescricao(result.getString("descricao"));
        prod.setPreco(result.getDouble("preco"));
        prod.setQuantidade(result.getInt("quantidade"));
        prod.setDataValidade(result.getDate("validade"));
        prod.setImagem(result.getString("imagem"));
        return prod;
    }

    public static Produto getProdutoByRequest(HttpServletRequest req) throws FileUploadException, IOException {
        Map<String, Object> parametters = ServletUtil.recuperaParametrosMultipart(req);
        System.out.println(parametters);

        String id = (String) parametters.get("produto-id");
        String nome = (String) parametters.get("produto-nome");
        String descricao = (String) parametters.get("produto-descricao");
        String preco = (String) parametters.get("produto-preco");
        String quantidade = (String) parametters.get("produto-quantidade");
        String validade = (String) parametters.get("produto-validade");
        Arquivo arquivoImagem = (Arquivo) parametters.get("produto-imagem");

        String imagemCaminho = null;
        if (arquivoImagem != null && arquivoImagem.temConteudo()) {
            imagemCaminho = ServletUtil.gravarArquivo(arquivoImagem);
        }

        Produto produto = new Produto();
        produto.setId(Formatter.stringParaLong(id));
        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(Formatter.stringParaDouble(preco));
        produto.setQuantidade(Formatter.stringParaInt(quantidade));
        produto.setDataValidade(Formatter.stringParaData(validade));
        produto.setImagem(imagemCaminho);
        return produto;
    }

}
