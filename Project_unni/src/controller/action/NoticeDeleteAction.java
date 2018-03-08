package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// Notice 게시판에 작성된 글을 삭제하는 액션
public class NoticeDeleteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		String url = "jsp/result.jsp";

		dao.deleteNoticeBoard(Integer.parseInt(req.getParameter("noticeNum")));

		req.setAttribute("url", "noticeList_form");
		req.setAttribute("msg", "삭제 완료되었습니다.");
		req.getRequestDispatcher(url).forward(req, resp);

	}
}