package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.NoticeBoard;
import model.NoticeBoardListView;

// Notice 게시판에 작성된 글의 List를 요청하는 액션
public class NoticeBoardListAction implements Action {

	private UnniDao dao;
	private static final int MESSAGE_COUNT_PER_PAGE = 10;

	public NoticeBoardListAction() {
		dao = UnniDao.getInstance();
	}

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<NoticeBoard> noticeBoardList = dao.selectAllNoticeBoard();
		req.setAttribute("noticeBoardList", noticeBoardList);

		int pageNumber = 1;
		String pageNumberStr = req.getParameter("page");
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		NoticeBoardListView viewData = getNoticeBoardList(pageNumber);
		req.setAttribute("viewData", viewData);

		String url = "/jsp/noticeListForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}

	public NoticeBoardListView getNoticeBoardList(int pageNumber) {
		NoticeBoardListView result = null;
		int totalCount = dao.selectCountNoticeBoard();

		List<NoticeBoard> noticeList = null;

		int firstRow = 0;
		int endRow = 0;
		
		firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
		endRow = pageNumber * MESSAGE_COUNT_PER_PAGE;
		noticeList = dao.selectNoticeList(firstRow, endRow);

		result = new NoticeBoardListView(pageNumber, MESSAGE_COUNT_PER_PAGE, totalCount, noticeList);
		return result;

	}
}