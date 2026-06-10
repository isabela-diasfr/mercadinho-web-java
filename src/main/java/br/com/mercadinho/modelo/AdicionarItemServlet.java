package br.com.mercadinho.modelo;


import jakarta.servlet.ServletException;
import java.util.ArrayList;
import java.util.List;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import jakarta.servlet.http.HttpSession;
import java.util.Locale;
import java.text.NumberFormat;

public class AdicionarItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdicionarItemServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Produto> itens = (List<Produto>) session.getAttribute("ExibirCarrinhoServlet");
		if(itens == null) {
			itens = new ArrayList<>();
		}
		
		String nome = request.getParameter("produto");
		String valor = request.getParameter("preco");
		
		if(nome != null && !nome.trim().isEmpty() && valor != null && !valor.trim().isEmpty()) {
			try {
				valor = valor.replace(",", ".");
			    double preco = Double.parseDouble(valor.trim());
			    itens.add(new Produto(nome, preco));
			} catch (NumberFormatException e) {
			    response.sendRedirect("erro.html");
			    return;
			}
			session.setAttribute("ExibirCarrinhoServlet", itens);
			double novoTotal = 0.0;
			
			for (Produto p : itens) {
				novoTotal += p.getPreco();
			}
			
			session.setAttribute("total_compra", novoTotal);
		}
		
		response.sendRedirect("ExibirCarrinhoServlet");
	}

}
