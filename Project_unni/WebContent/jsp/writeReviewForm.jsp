<%-- REVIEW 게시판 작성 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>REVIEW WRITE</title>
</head>
<div id="navi"><jsp:include page="outline.jsp" /></div>
<br><br>
<div id="wrap">
	<div id="boardWriteSection" align="center">
		<h1>REVIEW WRITE</h1><br><br><br>
		<form name="etcForm" action="unni" method="post">
			<input type="hidden" name="command" value="review_write">
			<table>
				<tr>
					<th id="reviewWriteTh">NAME</th>
					<td><font color="#B40404"><b>${member}</b></font></td>
				</tr>
				<tr>
					<th id="reviewWriteTh">TITLE</th>
					<td><input type="text" name="reviewTitle" value="" size="69"></td>
				</tr>
				<tr>
					<th id="reviewWriteTh">ITEM</th>
					<td><input type="text" name="item" value="Item : ${param.itemName} & Color : ${param.itemColor}" size="69" readonly></td>
				</tr>
				<tr>
					<th id="reviewWriteTh">CONTENT</th>
					<td><textarea rows="15" cols="70" name="reviewContent">
* 리뷰는 50자 이상 작성해주셔야 적립금 500원을 적립해드립니다. *
----------------------------------------------------------------
상세 사이즈를 입력해주세요.
키 : 
상의 사이즈 : 
하의 사이즈 : </textarea></td>
				</tr>

			</table>
			<p>
				<input type="submit" value="WRITE" id="write" class="btn" onclick="return reviewWriteCheck()"> 
				<input type="button" value="CANCLE" id="cancle" class="btn" onclick="reviewCancleCheck()">
			</p>
		</form>
	</div>
</div>
</body>
</html>