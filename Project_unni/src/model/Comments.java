package model;

public class Comments {
	private int commentsBoardNum;
	private int bNum;
	private int bType;
	private String writer;
	private String content;

	public int getCommentsBoardNum() {
		return commentsBoardNum;
	}

	public void setCommentsBoardNum(int commentsBoardNum) {
		this.commentsBoardNum = commentsBoardNum;
	}

	public int getbNum() {
		return bNum;
	}

	public void setbNum(int bNum) {
		this.bNum = bNum;
	}

	public int getbType() {
		return bType;
	}

	public void setbType(int bType) {
		this.bType = bType;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Comments [commentsBoardNum=" + commentsBoardNum + ", bNum=" + bNum + ", bType=" + bType + ", writer="
				+ writer + ", content=" + content + "]";
	}

}