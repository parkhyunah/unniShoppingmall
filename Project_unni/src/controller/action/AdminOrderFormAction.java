package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UnniDao;
import model.OrderItem;

//관리자가 전체주문 내역을 보는(adminOrderForm) 화면 요청
public class AdminOrderFormAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = "jsp/adminOrderForm.jsp";
		UnniDao dao = UnniDao.getInstance();
		List<OrderItem> orderList = dao.selectOrderList();
		
		req.setAttribute("orderList", orderList);
		req.getRequestDispatcher(url).forward(req, resp);
	}

}