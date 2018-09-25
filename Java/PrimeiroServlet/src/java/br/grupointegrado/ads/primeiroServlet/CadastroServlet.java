package br.grupointegrado.ads.primeiroServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CadastroServlet extends HttpServlet {

    private List<String> BANCO_DADOS = new ArrayList<>();
    
    /**
     * Processa as requisições recebidas por GET /tarefas
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        imprimirConteudo(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String titulo = req.getParameter("titulo");

        BANCO_DADOS.add(titulo);

        imprimirConteudo(resp);
    }

    private void imprimirConteudo(HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        PrintWriter conteudo = resp.getWriter();

        conteudo.println("<html>");
        conteudo.println("<head>");
        conteudo.println("<title>Cadastro de Tarefas</title>");
        conteudo.println("</head>");
        conteudo.println("<body>");
        
        conteudo.println("<form method=\"POST\" >");
        conteudo.println("<label>Tarefa: </label>");
        conteudo.println("<input type=\"text\" name=\"titulo\">");
        conteudo.println("<br />");
        conteudo.println("<button type=\"submit\">Cadastrar</button>");
        conteudo.println("</form>");

        conteudo.println("<ul>");
        for (String tarefa : BANCO_DADOS) {
            conteudo.println("<li>" + tarefa + "</li>");
        }
        conteudo.println("</ul>");

        conteudo.println("</body>");
        conteudo.println("</html>");
    }

}
