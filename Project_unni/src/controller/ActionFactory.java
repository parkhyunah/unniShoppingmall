package controller;

import controller.action.Action;
import controller.action.AdminDeleteItemAction;
import controller.action.AdminItemDetailFormAction;
import controller.action.AdminItemFormAction;
import controller.action.AdminMakeMemberListAction;
import controller.action.AdminMakeOrderListAction;
import controller.action.AdminMemberListFormAction;
import controller.action.AdminOrderDetailFormAction;
import controller.action.AdminOrderFormAction;
import controller.action.AdminUpdateItemAction;
import controller.action.AdminUpdateItemFormAction;
import controller.action.AdminWriteItemAction;
import controller.action.AdminWriteItemFormAction;
import controller.action.Best6FormAction;
import controller.action.BottomFormAction;
import controller.action.CommentMakeList;
import controller.action.DeleteComment;
import controller.action.DeliveryModifyAction;
import controller.action.FindIdFormAction;
import controller.action.FindIdResultAction;
import controller.action.FindPwFormAction;
import controller.action.FindPwResultAction;
import controller.action.InsertComment;
import controller.action.ItemDetail2Action;
import controller.action.ItemDetailAction;
import controller.action.ItemDetailFormAction;
import controller.action.JoinEmailCheckAction;
import controller.action.JoinFormAction;
import controller.action.JoinIdCheckAction;
import controller.action.JoinResultAction;
import controller.action.LoginAction;
import controller.action.LoginFormAction;
import controller.action.LogoutAction;
import controller.action.MainAction;
import controller.action.MemberCartDelete;
import controller.action.MemberCartFormAction;
import controller.action.MemberCartMakeList;
import controller.action.MemberCartUpdate;
import controller.action.MemberDeleteAction;
import controller.action.MemberModifyAction;
import controller.action.MemberMyPageFormAction;
import controller.action.MemberMyorderFormAction;
import controller.action.MemberOrderAction;
import controller.action.MemberOrderFormAction;
import controller.action.MemberWithdrawalAction;
import controller.action.NoticeBoardListAction;
import controller.action.NoticeDeleteAction;
import controller.action.NoticeModifyAction;
import controller.action.NoticeModifyFormAction;
import controller.action.NoticeViewAction;
import controller.action.NoticeWriteAction;
import controller.action.NoticeWriteFormAction;
import controller.action.OutlineMemberCartFormAction;
import controller.action.MemberMyInfoFormAction;
import controller.action.MemberMyInfoResultAction;
import controller.action.MemberMyOrderDetailFormAction;
import controller.action.PwUpdateAction;
import controller.action.QnaBoardListAction;
import controller.action.QnaBoardModifyAction;
import controller.action.QnaBoardModifyFormAction;
import controller.action.QnaBoardViewAction;
import controller.action.QnaBoardWriteAction;
import controller.action.QnaBoardWriteFormAction;
import controller.action.QnaDeleteAction;
import controller.action.QnaSearchResultAction;
import controller.action.ReviewBoardListAction;
import controller.action.ReviewBoardModifyAction;
import controller.action.ReviewBoardModifyFormAction;
import controller.action.ReviewBoardViewAction;
import controller.action.ReviewBoardWriteAction;
import controller.action.ReviewBoardWriteForm;
import controller.action.ReviewDeleteAction;
import controller.action.ReviewSearchResultAction;
import controller.action.SearchItemAction;
import controller.action.TopFormAction;

public class ActionFactory {
   private static ActionFactory instance;

   public static ActionFactory getInstance() {
      if (instance == null) {
         instance = new ActionFactory();
      }
      return instance;
   }

   private ActionFactory() {
   } // 기본 생성자 private으로 선언해서 외부에서 객체 생성 못하게 함

   public Action getAction(String command) {
      Action action = null;

      if (command.equals("main")) {
         // main 화면 요청
         action = new MainAction();

      } else if (command.equals("item_detail")) {
         // Main Ajax 1
         action = new ItemDetailAction();

      } else if (command.equals("item_detail2")) {
         // Main Ajax 2
         action = new ItemDetail2Action();

      } else if (command.equals("search_item")) {
         // 상품 검색 결과 화면 요청
         action = new SearchItemAction();

      } else if (command.equals("login_form")) {
         // 로그인 화면 요청
         action = new LoginFormAction();

      } else if (command.equals("login")) {
         // 로그인 요청
         action = new LoginAction();

      } else if (command.equals("logout")) {
         // 로그아웃 요청
         action = new LogoutAction();

      } else if (command.equals("findId_form")) {
         // 아이디 찾기 화면 요청
         action = new FindIdFormAction();

      } else if (command.equals("findId_result")) {
         // 아이디 찾기 + 결과 화면 요청
         action = new FindIdResultAction();

      } else if (command.equals("findPw_form")) {
         // 비밀번호 찾기 화면 요청
         action = new FindPwFormAction();

      } else if (command.equals("findPw_result")) {
         // 비밀번호 찾기 + 결과 화면 요청
         action = new FindPwResultAction();

      } else if (command.equals("pw_update")) {
         // 비밀번호 찾기 결과 화면 + 비밀번호 수정 요청
         action = new PwUpdateAction();

      } else if (command.equals("join_form")) {
         // 회원 가입 화면 요청
         action = new JoinFormAction();

      } else if (command.equals("join_result")) {
         // 회원 가입 + 결과 화면 요청
         action = new JoinResultAction();

      } else if (command.equals("join_idCheck")) {
         // 회원 가입 시 아이디 중복 확인
         action = new JoinIdCheckAction();

      } else if (command.equals("join_emailCheck")) {
         // 회원 가입 시 이메일 중복 확인 요청
         action = new JoinEmailCheckAction();

      } else if (command.equals("member_info_form")) {
         // 회원 정보 조회 화면 요청
         action = new MemberMyInfoFormAction();

      } else if (command.equals("memberMyInfo_result")) {
         // 회원 정보 수정 요청
         action = new MemberMyInfoResultAction();

      } else if (command.equals("member_withdrawal")) {
         // 회원 탈퇴 요청
         action = new MemberWithdrawalAction();

      } else if (command.equals("member_mypage_form")) {
         // 회원의 네비게이션 바 메뉴 중 MYPAGE 버튼을 눌렀을 때 나오는 화면 요청
         action = new MemberMyPageFormAction();

      } else if (command.equals("admin_write_item_form")) {
         // 관리자 상품 게시글 작성 폼 요청
         action = new AdminWriteItemFormAction();

      } else if (command.equals("admin_update_item_form")) {
         // 관리자 상품 게시글 수정 폼 요청
         action = new AdminUpdateItemFormAction();

      } else if (command.equals("admin_item_detail_form")) {
         // 관리자 상품 게시글 상세 화면 요청
         action = new AdminItemDetailFormAction();

      } else if (command.equals("admin_write_item")) {
         // 관리자 상품 게시글 작성 요청
         action = new AdminWriteItemAction();

      } else if (command.equals("admin_item_form")) {
         // 관리자 상품 게시글 목록 화면 요청 (네비게이션 바에서 ITEM 버튼 누르면 나오는 화면)
         action = new AdminItemFormAction();

      } else if (command.equals("admin_delete_item")) {
         // 관리자 상품 게시글 삭제 요청
         action = new AdminDeleteItemAction();

      } else if (command.equals("admin_update_item")) {
         // 관리자 상품 게시글 수정 요청
         action = new AdminUpdateItemAction();

      } else if (command.equals("delivery_modify")) {
         // 관리자 - 배송 상태 변경
          action = new DeliveryModifyAction();

      } else if (command.equals("item_detail_form")) {
         // 상품 상세 페이지 요청 (구매 & 장바구니 버튼이 있음)
         action = new ItemDetailFormAction();

      } else if (command.equals("top_form")) {
         // 상의 상품 게시판 요청
         action = new TopFormAction();

      } else if (command.equals("bottom_form")) {
         // 하의 상품 게시판 요청
         action = new BottomFormAction();

      } else if (command.equals("best6_form")) {
         // best6 상품 게시판 요청
         action = new Best6FormAction();

      } else if (command.equals("noticeList_form")) {
         // notice 게시판 list 출력
         action = new NoticeBoardListAction();

      } else if (command.equals("noticeWrite_form")) {
         // notice 게시판 write form 요청
         action = new NoticeWriteFormAction();

      } else if (command.equals("notice_write")) {
         // notice 게시판 write 요청
         action = new NoticeWriteAction();

      } else if (command.equals("notice_view")) {
         // notice 게시판 view 요청
         action = new NoticeViewAction();

      } else if (command.equals("notice_modify_form")) {
         // notice 게시판 modify 폼 요청
         action = new NoticeModifyFormAction();

      } else if (command.equals("notice_modify")) {
         // notice 게시판 modify 요청
         action = new NoticeModifyAction();

      } else if (command.equals("notice_delete")) {
         // notice 게시판 글 delete 요청
         action = new NoticeDeleteAction();

      } else if (command.equals("qnaList_form")) {
         // Q&A 게시판 list 출력
         action = new QnaBoardListAction();

      } else if (command.equals("qnaWrite_form")) {
         // Q&A 게시판 write form 요청
         action = new QnaBoardWriteFormAction();

      } else if (command.equals("qna_write")) {
         // Q&A 게시판 write 요청
         action = new QnaBoardWriteAction();

      } else if (command.equals("qna_view")) {
         // Q&A 게시판 view 요청
         action = new QnaBoardViewAction();

      } else if (command.equals("qna_modify_form")) {
         // Q&A 게시판 수정 폼 요청
         action = new QnaBoardModifyFormAction();

      } else if (command.equals("qna_modify")) {
         // Q&A 글 수정 요청
         action = new QnaBoardModifyAction();

      } else if (command.equals("qna_delete")) {
         // Q&A 글 삭제 요청
         action = new QnaDeleteAction();

      } else if (command.equals("qna_search_result")) {
         // Q&A 게시판 검색 결과 요청
         action = new QnaSearchResultAction();

      } else if (command.equals("reviewList_form")) {
         // REVIEW 게시판 리스트 요청
         action = new ReviewBoardListAction();

      } else if (command.equals("review_write_form")) {
         // REVIEW 게시판 write 폼 요청
         action = new ReviewBoardWriteForm();

      } else if (command.equals("review_write")) {
         // REVIEW 게시판 write 요청
         action = new ReviewBoardWriteAction();

      } else if (command.equals("review_modify_form")) {
         // REVIEW 게시판 modify 폼 요청
         action = new ReviewBoardModifyFormAction();

      } else if (command.equals("review_modify")) {
         // REVIEW 게시판 modify 요청
         action = new ReviewBoardModifyAction();

      } else if (command.equals("review_delete")) {
         // REVIEW 게시판 delete 요청
         action = new ReviewDeleteAction();

      } else if (command.equals("review_view")) {
         // REVIEW 게시판 view 요청
         action = new ReviewBoardViewAction();

      } else if (command.equals("review_search_result")) {
         // REVIEW 게시판 검색 결과 요청
         action = new ReviewSearchResultAction();

      } else if (command.equals("admin_member_form")) {
         // 관리자 - 회원 리스트 출력 폼 요청
         action = new AdminMemberListFormAction();

      } else if (command.equals("admin_make_memberList")) {
         // 관리자 - 멤버리스트 만들기
         action = new AdminMakeMemberListAction();

      } else if (command.equals("admin_make_orderList")) {
         // 관리자 - orderList 만들기
         action = new AdminMakeOrderListAction();

      } else if (command.equals("admin_order_form")) {
         // 관리자 - Order list 화면 요청
         action = new AdminOrderFormAction();

      } else if (command.equals("admin_order_detail_form")) {
         // 관리자 - 주문 상세 리스트 만들기
         action = new AdminOrderDetailFormAction();

      } else if (command.equals("delivery_modify")) {
         // 관리자 - 배송 상태 변경
         action = new DeliveryModifyAction();

      } else if (command.equals("member_delete")) {
         // 회원 정보 삭제
         action = new MemberDeleteAction();

      } else if (command.equals("member_modify")) {
         // 회원 정보 수정
         action = new MemberModifyAction();

      } else if (command.equals("member_cart_delete")) {
         // 회원 - 카트 삭제
         action = new MemberCartDelete();

      } else if (command.equals("member_cart_makeList")) {
         // 회원 - 카트 리스트를 만드는 요청
         action = new MemberCartMakeList();

      } else if (command.equals("outline_member_cart_form")) {
         // 회원 - outline에서 카트 폼 요청 (요청 정보를 갖고 옴)
         action = new OutlineMemberCartFormAction();

      } else if (command.equals("member_cart_update")) {
         // 회원 - 카트 상품 담는 요청
         action = new MemberCartUpdate();

      } else if (command.equals("member_cart_form")) {
         // 회원 - 내 장바구니 내역 화면 요청
         action = new MemberCartFormAction();

      } else if (command.equals("member_order")) {
         // 회원이 주문 페이지에서 주문을 눌렀을 때 하는 요청 *(주문 관련 db 처리를 하고, result.jsp로 보냄)
         action = new MemberOrderAction();

      } else if (command.equals("member_myOrder_detail")) {
         // 회원 - 내 주문 상세보기 요청
         action = new MemberMyOrderDetailFormAction();

      } else if (command.equals("member_myorder_form")) {
         // 회원 - 주문 요청
         action = new MemberMyorderFormAction();

      } else if (command.equals("member_order_form")) {
         // 회원 - 상품 주문 페이지 요청
         action = new MemberOrderFormAction();

      } else if (command.equals("insert_comment")) {
         // 댓글 추가
         action = new InsertComment();

      } else if (command.equals("comment_makeList")) {
         // 댓글 리스트 만드는 요청
         action = new CommentMakeList();

      } else if (command.equals("comment_delete")) {
         // 댓글 삭제 요청
         action = new DeleteComment();
      }
      return action;
   }
}