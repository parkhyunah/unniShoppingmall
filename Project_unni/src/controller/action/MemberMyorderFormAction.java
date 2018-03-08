package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.OrderItem;

// 회원 - 나의 주문 내역 폼을 요청하는 액션
public class MemberMyorderFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		
		String url = "jsp/memberMyOrderForm.jsp";

		String memberId = null;
		
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				memberId = c.getValue();
			}
		}

		List<OrderItem> orderList = dao.selectMyOrderList(memberId);
		req.setAttribute("orderList", orderList);
		
		req.getRequestDispatcher(url).forward(req, resp);
	}

}
