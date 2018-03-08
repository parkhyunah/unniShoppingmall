package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

//댓글을 삭제하는 액션
public class DeleteComment implements Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		dao.deleteComments(Integer.parseInt(req.getParameter("commentsBoardNum")));
		String result = "{\"result\" : "+true+"}";
		
		resp.getWriter().println(result);
	}
}
