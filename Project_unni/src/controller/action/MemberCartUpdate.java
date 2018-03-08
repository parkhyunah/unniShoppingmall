package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Cart;

// 회원 - 카트에 상품을 담으면 업데이트 요청이 실행되는 액션
public class MemberCartUpdate implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		Cart cart = new Cart();

		if (dao.selectOneItemByCodeNColor(Integer.parseInt(req.getParameter("itemCode")), req.getParameter("itemColor"))
				.getItemStock() < Integer.parseInt(req.getParameter("itemQty"))) {
			resp.getWriter().println(true);

		} else {
			cart.setItemCode(Integer.parseInt(req.getParameter("itemCode")));
			cart.setItemColor(req.getParameter("itemColor"));
			cart.setItemPrice(Integer.parseInt(req.getParameter("itemPrice")));
			cart.setMemberID(req.getParameter("memberId"));
			cart.setItemQty(Integer.parseInt(req.getParameter("itemQty")));
			dao.updateCart(cart);
		}
	}
}
