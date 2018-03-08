<%-- 공지사항 게시물 수정 화면  --%>

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
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MODIFY NOTICE</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<br><br>
		<div id="noticeBoardSection" align="center">
		<h1>MODIFY NOTICE</h1><br><br>
		<form name="etcModifyForm" action="unni" method="post">
			<input type="hidden" name="command" value="notice_modify"> 
			<input type="hidden" name="noticeNum" value="${noticeBoard.noticeNum}">
			<table>
				<tr>
					<th id="modifyNoticeTh">NAME</th>
					<td><font color="#B40404"><b>관리자</b></font></td>
				</tr>
				<tr>
					<th id="modifyNoticeTh">TITLE</th>
					<td><input type="text" name="noticeTitle" value="${noticeBoard.noticeTitle}" size="69"></td>
				</tr>
				<tr>
					<th id="modifyNoticeTh">CONTENT</th>
					<td><textarea rows="15" cols="70" name="noticeContent">${noticeBoard.noticeContent}</textarea></td>
				</tr>

			</table>
			<p>
				<input type="submit" value="MODIFY" id="write" class="btn" onclick="return etcBoardModifyCheck()"> 
				<input type="button" value="CANCLE" id="cancle" class="btn" onclick="cancleCheck()">
			</p>
		</form>
	</div>
	</div>
</body>
</html>