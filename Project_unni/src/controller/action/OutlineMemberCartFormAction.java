package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 회원 - outline에서 카트 폼을 요청하면, 요청정보를 가지고 오는 액션
public class OutlineMemberCartFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				req.setAttribute("memberId", c.getValue());
			}
		}
		
		req.getRequestDispatcher("jsp/memberCartForm.jsp").forward(req, resp);
	}

}
