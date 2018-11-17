package br.grupointegrado.ads.gerenciadorDeProdutos.modelos;

import br.grupointegrado.ads.gerenciadorDeProdutos.utils.Formatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ProdutoDao {

    private final Connection conexao;

    public ProdutoDao(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserir(Produto produto) throws SQLException {
        // SQL: INSERT INTO PRODUTOS ...

        String sql = "INSERT INTO produtos "
                + " (nome, descricao, preco, quantidade, validade) "
                + " VALUES (?, ?, ?, ?, ?) ";

        PreparedStatement statement = conexao.prepareStatement(sql);
        statement.setString(1, produto.getNome());
        statement.setString(2, produto.getDescricao());
        statement.setDouble(3, produto.getPreco());
        statement.setInt(4, produto.getQuantidade());

        long timeValidade = produto.getDataValidade().getTime();
        statement.setDate(5, new java.sql.Date(timeValidade));

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
        return prod;
    }

    public static Produto getProdutoByRequest(HttpServletRequest req) {
        Produto produto = new Produto();
        produto.setId(Formatter.stringParaLong(req.getParameter("produto-id")));
        produto.setNome(req.getParameter("produto-nome"));
        produto.setDescricao(req.getParameter("produto-descricao"));
        produto.setPreco(Formatter.stringParaDouble(req.getParameter("produto-preco")));
        produto.setQuantidade(Formatter.stringParaInt(req.getParameter("produto-quantidade")));
        produto.setDataValidade(Formatter.stringParaData(req.getParameter("produto-validade")));
        return produto;
    }

}
