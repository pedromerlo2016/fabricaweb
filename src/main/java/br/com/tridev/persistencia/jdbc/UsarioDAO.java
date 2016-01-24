package br.com.tridev.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.tridev.persistencia.entidades.Usuario;

public class UsarioDAO {

	private Connection con = ConexaoFactory.getConnection(); 
	public void cadastrar(Usuario us) {
		String sql = "insert into usuario(nome,login, senha) values(?,?,?)";
		try {
			//Criando um Statement
			PreparedStatement preparador =  con.prepareStatement(sql);
			preparador.setString(1, us.getNome());
			preparador.setString(2, us.getLogin());
			preparador.setString(3, us.getSenha());
			//Executa o comando SQL no banco
			preparador.execute();
			//Fecha o objeto preparador
			preparador.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
	}
	
	public void alterar(Usuario us){
		String sql = "update usuario set (nome =?,login=?, senha=?) where id=?";
		try {
			//Criando um Statement
			PreparedStatement preparador =  con.prepareStatement(sql);
			preparador.setString(1, us.getNome());
			preparador.setString(2, us.getLogin());
			preparador.setString(3, us.getSenha());
			preparador.setInt(4, us.getId());
			//Executa o comando SQL no banco
			preparador.execute();
			//Fecha o objeto preparador
			preparador.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		
	}

	public void excluir(Usuario us) {
		String sql = "delete from usuario where id=?";
		try {
			//Criando um Statement
			PreparedStatement preparador =  con.prepareStatement(sql);
			preparador.setInt(1, us.getId());
			//Executa o comando SQL no banco
			preparador.execute();
			//Fecha o objeto preparador
			preparador.close();
		} catch (SQLException e) {
			// 
			e.printStackTrace();
		}
		
	}
	
}
