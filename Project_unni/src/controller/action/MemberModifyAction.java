package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// 관리자 - 회원 정보 수정을 요청하는 액션
public class MemberModifyAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		Member member = new Member();
		member.setMemberPass(req.getParameter("memberPass"));
		member.setMemberName(req.getParameter("memberName"));
		member.setMemberAddress(req.getParameter("memberAddress"));
		member.setMemberPhoneNum(req.getParameter("memberPhoneNum"));
		member.setMemberPoint(Integer.parseInt(req.getParameter("memberPoint")));
		member.setMemberId(req.getParameter("memberId"));
		dao.modifyMember(member);

	}
}