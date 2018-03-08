package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Q&A 게시판의 작성한 글 수정 폼을 요청하는 액션
public class QnaBoardModifyFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		int qnaNum = Integer.parseInt(req.getParameter("bNum"));
		
		EtcBoard qnaBoard = dao.selectOneByTypeAndNum(qnaNum, 1);
		req.setAttribute("qnaBoard", qnaBoard);

		String url = "jsp/modifyQnaForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);

	}
}