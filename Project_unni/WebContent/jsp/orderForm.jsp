<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
   integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
   crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
   var login = ${login};
</script>
<script type="text/javascript">
   // 리스트를 표시하기 위해서 > 비동기 방식으로 하기
   // ajax로 하는 요청을 'list'
   // 리스트를 json으로 응답해서 화면에 띄우기
    
   // 호출 되어야지만 실행됨
   function orderList() {
      // 여기서 할 일 : 리스트 테이블에 회원 목록을 갖다 붙이는 함수
      // 얘는 그림 그리는 역할
      // tr이 없기 때문에, 새로운 데이터가 생기면 기존 테이블을 삭제하고 새로 그림을 그린다
      // 얘가 호출되면 ajax로 회원 목록 요청 
      // -> 회원 목록 받아와서 테이블에 tr, td요소 를 만들어서 갖다 붙임(append)

      var table = $("#memberList");
      // tr인덱스가 0보다 큰 애들은 전부 삭제
      // table 객체의 tr요소 중 index가 0보다 큰 tr요소를 모두 선택해서 삭제
      $("tr:gt(0)", table).remove();

      
            $.ajax({
               url : "unni?command=admin_make_orderList",
               // 목록 전체를 그릴 거라 데이터는 넘길 필요가 없음
               type : "post",
               dataType : "json",
               success : function(data) {

                  for ( var i in data) {
                     var tr = $("<tr>"); // tr 요소를 만들어서 객체로 저장
                     var deliveryState = $('<select style="width: 68px " ><option value="배송중">배송중</option><option value="배송전">배송전</option><option value="배송완료">배송완료</option></select>');
                     var orderNum = $('<a href="unni?command=order_list_detail_form&orderNum='+data[i].orderNum+'">'+data[i].orderNum+'</a>');
                     var ModifyBtn = $("<input type = 'button' value = 'MODIFY'>");
                     
                     // td 요소 만들어서 text 설정하고, 위에서 만들어놓은 tr객체를 하위 요소로 지정
                     // 하위에서 상위요소로 갖다붙일 땐 appendTo
                     
                     //배송상태 select box 고정
                     if(data[i].deliveryState=="배송전"){
                        deliveryState.html("<option value='배송중' >배송중</option><option value='배송전' selected='selected'>배송전</option><option value='배송완료'>배송완료</option></select>");
                     }else if(data[i].deliveryState=="배송중"){
                        deliveryState.html("<option value='배송중' selected='selected'>배송중</option><option value='배송전'>배송전</option><option value='배송완료'>배송완료</option></select>");
                     }else{
                        deliveryState.html("<option value='배송중'>배송중</option><option value='배송전'>배송전</option><option value='배송완료' selected='selected'>배송완료</option></select>");
                     }
                     
                     $("<td>").html(orderNum).appendTo(tr);
                     $("<td>").html(data[i].memberId).appendTo(tr);
                     $("<td>").html(data[i].memberName).appendTo(tr);
                     $("<td>").html(data[i].orderPhoneNum).appendTo(tr);
                     $("<td>").html(data[i].orderTotalPrice).appendTo(tr);
                     $("<td>").html(deliveryState).append(ModifyBtn).appendTo(tr);

                     tr.appendTo(table);
                  // 수정       
                  (function(e) {
                        
                        var orderNum = data[e].orderNum;
            
                        var deliveryStatetmp = deliveryState;
                        alert("찌버");
                        alert(deliveryStatetmp);
                        ModifyBtn.on("click",function() {
                                 $.ajax({
                                    url : "unni?command=delivery_modify",
                                    data : {
                                       deliveryState : deliveryStatetmp,
                                       orderNum : orderNum
                                    },
                                    type : "post",
                                    dataType : "json",
                                  
                                    success : function(data) {
                                          
                                          orderList(data);
                                    }
                                 });
                           

                        });
                     })(i);

                  
                  }

               }
            });

   }
   $(function() {
      orderList();
      });
</script>
<link
   href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two"
   rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<link rel="stylesheet" type="text/css" href="css/outline.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order List</title>
</head>
<body>
   <h2 align="center">ORDER LIST</h2>
   <div id="wrap">
      <table id="memberList">
         <tr>
            <th>ORDERNUM</th>
            <th>ID</th>
            <th>MEMBER</th>
            <th>PHONE</th>
            <th>TOTAL</th>
            <th>DELIVERY</th>
         </tr>
      </table>
   </div>
   
   <jsp:include page="outline.jsp" />
</body>
</html>