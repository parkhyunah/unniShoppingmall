<%-- 회원의 네비게이션 바 메뉴 중  MYPAGE를 누르면 전환되는 페이지  --%>

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
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY PAGE</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<table id="myMenu">
			<tr>
				<td><img src="img/order.png" style="width: 80px" onclick="location.href = 'unni?command=member_myorder_form'"></td>
				<td></td>
				<td></td>
				<td><img src="img/cart.png" style="width: 80px"
					onclick="location.href = 'unni?command=outline_member_cart_form'"></td>
			</tr>
			<tr id="myMenuName">
				<td>ORDER</td>
				<td></td>
				<td></td>
				<td>CART</td>
			</tr>
			<tr style="height: 180px">
				<td></td>
				<td style="width: 180px"></td>
				<td></td>
				<td></td>
			</tr>
			<tr>
				<td><img src="img/point.png" style="width: 80px"></td>
				<td></td>
				<td></td>
				<td><img src="img/profile.png" style="width: 80px"
					onclick="location.href = 'unni?command=member_info_form'"></td>
			</tr>
			<tr id="myMenuName">
				<td>POINT<br>${member.memberPoint}</td>
				<td></td>
				<td></td>
				<td>INFO</td>
			</tr>
		</table>
	</div>
</body>
</html>