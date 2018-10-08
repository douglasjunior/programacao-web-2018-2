package br.grupointegrado.ads.sistema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarrinhoCompraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // o calculo do valor total dos produtos
        // calculo do frete
        // calculo de imposto, etc

        List<String> produtos = new ArrayList<>();
        produtos.add("Adaptador HDMI para VGA");
        produtos.add("Case para HD externo");
        produtos.add("Cabo de rede ethernet RJ45");

        req.setAttribute("produtos", produtos);
        
        RequestDispatcher rq = req.getRequestDispatcher("/pagamento");
        rq.forward(req, resp);
    }

}
