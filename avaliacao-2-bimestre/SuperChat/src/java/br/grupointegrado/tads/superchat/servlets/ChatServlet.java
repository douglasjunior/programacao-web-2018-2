package br.grupointegrado.tads.superchat.servlets;

import br.grupointegrado.tads.superchat.modelos.Chat;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dougl
 */
public class ChatServlet extends HttpServlet {

    private void definirNomeUsuario(HttpServletRequest req) {
        HttpSession sessao = req.getSession();

        if (!(sessao.getAttribute("autor") instanceof String)) {
            String autor = "Usuário " + Math.round(Math.random() * 1000);
            sessao.setAttribute("autor", autor);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        definirNomeUsuario(req);

        req.setAttribute("mensagens", Chat.getMensagens());

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/paginas/chat.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        definirNomeUsuario(req);

        Object textoMensagem = req.getAttribute("mensagem");

        if (validarMensagem(textoMensagem)) {
            HttpSession sessao = req.getSession();
            String autor = (String) sessao.getAttribute("autor");

            Chat.addMensagem(autor, textoMensagem.toString());

            resp.sendRedirect("/superchat/chat");
        } else {
            req.setAttribute("mensagem-erro", "A mensagem não pode ser vazia.");

            doGet(req, resp);
        }
    }
    
    private boolean validarMensagem(Object textoMensagem) {
        if (textoMensagem instanceof String 
                && !textoMensagem.toString().isEmpty()) {
            return true;
        }
        return false;
    }

}
