<%@page import="br.grupointegrado.ads.gerenciadorDeProdutos.utils.Formatter"%>
<%@page import="br.grupointegrado.ads.gerenciadorDeProdutos.modelos.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <title>Gerenciamento de Produtos</title>

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <main class="container">
            <h1>Gerenciamento de Produtos</h1>

            <section>
                <h2>Cadastro de produtos</h2>
                <form method="POST">
                    <div class="form-group">
                        <label for="produto-nome">Nome</label>
                        <input type="text" class="form-control" 
                               id="produto-nome" name="produto-nome" />
                    </div>
                    <div class="form-group">
                        <label for="produto-descricao">Descrição</label>
                        <textarea class="form-control" id="produto-descricao" 
                                  name="produto-descricao" ></textarea>
                    </div>
                    <div class="form-row">
                        <div class="form-group col">
                            <label for="produto-preco">Preço</label>
                            <input type="number" class="form-control" id="produto-preco" 
                                   name="produto-preco" step="0.01" />
                        </div>
                        <div class="form-group col">
                            <label for="produto-quantidade">Quantidade</label>
                            <input type="number" class="form-control" id="produto-quantidade" 
                                   name="produto-quantidade" step="1" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="produto-validade">Data de validade</label>
                            <input type="date" class="form-control" id="produto-validade" 
                                   name="produto-validade" />
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </form>
            </section>

            <br /><br />

            <section>
                <h2>Listagem de produtos</h2>
                <table class="table table-sm table-striped">
                    <tr>
                        <th>ID</th>
                        <th>Produto</th>
                        <th>Descrição</th>
                        <th>Quantidade</th>
                        <th>Preço</th>
                        <th>Validade</th>
                    </tr>
                    <%
                        for (Produto produto : produtos) {
                    %>
                    <tr>
                        <td><%= produto.getId()%></td>
                        <td><%= produto.getNome()%></td>
                        <td><%= produto.getDescricao()%></td>
                        <td><%= produto.getQuantidade()%></td>
                        <td><%= produto.getPreco()%></td>
                        <td><%= Formatter.dataParaString(produto.getDataValidade())%></td>
                    </tr>
                    <%
                        }
                    %>
                </table>
            </section>
        </main>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
