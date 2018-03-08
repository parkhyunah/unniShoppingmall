<%-- 공지 게시판 작성 페이지 --%>

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Notice Write</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<br><br>
		<div id="noticeBoardSection" align="center">
			<h1>NOTICE WRITE</h1>
			<br><br>
			<form name="etcfrm" action="unni" method="post">
				<input type="hidden" name="command" value="notice_write"> 
				<input type="hidden" name="adminId" value="${adminId}">
				<table>
					<tr>
						<th id="noticeWriteTh">NAME</th>
						<td><font color="#B40404"><b>관리자</b></font></td>
					</tr>
					<tr>
						<th id="noticeWriteTh">TITLE</th>
						<td><input type="text" name="noticeTitle" value="✔ " size="67"></td>
					</tr>
					<tr>
						<th id="noticeWriteTh">CONTENT</th>
						<td><textarea rows="15" cols="70" name="noticeContent"></textarea></td>
					</tr>
				</table>
				<p>
					<input type="submit" value="WRITE" id="write" class="btn" onclick="return boardWriteCheck()"> 
					<input type="button" value="CANCLE" id="cancle" class="btn" onclick="cancleCheck()">
				</p>
			</form>
		</div>
	</div>
</body>
</html>