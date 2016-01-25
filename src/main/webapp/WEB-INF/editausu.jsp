<%@page import="br.com.tridev.persistencia.entidades.Usuario"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	Usuario usuario =(Usuario)request.getAttribute("usuario");
%>
<form action="usucontroller.do" method="post">
<% if(usuario.getId()>0){%>
	Id:   <input type="number" name="id" value="<%=usuario.getId()%>"/>	
<%
} 
%>

Nome: <input type="text" name="nome" value="<%=usuario.getNome()%>"/>
Login: <input type="text" name="login" value="<%=usuario.getLogin()%>"/>
Senha: <input type="text" name="senha" value="<%=usuario.getSenha()%>"/>
<input type="submit" value="Salvar"/> 
</form>
</body>
</html>