package br.com.mercadinho.modelo;
import jakarta.annotation.Resource;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/api") 
@OpenAPIDefinition(info = @Info(title = "Mercadinho API", version = "1.0.0"))
public class api extends Application {
 @Override
 public Set<Class<?>> getClasses() {
 Set<Class<?>> resources = new HashSet<>();
 

 resources.add(AdicionarItemServlet.class); 
 resources.add(AuditoriaVendasListener.class); 
 resources.add(ExibirCarrinhoServlet.class);
 resources.add(Filtro.class);
 resources.add(FiltroNotificacaoErro.class);
 resources.add(LogoutServlet.class);
 resources.add(Produto.class);
 resources.add(Receitas.class);
 resources.add(ReceitasServlet.class);
 
 // Esta linha é a responsável por habilitar a rota /openapi.json!
 resources.add(OpenApiResource.class); 
 
 return resources;
 }
}
