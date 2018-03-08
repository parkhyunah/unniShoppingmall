package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// Q&A 게시판에 적힌 글을 삭제하는 액션
public class QnaDeleteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/result.jsp";
		UnniDao dao = UnniDao.getInstance();

		int bType = 1;
		int bNum = Integer.parseInt(req.getParameter("bNum"));
		dao.deleteComments(bType, bNum);
		dao.deleteEtcBoard(bType, bNum);

		req.setAttribute("url", "qnaList_form");
		req.setAttribute("msg", "삭제 완료되었습니다.");
		req.getRequestDispatcher(url).forward(req, resp);
	}
}