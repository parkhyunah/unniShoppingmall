package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//아이디 찾기 화면 요청
public class FindIdFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/findIdForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}