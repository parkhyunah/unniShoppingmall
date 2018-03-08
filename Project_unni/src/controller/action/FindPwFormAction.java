package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//비밀번호 찾기 화면 요청
public class FindPwFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/findPwForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}