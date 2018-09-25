package br.grupointegrado.ads.primeiroServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ContadorServlet extends ServletGenerico {

    private int quantidadeAcessos = 0;
       
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        quantidadeAcessos++;
        
        res.setContentType("text/html");
        
        PrintWriter conteudo = res.getWriter();
        
        conteudo.print("<div>");
        conteudo.print("Quantidade de acessos: ");
        conteudo.print(quantidadeAcessos);
        conteudo.print("</div>");
        
        System.out.println("Contador: " + quantidadeAcessos);
    }

}
