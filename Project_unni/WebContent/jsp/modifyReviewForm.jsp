<%-- 리뷰 게시물 수정 화면  --%>

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
<title>MODIFY REVIEW</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap"><br><br>
		<div id="reviewModifySection" align="center"><br><br>
		<h1>MODIFY REVIEW</h1>
	<form name="etcModifyForm" action="unni" method="post">
	<input type="hidden" name="command" value="review_modify">
	<input type="hidden" name="bNum" value="${reviewBoard.bNum}">
        <table>
            <tr>
                <th id="modifyReviewTh">NAME</th>
                <td><font color="#B40404"><b>${reviewBoard.memberId}</b></font></td>
            </tr>
            <tr>
                <th id="modifyReviewTh">TITLE</th>
                <td><input type="text" name = "reviewTitle" value="${reviewBoard.boardTitle}" size="69"></td>
            </tr>
            <tr>
                <th id="modifyReviewTh">ITEM</th>
                <td>${reviewBoard.itemInfo }</td>
            </tr>
            <tr>
                <th id="modifyReviewTh">CONTENT</th>
                <td><textarea rows="15" cols="70" name="reviewContent">${reviewBoard.boardContent}</textarea></td>
            </tr>

        </table>
        <p>
            <input type="submit" value="MODIFY" class="btn" id="write" onclick="return etcBoardModifyCheck()">
            <input type="button" value="CANCLE" class="btn" id="cancle" onclick="reviewCancleCheck()">
        </p>
    </form>
</div>
</div>
</body>
</html>