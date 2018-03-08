package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.ItemBoard;

//관리자가 상품의 상세페이지를 요청하는 액션
public class AdminItemDetailFormAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/adminItemDetailForm.jsp";

		UnniDao dao = UnniDao.getInstance();
		ItemBoard itemBoard = new ItemBoard();
		itemBoard = dao.selectOneItemBoard(Integer.parseInt(req.getParameter("num")));
		
		req.setAttribute("itemBoard", itemBoard);
		req.getRequestDispatcher(url).forward(req, resp);
	}
}