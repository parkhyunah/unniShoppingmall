package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Q&A 게시판에 작성한 글을 등록하는 액션
public class QnaBoardWriteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		EtcBoard qnaBoard = new EtcBoard();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				qnaBoard.setMemberId(c.getValue());
			}
		}

		qnaBoard.setBoardTitle(req.getParameter("qnaTitle"));
		String content = req.getParameter("qnaContent");
		content = content.replaceAll("\r\n", "<br>");
		qnaBoard.setBoardContent(content);
		qnaBoard.setbType(1);
		
		dao.insertEtcBoardByQna(qnaBoard);

		req.setAttribute("url", "qnaList_form");
		req.setAttribute("msg", "글 등록 완료!");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

	}
}