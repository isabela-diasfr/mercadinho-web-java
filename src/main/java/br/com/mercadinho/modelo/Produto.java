package br.com.mercadinho.modelo;
import java.io.Serializable;
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private double preco;
	
	public Produto() {}
	
	public Produto(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return "Produto: " + nome + " | Preço: R$ " + preco;
	}

	
}
