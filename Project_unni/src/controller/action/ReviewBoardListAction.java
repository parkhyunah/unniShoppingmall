package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;
import model.EtcBoardListView;

// Review 게시판 리스트를 요청하는 액션 
public class ReviewBoardListAction implements Action {
	private UnniDao dao;
	private static final int MESSAGE_COUNT_PER_PAGE = 10;

	public ReviewBoardListAction() {
		dao = UnniDao.getInstance();
	}

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "/jsp/reviewListForm.jsp";

		List<EtcBoard> reviewBoardList = dao.selectOneByType(2);
		req.setAttribute("reviewBoardList", reviewBoardList);

		int pageNumber = 1;
		String pageNumberStr = req.getParameter("page");
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		EtcBoardListView viewData = getEtcBoardList(pageNumber);
		req.setAttribute("viewData", viewData);
		req.getRequestDispatcher(url).forward(req, resp);
	}

	public EtcBoardListView getEtcBoardList(int pageNumber) {
		EtcBoardListView result = null;
		int totalCount = dao.selectCountEtcBoard(2);

		List<EtcBoard> etcList = null;

		int firstRow = 0;
		int endRow = 0;

		firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
		endRow = pageNumber * MESSAGE_COUNT_PER_PAGE;
		etcList = dao.selectEtcList(firstRow, endRow, 2);

		result = new EtcBoardListView(pageNumber, MESSAGE_COUNT_PER_PAGE, totalCount, etcList);
		return result;
	}
}