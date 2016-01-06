package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import DAO.Funcionario;
import DAO.Gerente;
import view.ConexaoMySQL;

public class GerenciaDoSistema {
	static Scanner in = new Scanner(System.in);
	static Connection conexao;
	static int opcao;
	static boolean isGerente;
	public static void telaBoasVindas() throws SQLException{
		isGerente = false;
		String usuario;
		String senha;
		String senhaCorreta;
		
		System.out.println("Digite seu login:");
		usuario = in.next();
		System.out.println("Digite sua senha:");
		senha = in.next();
		
		senhaCorreta = getSenha(usuario);
		
		if(senhaCorreta == null) {
			System.out.println("Usuário inválido!");
			telaBoasVindas();
		} else if (!senhaCorreta.equals(senha)) {
			System.out.println("Senha inválida!");
			telaBoasVindas();
		} else {
			if(isGerente) {
				Gerente.menuDoGerente(conexao);
			} else {
				Funcionario.menuDoFuncionario(conexao);
			}
		}
	}
	public static void main(String[] args) throws SQLException {
		ConexaoMySQL conectar = new ConexaoMySQL();
		conectar.getConexao("jdbc:mysql", "localhost", "market", "root", "");
		conexao = ConexaoMySQL.conexao;
		telaBoasVindas();
	}
	public static String getSenha(String usuario) throws SQLException {
		ResultSet rs;
		String resultado;
		try {
			java.sql.PreparedStatement pstm = conexao.prepareStatement("select * from funcionario where login='"+usuario+"'");
			rs = pstm.executeQuery();
			
			if(!rs.next()) {
				resultado = null;
			} else {
				resultado = rs.getString("senha");
				isGerente = rs.getBoolean("isGerente");
			}
			return resultado;
		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir usuário - "+e.getMessage());
		}
	}
}
