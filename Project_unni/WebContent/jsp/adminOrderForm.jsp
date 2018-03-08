<%-- 관리자가 조회하는 회원 전체 주문 내역 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
	var login = ${login};
</script>
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order List</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="adminOrderList">
			<br><br>
			<h1 align="center">ORDER LIST</h1>
			<br><br>
			<table id="memberList">
				<tr>
					<th id="adminOrderListTh">ORDERNUM</th>
					<th id="adminOrderListTh">ID</th>
					<th id="adminOrderListTh">MEMBER</th>
					<th id="adminOrderListTh">PHONE</th>
					<th id="adminOrderListTh">TOTAL</th>
					<th id="adminOrderListTh">DELIVERY</th>
					<th id="adminOrderListTh">DELIVERY STATE</th>
				</tr>
				<c:forEach var="order" items="${orderList}">
					<tr>
						<td><a href="unni?command=admin_order_detail_form&orderNum=${order.orderNum}">${order.orderNum}</a></td>
						<td>${order.memberId}</td>
						<td>${order.memberName}</td>
						<td>${order.orderPhoneNum}</td>
						<td>${order.orderTotalPrice}</td>
						<td>${order.deliveryState}</td>
						<td>
							<form action="unni" method="post">
								<input type="hidden" name="command" value="delivery_modify">
								<input type="hidden" name="orderNum" value="${order.orderNum}">
								<select name="deliveryState"><option value="배송전">배송전</option>
									<option value="배송중">배송중</option>
									<option value="배송완료">배송완료</option></select>
									<input type="submit" class="btn" value="MODIFY">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>