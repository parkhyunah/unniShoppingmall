<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript">
	var login = ${login};

	function orderChecked() {
		if (document.orderForm.deliveryName.value.length == 0) {
			alert("이름을 입력하세요.");
			return false;
		}
		if (document.orderForm.deliveryPhoneNum.value.length == 0) {
			alert("전화번호를 입력하세요.");
			return false;
		}
		if (document.orderForm.memberAddress.value.length == 0
				&& document.orderForm.address.value.length == 0
				&& document.orderForm.addressdetail.value.length == 0) {
			alert("주소를 입력하세요.");
			return false;
		}
		if (document.orderForm.paymentName.value.length == 0) {
			alert("입금자명을 입력하세요.");
			return false;
		}

		return true;
	}

	$(function() {
		$("#check").click(function() {
			$("#deliveryName").val("${memberInfo.memberName}");
			$("#deliveryPhoneNum").val("${memberInfo.memberPhoneNum}");
			$("#memberAddress").val("${memberAddress[0]}");
			$("#address").val("${memberAddress[1]}");
			$("#addressdetail").val("${memberAddress[2]}");
		});

		$("#myPoint").on("blur", function() {
			var total = ${totalPrice+2500};

			var memberPoint = ${memberInfo.memberPoint};
			var myPoint = document.getElementById("myPoint").value;

			if (myPoint > memberPoint) {
				alert("적립금이 부족합니다.");
				document.getElementById("myPoint").value = "";

			} else {
				$("#point").text(memberPoint - myPoint);
				$("#total").val(total - myPoint);
			}

		});
	});
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<style type="text/css">
input {
	font-size: 12px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ORDER</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
<div id="wrap">
    <div id="memberOrderSection">
	<br><br><h1>ORDER</h1><br><br>
	<form name="orderForm" action="unni" method="post">
	<input type="hidden" name="command" value="member_order">
	        <table id="orderId">
	            <tr>
	                <th id="memberOrderTh" colspan="1">IMG</th>
	                <th id="memberOrderTh" colspan="3">ITEM</th>
	                <th id="memberOrderTh" colspan="1">QTY</th>
	                <th id="memberOrderTh" colspan="2">COLOR</th>
	                <th id="memberOrderTh" colspan="2">PRICE</th>
	                <th id="memberOrderTh" colspan="2">CHARGE</th>
	                <th id="memberOrderTh" colspan="2">TOTAL</th>
	            </tr>
	
	
	        <c:forEach var="cart" items="${cartInfo}">
	            <tr>
	                <td colspan="1"><img src="${cart.itemMainPic}" width="80px"></td>
	                <td colspan="3">${cart.itemName}</td>
	                <td colspan="1">${cart.itemQty}</td>
	                <td colspan="2">${cart.itemColor}</td>
	                <td colspan="2">${cart.itemPrice}</td>
	                <td colspan="2">2500</td>
	                <td colspan="2">${cart.itemTotal}</td>
	            </tr>
	        </c:forEach>
	            <tr>
	                <td colspan="14"><span style="color:black;font-weight: bolder;">
	                <b>PRICE : ${totalPrice} + FEE : 2500 = TOTAL : <font color="#B40404">${totalPrice+2500}</font></b></span></td>    
	            </tr>
	        </table> <br><br>
	
	        <p id="orderSubject">MY INFO</p>
	        <table id = "orderInfo">
	            <tr>
	                <th>NAME</th>
	                <td>${memberInfo.memberName}</td>
	            </tr>
	            <tr>    
	                <th>PHONE</th>
	                <td>${memberInfo.memberPhoneNum}</td>
	            </tr>
	            <tr>    
	                <th>EMAIL</th>
	                <td>${memberInfo.memberEmail}</td>
	            </tr>
	        </table> <br><br>
	        <p id="orderSubject">DELIVERY</p>
	        <p id="orderSubjectSame"><input type="checkbox" id="check" name="check">
	        <font size="3px" id="same" name="same">&nbsp;&nbsp;주문 정보가 같습니다.</font></p><br>
	        <table id = "orderInfo">
	            <tr>
	                <th>NAME</th>
	                <td><input type="text" id="deliveryName" name="deliveryName"></td>
	            </tr>
	            <tr>    
	                <th>PHONE</th>
	                <td colspan="2"><input type="text" id="deliveryPhoneNum" name="deliveryPhoneNum"> 
	                <b>주문관련 개인정보가 문자로 발송됩니다.</b></td>
	            </tr>
	            <tr>    
	                <th>ADDRESS</th>
	                <td><input type="text" id="memberAddress" name="memberAddress">
	                <input type="button" class="btn" onclick="execDaumPostcode()" value="SEARCH"><br>
	
	                <input type="text" id="address" name="address" size=70> 기본주소<br>
	                <input type="text" id="addressdetail" name="addressdetail" size=70> 나머지주소</td>
	            </tr>
	        </table>
	
	        <br><br>
	        <p id="orderSubject">PAYMENT</p>
	        <table id = "orderInfo">
	            <tr>
	                <th>TOTAL</th>
	                <td><input type="text" name="total" id="total" value="${totalPrice+2500}" readonly></td>
	            </tr>
	            <tr>    
	                <th>POINT</th>
	                <td><input type="text" value="0" id="myPoint" name="myPoint">
	                 원 (총 사용 가능 적립금 : <b><label id="point">${memberInfo.memberPoint}</label></b>원)<br>
	                - 적립금은 최소 0원 이상일 때 결제가 가능합니다.<br>
	                - 최대 사용 금액은 제한이 없습니다.</td>
	            </tr>
	            <tr>    
	                <th>PAYMENT<br>OPTION</th>
	                <td>입금자명 : <input type="text" name="paymentName" id="paymentName"></td>
	            </tr>
	
	        </table>        
			<br>
	        <p id="orderAgree"><input type="checkbox" id="orderCheckbox" name="orderCheckbox">
	        <img src="img/ico_required.gif" alt="필수">&nbsp;결제정보에 동의합니다.</p><br>
	        
	        <div id="orderBtn">
	        <input type="submit" class="btn" id="orderBtn" value="ORDER" onclick="return orderChecked()">
	        <input type="reset" class="btn" value="RESET">
	        </div><br><br>
	    </form>
    </div>
</div>
</body>
</html>