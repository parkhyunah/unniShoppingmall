package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.UnniDao;
import model.Cart;

// 회원 - 카트 리스트를 만드는 액션
public class MemberCartMakeList implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		List<Cart> cartList = dao.selectAllByMemberId(req.getParameter("memberId"));

		resp.getWriter().println(new Gson().toJson(cartList));
	}

}
