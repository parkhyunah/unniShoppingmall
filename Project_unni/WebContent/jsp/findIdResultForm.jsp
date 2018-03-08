<%-- 아이디 찾기 결과 화면  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
   var login = ${login};
</script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Cutive+Mono" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/login.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find ID Result</title>
</head>
<body>
   <div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="wrap">
      <br><br><br> <br>
      <div id="section" align="center" style="padding-top: 50px;">
         <form action="unni" method="post">
            <input type="hidden" name="command" value="login_form"> <br>
            <h1>RESULT</h1>
            <span class="inputLog"><br> ID는  '${member.memberId}' 입니다.<br> <br> <br> </span>
            <input type="submit" class="btn" value="LOGIN"> 
               <input type="button" class="btn" onclick="location.href = 'unni?command=findPw_form'" value="FINDPW">
         </form>
      </div>
   </div>
</body>
</html>