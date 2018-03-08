package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// 리뷰게시판에 작성된 글 하나를 선택하면 해당 글의 상세내용을 볼 수 있는 View 요청 액션
public class ReviewBoardViewAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		UnniDao dao = UnniDao.getInstance();
		String url = "/jsp/viewReviewForm.jsp";

		int reviewbNum = Integer.parseInt(req.getParameter("bNum"));
		int reviewbType = Integer.parseInt(req.getParameter("bType"));
		EtcBoard reviewBoard = dao.selectOneByTypeAndNum(reviewbNum, reviewbType);

		Cookie[] cookies = req.getCookies();
		for(Cookie c : cookies) {
			if(c.getName().equals("memberId")) {
				req.setAttribute("memberId", c.getValue());
			}
		}
		
		req.setAttribute("reviewBoard", reviewBoard);
		System.out.println(reviewBoard.getItemInfo());
		req.getRequestDispatcher(url).forward(req, resp);

	}
}