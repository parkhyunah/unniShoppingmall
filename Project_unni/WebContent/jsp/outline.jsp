<%-- 좌측 네비게이션 바 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<html>
<script type="text/javascript">
	$(function() {
		$("#searchBtn").on("click", function() {
			var a = document.getElementById("keyword").value;
			location.href = "unni?command=search_item&keyword=" + a;
		});
	});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<br>
<div id="outline">
   <div id="logo">
      <a href="unni?command=main"><span id="mainLogo">UNNI</span></a>
   </div>
   <br>
   <div id="menuList">
         <div id="logMenu">
            <span class="tit1" id="log" name="log"> 
            <a href="unni?command=login_form">LOGIN</a></span> 
            <span class="tit1" id="join" name="join"> 
            <a href="unni?command=join_form">JOIN</a></span><br>
         </div>

         <div id="subMenu">
            <span class="tit1" id="admin" name="admin"> 
            <a href="unni?command=member_mypage_form">MYPAGE</a></span>
            <span class="tit1" id="item" name="item"> 
            <a href="unni?command=outline_member_cart_form">CART</a></span>
            <span class="tit1" id="order" name="order"> 
            <a href="unni?command=member_myorder_form">ORDER</a></span>
         </div>
         <br><br>
         <div id="boardMenu">
            <span class="tit" onclick="location.href='unni?command=best6_form'">BEST6</span><br>
            <span class="tit" onclick="location.href='unni?command=top_form'">TOP</span><br>
            <span class="tit" onclick="location.href='unni?command=bottom_form'">BOTTOM</span><br>
            <br><br>
            <span class="tit" onclick="location.href='unni?command=noticeList_form'">NOTICE</span><br>
            <span class="tit" onclick="location.href='unni?command=qnaList_form'">Q &amp; A</span><br>
            <span class="tit" onclick="location.href='unni?command=reviewList_form'">REVIEW</span><br>
         </div>
	</div>
      <br><br><br><br>
      <div id="search">
         <input id="keyword" value="" type="text" size="15"/> 
         <input type="image" src="img/search1.PNG" alt="검색" id="searchBtn" /><br> 
         <input type="image" src="img/search2.PNG" alt="검색" />
      </div>
</div>
</html>