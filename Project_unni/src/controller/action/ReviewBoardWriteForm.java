package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// Review 게시판의 글 작성 폼을 요청하는 액션
public class ReviewBoardWriteForm implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = "/jsp/writeReviewForm.jsp";
		UnniDao dao = UnniDao.getInstance();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				req.setAttribute("member", dao.SelectOneMember(c.getValue()).getMemberId());
			}
		}

		req.getRequestDispatcher(url).forward(req, resp);

	}
}