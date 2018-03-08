package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

//가입하기(join)폼에서 중복된 이메일을 체크하는 액션
public class JoinEmailCheckAction implements Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 비밀번호 찾기 >> 비밀번호 재설정 화면 요청
		String memberEmail = req.getParameter("memberEmail");
		UnniDao dao = UnniDao.getInstance();
		Member member = dao.selectOneMemberByEmail(memberEmail);
		boolean isDup = false;
		if(member == null) {
			isDup = true;
		}
		
		String result = "{\"result\" : "+isDup+"}";
		resp.getWriter().println(result);
	}
}

