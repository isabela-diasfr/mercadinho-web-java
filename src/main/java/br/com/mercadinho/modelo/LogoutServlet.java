package br.com.mercadinho.modelo;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public LogoutServlet() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession(false);
		if(session != null) {
			request.getSession().invalidate();
		}
		var out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Logout</title>");
		out.println("</head>");
		out.println("<body>");
		
		out.println("<h2>Você saiu do sistema.</h2>");
		out.println("<p>Sessão encerrada com sucesso.</p>");
		
		out.println("<br>");
		out.println("<a href='index.html'>Voltar para o início</a>");
		
		out.println("</body>");
		out.println("</html>");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
