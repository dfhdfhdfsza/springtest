<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/board/modify" method="post">
		bno:<input type="text" name="bno" value="${bvo.bno}" readonly="readonly"> <br>
		title: <input type="text" name="title" value="${bvo.title}"> <br>
		writer: <input type="text" name="writer" value="${bvo.writer}" readonly="readonly"> <br>
		content: <input  name="content" value="${bvo.content}"></input> <br>
		<button type="submit">등록</button> <br>
	</form>
</body>
</html>