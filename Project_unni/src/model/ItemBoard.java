package model;


public class ItemBoard {
	private int itemCode;	//제품코드
	private int itemBoardNum;	//상품게시판 글번호
	private String itemName;	//제품명
	private String itemMainPic;	//제품 메인 사진
	private String itemDetailPic;	//제품 상세사진
	private int itemPrice;	//제품 가격
	private String itemStock;	//제품 제고량 : 색상1, 재고량1, 색상2, 재고량2,...이런식으로 입력받음
	private int itemType;	//카테고리 .. top : 1, bottom : 2
	
	

	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public int getItemBoardNum() {
		return itemBoardNum;
	}
	public void setItemBoardNum(int itemBoardNum) {
		this.itemBoardNum = itemBoardNum;
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
	public String getItemStock() {
		return itemStock;
	}
	public void setItemStock(String itemStock) {
		this.itemStock = itemStock;
	}
	public int getItemType() {
		return itemType;
	}
	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
	public String toString() {
	      return itemName+"<br>"+itemPrice+" won<br>재고량 : "+itemStock;
	}
	
	
}
