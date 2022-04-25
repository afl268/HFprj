package kr.co.HF.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class UserDAO {

	private DataSource ds=null;

	// 싱글턴 패턴 처리.
	// DAO 내부에 멤버변수로 자기 자신(현 파일의 클래스명은 UserDAO 이므로 UserDAO 타입) 변수 하나 생성
	private static UserDAO dao = new UserDAO();

	// 싱글턴-1. 생성자는 private으로 처리해 외부에서 생성명령을 내릴 수 없게 처리합니다.
	private UserDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 싱글턴-2. static 키워드를 이용해서 단 한 번만 생성하고, 그 이후로는
	// 주소를 공유하는 getInstance()메서드를 생성합니다.
	public static UserDAO getInstance() {
		if(dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	public List<UserVO> getAllUserList(){
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserVO> userList = new ArrayList<>();
		
		try {
			con = ds.getConnection();//context.xml 내부에 디비종류, 접속url, mysql아이디, 비번이 기입됨.
			// 쿼리문 저장
			String sql = "SELECT * FROM userinfo";
			// PreparedStatement에 쿼리문 입력
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				// 유저 한 명의 정보를 담을 수 있는 VO 생성
				UserVO user = new UserVO();
				// 디버깅으로 비어있는것 확인
				System.out.println("집어넣기 전 : " + user);
				// setter로 다 집어넣기
				user.setUserNum(rs.getInt(1));
				user.setUserId(rs.getString(2));
				user.setUserPw(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setUage(rs.getInt(6));
				user.setIsAdmin(rs.getInt(7));
				// 다 집어넣은 후 디버깅
				System.out.println("집어넣은 후 : " + user);
				// userList에 쌓기
				userList.add(user);
			}
			System.out.println("리스트에 쌓인 자료 체크 : " + userList);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return userList;
	}// getAllUserList() 끝나는 지점.
	

	public UserVO getUserInfo(String userId) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		UserVO user = new UserVO();
		try {
	
			con = ds.getConnection();

			String sql = "SELECT * FROM userinfo WHERE user_id = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
	
			System.out.println("데이터 입력 전 : " + user);
			if(rs.next()) {
				user.setUserNum(rs.getInt(1));
				user.setUserId(rs.getString(2));
				user.setUserPw(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setUage(rs.getInt(6));
				user.setIsAdmin(rs.getInt(7));
			}
			System.out.println("데이터 입력 후 : " + user);
		} catch(Exception e){
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return user;
	}
	public void userDelete(String user_id){

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			String sql = "DELETE FROM userinfo WHERE user_id = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
		
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
	
				con.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}// userDelete 마무리
	
	public void userJoinCheck(int userNum, String sId, String sPw, String name, String userEmail, int uage, int isAdmin){// userJoinCheck.jsp 확인
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			
			con = ds.getConnection();
			String sql = "INSERT INTO userinfo VALUES(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setString(2, sId);
			pstmt.setString(3, sPw);
			pstmt.setString(4, name);
			pstmt.setString(5, userEmail);
			pstmt.setInt(6, uage);
			pstmt.setInt(7, isAdmin);
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch(Exception e) {
				e.printStackTrace();
			} 
		}
	}// userJoinCheck


	public void userUpdateCheck(String userId, String userPw, String userName, 
			String userEmail, int uage, int isAdmin) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		
			con = ds.getConnection();
			String sql = "UPDATE userinfo SET user_pw=?, user_name=?, user_email=?, uage=?,  "
					+ "WHERE user_id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(5, userId);
			pstmt.setString(1, userPw);
			pstmt.setString(2, userName);
			pstmt.setString(3, userEmail);
			pstmt.setInt(4, uage);
	
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}// UserDAO 끝나는 지점.
