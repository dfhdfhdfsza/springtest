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
	
	<!-- 검색라인 -->
	
	<form action="/board/list" method="get">
		<div>
			<!-- c:set ->ph.pgvo.type를 typed라는 이름의 변수에 저장 -->
			<c:set value="${ph.pgvo.type}" var="typed"></c:set>
			<!-- 선택창 -->
			<!-- typed가 value값이랑 같으면 selected 구문 적용 -->	
			<!-- selected = 선택된상태 -->
			<select name="type">
				<option ${typed==null? 'selected':'' }>Choose...</option>
				<option value="t" ${typed eq 't' ? 'selected':'' }>Title</option>
				<option value="w" ${typed eq 'w' ? 'selected':'' }>writer</option>
				<option value="c" ${typed eq 'c' ? 'selected':'' }>contents</option>
				<option value="tw" ${typed eq 'tw' ? 'selected':'' }>Title+writer</option>
				<option value="tc" ${typed eq 'tc' ? 'selected':'' }>Title+contents</option>
				<option value="wc" ${typed eq 'wc' ? 'selected':'' }>writer+contents</option>
				<option value="twc" ${typed eq 'twc' ? 'selected':'' }>Title+writer+contents</option>
			</select>
			<input type="text" name="keyword" value="${ph.pgvo.keyword}"> <!-- 검색키워드 -->
			<input type="hidden" name="pageNo" value="${ph.pgvo.pageNo}"> <!-- pageNo를 전달해주기위한 hidden타입의 input -->
			<input type="hidden" name="qty" value="${ph.pgvo.qty}"> <!-- qty를 전달해주기위한 hidden타입의 input -->
			${ph.totalCount }
			<button type="submit" class="btn btn-outline-success">검색</button>
		</div>
	</form>
	
	<!-- 리스트 라인 -->
	<table border="1" style="text-align: center">
		<thead>
			<tr>
				<th>#</th>
				<th>제목</th>
				<th>댓글갯수</th>
				<th>파일갯수</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${list}" var="bvo">
				<tr>
					<td>${bvo.bno }</td>
					<td><a href="/board/detail?bno=${bvo.bno}">${bvo.title}</a></td>
					<td>${bvo.cmt_count}</td>
					<td>${bvo.file_count}</td>
					<td>${bvo.writer}</td>
					<td>${bvo.registerDate }</td>
					<td>${bvo.read_count }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<script type="text/javascript">
		const isOk=`<c:out value="${isOk}"/>`;
		console.log(isOk);
		if(isOk==1)
		{
			alert("삭제완료!");
		}
	</script>
	
	<!-- 페이징 라인 -->
	<c:if test="${ph.prev}">
		<a href="/board/list?pageNo=${ph.startPage-1}&qty=${ph.pgvo.qty}">◁ |</a>
	</c:if>
	<c:forEach begin="${ph.startPage}" end="${ph.endPage}" var="i">
		<a href="/board/list?pageNo=${i}&qty=${ph.pgvo.qty}">${i}</a>
	</c:forEach>
	<c:if test="${ph.next}">
		<a href="/board/list?pageNo=${ph.endPage+1}&qty=${ph.pgvo.qty}">| ▷</a>
	</c:if>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>