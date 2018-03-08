package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Cart;
import model.Item;

// 회원 - 카트 폼을 보여주는 액션 (이미 장바구니에 담겨있거나, 재고보다 많은 양을 담으면 카트에 담을 수 없게 설정)
public class MemberCartFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		String url = "jsp/result.jsp";
		String memberId = null;

		Item item = dao.selectOneItemByCodeNColor(Integer.parseInt(req.getParameter("itemCode")),
				req.getParameter("itemColor"));

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				memberId = c.getValue();
				req.setAttribute("memberId", c.getValue());

				if (dao.SelectOneCart(memberId, item.getItemColor(), item.getItemCode())) {
					req.setAttribute("msg", "이미 장바구니에 담긴 상품입니다.");
					req.setAttribute("url", "item_detail_form");
					req.setAttribute("itemCode", req.getParameter("itemCode"));
					url = "jsp/resultForCart.jsp";

				} else if (dao.selectOneItemByCodeNColor(Integer.parseInt(req.getParameter("itemCode")),
								req.getParameter("itemColor")).getItemStock() <Integer.parseInt(req.getParameter("QTY"))) {
					req.setAttribute("msg", dao.selectOneItemByCodeNColor(Integer.parseInt(req.getParameter("itemCode")),
									req.getParameter("itemColor")).getItemStock() + "개 이상 주문할 수 없습니다.");
					req.setAttribute("url", "item_detail_form");
					req.setAttribute("itemCode", req.getParameter("itemCode"));
					url = "jsp/resultForCart.jsp";
					
				} else {
					Cart cart = new Cart();
					cart.setItemCode(item.getItemCode());
					cart.setItemColor(item.getItemColor());
					cart.setItemMainPic(item.getItemMainPic());
					cart.setItemName(item.getItemName());
					cart.setItemPrice(item.getItemPrice());
					cart.setItemQty(Integer.parseInt(req.getParameter("QTY")));
					cart.setItemTotal(item.getItemPrice() * Integer.parseInt(req.getParameter("QTY")));
					cart.setMemberID(memberId);
					dao.insertCart(cart);

					url = "jsp/memberCartForm.jsp";
				}
				
			} else if (c.getName().equals("adminId")) {
				req.setAttribute("msg", "회원으로 로그인 하세요.");
				req.setAttribute("url", "main");
				url = "jsp/result.jsp";

			} else {
				req.setAttribute("msg", "로그인 하세요.");
				req.setAttribute("url", "login_form");
				url = "jsp/result.jsp";
			}
		}
		
		req.getRequestDispatcher(url).forward(req, resp);
	}
}