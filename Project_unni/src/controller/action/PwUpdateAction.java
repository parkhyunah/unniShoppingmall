package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

// FindPW에서 비밀번호를 재설정하면 수정하고 login Form으로 이동하는 액션
public class PwUpdateAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		String id = req.getParameter("memberId");
		String newPw = req.getParameter("memberPass");
		Member member = dao.SelectOneMember(id);
		member.setMemberPass(newPw);
		dao.updateMember(member);
		
		req.setAttribute("msg", "비밀번호가 성공적으로 변경되었습니다");
		req.setAttribute("url", "login_form");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
	}
}