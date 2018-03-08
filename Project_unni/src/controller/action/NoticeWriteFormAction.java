package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 관리자 - Notice 게시판에 글을 작성하는 폼을 요청하는 액션
public class NoticeWriteFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("adminId")) {
				req.setAttribute("adminId", c.getValue());
			}
		}

		String url = "/jsp/writeNoticeForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}