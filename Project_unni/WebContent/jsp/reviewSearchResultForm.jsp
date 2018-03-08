<%-- 리뷰 게시판 검색 결과 목록 화면 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript">
var login = ${login};
</script>
<script type="text/javascript" src="script/unni.js"></script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REVIEW SEARCH</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
	<form action="unni" name="reviewForm" method="post">
		<div id="section" align="center" style="padding-top: 50px;">
		<input type="hidden" name="command" value="review_search_result">

			<h1>REVIEW SEARCH</h1><br><br>
			<table class="list">
				<tr>
					<th>NO</th>
					<th>TITLE</th>
					<th>NAME</th>
					<th>DATE</th>
				</tr>

				<c:forEach var="reviewResultList" items="${reviewSearchResult}">
					<tr>
						<td id="reviewSearchResultTd">${reviewResultList.bNum}</td>
						<td id="reviewSearchResultTd"><a href="unni?command=review_view&bNum=${reviewResultList.bNum}&bType=${reviewResultList.bType}">${reviewResultList.boardTitle}</a></td>
						<td id="reviewSearchResultTd"><font color="#B40404"><b>${reviewResultList.memberId}</b></font></td>
						<td id="reviewSearchResultTd">${reviewResultList.regDate}</td>
					</tr>
				</c:forEach>
			</table><br><br>
			<p>
				SEARCH <select name="searchCol">
					<option value="title">TITLE</option>
					<option value="content">CONTENT</option>
					<option value="name">NAME</option>
				</select> <input type="text" name="reviewSearchKeyword" id="reviewSearchKeyword"> 
				<input type="submit" value="Search" class="btn" onclick="return review_result_check()">
			</p>
		</div>
	</form>
	</div>
</body>
</html>