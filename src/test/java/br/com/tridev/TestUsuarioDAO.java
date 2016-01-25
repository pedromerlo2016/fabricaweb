package br.com.tridev;

import java.util.List;

import br.com.tridev.persistencia.entidades.Usuario;
import br.com.tridev.persistencia.jdbc.UsuarioDAO;

public class TestUsuarioDAO {

	public static void main(String[] args) {
		testAutenticar();
		
	}
	
	public static void testAlterar(){
		//Alterar usuário
		Usuario us = new Usuario();
		us.setId(3);
		us.setNome("Pedro Merlo");
		us.setLogin("pedro");
		us.setSenha("123456");
		//Cadastrando usuário no banco de dados
		UsuarioDAO usDAO = new UsuarioDAO();
		usDAO.alterar(us);
		System.out.println("Alterado com sucesso.");
	}
	
	public static void testExcluir(){
		//Excluir usuário
		Usuario us = new Usuario();
		us.setId(5);
		//Cadastrando usuário no banco de dados
		UsuarioDAO usDAO = new UsuarioDAO();
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
		UsuarioDAO usDAO = new UsuarioDAO();
		usDAO.cadastrar(us);
		System.out.println("Cadastrado com sucesso.");
	}
	
	public static void testSalvar(){
		Usuario us = new Usuario();
		//us.setId(3);
		us.setNome("Pedro AAAAA");
		us.setLogin("pedro");
		us.setSenha("12345");
		UsuarioDAO usuDAO = new UsuarioDAO();
		usuDAO.salvar(us);
		System.out.println("Salvo com sucesso.");
	}
	
	public static void testBuscaPorId(){
		Usuario us = new Usuario();
		UsuarioDAO usDao = new UsuarioDAO();
		us = usDao.buscaPorId(3);
		if(us!=null){
			System.out.println(us.toString());
		}else{
			System.out.println("Usuário não cadastrado.");
		}
	}
	
	public static void testeBuscaTodos(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		List<Usuario> lista = usuDAO.buscaTodos();
		for (Usuario usuario : lista) {
			System.out.println(usuario.toString());
		}
	}
	
	public static void testAutenticar(){
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario u = new Usuario();
		u.setLogin("pedro");
		u.setSenha("12345");
		Usuario retorno =usuDAO.autenticar(u); 
		if(retorno!=null){
			System.out.println(retorno.toString());
			
		}else{
			System.out.println("Falha na autenticação.");
		}
	}
}
