package DAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import BO.Produtos;
import Modelo.GerenciaDoSistema;
import Modelo.Palhaco;
import Modelo.Seguranca;
import view.CDUtils;



public class Funcionario {
	
	int id;
	String nome;
	static Scanner in = new Scanner(System.in);
	static int opcao=90;
	static Carrinho carrinho = new Carrinho();
	
	Funcionario(){
		
	}
	
	
	public static void menuDoFuncionario(Connection conexao) throws SQLException{
		Gerente.conexao = conexao;
		System.out.println("Bem vindo Funcionario, qual operação deseja fazer?");
			System.out.println("1 - Listar os produtos cadastrados.");
			System.out.println("2 - Colocar produto no carrinho");
			System.out.println("3 - Tirar item do carrinhho.");
			System.out.println("4 - Finalizar Compra.");
			System.out.println("5 - Chamar Segurança.");
			System.out.println("6 - Chamar o palhaço para distrair as crianças.");
			System.out.println("7 - Logout.");
			opcao = in.nextInt();
			switch(opcao){
				case 1:
					listarProdutos(2, conexao);
					break;
				case 2:
					colocarNoCarrinho(conexao, 2);
				case 3:
					tirarItemDoCarrinho(conexao, 2);
				case 4:
					finalizarCompra(conexao, 2);
				case 5:
					Seguranca.SerChamado(conexao, 2);
				case 6:
					Palhaco.DivertirAsCriancas(conexao, 2);
				case 7:
					logOut();
				default:
					menuDoFuncionario(conexao);
					break;
			}
	}
	
	
	
	public static void listarProdutos(int menu, java.sql.Connection conexao) throws SQLException {
		ResultSet rs;
		
		try {
			java.sql.PreparedStatement pstm = conexao.prepareStatement("select * from produtos");
			
			rs = pstm.executeQuery();
			System.out.println("id | nome | valor | quantidade em estoque");
			while(rs.next()){
				System.out.println(rs.getInt("id")+" "+rs.getString("name")+" "+rs.getDouble("valor")+" "+rs.getInt("estoque"));
			}
			
			
		} catch (SQLException e) {
			throw new SQLException("Erro ao listar produtos - "+e.getMessage());
		}
		if(menu==1){
			Gerente.menuDoGerente(conexao);
		}
		else if(menu==0){
			
		}
		else{
			menuDoFuncionario(conexao);
		}
		
	}
	
	static void logOut() throws SQLException{
		GerenciaDoSistema.telaBoasVindas();
	}
	
	static void colocarNoCarrinho(Connection conexao, int menu) throws SQLException {
		int idAAdicionar;
		int quantidadeAAdicionar;
		int escolha;
		listarProdutos(0, conexao);
		System.out.println("Qual o id do item que você deseja colocar no carrinho?");
		idAAdicionar = in.nextInt();
		System.out.println("Qual a quantidade do item que você deseja adicionar?");
		quantidadeAAdicionar = in.nextInt();
		ResultSet rs;
		try {
			java.sql.PreparedStatement pstm = conexao.prepareStatement("select * FROM produtos WHERE id='"+idAAdicionar+"'");
			rs = pstm.executeQuery();
			rs.next();
			Produtos produto = new Produtos(rs.getString("name"),rs.getDouble("valor"), quantidadeAAdicionar, rs.getShort("id"));
			if(quantidadeAAdicionar > rs.getInt("estoque")) {
				System.out.println("Não temos a quantidade desejada em estoque.");
			} else {
				carrinho.carrinho.add(produto);
				carrinho.valorTotal = carrinho.valorTotal + (quantidadeAAdicionar * rs.getDouble("valor"));
				Statement stmt = Gerente.conexao.createStatement();
				stmt.executeUpdate("UPDATE produtos SET estoque='"+(rs.getInt("estoque") - quantidadeAAdicionar)+"' WHERE id='"+idAAdicionar+"'");
			}
		} catch(SQLException e) {
			throw new SQLException("Erro ao adicionar no carrinho - "+e.getMessage());
		}
		System.out.println("Deseja adicionar mais produtos? 1 - sim, 0 - não");
		escolha = in.nextInt();
		if(escolha == 1) {
			colocarNoCarrinho(conexao, 0);
		} else {
			if(menu == 1) {
				Gerente.menuDoGerente(conexao);
			} else {
				menuDoFuncionario(conexao);
			}
		}
	}
	
	static void tirarItemDoCarrinho(Connection conexao, int menu) throws SQLException {
		int quantidadeAAdicionar;
		int escolha = 0;
		ResultSet rs;
		for (int i = 0; i < carrinho.carrinho.size(); i++) {
			System.out.println(carrinho.carrinho.get(i).nome + " " + carrinho.carrinho.get(i).estoque);
			System.out.println("Deseja remover esse item? (1 - sim, 2 - não)");
			escolha = in.nextInt();
			if(escolha == 1) {
				System.out.println("Qual a quantidade você deseja remover desse item?");
				quantidadeAAdicionar = in.nextInt();
				if(carrinho.carrinho.get(i).estoque - quantidadeAAdicionar < 0) {
					System.out.println("Não podemos ter items no carrinho com quantiade menor que 0.");
					break;
				}
				else {
					carrinho.valorTotal = carrinho.valorTotal - (quantidadeAAdicionar * carrinho.carrinho.get(i).valor);
					try {
						java.sql.PreparedStatement pstm  = conexao.prepareStatement("select * FROM produtos WHERE id='"+carrinho.carrinho.get(i).id+"'");
						rs = pstm.executeQuery();
						rs.next();
						quantidadeAAdicionar = quantidadeAAdicionar + rs.getInt("estoque");
						Statement stmt = Gerente.conexao.createStatement();
						stmt.executeUpdate("UPDATE produtos SET estoque='"+ quantidadeAAdicionar +"' WHERE id='"+carrinho.carrinho.get(i).id+"'");
					} catch(SQLException e) {
						throw new SQLException("Erro ao retirar item do carrinho - "+e.getMessage());
					}
				}
			}
		}
		ArrayList<Integer> index = new ArrayList<Integer>();
		for (int i = 0; i < carrinho.carrinho.size(); i++) {
			if(carrinho.carrinho.get(i).estoque == 0) {
				index.add(i);
			}
		}
		for (int i = 0; i < index.size(); i++) {
			carrinho.carrinho.remove(index.get(i));
		}
		
		System.out.println("Deseja remover mais produtos? 1 - sim, 0 - não");
		escolha = in.nextInt();
		if(escolha == 1) {
			tirarItemDoCarrinho(conexao, 0);
		} else {
			if(menu == 1) {
				Gerente.menuDoGerente(conexao);
			} else {
				menuDoFuncionario(conexao);
			}
		}
	}
	
	static void finalizarCompra(Connection conexao, int menu) throws SQLException {
		int escolha;
		ResultSet rs;
		int quantidade;
		System.out.println("Os seguintes produtos estão no carrinho:");
		for (int i = 0; i < carrinho.carrinho.size(); i++) {
			System.out.println(carrinho.carrinho.get(i).nome + " " + carrinho.carrinho.get(i).estoque);	
		}
		System.out.println("No total o valor é de: R$"+carrinho.valorTotal);
		System.out.println("Deseja pagar(1) ou cencelar a compra(2)?");
		escolha = in.nextInt();
		if(escolha == 1){
			String drive = null;
			CDUtils.open(drive);
			CDUtils.close(drive);
		} else {
			for (int i = 0; i < carrinho.carrinho.size(); i++) {
				try {
					java.sql.PreparedStatement pstm  = conexao.prepareStatement("select * FROM produtos WHERE id='"+carrinho.carrinho.get(i).id+"'");
					rs = pstm.executeQuery();
					rs.next();
					quantidade = carrinho.carrinho.get(i).estoque + rs.getInt("estoque");
					Statement stmt = Gerente.conexao.createStatement();
					stmt.executeUpdate("UPDATE produtos SET estoque='"+ quantidade +"' WHERE id='"+carrinho.carrinho.get(i).id+"'");
				} catch(SQLException e) {
					throw new SQLException("Erro ao adicionar quantidade - "+e.getMessage());
				}
			}
		}
		carrinho.carrinho.clear();
		System.out.println("Você não possui mais itens no carrinho!");
		if(menu == 1) {
			Gerente.menuDoGerente(conexao);
		} else {
			menuDoFuncionario(conexao);
		}
	}
}
