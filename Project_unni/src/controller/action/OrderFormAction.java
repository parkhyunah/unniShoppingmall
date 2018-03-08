package controller.action;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;

// 회원 - 주문 폼을 요청하는 액션
public class OrderFormAction implements Action {
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UnniDao dao = UnniDao.getInstance();

		String url = "/jsp/orderForm.jsp";

		req.getRequestDispatcher(url).forward(req, resp);

	}

}
