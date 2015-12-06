import java.util.Scanner;

public class GerenciaSistema {
	static Scanner in = new Scanner(System.in);
	
	private static void opcaoGerente(){
		//cadastrar Funcionários
		//cadastrar Produtos
		//Alterar estoque (adicionar ou retirar produtos)
	}
	private static void opcaoVendedor(){
		//Alterar estoque (retirar produtos)
		//Emitir nota fiscal
		//Vendedor noturno recebe MAIS
	}
	protected static void opcaoSegurancaOuOutroFuncionario(){
		//funcionários noturnos recebem MAIS
	}
	

	public static void main(String[] args) {
	  System.out.println("*          * *    Mercearia JK     * *         *");
		System.out.println("Você é:\n  1. Gerente\n  2. Vendedor\n  3. Segurança/Outro Funcionário\n");
		int func = in.nextInt();
		
		switch (func){
			case 1:
				opcaoGerente();
				break;
			case 2:
				opcaoVendedor();
				break;
			case 3: 
				opcaoSegurancaOuOutroFuncionario();
				break;
			default:
				System.out.println("Opção Inválida.\nEste sistema é apenas para os funcionários da Mercearia");
			
		}
		
	}
}
