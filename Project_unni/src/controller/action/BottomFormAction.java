
package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.ItemBoard;

//하의 상품 게시판 (BottomForm.jsp) 화면 요청
public class BottomFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		List<ItemBoard> itemList = dao.selectTypeItemBoard(2);

		req.setAttribute("itemList", itemList);
		req.getRequestDispatcher("jsp/bottomForm.jsp").forward(req, resp);
	}
}