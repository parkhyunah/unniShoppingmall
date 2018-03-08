package dao;

import java.util.List;

import model.EtcBoard;
import model.ItemBoard;
import model.Member;

public class DaoTest {
	public static void main(String[] args) {

		UnniDao dao = UnniDao.getInstance();

		Member member = new Member();
		ItemBoard board = new ItemBoard();
		EtcBoard etcBoard = new EtcBoard();

		//etcBoard = dao.selectOneByTypeAndNum(13, 1);
		//System.out.println(etcBoard);
		 // 멤버 추가 테스트
		for (int i = 1; i < 3; i++) {
			System.out.println("추가한당!!");
			member.setMemberId("guest" + i);
			member.setMemberPass("123");
			member.setMemberName("횐");
			member.setMemberAddress("Address" + i);
			member.setMemberPhoneNum("0101234567" + i);
			member.setMemberEmail("email" + i);
			dao.insertMember(member);
		}

		// 멤버 하나만 조회
		// Member memberList = dao.MemberSelectOne("ID3");
		// System.out.println(memberList);

		// 멤버 모두 조회 테스트
		// List <Member> memberList = dao.MemberSelectAll();
		// for(Member memberAll : memberList) {
		// System.out.println(memberAll);
		// }

		// 멤버 수정 테스트
		// member.setMemberId("ID0");
		// member.setMemberPass("000");
		// member.setMemberAddress("address123");
		// member.setMemberPhoneNum(999);
		// member.setMemberEmail("email@email.com");
		// member.setMemberId("ID1");
		// dao.updateMember(member);

		// 멤버 포인트 수정 테스트
		// member.setMemberPoint(555);
		// member.setMemberId("ID1");
		// dao.updateMemberPoint(member);

		// 멤버 삭제 테스트
		// dao.deleteMember("ID4");

		
		// 게시글 추가
//		for (int i = 1; i < 4; i++) {
//			System.out.println("추가한당!!");
//			board.setItemCode(i);
//			board.setItemName("제품명" + i);
//			board.setItemMainPic("메인경로" + i);
//			board.setItemDetailPic("상세경로" + i);
//			board.setItemPrice(1000 + i);
//			board.setItemStock("빨강,2,파랑" + i);
//			board.setItemType(i % 2);
//			dao.insertItemBoard(board);
//		}
		
		
		// 기타 게시판 게시글 추가
//		for (int i = 1; i < 4; i++) {
//			System.out.println("추가한당!!");
//			
//			etcBoard.setMemberId("ryeowon");
//			etcBoard.setItemCode(i);
//			etcBoard.setBoardTitle("Title" + i);
//			etcBoard.setBoardContent("Content" + i);
//			
//			dao.insertEtcBoardByReview(etcBoard);
//		}
		
		// 각 게시판 게시글 모두 출력
//		List<EtcBoard> selectAllBoard = dao.selectOneByType(2);
//		for (EtcBoard selectAll : selectAllBoard) {
//			System.out.println(selectAll);
//		}
		
		
//		List<EtcBoard> etcboard = dao.selectEtcBoardByTitle(etcBoard);
//		for (EtcBoard selectAll : etcboard) {
//			etcBoard.setbType(1);
//			etcboard.setBoardTitle("t");
//		}
		
		

		
		// 삭제
//		etcBoard.setbType(1);
//		etcBoard.setbNum(18);
//		dao.deleteEtcBoard(etcBoard);
		
		// 수정
//		etcBoard.setBoardTitle("휴");
//		etcBoard.setBoardContent("졸려");
//		etcBoard.setbType(2);
//		etcBoard.setbNum(3);
//		dao.updateEtcBoard(etcBoard);
		
	}

}
