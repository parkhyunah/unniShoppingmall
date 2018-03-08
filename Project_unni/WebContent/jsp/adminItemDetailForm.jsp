<%-- 관리자 상품 게시판 상세 페이지 : 관리자만 볼수있는 상품 게시글 상세 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

tr {
	height: 40px;

}

td {
	width: 500px;
}

</style>
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>admin / Item Detail</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="adminItemDetailForm" align="center">
		<br><br>
		<h1>Item Detail</h1>
		<br><br>
		<input type="button" value="MODIFY" class="btn"
			onclick="location.href='unni?command=admin_update_item_form&num=${itemBoard.itemCode}'">
		<input type="button" value="DELETE" class="btn"
			onclick="location.href='unni?command=admin_delete_item&num=${itemBoard.itemCode}'">
		<input type="button" value="LIST" class="btn"
			onclick="location.href='unni?command=admin_item_form'"> 
		<input type="button" value="WRITE" class="btn"
			onclick="location.href='unni?command=admin_write_item_form'"><br><br>
		<table style="width: 80%;">
			<tr>
				<th id="adminItemDetailTh">ITEM CODE</th>
				<td>${itemBoard.itemCode}</td>
			</tr>
			<tr>
				<th id="adminItemDetailTh">ITEM NAME</th>
				<td>${itemBoard.itemName}</td>
				<th id="adminItemDetailTh">PRICE</th>
				<td>${itemBoard.itemPrice}</td>
			</tr>
			<tr>
				<th id="adminItemDetailTh">QTY</th>
				<td>${itemBoard.itemStock}</td>
				<th id="adminItemDetailTh">TYPE</th>
				<td>${itemBoard.itemType}</td>
			</tr>
			<tr>
				<th id="adminItemDetailTh">MAIN</th>
				<td id="itemMainPic" colspan="3"><img src="${itemBoard.itemMainPic}" width="300px"></td>
			</tr>
			<tr>
				<th id="adminItemDetailTh">DETAIL</th>
				<td id="itemDetailPic" colspan="3"><img src="${itemBoard.itemDetailPic}" width="300px"></td>
			</tr>
		</table>
	</div>
</body>
</html>