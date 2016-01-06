package Modelo;

import java.sql.SQLException;

import DAO.Funcionario;

import DAO.Gerente;

public class Palhaco {
	public static void DivertirAsCriancas(java.sql.Connection conexao, int menu) throws SQLException{
		System.out.println("As Crianças estão distraidas, os pais podem ir fazer as compras.");
		if(menu==1){
			Gerente.menuDoGerente(conexao);
		}
		else{
			Funcionario.menuDoFuncionario(conexao);
		}
	}
}
