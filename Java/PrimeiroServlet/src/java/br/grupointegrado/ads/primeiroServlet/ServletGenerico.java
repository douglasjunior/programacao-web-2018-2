package br.grupointegrado.ads.primeiroServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

public abstract class ServletGenerico implements Servlet {

    protected ServletConfig config;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.config = config;
    }

    @Override
    public String getServletInfo() {
        return this.getClass().getName();
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.config;
    }

    @Override
    public void destroy() {
    }
    
}
