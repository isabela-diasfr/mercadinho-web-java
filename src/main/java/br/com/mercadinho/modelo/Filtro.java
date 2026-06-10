package br.com.mercadinho.modelo;


import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalTime;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import java.util.Date;



public class Filtro extends HttpFilter implements Filter {
       
 
    public Filtro() {
        super();
        
    }

	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("[Mercadinho Log] Cliente entrou na loja virtual em: " + new java.util.Date());
		
		LocalTime agora = LocalTime.now();
		LocalTime abertura = LocalTime.of(7, 0);
		LocalTime fechamento = LocalTime.of(23, 0);
		
		if(agora.isBefore(abertura) || agora.isAfter(fechamento)) {
			System.out.println("[Mercadinho] Acesso bloqueado fora do horário!");
			
			HttpServletResponse resp = (HttpServletResponse) response;
			resp.sendRedirect("fechado.html");
			return;
		}
		
		chain.doFilter(request, response);
		System.out.println("[Mercadinho Log] Resposta do sistema enviada em: " + new java.util.Date());
	}



	public void init(FilterConfig fConfig) throws ServletException {


	}

}
