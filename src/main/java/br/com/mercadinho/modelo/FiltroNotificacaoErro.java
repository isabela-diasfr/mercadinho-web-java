package br.com.mercadinho.modelo;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.time.LocalTime;

@WebFilter(
    filterName = "FiltroNotificacaoErro",
    urlPatterns = "/*",
    dispatcherTypes = DispatcherType.ERROR
)
public class FiltroNotificacaoErro extends HttpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Throwable erro = (Throwable) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);

        if (erro != null) {
            System.err.println("[ALERTA ADMIN] Falha crítica no sistema às "
                    + LocalTime.now()
                    + ". Causa: " + erro.getMessage()
                    + ". Tomar providências.");
        }

        chain.doFilter(request, response);
    }
}
