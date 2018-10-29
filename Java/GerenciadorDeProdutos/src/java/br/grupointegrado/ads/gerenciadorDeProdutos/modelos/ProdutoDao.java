package br.grupointegrado.ads.gerenciadorDeProdutos.modelos;

import br.grupointegrado.ads.gerenciadorDeProdutos.utils.Formatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class ProdutoDao {

    private static List<Produto> produtos = new ArrayList<>();
    private static long sequenciaIds = 1;

    static {
        for (int i = 0; i < 10; i++) {
            Produto p = new Produto();
            p.setId(sequenciaIds++);
            p.setNome("produto " + i);
            p.setDescricao("produto descrição " + i);
            p.setPreco(10.5 * i);
            p.setQuantidade(8 * i);
            p.setDataValidade(new Date());
            produtos.add(p);
        }
    }

    public ProdutoDao() {
    }

    public void inserir(Produto produto) {
        // SQL: INSERT INTO PRODUTOS ...
        produto.setId(sequenciaIds++);
        produtos.add(produto);
    }

    public Produto buscaPorId(long id) {
        // SQL: SELECT * FROM PRODUTOS
        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    public void remover(long id) {
        // SQL: DELETE FROM PRODUTOS ...
        Produto produto = buscaPorId(id);
        if (produto != null) {
            produtos.remove(produto);
        }
    }

    public void atualizar(Produto produtoAtualizado) {
        // SQL: UPDATE PRODUTOS ...
        remover(produtoAtualizado.getId());
        produtos.add(produtoAtualizado);
    }

    public List<Produto> buscaTodos(String termoBusca) {
        // SQL: SELECT * FROM PRODUTOS ...
        if (termoBusca == null || termoBusca.isEmpty()) {
            return produtos;
        }
        List<Produto> produtosEncontrados = new ArrayList<>();
        for (Produto produto : produtos) {
            if (produto.getNome().contains(termoBusca)
                    || produto.getDescricao().contains(termoBusca)) {
                produtosEncontrados.add(produto);
            }
        }
        return produtosEncontrados;
    }

    public static Produto getProdutoByRequest(HttpServletRequest req) {
        Produto produto = new Produto();
        produto.setNome(req.getParameter("produto-nome"));
        produto.setDescricao(req.getParameter("produto-descricao"));
        produto.setPreco(Formatter.stringParaDouble(req.getParameter("produto-preco")));
        produto.setQuantidade(Formatter.stringParaInt(req.getParameter("produto-quantidade")));
        produto.setDataValidade(Formatter.stringParaData(req.getParameter("produto-validade")));
        return produto;
    }

}
