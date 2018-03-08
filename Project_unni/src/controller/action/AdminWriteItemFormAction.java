package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//관리자가 상품을 업데이트(글작성)을 할 폼 요청
public class AdminWriteItemFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/adminWriteItemForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
}