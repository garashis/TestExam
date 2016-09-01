<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New/Edit Contact</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="//ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>


<!-- jQuery Form Validation code -->
<script>

	$(document).ready(function(){
		var onlyAlpha =  new RegExp('^[a-zA-Z]*$');
		//var ssnRegex =  new RegExp('^\d{3}-?\d{2}-?\d{4}$');
		var ssnRegex = /^([0-6]\d{2}|7[0-6]\d|77[0-2])([ \-]?)(\d{2})\2(\d{4})$/;

		//alert('paage loaled');
			$(document).on('submit','#contact-form',function(){
				//alert('form submit');
				if($('#firstName').val()==''){
					alert('Please provide value for First Name.');
					return false;
				}
				if(!onlyAlpha.test($('#firstName').val())){
					alert('First Name should contain only characters.');
					return false;
				}
				
				if($('#lastName').val()==''){
					alert('Please provide value for Last Name.');
					return false;
				}
				if(!onlyAlpha.test($('#lastName').val())){
					alert('Last Name should contain only characters.');
					return false;
				}
				
				if($('#dob').val()==''){
					alert('Please provide value for Date of Birth.');
					return false;
				}
				
				if($('#ssn').val()==''){
					alert('Please provide value for SSN.');
					return false;
				}
				if(!ssnRegex.test($('#ssn').val())){
					alert('Please provide value of SSN in xxx-xx-xxxx format.');
					return false;
				}
				
				if($('#street').val()==''){
					alert('Please provide value for Street.');
					return false;
				}
				
				if($('#city').val()==''){
					alert('Please provide value for City.');
					return false;
				}
				
				if($('#state').val()==''){
					alert('Please provide value for State.');
					return false;
				}
				
				if($('#zip').val()==''){
					alert('Please provide value for Zip.');
					return false;
				}
				
				
			});
	});
</script>




</head>
<body>
	<div align="center">
		<h1>New/Edit Contact</h1>
		<spring:url value="/users" var="userActionUrl" />
		<form:errors path="contactForm.*"></form:errors>
		<form:form modelAttribute="contactForm" method="POST" id="contact-form"
			action="addContact">
			<table>
				<form:hidden path="id" />
				<tr>
					<td><form:label path="firstName">First Name</form:label></td>
					<td><form:input path="firstName" /> <form:errors
							path="firstName" cssClass="error" /></td>
				</tr>
				<tr>
					<td><form:label path="lastName">Last Name</form:label></td>
					<td><form:input path="lastName" /> <form:errors
							path="lastName" cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="dob">Date of Birth(dd/MM/yyyy)</form:label></td>
					<td><form:input path="dob" /> <form:errors path="dob"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="ssn">SSN (xxx-xx-xxxx)</form:label></td>
					<td><form:input path="ssn" /> <form:errors path="ssn"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="street">Street</form:label></td>
					<td><form:input path="street" /> <form:errors path="street"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="city">City</form:label></td>
					<td><form:input path="city" /> <form:errors path="city"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="state">State</form:label></td>
					<td><form:input path="state" /> <form:errors path="state"
							cssClass="error" /></td>
				</tr>

				<tr>
					<td><form:label path="zip">Zip</form:label></td>
					<td><form:input path="zip" /> <form:errors path="zip"
							cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Save"></td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>