package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.OrderItem;

//주문번호(OrderNum)를 이용하여 같은 주문번호에 대한 주문내역을 전체를 조회하는 폼을 요청하는 액션
public class AdminOrderDetailFormAction implements Action {
	   @Override
	   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      
	      String url = "jsp/adminOrderDetailForm.jsp";
	      UnniDao dao = UnniDao.getInstance();
	      List<OrderItem> orderDetail = dao.selectAllOrderByOrderNum(Integer.parseInt(req.getParameter("orderNum")));
	      int orderTotal = orderDetail.get(0).getOrderTotalPrice();
	     
	      req.setAttribute("orderDetail", orderDetail);
	      req.setAttribute("orderNum",req.getParameter("orderNum"));
	      req.setAttribute("orderTotal", orderTotal);
	      req.getRequestDispatcher(url).forward(req, resp);

	   }
	}