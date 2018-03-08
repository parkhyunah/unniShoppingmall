package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.UnniDao;
import model.ItemBoard;

//main의 ajax의 요청2
public class ItemDetail2Action implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		ItemBoard board = dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode")));
		String result = new Gson().toJson(board.toString());

		resp.getWriter().println(result);
	}
}