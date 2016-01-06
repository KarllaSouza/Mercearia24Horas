package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySQL implements IConexao {
	public static Connection conexao;
	
	@Override
	public synchronized Connection getConexao(String tipoBanco, String endereco, String nomeBanco,
			String nomeUsuario, String senha) throws SQLException {
		try {
			if(conexao == null) {
				DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conexao = DriverManager.getConnection(tipoBanco+"://"+endereco+"/"+ nomeBanco, nomeUsuario, senha);
			}
			return conexao;
		} catch(SQLException e) {
			throw new SQLException("Erro ao conectar com o banco de dados: "+e.getMessage());
		}
	}
}	
