package br.grupointegrado.ads.gerenciadorDeProdutos.servlets;

import br.grupointegrado.ads.gerenciadorDeProdutos.modelos.Produto;
import br.grupointegrado.ads.gerenciadorDeProdutos.modelos.ProdutoDao;
import br.grupointegrado.ads.gerenciadorDeProdutos.utils.Formatter;
import br.grupointegrado.ads.gerenciadorDeProdutos.utils.Validations;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * URL: /produtos
 *
 * @author dougl
 */
public class ProdutoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProdutoDao dao = new ProdutoDao();

        long produtoId = Formatter.stringParaLong(req.getParameter("produto"));
        long excluirProdutoId
                = Formatter.stringParaLong(req.getParameter("excluirProduto"));

        if (excluirProdutoId > 0) {
            // excluir o produto do banco de dados.
            dao.remover(excluirProdutoId);
            resp.sendRedirect("/gerenciador/produtos");
        } else {
            // Verifica se o ID do produto foi informado na URL
            if (produtoId > 0) {
                Produto produtoEncontrado = dao.buscaPorId(produtoId);
                if (produtoEncontrado != null) {
                    // Se o produto existe, então devolve o produto para a JSP
                    req.setAttribute("produto", produtoEncontrado);
                } else {
                    // Se não, exibe uma mensagem de erro
                    req.setAttribute("mensagem-erro", "Produto não encontrado.");
                }
            }
            listarProdutos(req, resp);
        }
    }

    private void listarProdutos(HttpServletRequest req, HttpServletResponse resp)
            throws IOException, ServletException {
        /**
         * 1. Fazer a consulta dos produtos a serem exibidos <br />
         * 2. Montar uma lista com os produtos que serão apresentados na página
         * JSP. <br />
         * 3. Encaminhar a requisição para a página JSP apresentar o "response".
         */
        ProdutoDao dao = new ProdutoDao();

        String buscaProduto = req.getParameter("buscar-produto");

        List<Produto> produtos = dao.buscaTodos(buscaProduto);

        req.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher
                = req.getRequestDispatcher("/WEB-INF/paginas/produtos.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 1. Recuperar os parâmetros do formulário. <br />
         * 2. Criar um objeto Produto. <br />
         * 3. Salvar o Produto no banco de dados. <br />
         * 4. Exibir a página de listagem atualizada.
         */
        String mensagemErro = validaCadastro(req);
        Produto produto = ProdutoDao.getProdutoByRequest(req);

        if (mensagemErro == null) {
            // Os dados do produto são válidos
            ProdutoDao dao = new ProdutoDao();

            if (produto.getId() > 0) {
                // Se o produto já possui ID, então deve atualizar
                dao.atualizar(produto);
            } else {
                // Se não, inserir novo produto
                dao.inserir(produto);
            }

            resp.sendRedirect("/gerenciador/produtos");
        } else {
            // Os dados do produto são inválidos
            req.setAttribute("mensagem-erro", mensagemErro);
            req.setAttribute("produto", produto);

            listarProdutos(req, resp);
        }
    }

    // teste vitor
    private String validaCadastro(HttpServletRequest req) {
        if (!Validations.validaString(req.getParameter("produto-nome"), 5)) {
            return "O nome do produto deve possuir ao menos 5 caracteres.";
        }
        if (!Validations.validaDouble(
                req.getParameter("produto-preco"), 0.01, Double.MAX_VALUE)) {
            return "O preço do produto é obrigatório.";
        }
        if (!Validations.validaLong(
                req.getParameter("produto-quantidade"), 0, Integer.MAX_VALUE)) {
            return "A quantidade do produto é obrigatória.";
        }

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date dataMinima = calendar.getTime();

        calendar.add(Calendar.YEAR, 10);
        Date dataMaxima = calendar.getTime();

        if (!Validations.validaData(
                req.getParameter("produto-validade"), dataMinima, dataMaxima)) {
            return "Informe a data de validade do produto.";
        }
        return null;
    }

}
