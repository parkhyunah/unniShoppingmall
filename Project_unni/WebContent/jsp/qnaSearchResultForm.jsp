<%-- qna 게시판 검색 결과 화면 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<title>Q &amp; A RESULT</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
	<form action="unni" name="qnaForm" method="post">
	<div id="section" align="center" style="padding-top: 50px;">
		<input type="hidden" name="command" value="qna_search_result">
			<h1>Q &amp; A SEARCH</h1><br><br>
			<table class="list">
				<tr>
					<th id="qnaListTh">NO</th>
					<th id="qnaListTh">TITLE</th>
					<th id="qnaListTh">NAME</th>
					<th id="qnaListTh">DATE</th>
				</tr>

				<c:forEach var="qnaResultList" items="${viewData.boardList}">
					<tr>
						<td id="qnaSearchResultTd">${qnaResultList.bNum}</td>
						<td id="qnaSearchResultTd"><a href="unni?command=qna_view&bNum=${qnaResultList.bNum}&bType=${qnaResultList.bType}">${qnaResultList.boardTitle}</a></td>
						<td id="qnaSearchResultTd"><font color="#B40404"><b>${qnaResultList.memberId}</b></font></td>
						<td id="qnaSearchResultTd">${qnaResultList.regDate}</td>
					</tr>
				</c:forEach>

			</table><br>

			<c:if test="${viewData.startPage != 1 }">
				<a href="unni?command=qna_search_result&page=1">[처음]</a>
				<a href="unni?command=qna_search_result&page=${viewData.startPage-1}">[이전]</a>
			</c:if>
			<c:forEach begin="${viewData.startPage}" end="${viewData.endPage < viewData.pageTotalCount? viewData.endPage : viewData.pageTotalCount}"
				var="pageNum">
				<c:choose>
					<c:when test="${pageNum == viewData.currentPageNumber}">
						<b>[${pageNum }]</b>
					</c:when>
					<c:otherwise>
						<a
							href="unni?command=qna_search_result&page=${pageNum}&qnaSearchKeyword=${qnaSerachKeyword}">[${pageNum }]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:if test="${viewData.endPage < viewData.pageTotalCount}">
				<a href="unni?command=qna_search_result=${viewData.endPage+1}">[다음]</a>
				<a href="unni?command=qna_search_result=${viewData.pageTotalCount}">[마지막]</a>
			</c:if>
			<p id="qnaBoardWriteBtn">
				<input type="button" value="Write" id="qnaWriteBtn" class="btn" onclick="location.href='unni?command=qnaWrite_form'">
			</p>

			<p id="search">
				SEARCH <select name="searchCol">
					<option value="title">NAME</option>
				</select> <input type="text" name="qnaSearchKeyword" id="qnaSearchKeyword">
				<input type="submit" value="Search" class="btn" onclick="return qna_result_check()">
			</p>
		</div>
	</form>
</div>
</body>
</html>