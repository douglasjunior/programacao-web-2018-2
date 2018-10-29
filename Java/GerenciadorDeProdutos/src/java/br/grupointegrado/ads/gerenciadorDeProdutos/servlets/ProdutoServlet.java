package br.grupointegrado.ads.gerenciadorDeProdutos.servlets;

import br.grupointegrado.ads.gerenciadorDeProdutos.modelos.Produto;
import br.grupointegrado.ads.gerenciadorDeProdutos.modelos.ProdutoDao;
import java.io.IOException;
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
        /**
         * 1. Fazer a consulta dos produtos a serem exibidos <br />
         * 2. Montar uma lista com os produtos que serão apresentados na página
         * JSP. <br />
         * 3. Encaminhar a requisição para a página JSP apresentar o "response".
         */
        ProdutoDao dao = new ProdutoDao();

        List<Produto> produtos = dao.buscaTodos(null);
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
        Produto produto = ProdutoDao.getProdutoByRequest(req);

        ProdutoDao dao = new ProdutoDao();
        dao.inserir(produto);

        doGet(req, resp);
    }

}
