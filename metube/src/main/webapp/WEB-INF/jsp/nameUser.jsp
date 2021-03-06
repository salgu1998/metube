<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/getUserPage.css" />
<link rel="stylesheet" href="/resources/css/header.css" />
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<title>MeTube</title>
</head>
<body>
	<div class="header">
		<%@ include file="./header.jsp"%>
	</div>
	<center>
		<c:if test="${userInfo eq null}">
			<h2>해당 이름을 가진 사용자가 없습니다.</h2>
		</c:if>

		<c:forEach var="userInfo" items="${userInfo}">
			<table class="type09">
				<thead>
					<tr>
						<th scope="cols">계정 정보</th>
						<th scope="cols">내용</th>
						<th scope="cols">
							<div id="app">
								<button @click="lock(${userInfo.pk}, ${userInfo.lock })">계정
									잠금</button>
							</div>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">pk</th>
						<td>${userInfo.pk}</td>
					</tr>
					<tr>
						<th scope="row">email</th>
						<td>${userInfo.email}</td>
					</tr>
					<tr>
						<th scope="row">name</th>
						<td>${userInfo.name}</td>
					</tr>
					<tr>
						<th scope="row">role</th>
						<td>
							<c:if test="${userInfo.role eq '1'}">
								Guest
							</c:if> 
							<c:if test="${userInfo.role eq '2'}">
								User
							</c:if> 
							<c:if test="${userInfo.role eq '3'}">
								Admin
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row">lock</th>
						<td><c:if test="${userInfo.lock eq '0'}">
						정상
					</c:if> <c:if test="${userInfo.lock eq '1'}">
						잠금
					</c:if></td>
					</tr>
				</tbody>
			</table>
		</c:forEach>
	</center>
</body>
<script src="/resources/js/userLock.js"></script>
</html>