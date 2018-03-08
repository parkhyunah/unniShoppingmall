package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// 회원 - 마이페이지 폼을 요청하는 액션
public class MemberMyPageFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		String url = "jsp/memberMypageForm.jsp";

		String memberId = null;
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				memberId = c.getValue();
			}
		}

		Member member = dao.SelectOneMember(memberId);
		req.setAttribute("member", member);

		req.getRequestDispatcher(url).forward(req, resp);

	}
}