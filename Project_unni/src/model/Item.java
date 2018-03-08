package model;

public class Item {
	private int itemCode;	//제품 코드 
	private String itemName; 	//제품 명
	private String itemMainPic; //메인 사진(사진 경로)
	private String itemDetailPic; //상세 사진(사진 경로)
	private int itemPrice;	//가격
	private String itemColor;	//색깔
	private int itemStock;	//재고량
	private int itemType;	//의류 카테고리 1 : top, 2 : bottom
	private int itemSales;
	
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
	public String getItemDetailPic() {
		return itemDetailPic;
	}
	public void setItemDetailPic(String itemDetailPic) {
		this.itemDetailPic = itemDetailPic;
	}
	public int getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(int itemPrice) {
		this.itemPrice = itemPrice;
	}
	public String getItemColor() {
		return itemColor;
	}
	public void setItemColor(String itemColor) {
		this.itemColor = itemColor;
	}
	public int getItemStock() {
		return itemStock;
	}
	public void setItemStock(int itemStock) {
		this.itemStock = itemStock;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public int getItemSales() {
		return itemSales;
	}
	public void setItemSales(int itemSales) {
		this.itemSales = itemSales;
	}
	@Override
	public String toString() {
		return "Item [itemCode=" + itemCode + ", itemName=" + itemName + ", itemMainPic=" + itemMainPic
				+ ", itemDetailPic=" + itemDetailPic + ", itemPrice=" + itemPrice + ", itemColor=" + itemColor
				+ ", itemStock=" + itemStock + ", itemType=" + itemType + ", itemSales=" + itemSales + "]";
	}
}
