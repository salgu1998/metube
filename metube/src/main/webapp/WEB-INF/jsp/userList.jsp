<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
</head>
<body>
<%@ include file="./header.jsp"%>

	<h3>user-list</h3>
	<c:forEach var="userList" items="${userList}">
		<div>
			pk: ${userList.pk }<br>
			이름: ${userList.name}<br>
			이메일: ${userList.email}<br>
			역할: ${userList.role}<br>
			lock: ${userList.lock}<br><br>
		</div>
	</c:forEach>
</body>
</html>
