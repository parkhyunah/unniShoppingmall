<%-- 아이디 찾기 화면  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link href="https://fonts.googleapis.com/css?family=Cutive+Mono" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/unni.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find ID</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="wrap"><br> <br><br> <br>
      <div id="section" align="center" style="padding-top: 50px;">
      <form action="unni" method="post">
         <input type="hidden" name="command" value="findId_result">
         <br>
            <h1>FIND ID</h1>
            <br>
            <p>
               <label class="inputLog" style="padding-right: 10px;">NAME</label>&nbsp;&nbsp;
               <input type="text" name="memberName" id="memberName" style="border-bottom: 1px solid #dddddd; padding:5px;">
            </p>
            <p>
               <label class="inputLog">EMAIL</label>&nbsp;&nbsp;
               <input type="text" name="memberEmail" id="memberEmail" style="border-bottom: 1px solid #dddddd; padding:5px;">
            </p>
            <br><br> <input type="submit" class="btn" value="CONFIRM">
         </form>
      </div>
   </div>
</body>
</html>