<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../layout/header.jsp"></jsp:include>
	<form action="/member/update" method="post">
		id:<input type="text" name="id" value="${loginmvo.id}"> <br>
		pw:<input type="text" name="pw"> <br>
		name: <input type="text" name="name" value="${loginmvo.name}"> <br>
		email: <input type="text" name="email" value="${loginmvo.email}"> <br>
		home: <input  type="text" name="home" value="${loginmvo.home}"></input> <br>
		age: <input type="text" name="age" value="${loginmvo.age}"></input> <br>
		<button type="submit">등록</button> <br>
	</form>
	
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>