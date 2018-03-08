package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;
import model.EtcBoardListView;

// Q&A 게시판 검색 결과를 요청하는 액션
public class QnaSearchResultAction implements Action {

	private UnniDao dao;
	private static final int MESSAGE_COUNT_PER_PAGE = 10;

	public QnaSearchResultAction() {
		dao = UnniDao.getInstance();
	}

	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "/jsp/qnaSearchResultForm.jsp";

		EtcBoard qnaBoardResult = new EtcBoard();
		qnaBoardResult.setMemberId(req.getParameter("qnaSearchKeyword"));
		qnaBoardResult.setbType(1);
		System.out.println("qnaBoardResult : " + qnaBoardResult);
		
		int totalCount = dao.selectCountEtcBoardForSearch(1, req.getParameter("qnaSearchKeyword"));

		List<EtcBoard> qnaSearchResultList = dao.selectEtcBoardById(qnaBoardResult);
		req.setAttribute("qnaSearchResult", qnaSearchResultList);

		int pageNumber = 1;
		String pageNumberStr = req.getParameter("page");
		if (pageNumberStr != null) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		EtcBoardListView viewData = getEtcBoardList(pageNumber, totalCount, req.getParameter("qnaSearchKeyword"));
		req.setAttribute("viewData", viewData);
		req.setAttribute("qnaSerachKeyword", req.getParameter("qnaSearchKeyword"));

		req.getRequestDispatcher(url).forward(req, resp);
	}

	public EtcBoardListView getEtcBoardList(int pageNumber, int totalCount, String keyword) {
		EtcBoardListView result = null;
		List<EtcBoard> etcList = null;

		int firstRow = 0;
		int endRow = 0;

		firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
		endRow = pageNumber * MESSAGE_COUNT_PER_PAGE;
		etcList = dao.selectEtcBoardByIdForPaging(firstRow, endRow, 1, keyword);

		result = new EtcBoardListView(pageNumber, MESSAGE_COUNT_PER_PAGE, totalCount, etcList);
		return result;
	}
}