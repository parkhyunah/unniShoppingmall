package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.EtcBoard;

// Q&A 게시판에 작성한 글의 상세 보기를 요청하는 액션
// 회원 : 본인이 작성한 글만 조회 가능 / 관리자 : 모든 글 조회 가능 
public class QnaBoardViewAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		String url = "jsp/result.jsp";

		int qnabNum = Integer.parseInt(req.getParameter("bNum"));
		int qnabType = Integer.parseInt(req.getParameter("bType"));
		EtcBoard qnaBoard = dao.selectOneByTypeAndNum(qnabNum, qnabType);

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if ((c.getName().equals("memberId") && qnaBoard.getMemberId().equals(c.getValue()))
					|| c.getName().equals("adminId")) {

				if (c.getName().equals("adminId")) {
					req.setAttribute("adminId", c.getValue());
				} else {
					req.setAttribute("adminId", "");
				}

				req.setAttribute("writer", c.getValue());
				url = "/jsp/viewQnaForm.jsp";
				req.setAttribute("qnaBoard", qnaBoard);

			} else {
				req.setAttribute("msg", "본인이 작성한 글만 조회가 가능합니다.");
				req.setAttribute("url", "qnaList_form");
			}
		}
		
		req.getRequestDispatcher(url).forward(req, resp);

	}
}