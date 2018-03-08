<%-- 관리자만 볼 수 있는 회원 리스트 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<script type="text/javascript">
var login = ${login};
</script>
<script type="text/javascript">
// 리스트를 표시하기 위해서 > 비동기 방식으로 하기
// ajax로 하는 요청을 'list'
// 리스트를 json으로 응답해서 화면에 띄우기

// 호출 되어야지만 실행됨
function memberList() {
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
		url : "unni?command=admin_make_memberList",
		// 목록 전체를 그릴 거라 데이터는 넘길 필요가 없음
		type : "post",
		dataType : "json",
		success : function(data) {
	
		for ( var i in data) {
		var tr = $("<tr>"); // tr 요소를 만들어서 객체로 저장

		var ModifyBtn = $("<input type = 'button' class='btn' value = 'MODIFY'>");
		var DeleteBtn = $("<input type = 'button' class='btn' value = 'DELETE'>");
		var memberPass = $('<input type="text" size="5" id="memberPass" value=' + data[i].memberPass + '>');
		var memberPoint = $('<input type="text" size="5" value=' + data[i].memberPoint + '>');
		// td 요소 만들어서 text 설정하고, 위에서 만들어놓은 tr객체를 하위 요소로 지정
		// 하위에서 상위요소로 갖다붙일 땐 appendTo

		$("<td>").html(data[i].memberId).appendTo(tr);
		$("<td>").html(memberPass).appendTo(tr);
		$("<td>").html(data[i].memberName).appendTo(tr);
		$("<td width='550'>").html(data[i].memberAddress)
                                .appendTo(tr);
		$("<td>").text(data[i].memberEmail).appendTo(tr);
		$("<td>").html(data[i].memberPhoneNum).appendTo(tr);
		$("<td>").html(memberPoint).appendTo(tr);

		// 상위에서 하위요소로 갖다붙일 땐 append
		// btn이 td에 갖다 붙이고, btn이 붙어있는 td를 tr에 갖다붙임 
		$("<td>").append(ModifyBtn).appendTo(tr);
		$("<td>").append(DeleteBtn).appendTo(tr);
		tr.appendTo(table);

		// 수정
		(function(e) {

			var memberId = data[e].memberId;
			var memberPasstmp = memberPass;
			var memberName = data[e].memberName;
			var memberAddress = data[e].memberAddress;
			var memberPhoneNum = data[e].memberPhoneNum;
			var memberPointtmp = memberPoint;

			ModifyBtn.on("click", function() {

				if (modifyMemberCheck()) {
					$.ajax({
						url : "unni?command=member_modify",
						data : {
							memberId : memberId,
							memberPass : memberPasstmp.val(),
							memberName : memberName,
							memberAddress : memberAddress,
							memberPhoneNum : memberPhoneNum,
							memberPoint : memberPointtmp.val()
							},
							type : "post",
							dataType : "json",
							success : function(
									data) {
								memberList(data);
								}
							});
					}

				});
			})(i);

		// 클로저가 발생하기 때문에, 즉시 실행함수 생성 후 실행
		(function(e) {

			var memberId = data[e].memberId;
			DeleteBtn.on("click", function() {
				if (deleteMemberCheck()) {
					$.ajax({
						url : "unni?command=member_delete",
						data : "memberId=" + memberId,
						type : "post",
						dataType : "json",
						success : function(data) {
							memberList();
						}
					});
					}

				});
			})(i);
		}

		}
	});

}
$(function() {
    memberList();
});
</script>
<style type="text/css">
input {
	text-align: center;
}
</style>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Shadows+Into+Light+Two" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/orderForm.css">
<link rel="stylesheet" type="text/css" href="css/unni.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Member List</title>
</head>
<body>
	<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="wrap">
		<div id="adminMemberList" align="center">
			<br><br>
			<h1 id="adminMemberListTitle">MEMBER LIST</h1>
			<br><br>
			<table id="memberList">
				<tr>
					<th id="adminMemberListTh">ID</th>
					<th id="adminMemberListTh">PASSWORD</th>
					<th id="adminMemberListTh">NAME</th>
					<th id="adminMemberListTh">ADDRESS</th>
					<th id="adminMemberListTh">EMAIL</th>
					<th id="adminMemberListTh">PHONE</th>
					<th id="adminMemberListTh">POINT</th>
					<th id="adminMemberListTh">MODIFY</th>
					<th id="adminMemberListTh">DELETE</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>