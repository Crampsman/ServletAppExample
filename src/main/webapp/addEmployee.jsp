<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Add new student</title>
</head>
<body>
	<h1 align="center">Add new student</h1>
	<form method="POST">
		<table align="center"  width="250px">
			<tr>
				<td><input type="hidden" name="id" value="<c:out value="${employee.id}"/>"></td>
			</tr>
			<tr>
				<td>Name<input type="text" name="name"  value="<c:out value="${employee.name}"/>"></td>
			</tr>
			<tr>
				<td>Salary<input type="text" name="salary"  value="<c:out value="${employee.salary}"/>"></td>
			</tr>
			<tr>
				<td><input type="submit" name="send"></td>
				<td><input type="reset" name="reset"></td>
			</tr>
		</table>
	</form>
</body>
</html>