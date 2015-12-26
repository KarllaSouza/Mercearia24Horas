import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Gerente extends Funcionario{
	static Scanner in = new Scanner(System.in);
	Gerente(String nome, int idade, String cpf, String rg, String turno, float salario){
		super(nome, idade, cpf, rg, turno, salario);
		isGerente = true;
		salario=(float) 700.00;
	}
	
	static void cadastroDeNovosFuncionarios(){
		Funcionario func = new Funcionario(nome, idade, cpf, rg, turno, salario);
		if(isGerente=true){
			Calendar c = Calendar.getInstance();
			Date data = c.getTime();
			DateFormat f = DateFormat.getDateInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			func.dataQueFoiContratado = sdf.format(data);
			
			System.out.print("Turno do novo funcionário: ");
			turno = in.nextLine();
			System.out.print("Nome do novo funcionário: ");
			nome = in.nextLine();		
			System.out.print("CPF do novo funcionário: ");
			cpf = in.nextLine();
			System.out.print("RG do novo funcionário: ");
			rg = in.nextLine();
			System.out.print("Idade do novo funcionário: ");
			idade = in.nextInt();
		}
		System.out.println("\n\nContratação realizada com sucesso!\n");
		System.out.print("Nome: "+func.nome+"      ");
		System.out.print("Idade: "+func.idade+"      ");
		System.out.print("CPF: "+func.cpf+"      ");
		System.out.print("RG: "+func.rg+"      ");
		System.out.print("\nTurno: "+func.turno+"      ");
		System.out.print("Data de contratação: "+func.dataQueFoiContratado/*sdf.format(data)*/+"\n");
	}
}
