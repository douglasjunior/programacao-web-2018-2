package br.grupointegrado.ads.primeiroServlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginServlet extends ServletGenerico {

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String senha = req.getParameter("senha");
    
        res.setContentType("text/html");
        PrintWriter print = res.getWriter();
        print.write("Usuário: " + usuario + " <br /> Senha: " + senha);
    }

}
