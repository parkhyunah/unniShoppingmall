package controller.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import dao.UnniDao;
import model.Item;
import model.ItemBoard;

//관리자가 상품의 상세정보를 수정하는 요청
public class AdminUpdateItemAction implements Action {
   private UnniDao dao;

   public AdminUpdateItemAction() {
      dao = UnniDao.getInstance();
   }

   @Override
   public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Item item = new Item();
      int result = 0;
      String contentType = req.getContentType();
      PrintWriter writer = resp.getWriter();

      if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
         printPartInfo(req, writer);

      } else {
         ItemBoard board = new ItemBoard();
         board = dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode")));
         board.setItemName(req.getParameter("itemName"));
         board.setItemPrice(Integer.parseInt(req.getParameter("itemPrice")));
         board.setItemStock(req.getParameter("itemStock"));
         board.setItemType(Integer.parseInt(req.getParameter("itemType")));
         dao.updateItemBoard(board);
      }

      String[] values = req.getParameter("itemStock").split(",");
      for (int i = 0; i < values.length; i = i + 2) {
         item.setItemCode(Integer.parseInt(req.getParameter("itemCode"))); // 상품코드
         item.setItemName(req.getParameter("itemName")); // 상품 이름
         item.setItemPrice(Integer.parseInt(req.getParameter("itemPrice"))); // 상품 가격
         item.setItemType(Integer.parseInt(req.getParameter("itemType"))); // 상품 타입
         item.setItemMainPic(
               dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode"))).getItemMainPic()); // 상품 메인
                                                                                    // 사진
         item.setItemDetailPic(
               dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode"))).getItemDetailPic()); // 상품 상세
                                                                                    // 사진
         item.setItemColor(values[i]);// 상품 색상
         item.setItemStock(Integer.parseInt(values[i + 1]));// 재고량
         result = dao.updateItem(item);
      }

      if (result > 0) {
         req.setAttribute("msg", "수정 완료");
      } else {
         req.setAttribute("msg", "수정 실패");
      }
      req.setAttribute("url", "admin_item_form");
      req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);

   }

   private int printPartInfo(HttpServletRequest req, PrintWriter writer) throws IOException, ServletException {
      // 전달받은 파트들을 파일로 만들고, 저장
      // 파일이름을 DB에 저장
      int result = 0;

      ItemBoard board = new ItemBoard();
      board.setItemCode(Integer.parseInt(req.getParameter("itemCode")));
      board = dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode")));
      board.setItemName(req.getParameter("itemName"));
      board.setItemPrice(Integer.parseInt(req.getParameter("itemPrice")));
      board.setItemStock(req.getParameter("itemStock"));
      board.setItemType(Integer.parseInt(req.getParameter("itemType")));

      // getParts() : 요청에 포함된 part 가져오기
      Collection<Part> parts = req.getParts();

      List<String> fileNames = new ArrayList<String>();
      for (Part part : parts) {
         // System.out.println(part.getSubmittedFileName())
         if (part.getHeader("Content-Disposition").contains("filename=")) {
            String fileName = part.getSubmittedFileName();
            System.out.println(fileName);
            // part.write("c:\\temp\\"+fileName);
            if (part.getSize() > 0) {
               part.write(fileName);
               fileNames.add(fileName);

               part.delete();
            }
         } else {
            System.out.println("name = " + part.getName());
         }
      }

      if (fileNames.size() == 2) {
         board.setItemMainPic("itemImg/" + fileNames.get(0));
         board.setItemDetailPic("itemImg/" + fileNames.get(1));
      }
      System.out.println(fileNames);
      result = dao.updateItemBoard(board);

      return result;
   }
}