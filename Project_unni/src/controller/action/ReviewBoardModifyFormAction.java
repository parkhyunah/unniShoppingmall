package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// 리뷰 게시판 게시글 수정 폼 요청 액션
public class ReviewBoardModifyFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UnniDao dao = UnniDao.getInstance();

		int reviewNum = Integer.parseInt(req.getParameter("bNum"));
		EtcBoard reviewBoard = dao.selectOneByTypeAndNum(reviewNum, 2);
		req.setAttribute("reviewBoard", reviewBoard);

		String url = "jsp/modifyReviewForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);

	}
}