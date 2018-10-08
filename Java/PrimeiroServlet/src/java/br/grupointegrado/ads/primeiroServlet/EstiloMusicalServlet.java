package br.grupointegrado.ads.primeiroServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EstiloMusicalServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doGet: " + new Date());
        
//        resp.addHeader("Cache-Control", "max-age=31536000");
//        resp.addHeader("Expires", "Thu, 26 Sep 2019 20:33:14 GMT");
        
        resp.setContentType("text/html");
        PrintWriter conteudo = resp.getWriter();

        conteudo.println("<html>");
        conteudo.println("<head>");
        conteudo.println("<title>Escolha seu Estilo Musical</title>");
        conteudo.println("</head>");
        conteudo.println("<body>");
        
        conteudo.println("<form method=\"POST\" >");
        conteudo.println("<input type=\"checkbox\" name=\"estilo\" value=\"rock\">");
        conteudo.println("<label>Rock</label>");
        conteudo.println("<br />");
        conteudo.println("<input type=\"checkbox\" name=\"estilo\" value=\"pop\">");
        conteudo.println("<label>Pop</label>");
        conteudo.println("<br />");
        conteudo.println("<input type=\"checkbox\" name=\"estilo\" value=\"jazz\">");
        conteudo.println("<label>Jazz</label>");
        conteudo.println("<br />");
        conteudo.println("<input type=\"checkbox\" name=\"estilo\" value=\"blues\">");
        conteudo.println("<label>Blues</label>");
        conteudo.println("<br />");
        conteudo.println("<button type=\"submit\">Enviar</button>");
        conteudo.println("</form>");

        conteudo.println("</body>");
        conteudo.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] estilosSelecionados = req.getParameterValues("estilo");
        
        System.out.println(Arrays.toString(estilosSelecionados));
    }
    
}
