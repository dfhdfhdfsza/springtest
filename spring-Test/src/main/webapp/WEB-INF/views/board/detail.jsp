<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../layout/header.jsp"></jsp:include>
<c:set value="${boardDTO.bvo}" var="bvo"></c:set>
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
	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
		getCommentList(bnoVal);
	</script>
	
	<!-- 파일 표시 영역 -->
	<!-- <resources  mapping="/upload/**" location="file:///D:\_mywebspring\_java\fileupload\"/> -->
	<c:set value="${boardDTO.flist}" var="flist"></c:set>
	
	<div>
		<ul>
			<!-- 파일 개수만큼 li를 추가하여 파일을 표시 타입이 1일경무만 표시 -->
			<!-- 
				li
				div => img 그림표시
				div=> div 파일이름,작성일자 span 크기 설정-->
				<!-- 하나의 파일만 따와서 fvo로 저장 -->
				<c:forEach items="${flist}" var="fvo">
					<li>
						<c:choose>
							<c:when test="${fvo.file_type>0}">
								<div>
								<!-- /upload/year/month/day/uuid_file_name -->
									<img alt="그림없음" src="/upload/${fn:replace(fvo.save_dir,'\\','/')}/${fvo.uuid}_th_${fvo.file_name}">
								</div>
							</c:when>
							<c:otherwise>
								<div>
									<!-- file 아이콘 같은 모양 값으로 넣을 수 잇음. -->
								</div>
							</c:otherwise>
						</c:choose>
						<div>
							<div>${fvo.file_name}</div>
							${fvo.reg_date}
						</div>
						<span>${fvo.file_size}Byte</span>
					</li>
				</c:forEach>
		</ul>
	</div>
	
	<jsp:include page="../layout/footer.jsp"></jsp:include>
</body>
</html>