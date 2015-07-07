<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>List of students</title>
</head>
<body>
	<h1 align="center">List of students</h1>
	<table align="center" border="2px" width="350px">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Salary</th>
			<th>Action</th>
		</tr>
		<c:forEach items="${employees}" var="emp">
			<tr>
				<td><c:out value="${emp.id}" /></td>
				<td><c:out value="${emp.name}" /></td>
				<td><c:out value="${emp.salary}" /></td>
				<td><a
					href="<c:url value="/employees?edit=delete&id=${emp.id}"/>">Delete   </a>
					<a href="<c:url value="/employees?edit=update&id=${emp.id}"/>">Update</a></td>
			</tr>
		</c:forEach>
	</table>
	<br />
	<br />
	<h3 align="center">
		<a href="<c:url value="/employees?edit=add"/>">Add new employee</a>
	</h3>
	<br />
	<p align="center">
		<a href="<c:url value="/"/>">Back to the home page</a>
	</p>
</body>
</html>