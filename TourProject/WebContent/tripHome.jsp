<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="com.training.entity.*"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
h3{
text-align:center;
}
#user{
text-align:right;
}
</style>
</head>
<body>
<h3>Travel Home Page</h3>
<span id="user">
<c:out value="Welcome ${sessionScope.user.firstName}" /></span><br><br>

<a href="ShowTrip">View All trip details</a><br><br>
<%
SignUp user=(SignUp)session.getAttribute("user");

if(user.getType().equalsIgnoreCase("employee")){     

%>
<a href="addTour.html">Add trip details</a>
<%} %>
</body>
</html>