package br.com.tridev.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.tridev.persistencia.entidades.Usuario;
import br.com.tridev.persistencia.jdbc.UsuarioDAO;

//http://localhost:8080/fabricaweb/usucontroller.do

@WebServlet("/usucontroller.do")
public class UsuarioController extends HttpServlet  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6426906037583511441L;

	
	
	public UsuarioController() {
		System.out.println("Novo Servlet!");
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init!");
		
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String acao = req.getParameter("acao");
		UsuarioDAO usuDAO = new UsuarioDAO();
		Usuario usu = new Usuario();
		resp.setContentType("text/html");
		if(acao.equals("excluir")){
			String id  = req.getParameter("id");
			if(id != null)usu.setId(Integer.parseInt(id));
			usuDAO.excluir(usu);
			resp.sendRedirect("usucontroller.do?acao=listar");
		}else if (acao.equals("listar")){
			List<Usuario> lista = usuDAO.buscaTodos();
			req.setAttribute("lista", lista);
			RequestDispatcher dispacher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			dispacher.forward(req, resp);
		}else if (acao.equals("alterar")){
			String id = req.getParameter("id");
			usu = usuDAO.buscaPorId(Integer.parseInt(id));
			req.setAttribute("usuario", usu);
			RequestDispatcher dispatcher =  req.getRequestDispatcher("WEB-INF/editausu.jsp");
			dispatcher.forward(req,resp);
		}else if(acao.equals("cadastrar")){
			usu.setId(0);
			usu.setLogin("");
			usu.setNome("");
			usu.setSenha("");
			req.setAttribute("usuario", usu);
			RequestDispatcher dispatcher =  req.getRequestDispatcher("WEB-INF/editausu.jsp");
			dispatcher.forward(req,resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id = req.getParameter("id");
		String nome = req.getParameter("nome");
		String login = req.getParameter("login");
		String senha= req.getParameter("senha");
		Usuario usu = new Usuario();
		UsuarioDAO usuDAO = new UsuarioDAO();
		if(id!=null) usu.setId(Integer.parseInt(id));
		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);
		usuDAO.salvar(usu);
		resp.getWriter().println("<b>Sucesso!</b>");;
		System.out.println("Chamou post!");
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy!");
		super.destroy();
	}
	
	
}
