package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;

//아이디를 찾은 결과를 요청하는 액션
public class FindIdResultAction implements Action{
@Override
public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String url = null;
    UnniDao dao = UnniDao.getInstance();
    Member member = new Member();

    String name = req.getParameter("memberName");
    String email = req.getParameter("memberEmail");
    member = dao.selectOneMemberByEmail(email);

    if(member!=null && member.getMemberName().equals(name)) {
        req.setAttribute("member", member);
        url = "jsp/findIdResultForm.jsp";
    }else {
        req.setAttribute("msg", "일치하는 회원 정보가 없습니다.");
        req.setAttribute("url", "findId_form");
        url = "jsp/result.jsp";
    }

    req.getRequestDispatcher(url).forward(req, resp);
}
}