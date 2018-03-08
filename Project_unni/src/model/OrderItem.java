package model;

import java.sql.Date;

public class OrderItem {
private String memberId; // 사용자(주문자) id
private int orderNum; // 주문번호 >> orderlist 출력할 때 이거 기준으로 하나씩 목록 출력할거임 primary key 이제 아님 !!!!
private Date orderDate; // 주문일자
private String orderAddress; // 배송지 주소
private String orderPhoneNum; // 배송지 전화번호
private String memberName; // 주문자 이름
private int orderTotalPrice; // 주문 총액
private String paymentName; // 입금자 이름
private String itemColor; // 주문 상품 색깔
private int itemQty; // 주문 상품 수량
private int itemCode; // 주문 상품 코드
private int orderItemNum; // 상품 주문 번호 >> primary key !!!!!!!!!!!!!!!!!!!!!!! orderitem_seq로 값 줄거임~~
private String deliveryState; // 배송 상태
private String itemMainPic; // 아이템 사진
private String itemName; // 아이템 이름
private int itemPrice; // 상품 가격

public String getMemberId() {
    return memberId;
}

public void setMemberId(String memberId) {
    this.memberId = memberId;
}

public int getOrderNum() {
    return orderNum;
}

public void setOrderNum(int orderNum) {
    this.orderNum = orderNum;
}

public Date getOrderDate() {
    return orderDate;
}

public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
}

public String getOrderAddress() {
    return orderAddress;
}

public void setOrderAddress(String orderAddress) {
    this.orderAddress = orderAddress;
}

public String getOrderPhoneNum() {
    return orderPhoneNum;
}

public void setOrderPhoneNum(String orderPhoneNum) {
    this.orderPhoneNum = orderPhoneNum;
}

public String getMemberName() {
    return memberName;
}

public void setMemberName(String memberName) {
    this.memberName = memberName;
}

public int getOrderTotalPrice() {
    return orderTotalPrice;
}

public void setOrderTotalPrice(int orderTotalPrice) {
    this.orderTotalPrice = orderTotalPrice;
}

public String getPaymentName() {
    return paymentName;
}

public void setPaymentName(String paymentName) {
    this.paymentName = paymentName;
}

public String getItemColor() {
    return itemColor;
}

public void setItemColor(String itemColor) {
    this.itemColor = itemColor;
}

public int getItemQty() {
    return itemQty;
}

public void setItemQty(int itemQty) {
    this.itemQty = itemQty;
}

public int getItemCode() {
    return itemCode;
}

public void setItemCode(int itemCode) {
    this.itemCode = itemCode;
}

public int getOrderItemNum() {
    return orderItemNum;
}

public void setOrderItemNum(int orderItemNum) {
    this.orderItemNum = orderItemNum;
}

public String getDeliveryState() {
    return deliveryState;
}

public void setDeliveryState(String deliveryState) {
    this.deliveryState = deliveryState;
}

public String getItemMainPic() {
    return itemMainPic;
}

public void setItemMainPic(String itemMainPic) {
    this.itemMainPic = itemMainPic;
}

public String getItemName() {
    return itemName;
}

public void setItemName(String itemName) {
    this.itemName = itemName;
}

public int getItemPrice() {
    return itemPrice;
}

public void setItemPrice(int itemPrice) {
    this.itemPrice = itemPrice;
}

@Override
public String toString() {
    return "OrderItem [memberId=" + memberId + ", orderNum=" + orderNum + ", orderDate=" + orderDate
            + ", orderAddress=" + orderAddress + ", orderPhoneNum=" + orderPhoneNum + ", memberName=" + memberName
            + ", orderTotalPrice=" + orderTotalPrice + ", paymentName=" + paymentName + ", itemColor=" + itemColor
            + ", itemQty=" + itemQty + ", itemCode=" + itemCode + ", orderItemNum=" + orderItemNum
            + ", deliveryState=" + deliveryState + ", itemMainPic=" + itemMainPic + ", itemName=" + itemName
            + ", itemPrice=" + itemPrice + "]";
}
}