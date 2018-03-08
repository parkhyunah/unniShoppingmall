<%-- 관리자가 상품 게시글 수정하는 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
   var login = ${login};
   var itemType = ${itemBoard.itemType};
   $(function(){
      if(itemType==1){
         $('[name="itemType"]').html("<option value='1' selected='selected'>TOP</option><option value='2'>BOTTOM</option>");
      }else if(itemType==2){
         $('[name="itemType"]').html("<option value='1'>TOP</option><option value='2' selected='selected'>BOTTOM</option>");
      }
   });
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<style type="text/css">
tr {
   height: 50px;
}

select, input {
   border: none;
   margin-left: 15px;
}
</style>
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify Item / admin</title>
</head>
<div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="adminItemDetailModifyForm">
      <br><br>
      <h1>Modify Item</h1><br><br>
      <form action="unni" method="post" name="frm" id="itemForm" enctype="multipart/form-data">
         <input type="hidden" name="command" value="admin_update_item">
         <table id="adminItemDetailModifyTb" border="1">
            <tr>
               <th id="adminModifyItemTh">CODE</th>
               <td id="adminModifyItemTd"><input type="text" name="itemCode" size="5" 
               value="${itemBoard.itemCode }" readonly="readonly"> <b>수정 불가</b></td>
            </tr>
            <tr>
               <th id="adminModifyItemTh">NAME</th>
               <td id="adminModifyItemTd"><input type="text" name="itemName" size="68" 
               value="${itemBoard.itemName }"></td>
            </tr>
            <tr>
               <th id="adminModifyItemTh">PRICE</th>
               <td id="adminModifyItemTd"><input type="text" name="itemPrice" size="68" 
               value="${itemBoard.itemPrice }"></td>
            </tr>
            <tr>
               <th id="adminModifyItemTh">STOCK</th>
               <td id="adminModifyItemTd"><input type="text" name="itemStock" size="68" 
               value="${itemBoard.itemStock }"></td>

            </tr>
            <tr>
               <th id="adminModifyItemTh">TYPE</th>
               <td id="adminModifyItemTd"><select name="itemType" style="width: 68">
                     <option value="1">TOP</option>
                     <option value="2">BOTTOM</option>
               </select></td>
            </tr>
            <tr>
               <th id="adminModifyItemTh">MAIN</th>
               <td id="adminModifyItemTd">
                  <input type="file" name="itemMainPic" size="50"  value="${itemBoard.itemMainPic }">
<%--                   <input type="hidden" name="itemMainPic" value="${itemBoard.itemMainPic }"> --%>
               </td>
            </tr>
            <tr>
               <th id="adminModifyItemTh">DETAIL</th>
               <td>
                  <input type="file" name="itemDetailPic" size="50"  value="${itemBoard.itemMainPic }">
<%--                   <input type="hidden" name="itemDetailPic" value="${itemBoard.itemDetailPic }"> --%>
               </td>
            </tr>
         </table>
         <br> <br>
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