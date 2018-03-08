package model;

public class Member {
	private String memberId;	//회원 아이디
	private String memberPass;	//회원 비밀번호
	private String memberName;
	
	private String memberAddress;	//회원 주소
	private String memberPhoneNum;	//회원 전화번호
	private String memberEmail;		//회원 이메일
	private int memberPoint;	//회원 적립금
	
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPass() {
		return memberPass;
	}
	public void setMemberPass(String memberPass) {
		this.memberPass = memberPass;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getMemberPhoneNum() {
		return memberPhoneNum;
	}
	public void setMemberPhoneNum(String memberPhoneNum) {
		this.memberPhoneNum = memberPhoneNum;
	}
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getMemberPoint() {
		return memberPoint;
	}
	public void setMemberPoint(int memberPoint) {
		this.memberPoint = memberPoint;
	}
	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberPass=" + memberPass + ", memberAddress=" + memberAddress
				+ ", memberPhonNum=" + memberPhoneNum + ", memberEmail=" + memberEmail + ", memberPoint=" + memberPoint
				+ "]";
	}
	
}
