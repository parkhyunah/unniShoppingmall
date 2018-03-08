package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// 아이디 찾기 화면을 요청하는 액션
public class JoinIdCheckAction implements Action{
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		String memberId = req.getParameter("memberId");
		Member member = dao.SelectOneMember(memberId);
		
		boolean isDup = false;
		
		if(member == null) {
			isDup = true;
		}
		
		String result = "{\"result\" : "+isDup+"}";
		resp.getWriter().println(result);
	}
}