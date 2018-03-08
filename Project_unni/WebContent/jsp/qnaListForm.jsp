<%-- QnA 게시판 목록 화면 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
var login = ${login};
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q &amp; A LIST</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="section" align="center" style="padding-top: 50px;">
		<h1>Q &amp; A</h1>
			<form action="unni" name="qnaForm" method="post">
				<input type="hidden" name="command" value="qna_search_result">
				<br><br>
				<table class="list">
					<tr>
						<th id="qnaListTh">NO</th>
						<th id="qnaListTh">TITLE</th>
						<th id="qnaListTh">NAME</th>
						<th id="qnaListTh">DATE</th>
					</tr>

					<%-- 게시글 목록 출력 --%>
					<c:forEach var="qnaBoard" items="${viewData.boardList}">
						<tr>
							<td id="QnaListTd">${qnaBoard.bNum}</td>
							<td id="QnaListTd"><a href="unni?command=qna_view&bNum=${qnaBoard.bNum}&bType=${qnaBoard.bType}">${qnaBoard.boardTitle}</a></td>
							<td id="QnaListTd"><font color="#B40404"><b>${qnaBoard.memberId}</b></font></td>
							<td id="QnaListTd">${qnaBoard.regDate}</td>
						</tr>
					</c:forEach>
				</table>
				<br>

				<c:if test="${viewData.startPage != 1 }">
					<a href="unni?command=qnaList_form&page=1">[처음]</a>
					<a href="unni?command=qnaList_form&page=${viewData.startPage-1}">[이전]</a>
				</c:if>
				<c:forEach begin="${viewData.startPage}"
					end="${viewData.endPage < viewData.pageTotalCount? viewData.endPage : viewData.pageTotalCount}"
					var="pageNum">
					<c:choose>
						<c:when test="${pageNum == viewData.currentPageNumber}">
							<b>[${pageNum }]</b>
						</c:when>
						<c:otherwise>
							<a href="unni?command=qnaList_form&page=${pageNum}">[${pageNum }]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${viewData.endPage < viewData.pageTotalCount}">
					<a href="unni?command=qnaList_form&page=${viewData.endPage+1}">[다음]</a>
					<a href="unni?command=qnaList_form?page=${viewData.pageTotalCount}">[마지막]</a>
				</c:if>
				<br>
				<br>

				<p id="qnaWriteBtn">
					<input type="button" class="btn" value="Write" id="qnaWriteBtn" onclick="location.href='unni?command=qnaWrite_form'">
				</p>
				<br>

				<p id="search">SEARCH 
				<select name="searchCol">
					<option value="title">NAME</option>
				</select> <input type="text" name="qnaSearchKeyword" id="qnaSearchKeyword">
					<input type="submit" class="btn" value="SEARCH" onclick="return qna_result_check()">
				</p>
			</form>
		</div>
	</div>
</body>
</html>