<%--  비밀번호 찾기 화면  --%>

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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Find PW</title>
</head>
<body>
   <div id="navi"><jsp:include page="outline.jsp" /></div>
   <div id="wrap">
      <br> <br> <br> <br>
      <div id="section" align="center" style="padding-top: 50px;">
         <form action="unni" method="post">
            <input type="hidden" name="command" value="findPw_result"> <br>
            <h1>FIND PW</h1>
            <br>
            <table class="inputLog">
               <tr>
                  <td>ID</td>
                  <td>&nbsp;&nbsp;<input type="text" id="memberId" name="memberId" style="border-bottom: 1px solid #dddddd; padding:5px;"></td>
               </tr>
               <tr>
                  <td>NAME</td>
                  <td>&nbsp;&nbsp;<input type="text" id="memberName" name="memberName" style="border-bottom: 1px solid #dddddd; padding:5px;"></td>
               </tr>
               <tr>
                  <td>EMAIL</td>
                  <td>&nbsp;&nbsp;<input type="text" id="memberEmail" name="memberEmail" style="border-bottom: 1px solid #dddddd; padding:5px;"></td>
               </tr>
            </table>
            <br>
            <p>
               <input type="submit" class="btn" value="Confirm">
            </p>
         </form>
      </div>
   </div>
</body>
</html>