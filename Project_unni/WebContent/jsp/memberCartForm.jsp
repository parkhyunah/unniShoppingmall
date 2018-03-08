<%-- 회원 장바구니 내역 --%>

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
	var cartstate = 0;
  	function createList(){
  		
 		var table = $("#listTable");
 		$("tr:gt(0)",table).remove();
 		$.ajax({
			url : "unni?command=member_cart_makeList",
			type : "post",
			data : "memberId="+"${memberId}",
			dataType : "json",
			success : function(data){
				if(data.length ==0){
					cartstate = 1;
				}
				var total = 0;
					//tr요소를 만들어서 객체에 저장
				var trr = $("<tr>");
				for(var i in data){
					
					var deleteBtn = $("<input type = 'button' value = 'DELETE' class='btn'>");
					//var updateBtn = $("<input type = 'number' value = 'EDIT' min='1'>");
					var inputQty = $('<input type="number" size="1" value=' + data[i].itemQty +'>');
					
					var tr = $("<tr>");
 					
 					$("<td>").html('<a href="unni?command=item_detail_form&itemCode='+data[i].itemCode+'"><img src="' + data[i].itemMainPic + '" width="70px" /></a>').appendTo(tr);	//appendTo - 하위요소에서 상위요소를 붙이는 것
 					$("<td>").text(data[i].itemName).appendTo(tr);
 					$("<td>").text(data[i].itemPrice).appendTo(tr);
 					$("<td>").html(inputQty).appendTo(tr);   
 					//$("<td>").append(updateBtn).appendTo(tr);
 					$("<td>").text(data[i].itemColor).appendTo(tr);
//  					$("<td>").text("-").appendTo(tr);
 					$("<td>").text(data[i].itemTotal).appendTo(tr);
 					//btn을 td에 붙이고, btn이 붙이있는 td를 다시 tr에 붙임
 					$("<td>").append(deleteBtn).appendTo(tr);	//append - 상위에서 하위요소로 갖다붙임
 					//$('<td colspan="14">').html('<b>상품 구매 금액 : '+ total +'+ 배송비 : 2500 = 합계 : '+ total+2500 +' </b>').appendTo(tr);
 					tr.appendTo(table);
 					
					(function(e){
 						var memberId = data[e].memberID;
						var memberItemColor = data[e].itemColor;
						var memberItemCode = data[e].itemCode;
						deleteBtn.on("click",function(){
	 						$.ajax({
	 							url : "unni?command=member_cart_delete",
	 							data : {
	 								itemColor : memberItemColor, 
	 								itemCode : memberItemCode,
	 								memberId : memberId
	 							}, 
	 							type : "post",
	 							dataType : "json",
	 							success : function(data){
	 								createList();
	 							}
	 						});		
						});
					})(i);
					
 					(function(e){
 						var memberId = data[e].memberID;
						var memberItemColor = data[e].itemColor;
						var memberItemCode = data[e].itemCode;
						var itemPrice = data[e].itemPrice;
						var inputQtytmp = inputQty;
						total += data[e].itemTotal;
						inputQty.on("blur",function(){
	 						$.ajax({
	 							url : "unni?command=member_cart_update",
	 							data : {
	 								itemColor : memberItemColor, 
	 								itemCode : memberItemCode,
	 								memberId : memberId,
	 								itemPrice : itemPrice,
	 								itemQty : inputQtytmp.val()
	 							}, 
	 							type : "post",
	 							dataType : "json",
	 							success : function(data){
	 								createList();
	 							}
	 						});		
						});
					})(i);
 					
 					
				}
				var totalPrice = total+2500;
					$('<td colspan="14">').html('<span style="color:black;"><br><br><b>PRICE &nbsp;&nbsp;'+ total +'&nbsp;&nbsp;&nbsp; +  &nbsp;&nbsp;&nbsp;FEE &nbsp;&nbsp;2500 &nbsp;&nbsp;= &nbsp;&nbsp;&nbsp;TOTAL&nbsp;&nbsp;'+ totalPrice +'</span>').appendTo(trr);
					trr.appendTo(table);
			}
		});
	}
  	
   $(function(){
	   createList();
   });
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<style type="text/css">
table td {
	text-align: center;
}

input {
	text-align: center;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>CART</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="wrap">
   <div id="section" align="center" style="padding-top: 50px;">
      <h1>CART</h1><br>
      <form name="orderForm" action="unni" method="post">
      	<input type="hidden" name="command" value="member_order_form">
        <table id="listTable">
		<colgroup>
			<col width = "150px">
			<col width = "220px">
			<col width = "150px">
			<col width = "150px">
			<col width = "150px">
			<col width = "150px">
		</colgroup>
		<tr>
			<th id="memberCartTh">IMG</th>
			<th id="memberCartTh">ITEM</th>
			<th id="memberCartTh">PRICE</th>
			<th id="memberCartTh">QTY</th>
			<th id="memberCartTh">COLOR</th>
			<th id="memberCartTh">TOTAL</th>
			<th id="memberCartTh">DELETE</th>
		</tr>
	</table><br><br>
      <input type="submit" class="btn" onclick="return checkCart()" value="ORDER">
      <input type="button" class="btn" onclick ="history.go(-2);" value="GO BACK">
      </form>
   </div>
   </div>
</body>
</html>
