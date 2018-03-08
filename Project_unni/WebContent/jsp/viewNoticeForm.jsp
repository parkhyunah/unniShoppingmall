<%-- 공지 게시판 상세 페이지  --%>

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
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>NOTICE VIEW</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="boardSection" align="center">
			<br><br>
			<h1>NOTICE</h1>
			<br><br>
			<form>
				<input type="hidden" name="noticeNum" value="${param.num}">
				<table>
					<tr>
						<th>NO</th>
						<td>${noticeBoard.noticeNum}</td>
						<th>READ</th>
						<td>${noticeBoard.noticeReadCount}</td>
					</tr>
					<tr>
						<th>NAME</th>
						<td colspan="3"><font color="#B40404"><b>관리자</b></font></td>
					</tr>
					<tr>
						<th>TITLE</th>
						<td colspan="3">${noticeBoard.noticeTitle}</td>
					</tr>
					<tr>
						<td colspan="5" height="300" overflow="hidden"> ${noticeBoard.noticeContent}</td>
					</tr>
				</table>
				<br><br>
				<label id="noticeModifyBtn"> 
				<input type="button" value="MODIFY" class="btn" onclick="location.href='unni?command=notice_modify_form&noticeNum=${noticeBoard.noticeNum}'"></label>

				<label id="noticeDeleteBtn">
				<input type="button" value="DELETE" class="btn" onclick="location.href='unni?command=notice_delete&noticeNum=${noticeBoard.noticeNum}'"></label>

				<label id="noticeWriteBtn"> 
				<input type="button" value="WRITE" class="btn" onclick="location.href='unni?command=noticeWrite_form'"></label> 
				<input type="button" class="btn" value="LIST" onclick="location.href='unni?command=noticeList_form'">
			</form>
		</div>
	</div>
</body>
</html>