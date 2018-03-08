<%-- main --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
var login = ${login};
var itemSize = ${listSize};
	window.onload = function() {
		$(".itemAtt").hide();
		for (var i = 0; i <= itemSize; i++) {
			(function(m) {
				document.getElementById("imgAtt" + i).addEventListener(
						"mouseover", function() {
							$("#itemAtt" + m).show();
						}, false);
				document.getElementById("imgAtt" + i).addEventListener(
						"mouseout", function() {
							$("#itemAtt" + m).hide();
						}, false);
			})(i);
		}
	}
</script>
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>언니옷</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="section" align="center">
			<table class="imgTable">
				<br>
				<br>
				<tr>
					<c:forEach var="item" items="${itemList}" varStatus="status">
						<c:if test="${status.index%3==0}">
				</tr>
				<tr>
					</c:if>
					<td align="center" width="361px">
						<div class="imgTopic">
							<a href="unni?command=item_detail_form&itemCode=${item.itemCode}">
								<img src="${item.itemMainPic}" width="360px" id="imgAtt${status.index }"> 
								<span class="itemAtt" id="itemAtt${status.index}" 
								style="position: relative; bottom: 70px; color: white;">${item.itemName}<br> ${item.itemPrice} won
							</span>
							</a>
						</div>
						<br>
					</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>