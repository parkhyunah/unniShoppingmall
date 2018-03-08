package controller.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.UnniDao;
import model.Cart;
import model.Member;
import model.OrderItem;

//내가 주문 버튼을 눌렀을 때 결과창으로 넘어가는 액션
public class MemberOrderAction implements Action {
@Override
public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // memberMyorderForm.jsp 화면 요청 >> 내 주문 내역 화면
    System.out.println("회원 - 나의 주문 내역 요청");
    UnniDao dao = UnniDao.getInstance();
    Cookie[] cookies = req.getCookies();
    String url = "jsp/result.jsp";
    Date d = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    int today = Integer.parseInt(sdf.format(d)); // orderNum에 넣을 숫자
    int seq = dao.selectOneItemBoardForSequence();
    int orderNum = (today * 100)+ seq;

    for (Cookie c : cookies) {
        if (c.getName().equals("memberId")) {
            List<Cart> cart = dao.selectAllByMemberId(c.getValue());

            for (int i = 0; i < cart.size(); i++) {
                OrderItem orderItem = new OrderItem();
                orderItem.setMemberId(c.getValue());
                orderItem.setOrderNum(orderNum);
                orderItem.setOrderAddress(req.getParameter("memberAddress") + req.getParameter("address")
                        + req.getParameter("addressdetail"));
                orderItem.setOrderPhoneNum(req.getParameter("deliveryPhoneNum"));
                orderItem.setMemberName(req.getParameter("deliveryName"));
                orderItem.setOrderTotalPrice(Integer.parseInt(req.getParameter("total")));
                orderItem.setPaymentName(req.getParameter("paymentName"));
                orderItem.setItemColor(cart.get(i).getItemColor());
                orderItem.setItemQty(cart.get(i).getItemQty());
                orderItem.setItemCode(cart.get(i).getItemCode());
                orderItem.setItemMainPic(cart.get(i).getItemMainPic());
                orderItem.setItemName(cart.get(i).getItemName());
                orderItem.setItemPrice(cart.get(i).getItemTotal());

                dao.insertOrder(orderItem);

                Cart ct = new Cart();
                ct.setItemCode(cart.get(i).getItemCode());
                ct.setItemColor(cart.get(i).getItemColor());
                ct.setMemberID(c.getValue());
                dao.deleteCart(ct);

                int newStock = dao.selectOneItemByCodeNColor(cart.get(i).getItemCode(), cart.get(i).getItemColor())
                        .getItemStock() - cart.get(i).getItemQty();
                int newSales = dao.selectOneItemByCodeNColor(cart.get(i).getItemCode(), cart.get(i).getItemColor())
                        .getItemSales() + cart.get(i).getItemQty();
                dao.updateItemByOrder(cart.get(i).getItemCode(), cart.get(i).getItemColor(), newSales, newStock);

                String[] value = dao.selectOneItemBoard(cart.get(i).getItemCode()).getItemStock().split(",");

                String Stock = "";
                for (int j = 0; j < value.length; j++) {
                    if (value[j].equals(cart.get(i).getItemColor())) {
                        value[j + 1] = Integer.toString(Integer.parseInt(value[j + 1]) - cart.get(i).getItemQty());
                    }
                    if (j == value.length - 1) {
                        Stock = Stock + value[j];
                    } else {
                        Stock = Stock + value[j] + ",";
                    }
                }
                dao.updateItemBoard(cart.get(i).getItemCode(), Stock);
            }

            Member member = dao.SelectOneMember(c.getValue());

            int memberPoint = member.getMemberPoint(); // 원래 내가 갖고있는 포인트
            int myPoint = Integer.parseInt(req.getParameter("myPoint")); // 사용할 포인트
            int result = memberPoint - myPoint; // 사용한 포인트 만큼 차감

            member.setMemberPoint(result);
            member.setMemberId(c.getValue());

            dao.updateMemberPoint(member);

            req.setAttribute("msg", "주문 완료!! 주문 내역으로 이동합니다 ^^");
            req.setAttribute("url", "member_myorder_form");
        } else {
            req.setAttribute("msg", "주문 실패");
            req.setAttribute("url", "member_order");
        }

    }

    req.getRequestDispatcher(url).forward(req, resp);
}
}