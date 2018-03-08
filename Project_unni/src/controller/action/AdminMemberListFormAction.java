package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//관리자가 회원관리 리스트 폼을 요청하는 액션
public class AdminMemberListFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String url = "jsp/adminMemberForm.jsp";
		
		req.getRequestDispatcher(url).forward(req, resp);
	}
}