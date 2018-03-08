<%-- 관리자 상품 게시판 목록 : 관리자만 볼수있는 상품 게시글 목록 화면 / ADMIN의 네비게이션 바 ITEM 메뉴를 누르면 전환되는 페이지 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<title>Item List / admin</title>
</head>
<body>
   <div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="wrap">
      <div id="section" align="center" style="padding-top: 50px">
         <h1>Item List</h1>
         <br>
         <table id="adminItemFormTable" style="width: 80%">
            <tr>
               <td colspan="8" id="adminItemListBtn">
                  <button class="btn" onclick="location.href='unni?command=admin_write_item_form'">REGISTER</button>
               </td>
            </tr>
            <tr>
               <th id="adminMemberListTh">NO</th>
               <th id="adminMemberListTh">ITEM CODE</th>
               <th id="adminMemberListTh">NAME</th>
               <th id="adminMemberListTh">MAIN</th>
               <th id="adminMemberListTh">DETAIL</th>
               <th id="adminMemberListTh">PRICE</th>
               <th id="adminMemberListTh">QTY</th>
               <th id="adminMemberListTh">TYPE</th>
            </tr>
            <%--게시글 목록 출력 : jstl로 이용--%>
            <c:forEach var="item" items="${viewData.boardList}">
               <tr>
                  <td id="adminMemberListTd">${item.itemBoardNum}</td>
                  <td id="adminMemberListTd">${item.itemCode}</td>
                  <td id="adminMemberListTd"><a href="unni?command=admin_item_detail_form&&num=${item.itemCode}">${item.itemName}</a></td>
                  <td id="adminMemberListTd"><img src="${item.itemMainPic}" width="50" height="50"></td>
                  <td id="adminMemberListTd"><img src="${item.itemDetailPic}" width="50" height="50"></td>
                  <td id="adminMemberListTd">${item.itemPrice}</td>
                  <td id="adminMemberListTd">${item.itemStock}</td>
                  <td id="adminMemberListTd">${item.itemType}</td>
               </tr>
            </c:forEach>
         </table><br>
      <c:if test="${viewData.startPage != 1}">
         <a href="unni?command=admin_item_form&page=1">[처음]</a>
         <a href="unni?command=admin_item_form&page=${viewData.startPage-1}">[이전]</a>
      </c:if>
      <c:forEach begin="${viewData.startPage}"
         end="${viewData.endPage < viewData.pageTotalCount? viewData.endPage : viewData.pageTotalCount}" var="pageNum">
         <c:choose>
            <c:when test="${pageNum == viewData.currentPageNumber}">
               <b>[${pageNum}]</b>
            </c:when>
            <c:otherwise>
               <a href="unni?command=admin_item_form&page=${pageNum}">[${pageNum}]</a>
            </c:otherwise>
         </c:choose>
      </c:forEach>
      <c:if test="${viewData.endPage < viewData.pageTotalCount}">
         <a href="unni?command=admin_item_form&page=${viewData.endPage+1}">[다음]</a>
         <a href="unni?command=admin_item_form?page=${viewData.pageTotalCount}">[마지막]</a>
      </c:if>
      <br><br>

      </div>
   </div>
</body>
</html>