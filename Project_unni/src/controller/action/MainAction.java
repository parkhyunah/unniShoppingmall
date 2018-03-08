package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.ItemBoard;

// 메인 화면을 요청하는 액션
public class MainAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		List<ItemBoard> itemList = dao.selectAllItemBoard();
		req.setAttribute("listSize", itemList.size());
		req.setAttribute("itemList", itemList);
		req.getRequestDispatcher("jsp/mainForm.jsp").forward(req, resp);
	}
}