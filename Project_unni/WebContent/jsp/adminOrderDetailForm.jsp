<%-- admin orderList에서 orderNum을 누르면 넘어가는 페이지 >> 한 order에 대해 자세한 정보를 볼 수 있다. --%>

<%@page import="model.OrderItem"%>
<%@page import="java.util.List"%>
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
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Detail</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="adminOrderList">
		<br><br>
		<h2 align="center">ORDER DETAIL</h2>
		<br><br>
		<table id="memberList">
			<tr>
				<td colspan="8" align="right"
					style="font-weight: bolder; border: none;">Order Number :
					${orderNum }</td>
			</tr>
			<tr>
				<th>DELIVERY ADDRESS</th>
				<th>PHONE</th>
				<th>ITEM CODE</th>
				<th>QTY</th>
				<th>COLOR</th>
				<th>NAME</th>
				<th>PAYMENT NAME</th>
				<th>DATE</th>
			</tr>
			<%--게시글 목록 출력 : jstl로 이용--%>
			<c:forEach var="order" items="${orderDetail}">
				<tr>
					<td>${order.orderAddress}</td>
					<td>${order.orderPhoneNum}</td>
					<td>${order.itemCode}</td>
					<td>${order.itemQty}</td>
					<td>${order.itemColor}</td>
					<td>${order.memberName}</td>
					<td>${order.paymentName}</td>
					<td>${order.orderDate}</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="8" align="right"
					style="font-weight: bolder; border: none;">TOTAL :
					${orderTotal }</td>
			</tr>
		</table>
	</div>
</body>
</html>