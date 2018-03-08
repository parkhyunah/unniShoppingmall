package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Item;

//상품의 상세페이지를 요청하는 액션
public class ItemDetailFormAction implements Action {
   @Override
   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 회원이 보는 상품 상세 페이지 요청
      String url = "jsp/itemDetailForm.jsp";
      UnniDao dao = UnniDao.getInstance();
      List<Item> item =  dao.selectAllByItemCode(Integer.parseInt(req.getParameter("itemCode")));
      req.setAttribute("boarditem", dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode"))));
      req.setAttribute("item", item);
      
      int totalStock = 0;
      
      for(int i=0;i<item.size();i++) {
         totalStock += item.get(i).getItemStock();
      }
      
      if(totalStock<=0) {
         req.setAttribute("sold", "품절");
      }else {
         req.setAttribute("sold", "");
      }
      
      req.getRequestDispatcher(url).forward(req, resp);
   }
}