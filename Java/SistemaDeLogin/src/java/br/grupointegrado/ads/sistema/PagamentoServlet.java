package br.grupointegrado.ads.sistema;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PagamentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> produtos = (List<String>) req.getAttribute("produtos");
        // processar o pagamento
        // devolver a mensagem ao usuário
        
        PrintWriter conteudo = resp.getWriter();
        conteudo.println("Pagamento processado: " + produtos.toString());
    }
    
}
