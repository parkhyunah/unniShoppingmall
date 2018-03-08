package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Admin;
import model.Member;

// 로그인 - 관리자와 멤버를 구분하여, 해당 요청에 따라 다른 기능/문구를 구현하는 액션
public class LoginAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		String memberId = req.getParameter("memberId");
		String memberPass = req.getParameter("memberPass");
		String url = "jsp/result.jsp";

		Member member = dao.SelectOneMember(memberId);
		Cookie[] cookies = req.getCookies();
		boolean cookieCh = false;
		Cookie cookie;

		if (memberId.charAt(0) == '1') {
			Admin admin = dao.selectOneAdmin(memberId);

			if (admin != null && memberId.equals(admin.getAdminId())) {
				if (admin.getAdminPass().equals(memberPass)) {
					for (Cookie c : cookies) {
						if (c.getName().equals("adminId")) {
							if (c.getValue().equals(memberId)) {
								req.setAttribute("msg", "이미 로그인 중입니다.");
								req.setAttribute("url", "main");
								cookieCh = true;
								
							} else {
								cookie = new Cookie("adminId", memberId);
								cookie.setMaxAge(0);
								resp.addCookie(cookie);
								req.setAttribute("msg", "새로운 아이디로 로그인합니다.");
								req.setAttribute("url", "main");
								cookieCh = true;
							}
						}
					}
					
					if (!cookieCh) {
						cookie = new Cookie("adminId", memberId);
						resp.addCookie(cookie);

						req.setAttribute("msg", "관리자로 로그인하셨습니다");
						req.setAttribute("url", "main");
					}
					
				} else {
					url = "jsp/result.jsp";
					req.setAttribute("msg", "비밀번호를 확인해주세요");
					req.setAttribute("url", "login_form");
				}
			}

		} else if (member != null && memberId.equals(member.getMemberId())) {
			if (member.getMemberPass().equals(memberPass)) {
				for (Cookie c : cookies) {
					if (c.getName().equals("memberId")) {
						if (c.getValue().equals(memberId)) {
							req.setAttribute("msg", "이미 로그인 중입니다.");
							req.setAttribute("url", "main");
							cookieCh = true;
							
						} else {
							cookie = new Cookie("memberId", memberId);
							cookie.setMaxAge(0);
							resp.addCookie(cookie);
							req.setAttribute("msg", "새로운 아이디로 로그인합니다.");
							req.setAttribute("url", "main");
							cookieCh = true;
						}
					}
				}

				if (!cookieCh) {
					cookie = new Cookie("memberId", memberId);
					resp.addCookie(cookie);

					req.setAttribute("msg", "환영합니다");
					req.setAttribute("url", "main");
				}
				
			} else {
				url = "jsp/result.jsp";
				req.setAttribute("msg", "비밀번호를 확인해주세요");
				req.setAttribute("url", "login_form");
			}

		} else {
			url = "jsp/result.jsp";
			req.setAttribute("msg", "아이디를 확인해주세요");
			req.setAttribute("url", "login_form");
		}
		
		req.getRequestDispatcher(url).forward(req, resp);
	}
}