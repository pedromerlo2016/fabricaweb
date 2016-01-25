package br.com.tridev.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.tridev.persistencia.entidades.Usuario;

public class UsuarioDAO {

	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario us) {
		String sql = "insert into usuario(nome,login, senha) values(?,?,?)";
		try {
			// Criando um Statement
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, us.getNome());
			preparador.setString(2, us.getLogin());
			preparador.setString(3, us.getSenha());
			// Executa o comando SQL no banco
			preparador.execute();
			// Fecha o objeto preparador
			preparador.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}
	}

	public void alterar(Usuario us) {
		String sql = "update usuario set nome =?,login=?, senha=? where id=?";
		try {
			// Criando um Statement
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setString(1, us.getNome());
			preparador.setString(2, us.getLogin());
			preparador.setString(3, us.getSenha());
			preparador.setInt(4, us.getId());
			// Executa o comando SQL no banco
			preparador.execute();
			// Fecha o objeto preparador
			preparador.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}

	}

	public void excluir(Usuario us) {
		String sql = "delete from usuario where id=?";
		try {
			// Criando um Statement
			PreparedStatement preparador = con.prepareStatement(sql);
			preparador.setInt(1, us.getId());
			// Executa o comando SQL no banco
			preparador.execute();
			// Fecha o objeto preparador
			preparador.close();
		} catch (SQLException e) {
			//
			e.printStackTrace();
		}

	}

	public void salvar(Usuario us) {
		if (us.getId() != null && us.getId()!=0) {
			alterar(us);
		} else {
			cadastrar(us);
		}
	}

	/**
	 * Busca de um registro no banco de dados pelo numero do id do usuário
	 * 
	 * @param id
	 *            Inteiro que representa o numero do id do usuário a ser buscado
	 * @return um usuário quando encontra ou null quando não encontra
	 */
	public Usuario buscaPorId(Integer id) {
		Usuario usuRetorno = null;
		String sql = "select * from usuario where id = ?";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setInt(1, id);
			ResultSet resultado = preparador.executeQuery();
			if (resultado.next()) {
				usuRetorno = new Usuario();
				usuRetorno.setId(resultado.getInt("id"));
				usuRetorno.setNome(resultado.getString("nome"));
				usuRetorno.setLogin(resultado.getString("login"));
				usuRetorno.setSenha(resultado.getString("senha"));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return usuRetorno;
	}

	/**
	 * Realiza a busca de todos os usuário na tabela de usuários
	 * 
	 * @return Uma lista de objetos Usuario contendo 0 elementos quando estiver
	 *         sem registro ou com os elementos registrados
	 */
	public List<Usuario> buscaTodos() {
		List<Usuario> lista = new ArrayList<Usuario>();
		String sql = "select * from usuario";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			ResultSet resultado = preparador.executeQuery();
			while (resultado.next()) {
				Usuario us = new Usuario();
				us.setId(resultado.getInt("id"));
				us.setNome(resultado.getString("nome"));
				us.setLogin(resultado.getString("login"));
				us.setSenha(resultado.getString("senha"));
				lista.add(us);
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return lista;
	}

	/**
	 * Autentica um usuário cadastrado através do login e da senha fornecida.
	 * @param usu Usuário a ser autenticado.
	 * @return O objeto Usuario se for autenticado ou null se não for. 
	 */
	public Usuario autenticar(Usuario usu) {
		Usuario u = new Usuario();
		String sql = "Select * from usuario where login=? and senha =?";
		try (PreparedStatement preparador = con.prepareStatement(sql)) {
			preparador.setString(1, usu.getLogin());
			preparador.setString(2, usu.getSenha());
			ResultSet r = preparador.executeQuery();
			if (r.next()) {
				u.setNome(r.getString("nome"));
				u.setId(r.getInt("id"));
				u.setLogin(r.getString("login"));
				u.setSenha(r.getString("senha"));
				return u;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return null;
	}
}
