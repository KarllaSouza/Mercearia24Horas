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
