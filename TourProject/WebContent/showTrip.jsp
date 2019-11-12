<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
table{
border:1px solid black;
}

</style>
</head>
<body>



<table border="1">
	<tr>
		<th>Code</th>
		<th>Tour Name</th>
		<th>Start Date</th>
		<th>End Date</th>
		<th>Places Covered</th>
		<th>Number of Days</th>
		</tr>
		<c:forEach var="eachtour" items="${requestScope.tourList}">
		<tr>
			<td><c:out value="${eachtour.code}" /></td>
			<td><c:out value="${eachtour.tourName}" /></td>
			<td><c:out value="${eachtour.startDate}" /></td>
			<td><c:out value="${eachtour.endDate}" /></td>
			<td><c:out value="${eachtour.places}" /></td>
			<td><c:out value="${eachtour.numberOfDays}" /></td>
		</tr>
		</c:forEach>
</table>

</body>
</html>