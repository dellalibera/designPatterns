<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
	</head>
	<body>
		<h2>Login</h2>
		<form name="frm" method="post" action="/designPatterns/FrontController">
			ID: <input type="text" name="id" ><br>
			Password: <input type="password" name="pwd"><br>
			<input type="submit" name="command" value="Login">
		</form>
	</body>
	<a href="register.jsp">Register</a>
	
	<% 	String message = (String) request.getAttribute("message"); 
		if(message != null){
	%>
		<p style="color:green;">Message: <%= message %></p>
	<%
		}
	%>
</html>