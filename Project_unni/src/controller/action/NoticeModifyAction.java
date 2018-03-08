package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.NoticeBoard;

// Notice 게시판에 작성된 글을 수정하는 액션
public class NoticeModifyAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		NoticeBoard noticeBoard = new NoticeBoard();
		noticeBoard.setNoticeNum(Integer.parseInt(req.getParameter("noticeNum")));
		noticeBoard.setNoticeTitle(req.getParameter("noticeTitle"));
		noticeBoard.setNoticeContent(req.getParameter("noticeContent"));
		dao.updateNoticeBoard(noticeBoard);

		req.setAttribute("url", "noticeList_form");
		req.setAttribute("msg", "글 수정 완료!");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

	}
}