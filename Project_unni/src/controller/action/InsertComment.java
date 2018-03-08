package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Comments;

//댓글을 추가하는 액션
public class InsertComment implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		Comments comment = new Comments();
		String url = "jsp/resultForComments.jsp";

		comment.setbNum(Integer.parseInt(req.getParameter("bNum")));
		comment.setbType(Integer.parseInt(req.getParameter("bType")));
		comment.setWriter(req.getParameter("writer"));
		comment.setContent(req.getParameter("content"));
		dao.insertComments(comment);

		req.setAttribute("bNum", Integer.parseInt(req.getParameter("bNum")));
		req.setAttribute("bType", Integer.parseInt(req.getParameter("bType")));
		req.setAttribute("url", "qna_view");
		req.getRequestDispatcher(url).forward(req, resp);
	}
}
