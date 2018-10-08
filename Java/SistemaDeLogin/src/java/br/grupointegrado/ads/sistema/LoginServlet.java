package br.grupointegrado.ads.sistema;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    private void imprimirFormularioLogin(HttpServletResponse resp,
            boolean erroLogin) throws IOException {
        resp.setContentType("text/html");

        PrintWriter conteudo = resp.getWriter();

        conteudo.println("<html>");
        conteudo.println("<head>");
        conteudo.println("<meta charset=\"utf-8\">");
        conteudo.println("<title>Página de Login</title>");
        conteudo.println("</head>");
        conteudo.println("<body>");
        conteudo.println("<h1>Informe os dados de Login</h1>");
        conteudo.println("<form method=\"POST\">");
        conteudo.println("<input type=\"text\" name=\"usuario\" placeholder=\"Usuário\">");
        conteudo.println("<br /><br />");
        conteudo.println("<input type=\"password\" name=\"senha\" placeholder=\"Senha\">");
        conteudo.println("<br />");
        if (erroLogin) {
            conteudo.println("<p style=\"color: red\">Usuário ou senha incorretos.</p>");
        }
        conteudo.println("<br />");
        conteudo.println("<button type=\"submit\">Entrar</button>");
        conteudo.println("</form>");
        conteudo.println("</body>");
        conteudo.println("</html>");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        imprimirFormularioLogin(resp, false);
    }

    private static final String USUARIO = "douglas";
    private static final String SENHA = "123456";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String usuario = req.getParameter("usuario");
        String senha = req.getParameter("senha");

        if (USUARIO.equals(usuario) && SENHA.equals(senha)) {
            // acertou usuário e senha

            HttpSession session = req.getSession();
            session.setAttribute("usuario-logado", true);

//            Cookie cookieLogin = new Cookie("cookie-login", "dsfa97as97d89asdf");
//            cookieLogin.setMaxAge(60 * 60 * 24);
//            
//            resp.addCookie(cookieLogin);
            resp.sendRedirect("/sistema/perfil");
        } else {
            // errou usuário ou senha
//            resp.sendError(401, "Usuário ou senha incorreto.");
            resp.setStatus(401);
            imprimirFormularioLogin(resp, true);
        }
    }

}
