package br.com.mercadinho.modelo;


import jakarta.servlet.ServletException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ReceitasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ReceitasServlet() {
        super();

    }
    
    private List<Receitas> database = Arrays.asList(

    	    new Receitas("Bolo de Cenoura", "Cenoura, Óleo, Ovos, Farinha, Açúcar, Chocolate", "Sobremesa"),

    	    new Receitas("Pão de Queijo", "Polvilho, Queijo Meia Cura, Ovos, Leite, Óleo", "Lanche"),

    	    new Receitas("Omelete de Ervas", "Ovos, Salsinha, Cebolinha, Sal, Manteiga", "Refeição Rápida"),

    	    new Receitas("Panqueca Americana", "Farinha, Leite, Ovo, Fermento, Baunilha", "Café da Manhã")

    	);
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String query = request.getParameter("q");
		List<Receitas> filtrados = database;
		
		if(query != null && !query.isEmpty()) {
			filtrados = database.stream()
					.filter(d->d.getNome().toLowerCase().contains(query.toLowerCase()))
					.collect(Collectors.toList());
		}
		StringBuilder json = new StringBuilder("[");
		for (int i = 0; i < filtrados.size(); i++) {
			Receitas d = filtrados.get(i);
			json.append(String.format("{\"nome\":\"%s\", \"ingredientes\":\"%s\",\"cat\":\"%s\"}",
					d.getNome(), d.getIngredientes(), d.getCategoria()));
						if(i < filtrados.size() - 1) json.append(",");
		}
		json.append("]");
		
		response.getWriter().print(json.toString());
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
