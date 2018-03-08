package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// Review 게시판 글 삭제 요청 액션
public class ReviewDeleteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Review 게시판 글 삭제 요청");

		String url = "jsp/result.jsp";
		UnniDao dao = UnniDao.getInstance();

		int bType = 2;
		int bNum = Integer.parseInt(req.getParameter("bNum"));

		dao.deleteEtcBoard(bType, bNum);

		req.setAttribute("url", "reviewList_form");
		req.setAttribute("msg", "삭제 완료되었습니다.");
		req.getRequestDispatcher(url).forward(req, resp);
	}
}