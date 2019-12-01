<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Register</title>
	</head>
		
	<body>
		<h2>Register</h2>
		<form name="frm" method="post" action="/designPatterns/FrontController">
			ID: <input type="text" name="id" ><br>
			Name: <input type="text" name="name"><br>
			Password: <input type="text" name="pwd"><br>
			Address: 	<input type="text" name="street" placeholder="street">
						<input type="text" name="postCode" placeholder="postCode">
						<input type="text" name="city" placeholder="city"><br>
			Best Friend: <input type="text" name="bestFriend"><br>
			<input type="submit" name="command" value="Register">
		</form>
		Go back to <a href="../index.jsp">homepage</a>.
		
		<% 	String message = (String) request.getAttribute("message"); 
			if(message != null){
		%>
			<p style="color:green;">Message: <%= message %></p>
		<%
			}
		%>
	</body>
</html>