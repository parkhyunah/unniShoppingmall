package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// 관리자 - 회원을 삭제하는 요청. 회원을 삭제하면 회원이 작성한 글도 함께 삭제됨
public class MemberDeleteAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		String id = req.getParameter("memberId");
		dao.deleteOrder(id);
		dao.deleteEtcBoard(id);
		dao.deleteMember(id);

		String result = "{\"result\" : " + true + "}";
		resp.getWriter().println(result);
	}
}