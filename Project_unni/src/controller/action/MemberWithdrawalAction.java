package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// 회원 - 탈퇴하는 요청
public class MemberWithdrawalAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		String url = "jsp/result.jsp";
		
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				String memberId = c.getValue();
				
				req.setAttribute("url", "logout");
				
				dao.deleteOrder(memberId);
				dao.deleteEtcBoard(memberId);
				dao.deleteMember(memberId);

			} else if (c.getName().equals("adminId")) {
				dao.deleteMember((String) req.getAttribute("memberId"));
			}
		}

		req.setAttribute("msg", "탈퇴가 완료되었습니다.");
		req.getRequestDispatcher(url).forward(req, resp);
	}

}
