package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Review 게시판에서 작성한 글 제목, 내용을 작성하는 액션
public class ReviewBoardWriteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		EtcBoard reviewBoard = new EtcBoard();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				reviewBoard.setMemberId(c.getValue());
			}
		}
		reviewBoard.setBoardTitle(req.getParameter("reviewTitle"));

		String content = req.getParameter("reviewContent");
		content = content.replaceAll("\r\n", "<br>");
		reviewBoard.setBoardContent(content);
		reviewBoard.setbType(2);
		reviewBoard.setItemInfo(req.getParameter("item"));

		dao.insertEtcBoardByReview(reviewBoard);

		req.setAttribute("url", "reviewList_form");
		req.setAttribute("msg", "글 등록 완료!");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

	}
}