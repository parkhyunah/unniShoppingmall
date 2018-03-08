package model;

public class NoticeBoard {
	private int noticeNum;	//공지사항 글 번호
	private String adminId;	//관리자 아이디
	private String noticeTitle;	//글 제목
	private String noticeContent;	//공지사항 글 내용
	private int noticeReadCount;	//공지사항 글 조회수
	
	public int getNoticeNum() {
		return noticeNum;
	}
	public void setNoticeNum(int noticeNum) {
		this.noticeNum = noticeNum;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public int getNoticeReadCount() {
		return noticeReadCount;
	}
	public void setNoticeReadCount(int noticeReadCount) {
		this.noticeReadCount = noticeReadCount;
	}
	@Override
	public String toString() {
		return "NoticeBoard [noticeNum=" + noticeNum + ", adminId=" + adminId + ", noticeTitle=" + noticeTitle
				+ ", noticeContent=" + noticeContent + ", noticeReadCount=" + noticeReadCount + "]";
	}
	
}
