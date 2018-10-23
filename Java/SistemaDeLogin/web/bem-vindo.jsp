<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="WEB-INF/includes/head.jsp" %>
    <body>
        <h1>
            <%
                out.println("Olá Mundo!");
            %>
        </h1>

        <% String nome = request.getParameter("nome"); %>

        <%
            if (nome != null) {
        %>
        <h2>
            Olá <%= nome%>, seja bem-vindo ao nosso site!
        </h2>
        <%
        } else {
        %>
        <h2>Olá visitante!</h2>
        <%
            }
        %>

        <%!
            private String getDataAtual() {
                return new Date().toString();
            }
        %>

        <!-- Um comentário -->
        <%-- Outro comentário --%>
        <%
            /*
            Mais um comentário
             */
        %>

        <h3>Hora data atual: <%= getDataAtual()%>.</h3>
    </body>
</html>
