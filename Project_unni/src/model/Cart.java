package model;

public class Cart {
	int itemCode;
	String itemName;
	String itemMainPic;
	String itemColor;
	String memberID;
	int itemQty;
	int itemTotal;
	int itemPrice;
	
	
	public int getItemCode() {
		return itemCode;
	}


	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getItemMainPic() {
		return itemMainPic;
	}


	public void setItemMainPic(String itemMainPic) {
		this.itemMainPic = itemMainPic;
	}


	public String getItemColor() {
		return itemColor;
	}


	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}


	public String getMemberID() {
		return memberID;
	}


	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}


	public int getItemQty() {
		return itemQty;
	}


	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}


	public int getItemTotal() {
		return itemTotal;
	}


	public void setItemTotal(int itemTotal) {
		this.itemTotal = itemTotal;
	}


	public int getItemPrice() {
		return itemPrice;
	}


	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}


	@Override
	public String toString() {
		return "Cart [itemCode=" + itemCode + ", itemName=" + itemName + ", itemMainPic=" + itemMainPic + ", itemColor="
				+ itemColor + ", memberID=" + memberID + ", itemQty=" + itemQty + ", itemTotal=" + itemTotal
				+ ", itemPrice=" + itemPrice + "]";
	}
	
	
}
