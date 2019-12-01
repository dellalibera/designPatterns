<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page 
import="model.User"
import="model.Address"
%>

<%
User user = (User) request.getAttribute("user");
Address address = (Address) request.getAttribute("address");
String[] addr = address.getFullAddress().split(",");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>UserPage</title>
	</head>
	<body>
		<h2>Welcome : <%= user.getName() %></h2>
		<h3>Your profile</h3>
		<form name="frm" method="post" action="/designPatterns/FrontController">
			ID: <%= user.getId() %> <br>
			Name: <input type="text" name="name" value="<%= user.getName() %>"><br>
			Password: <input type="text" name="pwd" value="<%= user.getPwd() %>"><br>
			Address: <input type="text" name="street" placeholder="street" value="<%= addr[0] %>"><input type="text" name="postCode" placeholder="postCode" value="<%= addr[1] %>"><input type="text" name="city" placeholder="city" value="<%= addr[2] %>"><br>
			Best Friend: <input type="text" name="bestFriend" value="<%= user.getBestFriend() %>"><br>
			<input type="submit" name="command" value="Update">
		</form>
		<a href="jsp/search.jsp">Search</a><br>
		<a href="jsp/index.jsp">HomePage</a>
	</body>
</html>