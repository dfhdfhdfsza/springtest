<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
	<table class="table table-hover">
			<tr>
				<th>bno</th>
				<td>${bvo.bno }</td>
			</tr>
			<tr>
				<th>title</th>
				<td>${bvo.title }</td>
			</tr>
			<tr>
				<th>content</th>
				<td>${bvo.content }</td>
			</tr>
			<tr>
				<th>writer</th>
				<td>${bvo.writer }</td>
			</tr>
			<tr>
				<th>registerDate</th>
				<td>${bvo.registerDate }</td>
			</tr>
			<tr>
				<th>read_count</th>
				<td>${bvo.read_count }</td>
			</tr>
	</table>
	<a href="/board/modify?bno=${bvo.bno}">
		<button type="button">수정</button>
	</a>
	<a href="/board/remove?bno=${bvo.bno}">
		<button type="button">삭제</button>
	</a>
	<br>
	<!-- comment -->
	<div>
		<!-- 댓글작성 라인 -->
		<div>
			<span id="cmtWriter">${loginmvo.id}</span>
			<input type="text" id="cmtText" placeholder="Add Comment...">
			<button type="button" id="cmtPostBtn">댓글등록</button>
		</div>
		<!-- 댓글 표시 라인 -->
		<div>
			<ul id="cmtListArea">
				
			</ul>
		</div>
	</div>
	<script type="text/javascript">
		const bnoVal=`<c:out value="${bvo.bno}"/>`;
		console.log(bnoVal);
	</script>
	<script type="text/javascript" src="/resources/js/boardComment.js">
	</script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>