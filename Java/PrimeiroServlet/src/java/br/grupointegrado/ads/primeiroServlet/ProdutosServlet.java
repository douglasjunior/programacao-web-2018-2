package br.grupointegrado.ads.primeiroServlet;

import java.io.IOException;
import java.util.Date;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class ProdutosServlet implements Servlet {

    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init: " + new Date());

        this.config = config;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("service: " + new Date());
        res.getWriter().print("Ok");

        String email = config.getInitParameter("adminEmail");
        String fone = config.getInitParameter("adminFone");
        System.out.println("Email: " + email + " - Fone: " + fone);
        
        ServletContext context = config.getServletContext();
        String usuario = context.getInitParameter("bancoUsuario");
        String senha = context.getInitParameter("bancoSenha");
        System.out.println("Usuário: " + usuario + " - Senha: " + senha);
        System.out.println("Versão maior: " + context.getMajorVersion());
        System.out.println("Versão menor: " + context.getMinorVersion());
        System.out.println("Info: " + context.getServerInfo());
    }

    @Override
    public ServletConfig getServletConfig() {
        System.out.println("getServletConfig: " + new Date());
        return null;
    }

    @Override
    public String getServletInfo() {
        System.out.println("getServletInfo: " + new Date());
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("destroy: " + new Date());
    }

}
