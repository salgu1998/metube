<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<link rel="stylesheet" href="/resources/css/body-line.css" />
</head>
<body>
<%@ include file="header.jsp"%>
	<div class="content">
		<c:forEach var="postList" items="${postList}">
			<div class="oneContent">
				<c:if test="${postList.is_delete eq '0'}" >
					<a href="/post/detail/${postList.pk}">
						<p>[thumbnail]</p>
						<p>${postList.title}</p>
						<div class="small">
							<p>${postList.name }</p>
							<p>조회수 ${postList.view_count }회 • ${postList.create_at }</p>
						</div>
					</a>
				</c:if>
				
				<c:if test="${postList.is_delete eq '1'}" >
					<p>======================</p>
					<p>[ 관리자에 의해 삭제된 게시글 ]</p>
					<p>======================</p>
				</c:if>
			</div>
		</c:forEach>
	</div>
</body>
<script>
<%
	if(lock == 1) {
%>
		alert("잠금상태입니다.");
		
		const requestOptions = {
                method: "GET",
                headers: {
             	   "Content-Type": "application/json" 
                }
            };
         fetch("/user/logout", requestOptions)
			.then(res=>{ location.href="/"; })
		.catch(err => console.log(err))
<%
	}
%>
</script>
</html>