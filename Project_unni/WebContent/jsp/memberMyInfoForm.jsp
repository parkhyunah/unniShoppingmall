<%-- 회원이 자기 자신의 정보 조회 및 수정하는 페이지  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
	var login = ${login};
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MY INFO</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
		<div id="memberMyInfoSection" align="center" style="padding-top: 50px;">
			<h1>MY INFO</h1>
			<br>
			<form action="unni" method="post" name="frm">
				<input type="hidden" name="command" value="memberMyInfo_result">
				<table id="memberMyInfoTb">
					<tr>
						<th scope="row" width="90px" id="memberMyInfoTh">ID <img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd"><input type="text" id="InfoMemberId" name="InfoMemberId" value="${member.memberId}" readonly></td>
					</tr>
					<tr>
						<th scope="row" id="memberMyInfoTh">PW<img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd"><input type="password" name="memberPass" id="memberPass"></td>
					</tr>
					<tr>
						<th scope="row" id="memberMyInfoTh">PW CHECK<img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd"><input type="password" name="memberPassCheck" id="memberPassCheck" onkeyup="join_checkPw()"> 
						<label id="passwordCheck" style="color:red; font-size:11px"></label></td>
					</tr>
					<tr>
						<th scope="row" id="memberMyInfoTh">NAME<img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd"><input type="text" name="memberName" id="memberName" value="${member.memberName}"></td>
					</tr>
					<tr>
						<th scope="row" id="memberMyInfoTh">ADDRESS<img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd">
						<input type="text" size="10" name="memberAddress" id="memberAddress" value="${memberAddress}" style="border-bottom: 1px solid #dddddd; padding:5px;" readonly> 
						<input type="button" class="btn" onclick="execDaumPostcode()" id="memberMyInfoAddress" value="SEARCH"><br> 
						<input type="text" size="50" name="address" id="address" value="${address}" style="border-bottom: 1px solid #dddddd; padding:3px;" readonly><br>
						<input type="text" size="50" name="addressdetail" id="addressdetail" value="${addressdetail}"  style="border-bottom: 1px solid #dddddd; padding:5px;"></td>
					</tr>
					<tr>
						<th scope="row" id="memberMyInfoTh">PHONE<img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd"><input type="text" name="memberPhoneNum"
							id="memberPhoneNum" value="${member.memberPhoneNum}"></td>
					</tr>
					<tr>
						<th scope="row" id="memberMyInfoTh">EMAIL<img src="img/ico_required.gif" alt="필수" /></th>
						<td id="memberMyInfoTd"><input type="text" name="InfoMemberEmail" id="InfoMemberEmail" value="${member.memberEmail}" readonly>
						</td>
					</tr>
				</table>
				<br>
				<div align="center">
					<input type="submit" class="btn" value="MODIFY" id="update" onclick="return myInfoUpdate()"> 
					<input type="button" class="btn" value="DELETE" onclick="location.href='unni?command=member_withdrawal'">
				</div>
			</form>
			<br>
		</div>
</body>
</html>