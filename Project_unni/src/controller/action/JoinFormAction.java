package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 가입 폼을 요청하는 액션
public class JoinFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/joinForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}

}
