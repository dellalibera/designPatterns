<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page 
	import="model.User"
	import="model.Address"
	import="java.util.*"
%>

<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Search</title>
	</head>
	<body>
		<h2>Search</h2>
		<form name="frm" method="post" action="/designPatterns/FrontController">
			Name: <input type="text" name="name" >
			<input type="submit" name="command" value="Search">
		</form>
		<form name="frm" method="post" action="/designPatterns/FrontController">
			Name: <input type="text" name="name" >
			<input type="submit" name="command" value="Search">
		</form>
		<form name="frm" method="post" action="/designPatterns/FrontController">
			BestFriend: <input type="text" name="bestFriend">
			<input type="submit" name="command" value="Search">
		</form>
		<a href="register.jsp">Register</a><br>
		Go back to <a href="../index.jsp">homepage</a>.
		<% 	String message = (String) request.getAttribute("message"); 
			if(message != null){
		%>
			<p style="color:green;">Message: <%= message %></p>
		<%
			}
		%>		
		
		<%
			ArrayList<User> users = (ArrayList<User>) request.getAttribute("users");
			
			if(users != null){
				for(User user : users) {
				%>
					<p>ID: <%=user.getId() %>, Name: <%=user.getName() %>, Address: <%=user.getAddress() %>, Best Friend: <%=user.getBestFriend() %></p>
				<%
				}
			}
			
		%>
	</body>
</html>