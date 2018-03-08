package model;

import java.sql.Date;

public class Delivery {
	private int deliveryNum;	//배송번호
	private int deliveryPrice;	//배송금액
	private Date deliveryDate;	//배송일자
	private String deliveryState;	//배송 상태
	private int orderNum;	//주문 번호
	
	public int getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(int deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getDeliveryState() {
		return deliveryState;
	}
	public void setDeliveryState(String deliveryState) {
		this.deliveryState = deliveryState;
	}
	public int getdeliveryNum() {
		return deliveryNum;
	}
	public void setdeliveryNum(int deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public int getDeliveryPrice() {
		return deliveryPrice;
	}
	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}
	public Date getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	@Override
	public String toString() {
		return "Delivery [deliveryNum=" + deliveryNum + ", deliveryPrice=" + deliveryPrice + ", deliveryDate="
				+ deliveryDate + ", deliveryState=" + deliveryState + ", ordernum=" + orderNum + "]";
	}
}
