<%-- 관리자가 상품 게시글 작성하는 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
	var login = ${login};
</script>
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two"
	rel="stylesheet">
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Item / admin</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="adminNewItemSection">
		<br><br>
		<h1>New Item</h1>
		<br>
		<form action="unni" method="post" name="frm" id="itemForm" enctype="multipart/form-data">
			<input type="hidden" name="command" value="admin_write_item">
			<table id="adminNewItemTable" border="1">
				<tr>
					<th id="adminWriteItemTh">CODE</th>
					<td style="padding-left: 8px"><input type="text" name="itemCode" size="68"></td>
				</tr>
				<tr>
					<th id="adminWriteItemTh">NAME</th>
					<td style="padding-left: 8px"><input type="text" name="itemName" size="68"></td>
				</tr>
				<tr>
					<th id="adminWriteItemTh">PRICE</th>
					<td style="padding-left: 8px"><input type="text" name="itemPrice" size="68"></td>
				</tr>
				<tr>
					<th id="adminWriteItemTh">STOCK</th>
					<td style="padding-left: 8px"><input type="text" name="itemStock" size="68"></td>
				</tr>
				<tr>
					<th id="adminWriteItemTh">TYPE</th>
					<td style="padding-left: 8px"><select name="itemType" style="width: 68">
							<option value="1">TOP</option>
							<option value="2">BOTTOM</option>
					</select>
					</td>
				</tr>
				<tr>
					<th id="adminWriteItemTh">MAIN</th>
					<td style="padding-left: 8px"><input type="file" name="itemMainPic" size="50"></td>
				</tr>
				<tr>
					<th id="adminWriteItemTh">DETAIL</th>
					<td style="padding-left: 8px"><input type="file" name="itemDetailPic" size="50"></td>
				</tr>
			</table>
			<br><br>
			<div align="center">
				<p class="joinBtn">
					<input type="submit" class="btn" value="WRITE"> 
					<input type="reset" class="btn" value="RESET"> 
					<input type="button" class="btn" value="LIST" onclick="location.href='unni?command=admin_item_form'">
				</p>
			</div>
		</form>
	</div>
</body>
</html>