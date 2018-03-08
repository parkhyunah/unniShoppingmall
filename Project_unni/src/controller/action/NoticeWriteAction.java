package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.NoticeBoard;

// Notice 게시판에 작성한 글의 등록을 요청하는 액션
public class NoticeWriteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		NoticeBoard noticeBoard = new NoticeBoard();
		noticeBoard.setNoticeTitle(req.getParameter("noticeTitle"));

		String content = req.getParameter("noticeContent");
		content = content.replaceAll("\r\n", "<br>");
		noticeBoard.setNoticeContent(content);

		noticeBoard.setAdminId(req.getParameter("adminId"));
		dao.insertNoticeBoard(noticeBoard);

		req.setAttribute("url", "noticeList_form");
		req.setAttribute("msg", "글 등록 완료!");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

	}
}
