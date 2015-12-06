
public class Pessoa {
	protected static String nome;
	protected static int idade;
	protected static String cpf;
	protected static String rg;
	protected static char turno; //onde ao digitar D está dizendo que esta pessoa trabalha pelo dia, e N que trabalha pela noite
	
	
	Pessoa(String nome,	int idade, String cpf, String rg, char turno){
		Pessoa.nome=nome;
		Pessoa.idade=idade;
		Pessoa.cpf=cpf;
		Pessoa.rg=rg;
		Pessoa.turno=turno;
	}


}
