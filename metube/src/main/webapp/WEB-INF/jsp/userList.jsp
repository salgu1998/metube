<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	
%>
<title>Insert title here</title>
</head>
<body>
<h1>hi spring</h1>
	<c:forEach var="user" items="${user}">
		<div>
			pk: ${user.pk }
			이름: ${user.name}
			이메일: ${user.email}
			암호: ${user.password}
			역할: ${user.role}
		</div>
	</c:forEach>
</body>
</html>
