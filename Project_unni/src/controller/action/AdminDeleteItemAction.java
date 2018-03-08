package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

//관리자가 상품리스트에서 상품을 삭제하는 액션
public class AdminDeleteItemAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		UnniDao dao = UnniDao.getInstance();
		int result = dao.deleteItemBoard(Integer.parseInt(req.getParameter("num")));
		dao.deleteItem(Integer.parseInt(req.getParameter("num")));
		dao.deleteCart(Integer.parseInt(req.getParameter("num")));
		if (result > 0) {
			// 삭제 성공
			req.setAttribute("msg", "삭제 성공");
		} else {
			// 삭제 실패
			req.setAttribute("msg", "삭제 실패");
		}
		req.setAttribute("url", "admin_item_form");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
	}
}