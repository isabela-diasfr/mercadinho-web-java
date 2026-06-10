package br.com.mercadinho.modelo;

public class Receitas {
	private String nome;
	private String ingredientes;
	private String categoria;
	
	public Receitas(String nome, String ingredientes, String categoria) {
		this.nome = nome;
		this.ingredientes = ingredientes;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIngredientes() {
		return ingredientes;
	}

	public void setIngredientes(String ingredientes) {
		this.ingredientes = ingredientes;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
}
