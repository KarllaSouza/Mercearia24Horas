import java.util.Scanner;

public class Produto extends Estoque{
	static Scanner in = new Scanner(System.in);
	static String nome;
	static int codigoDoProduto;
	static int quantidade;
	static double preco;
	
	public Produto(String nome, int codigoDoProduto, int quantidade, double preco) {
		Produto.nome = nome;
		Produto.codigoDoProduto = codigoDoProduto;
		Produto.quantidade = quantidade;
		Produto.preco = preco;
	}
}
