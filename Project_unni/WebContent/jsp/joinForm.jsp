<%-- 회원 가입 화면 --%>

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
<link href="http://fonts.googleapis.com/earlyaccess/nanumgothic.css" rel="stylesheet">   
<link rel="stylesheet" type="text/css" href="css/unni.css">

<!--autoload=false 파라미터를 이용하여 자동으로 로딩되는 것을 막습니다.-->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<style type="text/css">
@import url(http://fonts.googleapis.com/earlyaccess/notosansmalayalamui.css);
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JOIN US</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
<div id="wrap">
   <div id="section" align="center" style="padding-top: 50px;">
   <h1>JOIN US</h1><br>
      <div class="agreeArea" align="center" style="border: black; margin:10px;">
         <div id="joinTerms" style="font-size:12px; width: 770px; padding:10px; height: 100px;overflow: auto;text-align:left;  border:1px solid #F2F2F2;">
            1. 개인정보 수집목적 및 이용목적<br>&nbsp;가. 서비스 제공에 관한 계약 이행 및 서비스 제공에 따른 요금정산<br>
            콘텐츠 제공 , 구매 및 요금 결제 , 물품배송 또는 청구지 등 발송 , 금융거래 본인 인증 및 금융 서비스<br>
            &nbsp;나. 회원 관리<br> 회원제 서비스 이용에 따른 본인확인 , 개인 식별 , 불량회원의 부정 이용 방지와 비인가
            사용 방지 , 가입 의사 확인 , 연령확인 , 만14세 미만 아동 개인정보 수집 시 법정 대리인 동의여부 확인, 불만처리
            등 민원처리 , 고지사항 전달<br> 2. 수집하는 개인정보 항목 : 이름 , 생년월일 , 성별 , 로그인ID ,
            비밀번호 , 자택 전화번호 , 휴대전화번호 , 이메일 , 14세미만 가입자의 경우 법정대리인의 정보<br> 3.
            개인정보의 보유기간 및 이용기간<br> &nbsp;원칙적으로, 개인정보 수집 및 이용목적이 달성된 후에는 해당 정보를 지체
            없이 파기합니다. 단, 다음의 정보에 대해서는 아래의 이유로 명시한 기간 동안 보존합니다. 가. 회사 내부 방침에 의한
            정보 보유 사유<br> o 부정거래 방지 및 쇼핑몰 운영방침에 따른 보관 : 1년<br> &nbsp;나. 관련
            법령에 의한 정보보유 사유<br> ※ 동의를 거부할 수 있으나 거부시 회원 가입이 불가능합니다.
         </div>
         <div id="agreeCheck" style="text-align: center"><br> 
            <input type="checkbox" id="check" name="check"> 
            <img src="img/ico_required.gif" alt="필수" /> <b><span style="font-size:12px;">동의합니다.</sapn></b><br><br>
         </div>
      </div><br>   
      <h3 style="color:green">가입 정보 입력</h3><br><br>
      <form action="unni" method="post" name="frm">
         <input type="hidden" name="command" value="join_result">
         <table style="width: 770px; font-size:13px; border:1px solid #F2F2F2; text-align:left;">
            <tr>
               <th scope="row" width="90px" style="padding:10px">ID<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="text" name="joinMemberId" id="joinMemberId" style="border-bottom: 1px solid #dddddd; padding:10px;">
                  &nbsp;&nbsp;<input type="button" class="btn" value="Check" id="idCheckBtn" name="idCheckBtn" style="font-size:13px"></td>
            </tr>
            <tr>
               <th scope="row" id="joinTh" style="padding:10px">PW<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="password" name="memberPass" id="memberPass" style="border-bottom: 1px solid #dddddd; padding:10px;"></td>
            </tr>
            <tr>
               <th scope="row" style="padding:10px">PW CHECK<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="password" name="memberPassCheck" id="memberPassCheck" onkeyup="join_checkPw()" style="border-bottom: 1px solid #dddddd; padding:10px;">
               &nbsp;<label id="passwordCheck" style="color:red; font-size:12px"></label></td>
               
            </tr>
            <tr>
               <th scope="row" style="padding:10px">NAME<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="text" name="memberName" id="memberName" style="border-bottom: 1px solid #dddddd; padding:10px;"></td>
            </tr>
            <tr>
               <th scope="row" style="padding:10px">ADDRESS<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="text" name="memberAddress" id="memberAddress" placeholder="우편번호" readonly style="border-bottom: 1px solid #dddddd; padding:10px;"> 
                  &nbsp;&nbsp;<input type="button" class="btn" onclick="execDaumPostcode()" value="SEARCH" style="font-size:13px"><br> 
                  &nbsp;&nbsp;<input type="text" size="60" name="address" id="address" placeholder="주소" readonly style="border-bottom: 1px solid #dddddd; padding:10px;"><br> 
                  &nbsp;&nbsp;<input type="text" size="60" name="addressdetail" id="addressdetail" placeholder="상세주소" style="border-bottom: 1px solid #dddddd; padding:10px;"></td>
            </tr>
            <tr>
               <th scope="row" style="padding:10px">PHONE<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="text" name="memberPhoneNum" id="memberPhoneNum" style="border-bottom: 1px solid #dddddd; padding:10px;"></td>
            </tr>
            <tr>
               <th scope="row" style="padding:10px">EMAIL<img src="img/ico_required.gif" alt="필수" /></th>
               <td>&nbsp;&nbsp;<input type="text" name="joinMemberEmail" id="joinMemberEmail" style="border-bottom: 1px solid #dddddd; padding:10px;">
                  &nbsp;&nbsp;<input type="button" class="btn" value="Check" id="joinEmailCheckBtn" name="joinEmailCheckBtn" style="font-size:13px"></td>
            </tr>
         </table>
         <br>
         <div align="center">
               <input type="submit" class="btn"  value="JOIN" id="joinBtn" onclick="return checkJoin()" style="font-size:20px"> 
               <input type="reset" class="btn"  value="RESET" style="font-size:20px">
         </div>
         <br><br><br><br><br>
      </form>
   </div>
</div>   
</body>
</html>