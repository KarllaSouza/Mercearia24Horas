public class SegurancaOuOutrosFuncionarios extends Funcionario{
	SegurancaOuOutrosFuncionarios(String nome, int idade, String cpf, String rg, String turno, float salario){
		super(nome, idade, cpf, rg, turno, salario);
		isGerente = false;
		salario=(float) 650.00;
	}
}
