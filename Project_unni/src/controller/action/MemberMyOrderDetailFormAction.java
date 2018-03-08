package controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Member;
import model.OrderItem;

// 회원 - 나의 주문 내역에서 주문 번호를 누르면 해당 번호에 대한 주문 디테일 폼을 요청하는 액션
public class MemberMyOrderDetailFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("memberId")) {
				Member member = dao.SelectOneMember(c.getValue());

				List<OrderItem> myOrder = dao.selectAllOrderByOrderNum(Integer.parseInt(req.getParameter("orderNum")));
				List<OrderItem> orderList = new ArrayList<OrderItem>();

				for (int i = 0; i < myOrder.size(); i++) {
					OrderItem orderItem = new OrderItem();
					orderItem.setMemberId(myOrder.get(i).getMemberId());
					orderItem.setOrderNum(myOrder.get(i).getOrderNum());
					orderItem.setOrderDate(myOrder.get(i).getOrderDate());
					orderItem.setOrderAddress(myOrder.get(i).getOrderAddress());
					orderItem.setOrderPhoneNum(myOrder.get(i).getOrderPhoneNum());
					orderItem.setMemberName(myOrder.get(i).getMemberName());
					orderItem.setOrderTotalPrice(myOrder.get(i).getOrderTotalPrice());
					orderItem.setPaymentName(myOrder.get(i).getPaymentName());
					orderItem.setItemCode(myOrder.get(i).getItemCode());
					orderItem.setItemColor(myOrder.get(i).getItemColor());
					orderItem.setItemQty(myOrder.get(i).getItemQty());
					orderItem.setOrderItemNum(myOrder.get(i).getOrderItemNum());
					orderItem.setDeliveryState(myOrder.get(i).getDeliveryState());
					orderItem.setItemMainPic(myOrder.get(i).getItemMainPic());
					orderItem.setItemName(myOrder.get(i).getItemName());
					orderItem.setItemPrice(myOrder.get(i).getItemPrice());
					orderList.add(orderItem);
					req.setAttribute("orderItem", orderItem);
					req.setAttribute("delivertyState", myOrder.get(i).getDeliveryState());
				}
				req.setAttribute("orderList", orderList);
				req.setAttribute("member", member);

				String url = "jsp/memberMyOrderDetailForm.jsp";
				req.getRequestDispatcher(url).forward(req, resp);

			}
		}

	}

}