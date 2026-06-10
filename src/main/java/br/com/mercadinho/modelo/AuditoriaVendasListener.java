package br.com.mercadinho.modelo;


import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class AuditoriaVendasListener
 *
 */
public class AuditoriaVendasListener implements HttpSessionAttributeListener {
	private static final double LIMITE_COMPRA_SUSPEITA = 1000.00;
	private static final double VARIACAO_ALERTA_ESTOQUE = 200.00;

    /**
     * Default constructor. 
     */
    public AuditoriaVendasListener() {
    	
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    @Override
    public void attributeReplaced(HttpSessionBindingEvent event)  { 
    	processarAuditoria(event, "ATUALIZAÇÃO DE CARRINHO");
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event)  { 
        processarAuditoria(event, "INICIO DE COMPRA");
    }

	private void processarAuditoria(HttpSessionBindingEvent event, String operacao) {
		if ("total_compra".equals(event.getName())) {
			Double ValorAntigo = (event.getValue() instanceof Double) ? (Double) event.getValue() : 0.0;
			Double novoTotal  = (Double) event.getSession().getAttribute("total_compra");
			System.out.println("\n[LOG AUDITORIA MAX] " + operacao);
			System.out.println("ID Cliente (Sessão): " + event.getSession().getId());
			System.out.printf("Valor Atual: R$ %.2f%n", novoTotal);
			
			//REGRA 1
			if (novoTotal > LIMITE_COMPRA_SUSPEITA) {
				System.err.println("WARNING: Compra de alto valor detectada. Verificar limite de crédito.");
			}
			//REGRA 2
			Double diferenca = novoTotal - ValorAntigo;
			if ( diferenca > VARIACAO_ALERTA_ESTOQUE) {
				System.err.printf("WARNING: Incremento brusco de R$ %.2f detectado em uma única operação.%n", diferenca);
				
			}
			
		}
		
		
		
	}
	
	
	
	
}