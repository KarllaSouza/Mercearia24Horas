package BO;

public class Produtos {
	public int id;
	public String nome;
	public double valor;
	public int estoque;
	
	public Produtos(String nome, double valor, int estoque, int id) {
		this.nome = nome;
		this.valor = valor;
		this.estoque = estoque;
		this.id = id;
	}
}
