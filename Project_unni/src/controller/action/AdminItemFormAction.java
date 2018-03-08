package controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UnniDao;
import model.ItemBoard;
import model.ItemBoardListView;
import model.NoticeBoard;
import model.NoticeBoardListView;

//관리자가 상품전체를 조회하는 폼을 요청하는 액션
public class AdminItemFormAction implements Action {
   private UnniDao dao;
   private static final int MESSAGE_COUNT_PER_PAGE = 10;

   public AdminItemFormAction() {
      dao = UnniDao.getInstance();
   }
   
   @Override
   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 상품 목록 화면 요청
      String url = "jsp/adminItemForm.jsp";

      List<ItemBoard> itemList = dao.selectAllItemBoard();
      req.setAttribute("itemList", itemList);
      
      int pageNumber = 1;
      String pageNumberStr = req.getParameter("page");
      if (pageNumberStr != null) {
         pageNumber = Integer.parseInt(pageNumberStr);
      }

      ItemBoardListView viewData = getItemBoardList(pageNumber);
      req.setAttribute("viewData", viewData);
      

      req.getRequestDispatcher(url).forward(req, resp);

   }
   
   public ItemBoardListView getItemBoardList(int pageNumber) {
      ItemBoardListView result = null;
      int totalCount = dao.selectCountItemBoard();

      List<ItemBoard> itemList = null;

      int firstRow = 0;
      int endRow = 0;
      
      firstRow = (pageNumber - 1) * MESSAGE_COUNT_PER_PAGE + 1;
      endRow = pageNumber * MESSAGE_COUNT_PER_PAGE;
      itemList = dao.selectItemList(firstRow, endRow);

      result = new ItemBoardListView(pageNumber, MESSAGE_COUNT_PER_PAGE, totalCount, itemList);
      return result;

   }
}