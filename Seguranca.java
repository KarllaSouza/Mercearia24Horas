package Modelo;
import java.sql.SQLException;
import DAO.Funcionario;
import DAO.Gerente;

public class Seguranca {
	public static void SerChamado(java.sql.Connection conexao, int menu) throws SQLException{
		System.out.println("A caminho.");
		if(menu==1){
			Gerente.menuDoGerente(conexao);
		} else{
			Funcionario.menuDoFuncionario(conexao);
		}
	}
}
