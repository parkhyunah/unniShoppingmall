package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UnniDao;
import model.OrderItem;

//관리자가 전체 주문을 조회할 때 주문 리스트를 만들 데이터를 반환하는 요청
public class AdminMakeOrderListAction implements Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();
		List<OrderItem> orderList = dao.selectOrderList();
		String result = new Gson().toJson(orderList);
		
		resp.getWriter().println(result);
	}

}