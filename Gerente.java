package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import Modelo.Palhaco;
import Modelo.Seguranca;

public class Gerente extends Funcionario{
	static Connection conexao; 
		
	public static void menuDoGerente(Connection conexao) throws SQLException{
		Gerente.conexao = conexao;
		System.out.println("Bem vindo Gerente, qual operação deseja fazer?");
			System.out.println("1 - Cadastrar novo funcionario.");
			System.out.println("2 - Listar todos os funcionarios cadastrados.");
			System.out.println("3 - Excluir um funcionario.");
			System.out.println("4 - Cadastrar um novo produto.");
			System.out.println("5 - Listar os produtos existentes.");
			System.out.println("6 - Deletar um produto.");
			System.out.println("7 - Adicionar estoque ao produto.");
			System.out.println("8 - Colocar produto no carrinho.");
			System.out.println("9 - Tirar item do carrinhho.");
			System.out.println("10 - Finalizar compra.");
			System.out.println("11 - Chamar segurança.");
			System.out.println("12 - Chamar o palhaço para distrair as crianças.");
			System.out.println("13 - Logout.");
			opcao = in.nextInt();
			switch(opcao){
				case 1:
					cadastrarFuncionario();
					break;
				case 2:
					listarFuncionarios(true);
				case 3:
					excluirFuncionario();
				case 4:
					cadastrarProduto();
				case 5:
					Funcionario.listarProdutos(1, conexao);
				case 6:
					excluirProduto();
				case 7:
					adicionarAoEstoque();
				case 8:
					colocarNoCarrinho(conexao, 1);
				case 9:
					tirarItemDoCarrinho(conexao, 1);
				case 10:
					finalizarCompra(conexao, 1);
				case 11:
					Seguranca.SerChamado(conexao, 1);
				case 12:
					Palhaco.DivertirAsCriancas(conexao, 1);
				case 13:
					Funcionario.logOut();
				default:
					menuDoGerente(conexao);
					break;
			}
	}
	
	private static void cadastrarFuncionario() throws SQLException {
		String nome;
		String senha;
		String login;
		int isGerente;
		System.out.println("Insira seu nome");
		nome = in.next();
		System.out.println("Insira seu login");
		login = in.next();
		System.out.println("Insira seu senha");
		senha = in.next();
		System.out.println("Digite 1 se o usuario for um gerente 0 se ele não for um gerente.");
		isGerente = in.nextInt();
		
		java.sql.Statement stmt;
		try {
			stmt = Gerente.conexao.createStatement();
			stmt.executeUpdate("INSERT INTO funcionario(name,login,senha,isGerente) VALUES('"+nome+"','"+login+"','"+senha+"','"+isGerente+"')");
		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir usuário - "+e.getMessage());
		}
		menuDoGerente(conexao);
	}
	
	private static void listarFuncionarios(boolean menu) throws SQLException {
		ResultSet rs;
		
		try {
			java.sql.PreparedStatement pstm = conexao.prepareStatement("select * from funcionario");
			rs = pstm.executeQuery();
			System.out.println("id | nome | Gerente");
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getInt("isGerente"));
			}
		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir usuário - "+e.getMessage());
		}
		if(menu){
			menuDoGerente(conexao);
		}
	}
	
	private static void excluirFuncionario() throws SQLException {
			java.sql.Statement stmt;
			listarFuncionarios(false);
			int idASerDeletado;
			System.out.println("Qual id do funcionario que vc deseja deletar");
			idASerDeletado = in.nextInt();
			try {
				stmt = Gerente.conexao.createStatement();
				stmt.executeUpdate("DELETE FROM funcionario WHERE id='"+idASerDeletado+"'");
			} catch(SQLException e) {
				throw new SQLException("Erro ao excluir usuário - "+e.getMessage());
			}
	}
	private static void cadastrarProduto() throws SQLException {
		String nome;
		double valor;
		int estoque;
		
		System.out.println("Insira o produto");
		nome = in.next();
		System.out.println("Insira o valor unitario do produto");
		valor = in.nextDouble();
		System.out.println("Insira a quantidade de unidades do produto");
		estoque = in.nextInt();
		
		java.sql.Statement stmt;
		try {
			stmt = Gerente.conexao.createStatement();
			stmt.executeUpdate("INSERT INTO produtos(name,valor,estoque) VALUES('"+nome+"','"+valor+"','"+estoque+"')");
		} catch (SQLException e) {
			throw new SQLException("Erro ao inserir usuário - "+e.getMessage());
		}
		menuDoGerente(conexao);
	}
	
	private static void excluirProduto() throws SQLException {
		java.sql.Statement stmt;
		listarProdutos(0, conexao);
		int idASerDeletado;
		System.out.println("Qual id do produto que vc deseja deletar");
		idASerDeletado = in.nextInt();
		try {
			stmt = Gerente.conexao.createStatement();
			stmt.executeUpdate("DELETE FROM produtos WHERE id='"+idASerDeletado+"'");
		} catch(SQLException e) {
			throw new SQLException("Erro ao excluir produto - "+e.getMessage());
		}
		menuDoGerente(conexao);
	}
	private static void adicionarAoEstoque() throws SQLException {
		java.sql.Statement stmt;
		ResultSet rs;
		listarProdutos(0, conexao);
		int idASerAdicionado;
		int quantidadeASerAdicionada;
		System.out.println("Qual id do produto que vc deseja adicionar unidades ao estoque");
		idASerAdicionado = in.nextInt();
		System.out.println("Quantas unidades você quer adicionar");
		quantidadeASerAdicionada = in.nextInt();
		try {
			stmt = Gerente.conexao.createStatement();
			java.sql.PreparedStatement pstm = conexao.prepareStatement("select estoque FROM	produtos WHERE id='"+idASerAdicionado+"'");
			rs = pstm.executeQuery();
			rs.next();
			quantidadeASerAdicionada = quantidadeASerAdicionada + rs.getInt("estoque");
			stmt.executeUpdate("UPDATE produtos SET estoque='"+quantidadeASerAdicionada+"' WHERE id='"+idASerAdicionado+"'");
		} catch(SQLException e) {
			throw new SQLException("Erro ao adicionar quantidade - "+e.getMessage());
		}
		menuDoGerente(conexao);
	}
	
	
}
