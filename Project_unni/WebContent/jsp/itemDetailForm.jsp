<%-- 상품 상세 페이지 : 장바구니, 구매 버튼이 있음 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
var login = ${login};
var itemPrice = ${boarditem.itemPrice};
setInterval(function(){
     $(".blinkEle").toggle();
   }, 350);

</script>
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/nanumgothic.css);

table {
   width: 100%;
   border-collapse: collapse;
   font-size: 15px;
   line-height: 24px;
   border: none;
   top: 100px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Item Detail</title>
</head>
<body>
   <div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="wrap">
      <div id="itemDetailForm" align="center">
         <form id="frm" name="frm" style="padding-left: 400px">
            <input type="hidden" name="command" value="member_cart_form">
            <input type="hidden" name="itemCode" value="${boarditem.itemCode}">
            <input type="hidden" name="numberOfItem" value="${numberOfItem}">
            <div id="mainPic" align="right">
               <img src="${boarditem.itemMainPic}" width="300">
            </div>
            <div id="detailForm">
               <table style="border: none;">
                  <tbody>
                     <tr>
                        <td colspan="2" height="55" align="center" id="itemName"><b>${boarditem.itemName}</b> <span class="blinkEle" style="color: red;">    ${sold }</span></td>
                     </tr>
                     <tr>
                        <th>PRICE</th>
                        <td height="40">${boarditem.itemPrice}</td>
                     </tr>
                     <tr>
                        <th>COLOR</th>
                        <td height="40"><select name="itemColor" style="width: 68">
                              <c:forEach var="i" items="${item}">
                                 <option value="${i.itemColor}">${i.itemColor}</option>
                              </c:forEach>
                        </select></td>
                     </tr>
                     <tr>
                        <th>QTY</th>
                        <td height="40"><input type="number" id="QTY" name="QTY" min="1" max="20" value="0"> 개</td>
                     </tr>
                  </tbody>
               </table>
               <br>
               <div class="TotalPrice" align="right">
                  TOTAL : <span class="TotalPrice" id="totalPrice" name="totalPrice"></span>
                  (<span class="TotalPrice" id="numberOfItem" name="numberOfItem"></span>개)
               </div>
               <br>
               <div id="tmp"></div>
               <div>
                  <div class="btn_con_small">
                     <input type="submit" class="btn" id="cartBtn" value="CART" onclick="return checkQTY()">
                  </div>
               </div>
            </div>

            <div id="detailPic" align="right">
               <table style="border: none;">
                  <tr>
                     <td colspan="4" align="center"><img src="${boarditem.itemDetailPic}"></td>
                  </tr>
               </table>
            </div>
         </form>
      </div>
   </div>
</body>
</html>