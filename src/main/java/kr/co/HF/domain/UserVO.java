package kr.co.HF.domain;

public class UserVO {
	// VO클래스는 일반적으로 특정 테이블의 자료를 한 row단위로 저장할수있도록
	// 컬럼정보를 변수로 나열한것입니다.
	// 각 컬럼에 대응하는 변수를 선언해줍니다.
	private int userNum;
	private String userId;
	private String userPw;
	private String userName;
	private String userEmail;
	private int uage;
	private int isAdmin;
	

	public int getUserNum() {
		return userNum;
	}
	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getUage() {
		return uage;
	}
	public void setUage(int uage) {
		this.uage = uage;
	}
	public int getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(int isAdmin) {
		this.isAdmin = isAdmin;
	}
}