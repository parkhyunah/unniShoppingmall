<%-- Q&A 게시판 작성 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Q &amp; A WRITE</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<br><br>
		<div id="boardWriteSection" align="center">
			<h1>Q &amp; A WRITE</h1><br><br>
			<form name="etcForm" action="unni" method="post">
				<input type="hidden" name="command" value="qna_write">
				<table>
					<tr>
						<th id="qnaWriteTh">NAME</th>
						<td><font color="#B40404"><b>${member}</b></font></td>
					</tr>
					<tr>
						<th id="qnaWriteTh">TITLE</th>
						<td><select name="qnaTitle" style="width: 480px;">
								<option value="배송 : 질문입니다."> 배송 : 질문입니다. </option>
								<option value="상품 : 질문입니다."> 상품 : 질문입니다. </option>
								<option value="반품 : 질문입니다."> 반품 : 질문입니다. </option>
						</select></td>
					</tr>
					<tr>
						<th id="qnaWriteTh">CONTENT</th>
						<td><textarea rows="15" cols="70" name="qnaContent"></textarea></td>
					</tr>
				</table>
				<p>
					<input type="submit" value="WRITE" id="write" class="btn" onclick="return boardWriteCheck()"> 
					<input type="button" value="CANCLE" id="cancle" class="btn" onclick="reviewCancleCheck()">
				</p>
			</form>
		</div>
	</div>
</body>
</html>