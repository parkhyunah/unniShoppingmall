package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Review 게시판에서 검색(제목/내용/이름 별)한 결과를 요청하는 액션
public class ReviewSearchResultAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = "/jsp/reviewSearchResultForm.jsp";

		UnniDao dao = UnniDao.getInstance();
		EtcBoard reviewBoardResult = new EtcBoard();

		if (req.getParameter("searchCol").equals("title")) {
			reviewBoardResult.setbType(2);
			reviewBoardResult.setBoardTitle(req.getParameter("reviewSearchKeyword"));

			List<EtcBoard> reviewSearchResultByTitle = dao.selectEtcBoardByTitle(reviewBoardResult);
			// System.out.println(reviewSearchResultByTitle);

			req.setAttribute("reviewSearchResult", reviewSearchResultByTitle);
			req.getRequestDispatcher(url).forward(req, resp);

		} else if (req.getParameter("searchCol").equals("content")) {
			reviewBoardResult.setbType(2);
			reviewBoardResult.setBoardContent(req.getParameter("reviewSearchKeyword"));

			List<EtcBoard> reviewSearchResultByContent = dao.selectEtcBoardByContent(reviewBoardResult);
			// System.out.println(reviewSearchResultByContent);

			req.setAttribute("reviewSearchResult", reviewSearchResultByContent);
			req.getRequestDispatcher(url).forward(req, resp);

		} else if (req.getParameter("searchCol").equals("name")) {
			reviewBoardResult.setMemberId(req.getParameter("reviewSearchKeyword"));
			reviewBoardResult.setbType(2);

			List<EtcBoard> reviewSearchResultById = dao.selectEtcBoardById(reviewBoardResult);
			// System.out.println(reviewSearchResultById);

			req.setAttribute("reviewSearchResult", reviewSearchResultById);
			req.getRequestDispatcher(url).forward(req, resp);
		}

	}
}