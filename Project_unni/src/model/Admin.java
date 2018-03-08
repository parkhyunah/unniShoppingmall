package model;

public class Admin {
	private String adminId;		//관리자 아이디
	private String adminPass;	//관리자 비밀번호
	private String adminName;	//관리자 이름
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getAdminPass() {
		return adminPass;
	}
	public void setAdminPass(String aiminPass) {
		this.adminPass = aiminPass;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", aiminPass=" + adminPass + ", adminName=" + adminName + "]";
	}
	
}
