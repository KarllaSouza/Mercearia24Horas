import java.util.Scanner;

public class Produto extends Estoque{
	static Scanner in = new Scanner(System.in);
	
	private static String nome;
	private static int codigoDoProduto;
	private static int quantidade;
	private static double preco;
	
	public Produto(String nome, int codigoDoProduto, int quantidade, double preco) {
		Produto.nome = nome;
		Produto.codigoDoProduto = codigoDoProduto;
		Produto.quantidade = quantidade;
		Produto.preco = preco;
	}

}
