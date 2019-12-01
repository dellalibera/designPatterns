<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>HomePage</title>
	</head>
	<body>
		<h1>Welcome</h1>
		<a href="jsp/login.jsp">Login</a><br>
		<a href="jsp/register.jsp">Register</a><br>
		<a href="jsp/search.jsp">Search</a>	
	</body>
	
	<% 	String message = (String) request.getAttribute("message"); 
		if(message != null){
	%>
		<p style="color:green;">Message: <%= message %></p>
	<%
		}
	%>
</html>