package controller.action;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Item;

//Best 6 상품 게시판 요청
public class Best6FormAction implements Action {
   @Override
   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       UnniDao dao = UnniDao.getInstance();
       List<Item> itemList = dao.selectBestItem();

       req.setAttribute("itemList", itemList);
       req.getRequestDispatcher("jsp/best6Form.jsp").forward(req, resp);
   }
}