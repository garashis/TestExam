<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
td, th {
	border: 1px solid black;
}
</style>


<title>Contact Manager Home</title>
</head>

<body>
	<div align="center">
		<h1>Contact List</h1>
		<h3>
			<a href="${pageContext.request.contextPath}/contact">Add Contact</a>
		</h3>
		<h3>
			<a href="${pageContext.request.contextPath}/logout"> Logout</a>
		</h3>
		

		<c:if test="${not empty contacts}">
			<table border="1">
			<tr>
					<th>Sr. No.</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Date of Birth</th>
					<th>SSN</th>
					<th>Street</th>
					<th>City</th>
					<th>State</th>
					<th>Zip Code</th>
					<th>Actions</th>
</tr>
				<c:forEach var="contact" items="${contacts}"  varStatus="status">
					<tr>
					<td>${status.index + 1}</td>
						<td>${contact.firstName}</td>
						<td>${contact.lastName}</td>
						<td>${contact.dob}</td>
						<td>${contact.ssn}</td>
						<td>${contact.street}</td>
						<td>${contact.city}</td>
						<td>${contact.state}</td>
						<td>${contact.zip}</td>
						<td>
						
						<a href="${pageContext.request.contextPath}/deleteContact?id=${contact.id}">Delete</a>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="${pageContext.request.contextPath}/updateContact?id=${contact.id}">Update</a></td>
					</tr>
				</c:forEach>
			</table>


		</c:if>
	</div>
</body>


</html>