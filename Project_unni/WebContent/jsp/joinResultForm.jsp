<%-- 회원가입 결과 화면  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">	
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/join.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JOIN RESULT</title>
<script type="text/javascript">
	var login = ${login};
</script>
<script type="text/javascript" src="script/unni.js"></script>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div><br><br>
   <div id="section" align="center" style="padding-top: 200px;">
		<h1>WELCOME!</h1><br><br>
		<p><font color="#B40404"><b>${param.joinMemberId}</b></font>님, 가입을 축하합니다.<br> 로그인을 원하시면 아래 버튼을 눌러주세요.</p><br>
		<p><input type="submit" value="LOGIN" id="button" class="btn"
		style="width: 175pt; height: 30pt" onclick="location.href='unni?command=login_form'"><br>
		</p><br>
	</div>
</body>
</html>