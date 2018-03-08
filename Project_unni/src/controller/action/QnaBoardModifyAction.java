package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Q&A 게시판에서 작성한 글을 수정하는 액션
public class QnaBoardModifyAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		EtcBoard qnaBoard = new EtcBoard();
		qnaBoard.setBoardTitle(req.getParameter("qnaTitle"));
		qnaBoard.setBoardContent(req.getParameter("qnaContent"));
		qnaBoard.setbNum(Integer.parseInt(req.getParameter("bNum")));
		qnaBoard.setbType(1);
		dao.updateEtcBoard(qnaBoard);

		req.setAttribute("url", "qnaList_form");
		req.setAttribute("msg", "글 수정 완료!");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

	}
}