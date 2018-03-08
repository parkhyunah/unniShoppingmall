package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Cart;

// 회원 - 카트에서 상품을 삭제하는 요청
public class MemberCartDelete implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		Cart cart = new Cart();
		cart.setItemCode(Integer.parseInt(req.getParameter("itemCode")));
		cart.setItemColor(req.getParameter("itemColor"));
		cart.setMemberID(req.getParameter("memberId"));
		Boolean isDel = dao.deleteCart(cart);

		String result = "{\"result\" : " + isDel + "}";
		resp.getWriter().println(result);
	}

}
