package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// 회원 - 내 정보를 조회할 수 있고, 회원정보수정을 요청하면 수정을 수행하는 액션
public class MemberMyInfoResultAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		String url = "jsp/result.jsp";

		Member member = new Member();
		String memberId = req.getParameter("InfoMemberId");
		String memberPass = req.getParameter("memberPass");
		String memberName = req.getParameter("memberName");
		String memberAddress = req.getParameter("memberAddress");
		String address = req.getParameter("address");
		String addressdetail = req.getParameter("addressdetail");
		String memberPhoneNum = req.getParameter("memberPhoneNum");

		member.setMemberId(memberId);
		member.setMemberPass(memberPass);
		member.setMemberName(memberName);
		member.setMemberAddress(memberAddress + "@" + address + "@" + addressdetail);
		member.setMemberPhoneNum(memberPhoneNum);
		dao.updateMember(member);

		req.setAttribute("url", "member_info_form");
		req.setAttribute("msg", "수정이 완료되었습니다.");
		req.getRequestDispatcher(url).forward(req, resp);
	}
}