package br.grupointegrado.ads.primeiroServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CabecalhosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Enumeration<String> cabecalhosNomes = req.getHeaderNames();
        
        while(cabecalhosNomes.hasMoreElements()){
            String nomeCabecalho = cabecalhosNomes.nextElement();
            String valorCabecalho = req.getHeader(nomeCabecalho);
            
            System.out.println("Cabeçalho: " + nomeCabecalho + " -> " + valorCabecalho);
        }
    }
    
}
