package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// 가입 결과 화면을 요청하는 액션
public class JoinResultAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		Member member = new Member();
		String memberId = req.getParameter("joinMemberId");
		String memberPass = req.getParameter("memberPass");
		String memberName = req.getParameter("memberName");
		String memberAddress = req.getParameter("memberAddress");
		String address = req.getParameter("address");
		String addressdetail = req.getParameter("addressdetail");
		String memberEmail = req.getParameter("joinMemberEmail");
		String memberPhoneNum = req.getParameter("memberPhoneNum");
		
		member.setMemberId(memberId);
		member.setMemberPass(memberPass);
		member.setMemberName(memberName);
		member.setMemberAddress(memberAddress + "@" + address + "@" + addressdetail);
		member.setMemberPhoneNum(memberPhoneNum);
		member.setMemberEmail(memberEmail);
		// System.out.println(member);
		dao.insertMember(member);

		String url = "jsp/joinResultForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}
