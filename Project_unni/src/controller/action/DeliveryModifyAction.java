package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

//관리자가 전체 주문 게시판(OrderList)에서 배송 상태를 변경하는 액션
public class DeliveryModifyAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		String deliveryState = req.getParameter("deliveryState");
		int orderNum = Integer.parseInt(req.getParameter("orderNum"));
		int result = dao.updateDeliveryState(deliveryState, orderNum);

		if (result > 0) {
			req.setAttribute("msg", "수정 완료");
		} else {
			req.setAttribute("msg", "수정 실패");
		}
		req.setAttribute("url", "admin_order_form");
		req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
	}
}