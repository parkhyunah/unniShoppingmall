var isIdCheck = false;
var isEmailCheck = false;
var idCheckState = true;
var emailCheckState = true;

$(function() {
   filter();
   
   $("#orderBtn").prop("disabled", true);
   $("#orderCheck").click(function() {
       if($("#orderCheck").click) {
           $("#orderBtn").prop("disabled", false);
       } else {
           $("#orderBtn").prop("disabled", true);
       }
   })
   
    $("#CommentWriteBtn").on("click",function(){
         var a = document.getElementById("qnaComment").value;
         location.href="unni?command=insert_comment&content="+a+"&bType="+bType+"&bNum="+bNum+"&writer="+writer;
     });
   
   $("#joinBtn").prop("disabled", true);
   $("#check").click(function() {
      if ($("#check").click) {
         $("#joinBtn").prop("disabled", false);
      } else {
         $("#joinBtn").prop("disabled", true);
      }
   });

   // ID check
   $("#idCheckBtn").on("click", function() {
      $.ajax({
         url : "unni?command=join_idCheck",
         data : "memberId=" + $("#joinMemberId").val(),
         type : "post",
         dataType : "json",
         success : function(data) {
            if (data.result) {
               alert("가입 가능한 아이디입니다.");
               idCheckState = true;
            } else {
               alert("이미 가입한 아이디입니다.");
            }
         }
      });
      return false;
   });

   $("#joinEmailCheckBtn").on("click", function() {
      $.ajax({
         url : "unni?command=join_emailCheck",
         data : "memberEmail=" + $("#joinMemberEmail").val(),
         type : "post",
         dataType : "json",
         success : function(data) {
            if (data.result) {
               emailCheckState = true;
               alert("가입 가능한 이메일 입니다.");
            } else {
               alert("이미 가입한 이메일 입니다.");
            }
         }
      });
      return false;
   });
   $("#joinMemberId").on("blur", function() {
      idCheckStage = false;
      alert("아이디 중복 확인하세요.");
   });

   $("#joinMemberEmail").on("blur", function() {
      emailCheckState = false;
      alert("이메일 중복 확인 하세요.");
   });

   $("#QTY").on("click",function() {
            document.getElementById("numberOfItem").innerHTML = document.getElementById("QTY").value;
            document.getElementById("totalPrice").innerHTML = itemPrice* document.getElementById("QTY").value;
   });
});


function checkCart(){
   if(cartstate == 1){
      alert("장바구니에 상품이 존재하지 않습니다.");
      return false;
   }
   return true;
}

function myInfoUpdate() {
   if (document.frm.memberPass.value.length == 0) {
      alert("비밀번호를 입력하세요.");
      return false;
   }
   if (document.frm.memberPassCheck.value.length == 0) {
      alert("비밀번호 확인란을 입력하세요.");
      return false;
   }
   if (document.frm.memberPass.value != document.frm.memberPassCheck.value) {
      alert("비밀번호가 다릅니다.");
      return false;
   }
   if (document.frm.memberName.value.length == 0) {
      alert("이름을 입력하세요.");
      return false;
   }
   if (document.frm.memberAddress.value.length == 0
         || document.frm.address.value.length == 0
         || document.frm.addressdetail.value.length == 0) {
      alert("주소를 입력하세요.");
      return false;
   }
   if (document.frm.memberPhoneNum.value.length == 0) {
      alert("연락처를 입력하세요.");
      return false;
   }
   if (document.frm.InfoMemberEmail.value.length == 0) {
      alert("이메일을 입력하세요.");
      return false;
   }
   return true;
}

/** ************가입 비밀번호 체크************* */
function join_checkPw() {
   var memberPass = document.getElementById("memberPass").value;
   var memberPassCheck = document.getElementById("memberPassCheck").value;

   if (memberPass != memberPassCheck) {
      document.getElementById("passwordCheck").innerHTML = "비밀번호가 같지 않습니다."
   }
   if (memberPass == "") {
      document.getElementById("passwordCheck").innerHTML = "비밀번호를 입력해주세요."
   }
   if (memberPassCheck == "") {
      document.getElementById("passwordCheck").innerHTML = "비밀번호를 입력해주세요."
   }
   if (memberPass == memberPassCheck) {
      document.getElementById("passwordCheck").innerHTML = ""
   }
}

function modifyMemberCheck() {
   if (confirm("수정 하시겠습니까?")) {
      return true;

   }

   return false;
}

function deleteMemberCheck() {
   if (confirm("삭제 하시겠습니까?")) {
      return true;

   }

   return false;
}

function qna_result_check() {
   if (document.qnaForm.qnaSearchKeyword.value.length == 0) {
      alert("검색할 이름을 입력하세요.");
      return false;
   }
   return true;
}

function review_result_check() {
   if (document.reviewForm.reviewSearchKeyword.value.length == 0) {
      alert("검색할 단어를 입력하세요.");
      return false;
   }

   return true;
}

/** ************비밀번호 찾기************* */
function findPwResult_checkPw() {
   document.f1.onsubmit = function() {
      /*
       * 비밀번호 두개가 일치 하는지 확인
       */
      var pw1 = document.f1.memberPass;
      var pw2 = document.f1.memberPass_Ch;

      if (pw1.value.length == 0) {
         alert("공백입니다");
         return false;
      } else if (pw1.value == pw2.value) {

      } else {
         alert("일치하지 않습니다.");
         return false;
      }
      // submit을 하면 정상적으로 처리되었을 때, 화면이 바뀜
      // 화면 바뀜을 방지 하기 위해서 false로 리턴 >> submit 이 더이상 진행이 되지 않음
   };
};

/** ************PwCheck**************** */
function loginCheck() {
   // 요소 검사해서 요소에 값이 있는지 없는지만 검사
   // 입력폼(frm)요소의 name input 요소의 값의 길이가 0이면 아무 것도 없는 것
   if (document.frm.memberId.value.length == 0) {
      alert("아이디를 입력하세요.");
      return false;
   }

   if (document.frm.memberPass.value.length == 0) {
      alert("비밀번호를 입력하세요.");

      return false;
   }
   return true;
}

function checkQTY() {
   if (document.frm.QTY.value == 0) {
      alert("최소 수량은 1입니다.");
      return false;
   }
}

/** ************필터**************** */
function filter() {
   if (login == 1) { // 회원 로그인
      $("#log").html("<a href='unni?command=logout'>LOGOUT</a>");
      $("#join").html("");
      $("#noticeDeleteBtn").html("");
      $("#noticeWrite").html("");
      $("#noticeModifyBtn").html("");
      $("#noticeWriteBtn").html("");

   } else if (login == 2) { // 관리자 로그인

      $("#qnaModifyBtn").html("");
      $("#log").html("<a href='unni?command=logout'>LOGOUT</a>");
      $("#join").html("");
      $("#qnaWriteBtn").html("");
      $("#admin").html("<a href='unni?command=admin_member_form'>MEMBER</a>");
      $("#item").html("<a href='unni?command=admin_item_form'>ITEM</a>");
      $("#order").html("<a href='unni?command=admin_order_form'>ORDER</a>");
      
   } else if (login == 0) { // 로그인 안한 상태

      $("#admin").html("");
      $("#item").html("");
      $("#order").html("");
      $("#noticeDeleteBtn").html("");
      $("#noticeWrite").html("");
      $("#noticeModifyBtn").html("");
      $("#noticeWriteBtn").html("");
      $("#qnaWriteBtn").html("");
      $("#qnaModifyBtn").html("");
      $("#qnaDeleteBtn").html("");
      $("#reviewModifyBtn").html("");
      $("#reviewDeleteBtn").html("");
   }
}
/* 상품 게시글 작성 및 수정 시 체크 함수 */
function itemBoardCheck() {
   // 요소 검사해서 요소에 값이 있는지 없는지만 검사
   if (document.frm.itemCode.value.length == 0) {
      // 입력폼(frm)요소의 code의 value 길이가 0이면
      alert("제품코드를 입력하세요.")
      return false; // 화면이 넘어가지 않도록
   } else if (document.frm.itemName.value.length == 0) {
      // 제품명 입력 x
      alert("제품명을 입력하세요.")
      return false;
   } else if (document.frm.itemPrice.value.length == 0) {
      // 가격 입력 x
      alert("제품 가격을 입력하세요.")
      return false;
   } else if (document.frm.itemStock.value.length == 0) {
      // 재고량 입력 x
      alert("재고량을 입력하세요.")
      return false;
   } else if (document.frm.itemMainPic.value.length == 0) {
      // 메인 사진입력 x
      alert("메인 사진을 입력하세요.")
      return false;
   } else if (document.frm.itemDetailPic.value.length == 0) {
      // 상세사진 입력 x
      alert("상세 사진을 입력하세요.")
      return false;
   } else {
      return true;
   }
}

function reviewWriteCheck() {
      if (document.etcForm.reviewTitle.value.length == 0) {
         alert("제목을 입력하세요.");
         return false;
      }
      if (document.etcForm.reviewContent.value.length == 0) {
         alert("내용을 입력하세요.");
         return false;
      }

      if (confirm("글을 작성하시겠습니까?")) {
         return true;

      } else {
         return false;
      }
      
   }

function etcBoardModifyCheck() {
   if (document.etcModifyForm.noticeTitle.value.length == 0) {
      alert("제목을 입력하세요.");
      return false;
   }
   if (document.etcModifyForm.noticeContent.value.length == 0) {
      alert("내용을 입력하세요.");
      return false;
   }

   if (confirm("글을 수정하시겠습니까?")) {

      return true;

   } else {
      return false;

   }
}

function IdCheck() {
   isIdCheck = !isIdCheck;
}

function EmailCheck() {
   isEmailCheck = !isEmailCheck;
}

function boardWriteCheck() {
   if (document.noticeWriteForm.noticeTitle.value.length == 0) {
      alert("제목을 입력하세요.");
      return false;
   }
   if (document.noticeWriteForm.noticeContent.value.length == 0) {
      alert("내용을 입력하세요.");
      return false;
   }

   if (confirm("글을 작성하시겠습니까?")) {

      return true;

   } else {
      return false;

   }
}

function reviewCancleCheck() {
   var url = null;

   if (confirm("글 수정을 취소 하시겠습니까?")) {
      url = "unni?command=reviewList_form";

   } else {
      return false;
   }

   location.href = url;
}


function qnaCancleCheck() {
	   var url = null;

	   if (confirm("글 수정을 취소 하시겠습니까?")) {
	      url = "unni?command=qnaList_form";

	   } else {
	      return false;
	   }

	   location.href = url;
	}


function cancleCheck() {
   var url = null;

   if (confirm("글 작성을 취소 하시겠습니까?")) {
      url = "unni?command=noticeList_form";

   } else {
      return false;
   }

   location.href = url;
}

function noticeBoardModifyCheck() {
   if (document.noticeWriteForm.noticeTitle.value.length == 0) {
      alert("제목을 입력하세요.");
      return false;
   }
   if (document.noticeWriteForm.noticeContent.value.length == 0) {
      alert("내용을 입력하세요.");
      return false;
   }

   if (confirm("글을 수정하시겠습니까?")) {

      return true;

   } else {
      return false;

   }
}

function checkJoin() {
   if (document.frm.joinMemberId.value.length == 0) {
      alert("아이디를 입력하세요.");
      return false;
   }
   if (!idCheckState) {
      alert("아이디 중복 확인하세요.");
      return false;
   }
   if (document.frm.memberPass.value.length == 0) {
      alert("비밀번호를 입력하세요.");
      return false;
   }
   if (document.frm.memberPassCheck.value.length == 0) {
      alert("비밀번호확인을 입력하세요.");
      return false;
   }
   if (document.frm.memberPass.value != document.frm.memberPassCheck.value) {
      alert("비밀번호가 다릅니다.");
      return false;
   }
   if (document.frm.memberName.value.length == 0) {
      alert("이름을 입력하세요.");
      return false;
   }
   if (document.frm.memberAddress.value.length == 0
         || document.frm.address.value.length == 0
         || document.frm.addressdetail.value.length == 0) {
      alert("주소를 입력하세요.");
      return false;
   }
   if (document.frm.memberPhoneNum.value.length == 0) {
      alert("휴대폰를 입력하세요.");
      return false;
   }
   if (document.frm.joinMemberEmail.value.length == 0) {
      alert("이메일을 입력하세요.");
      return false;
   }
   if (!emailCheckState) {
      alert("이메일 중복 확인하세요.");
      return false;
   }

   return true;
}


function myInfoUpdate() {
   if (document.frm.memberPass.value.length == 0) {
      alert("비밀번호를 입력하세요.");
      return false;
   }
   if (document.frm.memberPassCheck.value.length == 0) {
      alert("비밀번호확인을 입력하세요.");
      return false;
   }
   if (document.frm.memberPass.value != document.frm.memberPassCheck.value) {
      alert("비밀번호가 다릅니다.");
      return false;
   }
   if (document.frm.memberName.value.length == 0) {
      alert("이름을 입력하세요.");
      return false;
   }
   if (document.frm.memberAddress.value.length == 0
         || document.frm.address.value.length == 0
         || document.frm.addressdetail.value.length == 0) {
      alert("주소를 입력하세요.");
      return false;
   }
   if (document.frm.memberPhoneNum.value.length == 0) {
      alert("휴대폰를 입력하세요.");
      return false;
   }
   if (document.frm.InfoMemberEmail.value.length == 0) {
      alert("이메일을 입력하세요.");
      return false;
   }
   return true;
}

function execDaumPostcode() {
   daum.postcode
         .load(function() {
            new daum.Postcode(
                  {

                     oncomplete : function(data) {
                        // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                        // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                        // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기
                        // 한다.
                        var fullAddr = ''; // 최종 주소 변수
                        var extraAddr = ''; // 조합형 주소 변수

                        // 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                        if (data.userSelectedType === 'R') { // 사용자가
                           // 도로명
                           // 주소를
                           // 선택했을
                           // 경우
                           fullAddr = data.roadAddress;

                        } else { // 사용자가 지번 주소를 선택했을 경우(J)
                           fullAddr = data.jibunAddress;
                        }

                        // 사용자가 선택한 주소가 도로명 타입일때 조합한다.
                        if (data.userSelectedType === 'R') {
                           // 법정동명이 있을 경우 추가한다.
                           if (data.bname !== '') {
                              extraAddr += data.bname;
                           }
                           // 건물명이 있을 경우 추가한다.
                           if (data.buildingName !== '') {
                              extraAddr += (extraAddr !== '' ? ', '
                                    + data.buildingName
                                    : data.buildingName);
                           }
                           // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                           fullAddr += (extraAddr !== '' ? ' ('
                                 + extraAddr + ')' : '');
                        }

                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('memberAddress').value = data.zonecode; // 5자리
                        // 새우편번호
                        // 사용
                        document.getElementById('address').value = fullAddr;

                        // 커서를 상세주소 필드로 이동한다.
                        document.getElementById('addressdetail')
                              .focus();
                     }
               }).open();
         });
}