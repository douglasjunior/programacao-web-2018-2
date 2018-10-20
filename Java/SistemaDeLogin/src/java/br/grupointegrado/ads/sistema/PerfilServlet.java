package br.grupointegrado.ads.sistema;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PerfilServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        
        Object usuarioLogado = session.getAttribute("usuario-logado");
                
        if (usuarioLogado != null) {
            PrintWriter conteudo = resp.getWriter();
            
            conteudo.println("Bem-vindo ao seu perfil!");
        } else {
            resp.sendRedirect("/sistema/login");
        }
    }
    
}
