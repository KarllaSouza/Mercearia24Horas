import java.util.Scanner;


public class FuncoesEObservacoes{
	static Scanner in = new Scanner(System.in);
	public static String funcao;

	static void salario() {
		System.out.print("Qual a função? (gerente, vendedor, seguranca ou outros)");
		funcao = in.nextLine();
		
		if(funcao=="gerente"){
			if(turno=="manha"||turno=="tarde"){
				System.out.println("Salario: R$ "+Gerente.salario);
			}
		} else if(funcao=="vendedor"){
			if(turno=="manha"||turno=="tarde"){
				System.out.println("Salario: R$ "+Vendedor.salario);
			}
		} else if(funcao=="seguranca"||funcao=="outros"){
			if(turno=="manha"||turno=="tarde"){
				System.out.println("Salario: R$ "+SegurancaOuOutrosFuncionarios.salario);
			}
		} else if(funcao=="gerente"){
			if(turno=="noite"||turno=="noturno"){
				Gerente.salario = (float)(salario+(salario*0.3));
				System.out.println("Salario: R$ "+Gerente.salario);
			}
		} else if(funcao=="vendedor"){
			if(turno=="noite"||turno=="noturno"){
				Vendedor.salario = (float)(salario+(salario*0.3));
				System.out.println("Salario: R$ "+Vendedor.salario);
			}
		} else if(funcao=="seguranca"||funcao=="outros"){
			if(turno=="noite"||turno=="noturno"){
				SegurancaOuOutrosFuncionarios.salario = (float)(salario+(salario*0.3));
				System.out.println("Salario: R$ "+SegurancaOuOutrosFuncionarios.salario);
			}
		} 
	}
}
