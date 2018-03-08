package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.UnniDao;
import model.Comments;

//각 페이지에 해당하는 댓글리스트 요청하는 액션
public class CommentMakeList implements Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		List<Comments> commentsList = dao.selectAllCommentsByBoard(Integer.parseInt(req.getParameter("bNum")),Integer.parseInt(req.getParameter("bType")));
		String result = new Gson().toJson(commentsList);
		resp.getWriter().println(result);
	}
}
