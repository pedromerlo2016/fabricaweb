package br.com.tridev;

import br.com.tridev.persistencia.entidades.Usuario;
import br.com.tridev.persistencia.jdbc.UsarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testExcluir();
		
	}
	
	public static void testAlterar(){
		//Alterar usuário
		Usuario us = new Usuario();
		us.setId(3);
		us.setNome("Pedro Merlo");
		us.setLogin("pedro");
		us.setSenha("123456");
		//Cadastrando usuário no banco de dados
		UsarioDAO usDAO = new UsarioDAO();
		usDAO.alterar(us);
		System.out.println("Alterado com sucesso.");
	}
	
	public static void testExcluir(){
		//Excluir usuário
		Usuario us = new Usuario();
		us.setId(5);
		//Cadastrando usuário no banco de dados
		UsarioDAO usDAO = new UsarioDAO();
		usDAO.excluir(us);
		System.out.println("Excluido com sucesso.");
	}
	
	public static void testCadastrar(){
		//Criar usuário
		Usuario us = new Usuario();
		us.setNome("Pedro");
		us.setLogin("pedro");
		us.setSenha("123456");
		//Cadastrando usuário no banco de dados
		UsarioDAO usDAO = new UsarioDAO();
		usDAO.cadastrar(us);
		System.out.println("Cadastrado com sucesso.");
		
		
	}
}
