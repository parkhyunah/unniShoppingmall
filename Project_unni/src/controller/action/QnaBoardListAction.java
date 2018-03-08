package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;
import model.EtcBoardListView;

// Q&A 게시판의 글 리스트를 요청하는 액션
public class QnaBoardListAction implements Action {
	private UnniDao dao;
	private static final int MESSAGE_COUNT_PER_PAGE = 10;

	public QnaBoardListAction() {
		dao = UnniDao.getInstance();
	}

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "/jsp/qnaListForm.jsp";

		List<EtcBoard> qnaBoardList = dao.selectOneByType(1);
		req.setAttribute("qnaBoardList", qnaBoardList);

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
		int totalCount = dao.selectCountEtcBoard(1);

		List<EtcBoard> etcList = null;

		int firstRow = 0;
		int endRow = 0;
		
		firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
		endRow = pageNumber * MESSAGE_COUNT_PER_PAGE;
		etcList = dao.selectEtcList(firstRow, endRow, 1);

		result = new EtcBoardListView(pageNumber, MESSAGE_COUNT_PER_PAGE, totalCount, etcList);
		return result;
	}
}