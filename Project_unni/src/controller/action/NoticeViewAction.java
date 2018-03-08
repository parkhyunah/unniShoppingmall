package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.NoticeBoard;

// Notice 게시판에 작성한 글의 상세보기를 요청하는 액션
public class NoticeViewAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		int noticeNum = Integer.parseInt(req.getParameter("noticeNum"));
		NoticeBoard member = dao.selectOneNumber(noticeNum);

		int num = Integer.parseInt(req.getParameter("noticeNum"));
		dao.updateReadCount(num);

		String url = "/jsp/viewNoticeForm.jsp";

		req.setAttribute("noticeBoard", member);
		req.getRequestDispatcher(url).forward(req, resp);

	}
}