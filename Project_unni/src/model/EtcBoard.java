package model;

import java.util.Date;

public class EtcBoard {
	private int boardNum; // 글 번호
	private String memberId; // 사용자 아이디
	private String itemInfo; // 제품 코드
	private Date regDate; // 작성일
	private String boardTitle; // 제목
	private String boardContent; // 내용
	private String boardComment; // 댓글
	private int bType; // 타입
	private int bNum; // 타입별 글번호

	public int getBoardNum() {
		return boardNum;
	}

	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getItemInfo() {
		return itemInfo;
	}

	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardComment() {
		return boardComment;
	}

	public void setBoardComment(String boardComment) {
		this.boardComment = boardComment;
	}

	public int getbType() {
		return bType;
	}

	public void setbType(int bType) {
		this.bType = bType;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	@Override
	public String toString() {
		return "EtcBoard [boardNum=" + boardNum + ", memberId=" + memberId + ", itemInfo=" + itemInfo + ", regDate="
				+ regDate + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent + ", boardComment="
				+ boardComment + ", bType=" + bType + ", bNum=" + bNum + "]";
	}
}