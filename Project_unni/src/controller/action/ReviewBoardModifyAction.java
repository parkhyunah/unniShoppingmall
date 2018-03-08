package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Review 게시판 글 수정 요청 액션
public class ReviewBoardModifyAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UnniDao dao = UnniDao.getInstance();
		EtcBoard reviewBoard = new EtcBoard();

		reviewBoard.setBoardTitle(req.getParameter("reviewTitle"));
		reviewBoard.setBoardContent(req.getParameter("reviewContent"));
		reviewBoard.setbNum(Integer.parseInt(req.getParameter("bNum")));
		reviewBoard.setbType(2);
		dao.updateEtcBoard(reviewBoard);

		req.setAttribute("url", "reviewList_form");
		req.setAttribute("msg", "글 수정 완료!");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

	}
}