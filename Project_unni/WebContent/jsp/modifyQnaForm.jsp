<%-- Q&A 게시물 수정화면  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
	var login = ${login};
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">   
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<link rel="stylesheet" type="text/css" href="css/unni.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MODIFY Q &amp; A</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="qnaViewForm" align="center" style="padding-top: 50px">
		<div id="boardSection">
		<h1>MODIFY Q &amp; A</h1>
		<form name="etcModifyForm" action="unni" method="post">
			<input type="hidden" name="command" value="qna_modify"> 
			<input type="hidden" name="bNum" value="${qnaBoard.bNum}">
			<table>
				<tr>
					<th>NAME</th>
					<td><font color="#B40404"><b>${qnaBoard.memberId}</b></font></td>
				</tr>
				<tr>
					<th>TITLE</th>
					<td><select name="qnaTitle" style="width: 480px;">
							<option value="배송 : 질문입니다."> 배송 : 질문입니다. </option>
							<option value="상품 : 질문입니다."> 상품 : 질문입니다. </option>
							<option value="반품 : 질문입니다."> 반품 : 질문입니다. </option>
					</select></td>
				</tr>
				<tr>
					<th>CONTENT</th>
					<td><textarea rows="15" cols="70" name="qnaContent">${qnaBoard.boardContent}</textarea></td>
				</tr>

			</table>
			<p>
				<input type="submit" value="MODIFY" id="write" class="btn" onclick="return etcBoardModifyCheck()"> 
				<input type="button" value="CANCLE" id="cancle" class="btn" onclick="qnaCancleCheck()">
			</p>
		</form>
	</div>
	</div>
</body>
</html>