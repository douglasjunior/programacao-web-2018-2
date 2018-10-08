package br.grupointegrado.ads.sistema;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CaracterReservadoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter conteudo = resp.getWriter();

        conteudo.println("<html>");
        conteudo.println("<body>");
        conteudo.println("<p>Para quebrar uma linha você deve usar a tag "
                + encodeHtmlTag("<br />") + " do HTML.</p>");
        conteudo.println("</body>");
        conteudo.println("</html>");
    }

    public static String encodeHtmlTag(String tag) {
        if (tag == null) {
            return null;
        }
        StringBuffer encodingTag = new StringBuffer();
        int length = tag.length();
        for (int i = 0; i < length; i++) {
            char c = tag.charAt(i);
            if (c == '<') {
                encodingTag.append("&lt;");
            } else if (c == '>') {
                encodingTag.append("&gt;");
            } else if (c == '&') {
                encodingTag.append("&amp;");
            } else if (c == '"') {
                encodingTag.append("&quot;");
            } else if (c == ' ') {
                encodingTag.append("&nbsp;");
            } else {
                encodingTag.append(c);
            }
        }
        return encodingTag.toString();
    }

}
