package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// 회원 - 내 정보가 담긴 폼을 요청하는 액션
public class MemberMyInfoFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		Cookie[] cookies = req.getCookies();
		String memberId = null;

		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				memberId = c.getValue();
			}
		}

		Member member = dao.SelectOneMember(memberId);

		String[] values = member.getMemberAddress().split("@");
		for (String s : values) {
			System.out.println(s);
		}

		req.setAttribute("memberAddress", values[0]);
		req.setAttribute("address", values[1]);
		req.setAttribute("addressdetail", values[2]);
		req.setAttribute("member", member);

		req.getRequestDispatcher("jsp/memberMyInfoForm.jsp").forward(req, resp);
	}
}