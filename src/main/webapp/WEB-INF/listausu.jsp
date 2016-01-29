<%@page import="br.com.tridev.persistencia.entidades.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="CSS/estilo.css">
	<title>Lista de usuários</title>
	 
	<script type="text/javascript">
	function confirmaExclusao(id){
		if(window.confirm("Tem certeza que deseja exluir?")){
			location.href="usucontroller.do?acao=excluir&id="+id;			
		}
	}
	</script>
</head>
<body>
	<% 	List<Usuario> lista =  (List<Usuario>)request.getAttribute("lista"); %>
	<table>
		<tr>
		<th class="centro">Id</th>
		<th class="esquerda">Nome</th>
		<th class="centro" colspan="2">Ação</th>
		</tr>
	<% for(Usuario u :lista){ %>
		<tr>
		<td class="centro"><% out.print(u.getId()); %> </td>
		<td><%=u.getNome() %> </td>
		<td class="centro"><a href="javascript:confirmaExclusao(<%=u.getId()%>)">Excluir</a></td>
		<td class="centro"><a href="usucontroller.do?acao=alterar&id=<%=u.getId()%>">Editar</a></td>
		</tr>
	<% }%>	
	</table>	
</body>
</html>