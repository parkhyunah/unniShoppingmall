<%-- REVIEW 게시판 상세 페이지  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
   var login = '${login}';
   var reviewId = '${reviewBoard.memberId}';
   var memberId = '${memberId}';
  
$(function() { 
	  if(reviewId != memberId){
	   	 $("#reviewModifyBtn").html("");
	   	 $("#reviewDeleteBtn").html("");
	   }
   });
</script>
<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REVIEW VIEW</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="boardSection" align="center">
			<br><br>
			<h1>REVIEW</h1>
			<br><br>
				<form>
					<input type="hidden" name="noticeNum" value="${param.num}">
					<table>
						<tr>
							<th id="reviewViewTh">NO</th>
							<td>${reviewBoard.bNum}</td>
							<th id="reviewViewTh">NAME</th>
							<td colspan="3"><font color="#B40404"><b>${reviewBoard.memberId}</b></font></td>
						</tr>

						<tr>
							<th id="reviewViewTh">TITLE</th>
							<td colspan="3">${reviewBoard.boardTitle}</td>
						</tr>

						<tr>
							<th id="reviewViewTh">ITEM</th>
							<td colspan="3">${reviewBoard.itemInfo}</td>
						</tr>
						<tr>
							<td colspan="5" height="300" overflow="hidden">${reviewBoard.boardContent}</td>
						</tr>
					</table>
					<br><br> 
					<label id="reviewModifyBtn"> 
					<input type="button" value="MODIFY" class="btn"
						onclick="location.href='unni?command=review_modify_form&bNum=${reviewBoard.bNum}'"></label>

					<label id="reviewDeleteBtn">
					<input type="button" value="DELETE" class="btn"
						onclick="location.href='unni?command=review_delete&bNum=${reviewBoard.bNum}'"></label>

					<input type="button" class="btn" value="LIST" onclick="location.href='unni?command=reviewList_form'">
				</form>
			</div>
		</div>
</body>
</html>