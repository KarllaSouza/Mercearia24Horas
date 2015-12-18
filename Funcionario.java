import java.util.Calendar;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Funcionario {
	protected static String nome;
	protected static int idade;
	protected static Date dataNascimento;
	protected static String dataQueFoiContratado;
	protected static String cpf;
	protected static String rg;
	protected static String turno; //Que pode ser diurno ou noturno
	
	Funcionario(String nome, int idade, String cpf, String rg, String turno){
		Funcionario.nome=nome;
		Funcionario.idade=idade;
		Funcionario.cpf=cpf;
		Funcionario.rg=rg;
		Funcionario.turno=turno;
	}

	public static void main(String[] args) throws ParseException {
		Calendar c = Calendar.getInstance();
		Date data = c.getTime();
		DateFormat f = DateFormat.getDateInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Data de contratação: "+sdf.format(data));
	}

}
