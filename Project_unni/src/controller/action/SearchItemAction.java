package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.ItemBoard;

// 네비게이션 바에 있는 Search에서 검색한 상품의 검색 결과 화면을 요청하는 액션
public class SearchItemAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		List<ItemBoard> itemList = dao.selectItemBoardByName(req.getParameter("keyword"));

		req.setAttribute("itemList", itemList);
		req.getRequestDispatcher("jsp/searchResultForm.jsp").forward(req, resp);
	}
}