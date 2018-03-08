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

//관리자가 상품 게시글 등록하는 요청 (파라미터로 넘어온 데이터들을 가공해서 insertItemBoard 하기)
public class AdminWriteItemAction implements Action {
	private UnniDao dao;

	public AdminWriteItemAction() {
		dao = UnniDao.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Item item = new Item();
		boolean flag = false;
		int result = 0;

		List<ItemBoard> boardList = dao.selectAllItemBoard();
		for (ItemBoard i : boardList) {
			if (i.getItemCode() == Integer.parseInt(req.getParameter("itemCode"))) {
				req.setAttribute("msg", "이미 존재하는 상품코드 입니다.");
				req.setAttribute("url", "admin_write_item_form");
				req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
				flag = true;
			}
		}

		if (!flag) {
			String contentType = req.getContentType();
			PrintWriter writer = resp.getWriter();

			if (contentType != null && contentType.toLowerCase().startsWith("multipart/")) {
				result = printPartInfo(req, writer);
			}

			String[] values = req.getParameter("itemStock").split(",");
			for (int i = 0; i < values.length; i = i + 2) {
				item.setItemCode(Integer.parseInt(req.getParameter("itemCode"))); // 상품코드
				item.setItemName(req.getParameter("itemName")); // 상품 이름
				item.setItemPrice(Integer.parseInt(req.getParameter("itemPrice"))); // 상품 가격
				item.setItemType(Integer.parseInt(req.getParameter("itemType"))); // 상품 타입
				item.setItemMainPic(
						dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode"))).getItemMainPic()); // 상품
																													// 메인
																													// 사진
				item.setItemDetailPic(
						dao.selectOneItemBoard(Integer.parseInt(req.getParameter("itemCode"))).getItemDetailPic()); // 상품
																													// 상세
																													// 사진
				item.setItemColor(values[i]);// 상품 색상
				item.setItemStock(Integer.parseInt(values[i + 1]));// 재고량
				dao.insertItem(item);
			}

			// item에 추가하기
			// 게시글 등록 완료 (성공)
			if (result > 0) {
				req.setAttribute("msg", "게시글 등록 완료");
			} else { // 게시글 등록 실패
				req.setAttribute("msg", "게시글 등록 실패");
			}
			req.setAttribute("url", "admin_item_form");
			req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
		}
	}

	private int printPartInfo(HttpServletRequest req, PrintWriter writer) throws IOException, ServletException {
		// 전달받은 파트들을 파일로 만들고, 저장
		// 파일이름을 DB에 저장
		int result = 0;

		ItemBoard board = new ItemBoard();
		board.setItemCode(Integer.parseInt(req.getParameter("itemCode")));
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
		board.setItemMainPic("itemImg/" + fileNames.get(0));
		board.setItemDetailPic("itemImg/" + fileNames.get(1));
		result = dao.insertItemBoard(board);

		return result;
	}
}