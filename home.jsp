<%@ page language="java" contentType="text/html; charset=utf8"
	pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Home page</title>
</head>
<body>
	<h1 align="center">Welcome to home page application "Employee Manager"</h1>
	<br/>
	<br/>
	<h2 align="center"><a href='<c:url value="/employees"/>'>Click here! And you will to show list of employees</a></h2>
	<br/>
	<br/>
	<h3 align="center">Current time your visit is <c:out value="${date}"></c:out></h3>
</body>
</html>