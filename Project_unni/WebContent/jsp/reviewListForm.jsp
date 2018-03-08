<%-- 리뷰 게시판 목록 화면 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
var login = ${login};
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REVIEW LIST</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="section" align="center" style="padding-top: 50px;">
			<form action="unni" name="reviewForm" method="post">
				<input type="hidden" name="command" value="review_search_result">
				<h1>REVIEW</h1>
				<br>
				<br>
				<table class="list">
					<tr>
						<th id="reviewListTh">NO</th>
						<th id="reviewListTh">TITLE</th>
						<th id="reviewListTh">NAME</th>
						<th id="reviewListTh">DATE</th>
					</tr>

					<c:forEach var="reviewBoard" items="${viewData.boardList}">
						<tr>
							<td id="reviewListTd">${reviewBoard.bNum}</td>
							<td id="reviewListTd"><a href="unni?command=review_view&bNum=${reviewBoard.bNum}&bType=${reviewBoard.bType}">${reviewBoard.boardTitle}</a></td>
							<td id="reviewListTd"><font color="#B40404"><b>${reviewBoard.memberId}</b></font></td>
							<td id="reviewListTd">${reviewBoard.regDate}</td>
						</tr>
					</c:forEach>
				</table>
				<br>

				<c:if test="${viewData.startPage != 1 }">
					<a href="unni?command=reviewList_form&page=1">[처음]</a>
					<a href="unni?command=reviewList_form&page=${viewData.startPage-1}">[이전]</a>
				</c:if>
				<c:forEach begin="${viewData.startPage}"
					end="${viewData.endPage < viewData.pageTotalCount? viewData.endPage : viewData.pageTotalCount}" var="pageNum">
					<c:choose>
						<c:when test="${pageNum == viewData.currentPageNumber}">
							<b>[${pageNum }]</b>
						</c:when>
						<c:otherwise>
							<a href="unni?command=reviewList_form&page=${pageNum}">[${pageNum }]</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:if test="${viewData.endPage < viewData.pageTotalCount}">
					<a href="unni?command=reviewList_form&page=${viewData.endPage+1}">[다음]</a>
					<a href="unni?command=reviewList_form?page=${viewData.pageTotalCount}">[마지막]</a>
				</c:if>
				<br><br>
				
				<p>
					SEARCH <select name="searchCol">
						<option value="title">TITLE</option>
						<option value="content">CONTENT</option>
						<option value="name">NAME</option>
					</select> <input type="text" name="reviewSearchKeyword"
						id="reviewSearchKeyword"> <input type="submit" class="btn" value="SEARCH" onclick="return review_result_check()">
				</p>
			</form>
		</div>
	</div>
</body>
</html>