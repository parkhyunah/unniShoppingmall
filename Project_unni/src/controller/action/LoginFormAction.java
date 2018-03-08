package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 로그인 화면 요청 액션
public class LoginFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = "jsp/loginForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);

	}
}