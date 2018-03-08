<%-- 회원이 자기 자신의 주문 내역을 조회하는 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
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
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY ORDER</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="myOrderList" align="center">
   <br><br><h1>MY ORDER</h1><br><br>
      <table id="memberList" class="myOrderList">
         <tr>
            <th id="memberMyOrderTh">ORDERNUM</th>
            <th id="memberMyOrderTh">PHONE</th>
            <th id="memberMyOrderTh">TOTAL</th>
            <th id="memberMyOrderTh">DELIVERY</th>
         </tr>
         <c:forEach var="order" items="${orderList}">
            <tr>
            <td><a href="unni?command=member_myOrder_detail&orderNum=${order.orderNum}">${order.orderNum}</a></td>
            <td>${order.orderPhoneNum }</td>
            <td>${order.orderTotalPrice }</td>   
            <td><b>${order.deliveryState }</b></td>
            </tr>
         </c:forEach>
      </table>
   </div>
</body>
</html>