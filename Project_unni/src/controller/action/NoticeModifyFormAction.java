package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.NoticeBoard;

// Notice 게시판에 작성된 글을 수정하기 위한 폼을 요청하는 액션
public class NoticeModifyFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		int noticeNum = Integer.parseInt(req.getParameter("noticeNum"));
		NoticeBoard noticeBoard = dao.selectOneNumber(noticeNum);
		req.setAttribute("noticeBoard", noticeBoard);

		String url = "jsp/modifyNoticeForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);

	}
}