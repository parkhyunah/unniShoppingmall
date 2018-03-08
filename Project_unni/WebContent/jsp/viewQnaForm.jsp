<%-- Q&A 게시판 상세 페이지  --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>
<script type="text/javascript" src="script/unni.js"></script>
<link href="https://fonts.googleapis.com/css?family=Sriracha" rel="stylesheet">   
<link rel="stylesheet" type="text/css" href="css/unni.css">
<link rel="stylesheet" type="text/css" href="css/etcBoard.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript">
   var login = '${login}';
   var bType = '${qnaBoard.bType}';
   var bNum = '${qnaBoard.bNum}';
   var writer = '${writer}';
   var adminId = '${adminId}'
   function commentsList() {
      
      var table = $("#commentstable");
      $("tr:gt(0)", table).remove();
      $.ajax({
         url : "unni?command=comment_makeList",
         // 목록 전체를 그릴 거라 데이터는 넘길 필요가 없음
         type : "post",
         data : {
            bType : bType,
            bNum : bNum
         },
         dataType : "json",
         success : function(data) {
            var deleteBtn;
            
            for ( var i in data) {
               var tr = $("<tr>"); // tr 요소를 만들어서 객체로 저장
               var commentsWriter = data[i].writer;
               
               //댓글 작성자(commentsWriter)가 내 아이디(writer)랑 같으면 버튼을 달고 아니면 버튼을 안달고
               if(writer==commentsWriter){
                  deleteBtn = $("<input type = 'button' value = 'x'>");
               }else{
                  deleteBtn = $("");
               }
               
               $("<td>").text(data[i].writer).appendTo(tr);
               $("<td>").text(data[i].content).appendTo(tr);
               $("<td>").append(deleteBtn).appendTo(tr);
               tr.appendTo(table);

               // 삭제
               (function(e) {
                  var commentsBoardNum = data[e].commentsBoardNum;
                  deleteBtn.on("click", function() {
                        $.ajax({
                           url : "unni?command=comment_delete",
                           data :"commentsBoardNum="+commentsBoardNum,
                           type : "post",
                           dataType : "json",
                           success : function(data) {
                              commentsList(data);
                           }
                        });
                  });
               })(i);
            }
         }
      });
   }
   $(function() {
      commentsList();
   });
</script>  
<title>Q &amp; A VIEW</title>
</head>
<body>
<div id="navi"><jsp:include page="outline.jsp" /></div>
	<div id="qnaViewForm" align="center" style="padding-top: 50px">
		<div id="boardSection">
			<h1>Q &amp; A</h1><br><br>
			<form>
				<input type="hidden" name="noticeNum" value="${param.num}">
				<table>
					<tr>
						<th id="qnaViewTh">NO</th>
						<td>${qnaBoard.bNum}</td>
						<th id="qnaViewTh">NAME</th>
						<td colspan="2"><font color="#B40404"><b>${qnaBoard.memberId}</b></font></td>
					</tr>
					<tr>
						<th id="qnaViewTh">TITLE</th>
						<td colspan="3">${qnaBoard.boardTitle}</td>
					</tr>
					<tr>
						<td colspan="4" height="300" overflow="hidden">${qnaBoard.boardContent}</td>
					</tr>
				</table>
				<br><br> 
				<label id="qnaModifyBtn"> 
				<input type="button" class="btn" value="MODIFY" onclick="location.href='unni?command=qna_modify_form&bNum=${qnaBoard.bNum}'"></label>

				<label id="qnaDeleteBtn"> 
				<input type="button" class="btn" value="DELETE" onclick="location.href='unni?command=qna_delete&bNum=${qnaBoard.bNum}'"></label>
				
				<label id="qnaWriteBtn"> 
				<input type="button" class="btn" value="WRITE" onclick="location.href='unni?command=qnaWrite_form'"></label>
				<input type="button" class="btn" value="LIST" onclick="location.href='unni?command=qnaList_form'"> 
				<br><br><br>

				<table id="commentstable">
					<tr>
						<td width="15" id="comments" ><b>COMMENTS</b></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				<br>
				<div id="qnaComments">
					<table id="qnaCommentsWriter">
						<tr>
							<td id="commentWriter">${writer}</td>
							<td><textarea id="qnaComment" name="qnaComment"></textarea>
								<input type="button" id="CommentWriteBtn" class="btn" value="COMMENT" style="height: 60px"></td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</div>
</body>
</html>