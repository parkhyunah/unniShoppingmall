<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
   crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	var login = ${login};
	var delivertyState = '${delivertyState}';
	var clear="배송완료";
	function reviewCheck() {
      if (delivertyState==clear) {
      } else {
         $(".btnTd").html("");
      }
   }

   function reviewBtn(itemName,itemColor) {
      location.href = "unni?command=review_write_form&itemName="+itemName+"&itemColor="+itemColor;
      return true;
   }
   $(function() {
      reviewCheck();
   });
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY ORDER DETAIL</title>
</head>
<body>
<div id="wrap">
	<div id="navi"><jsp:include page="outline.jsp" /></div>
		<br><br>
		<div id="myOrderList">
			<h1>MY ORDER DETAIL</h1>
			<br><br>
			<form name="orderForm" action="unni" method="post">
				<table id="orderId">
					<tr>
						<th id="orderFormTh" colspan="1">IMG</th>
						<th id="orderFormTh" colspan="3">ITEM</th>
						<th id="orderFormTh" colspan="1">QTY</th>
						<th id="orderFormTh" colspan="2">COLOR</th>
						<th id="orderFormTh" colspan="2">PRICE</th>
						<th id="orderFormTh" colspan="1">REVIEW</th>
					</tr>

					<c:forEach var="order" items="${orderList}">
						<tr>
							<td colspan="1"><img id="itemPic" src="${order.itemMainPic}" width="70px"></td>
							<td colspan="3">${order.itemName}</td>
							<td colspan="1">${order.itemQty}</td>
							<td colspan="2">${order.itemColor}</td>
							<td colspan="2">${order.itemPrice}</td>
							<td class="btnTd" colspan="1">
							<input type="button" class="btn" onclick='return reviewBtn("${order.itemName}","${order.itemColor}")' value="Review"></td>
						</tr>
					</c:forEach>
					<tr>
						<th colspan="14"><b>상품 구매 금액 :
								${orderItem.orderTotalPrice-2500} + 배송비 2500 = 합계 :
								${orderItem.orderTotalPrice} </b></th>
					</tr>
				</table>
				<br><br>

				<p id="subject">MY INFO</p>
				<table id="orderInfo">
					<tr>
						<th>NAME</th>
						<td>${member.memberName}</td>
					</tr>
					<tr>
						<th>PHONE</th>
						<td>${member.memberPhoneNum}<br></td>
					</tr>
					<tr>
						<th>EMAIL</th>
						<td>${member.memberEmail}</td>
					</tr>
				</table>
				<br><br><br>

				<p id="subject">DELIVERY</p>
				<table id="orderInfo">
					<tr>
						<th>NAME</th>
						<td>${orderItem.memberName}</td>
					</tr>
					<tr>
						<th>PHONE</th>
						<td>${orderItem.orderPhoneNum}</td>
					</tr>
					<tr>
						<th>ADDRESS</th>
						<td>${orderItem.orderAddress}
					</tr>
				</table>
				<br><br><br>
				
				<p id="subject">PAYMENT</p>
				<table id="orderInfo">
					<tr>
						<th>TOTAL</th>
						<td>총 <b>${orderItem.orderTotalPrice}</b> 원
						</td>
					</tr>
					<tr>
						<th>PAYMENT<br>OPTION
						</th>
						<td>입금자명 : ${orderItem.paymentName}</td>
					</tr>
					<tr>
						<th>DELIVERY</th>
						<td>${orderItem.deliveryState}</td>
					</tr>
				</table>
				<br><br>
			</form>
			<div id="orderDetailbtn">
			<p>
				<input type="button" id="list" value="LIST" class="btn" onclick="location.href='unni?command=member_myorder_form'">
			</p><br><br><br><br>
			</div>
		</div>
	</div><br><br>
</body>
</html>