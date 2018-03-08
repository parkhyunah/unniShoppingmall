package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UnniDao;
import model.Member;

//비밀번호 찾기은 결과를 요청하는 액션, 찾으면 비밀번호 재설정 화면 요청
public class FindPwResultAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = null;
		UnniDao dao = UnniDao.getInstance();
		Member member = new Member();

		String id = req.getParameter("memberId");
		String name = req.getParameter("memberName");
		String email = req.getParameter("memberEmail");
		member = dao.SelectOneMember(id);

		if ((member != null && member.getMemberName().equals(name)) && member.getMemberEmail().equals(email)) {
			req.setAttribute("member", member);
			url = "jsp/findPwResultForm.jsp";
		} else {
			req.setAttribute("msg", "일치하는 회원 정보가 없습니다.");
			req.setAttribute("url", "findPw_form");
			url = "jsp/result.jsp";
		}

		req.getRequestDispatcher(url).forward(req, resp);
	}
}