package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.ItemBoard;

// 관리자가 ItemBoard에서 Top타입(1)을 조회해서 TOP 리스트를 보내는 액션 
public class TopFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		List<ItemBoard> itemList = dao.selectTypeItemBoard(1);

		req.setAttribute("itemList", itemList);
		req.getRequestDispatcher("jsp/topForm.jsp").forward(req, resp);
	}
}
