package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 로그아웃 요청 - 가지고 있던 정보 모두 지우고, mainForm.jsp로 이동  
public class LogoutAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie cookie1 = new Cookie("memberId", "");
		Cookie cookie2 = new Cookie("adminId", "");
		cookie1.setMaxAge(0);
		cookie2.setMaxAge(0);
		resp.addCookie(cookie1);
		resp.addCookie(cookie2);

		req.setAttribute("url", "main");
		req.setAttribute("msg", "로그아웃 되었습니다.");
		String url = "jsp/result.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}