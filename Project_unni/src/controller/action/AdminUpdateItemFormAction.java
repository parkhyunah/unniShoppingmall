package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UnniDao;

import model.ItemBoard;

//관리자가 상품 게시글을 수정하는 화면 요청하는 액션
public class AdminUpdateItemFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/adminUpdateItemForm.jsp";
		int code = Integer.parseInt(req.getParameter("num"));
		UnniDao dao = UnniDao.getInstance();
		ItemBoard itemBoard = dao.selectOneItemBoard(code);

		req.setAttribute("itemBoard", itemBoard);
		req.getRequestDispatcher(url).forward(req, resp);
	}
}