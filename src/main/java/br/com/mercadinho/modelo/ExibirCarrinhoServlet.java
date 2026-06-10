package br.com.mercadinho.modelo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;

import java.io.IOException;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

// IMPORTS DO SWAGGER
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;

@WebServlet("/carrinho")
@Path("/carrinho")
@Tag(
    name = "Exibir Carrinho de Compras",
    description = "Operação responsável por visualizar os produtos armazenados no carrinho da sessão"
)
public class ExibirCarrinhoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ExibirCarrinhoServlet() {
        super();
    }

    @GET
    @Produces("text/html")
    @Operation(
        summary = "Exibe o conteúdo do carrinho",
        description = "Gera uma página HTML contendo a lista de produtos armazenados na sessão e o valor total da compra."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Página do carrinho exibida com sucesso",
            content = @Content(mediaType = "text/html")
        ),
        @ApiResponse(
            responseCode = "500",
            description = "Erro interno ao acessar a sessão"
        )
    })
    protected void doGet(
            @Context HttpServletRequest request,
            @Context HttpServletResponse response
    ) throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        HttpSession session = request.getSession();

        // CORREÇÃO: atributo deve ser "carrinho"
        List<Produto> itens = (List<Produto>) session.getAttribute("ExibirCarrinhoServlet");

        Locale ptBr = new Locale("pt", "BR");
        NumberFormat moeda = NumberFormat.getCurrencyInstance(ptBr);

        double total = 0;
        var out = response.getWriter();

        out.println("<html><body><h2>Itens no seu Carrinho:</h2><ul>");

        if (itens != null && !itens.isEmpty()) {
            for (Produto item : itens) {
                out.println("<li>" + item.getNome() + "<br>" +
                        moeda.format(item.getPreco()) + "</li>");
                total += item.getPreco();
            }

            // salva total na sessão
            session.setAttribute("total_compra", total);

            out.println("<p>O valor total do carrinho é: "
                    + moeda.format(total) + "</p>");
        } else {
            out.println("<p>O carrinho está vazio!</p>");
        }

        out.println("</ul>");
        out.println("<a href='index.html'>Adicionar mais</a> | ");
        out.println("<a href='LogoutServlet'>Limpar Sessão (Logout)</a>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
