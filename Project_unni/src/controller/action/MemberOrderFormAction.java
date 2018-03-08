package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Cart;
import model.Member;

// 회원 - 상품 주문 페이지 요청 액션
public class MemberOrderFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				Member member = dao.SelectOneMember(c.getValue());
				int totalPrice = dao.SelectCartPriceByMemberId(c.getValue());
				req.setAttribute("totalPrice", totalPrice);

				String address = member.getMemberAddress();
				String[] addArr = address.split("@");

				List<Cart> cart = dao.selectAllByMemberId(c.getValue());
				req.setAttribute("cartInfo", cart);
				req.setAttribute("memberInfo", member);
				req.setAttribute("memberAddress", addArr);
			}
		}

		String url = "jsp/memberOrderForm.jsp";
		req.getRequestDispatcher(url).forward(req, resp);

	}
}