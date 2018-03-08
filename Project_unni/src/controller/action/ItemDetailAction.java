package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.UnniDao;
import model.ItemBoard;

//main의 ajax의 요청2
public class ItemDetailAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		List<ItemBoard> list = dao.selectAllItemBoard();
		String result = new Gson().toJson(list);

		resp.getWriter().println(result);
	}
}