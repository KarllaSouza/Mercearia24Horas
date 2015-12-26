
public class Vendedor extends Funcionario{
	Vendedor(String nome,	int idade, String cpf, String rg, String turno,float salario){
		super(nome, idade, cpf, rg, turno,salario);
		isGerente = false;
		salario=(float) 650.00;
	}
}
