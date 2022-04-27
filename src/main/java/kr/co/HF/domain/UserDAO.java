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


	private static UserDAO dao = new UserDAO();

	private UserDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

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
			con = ds.getConnection();
			String sql = "SELECT * FROM user";

			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				UserVO user = new UserVO();

				user.setUserNum(rs.getInt(1));
				user.setUserId(rs.getString(2));
				user.setUserPw(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setUserEmail(rs.getString(5));
				user.setUage(rs.getInt(6));
				user.setIsAdmin(rs.getInt(7));

				userList.add(user);
			}

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
	

	public UserVO getUserInfo(String userNum) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		UserVO user = new UserVO();
		try {
	
			con = ds.getConnection();

			String sql = "SELECT * FROM user WHERE userNum = ?";
			
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, userNum);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				user.setUserNum(rs.getInt(1));
				user.setUserId(rs.getString(2));
				user.setUserPw(rs.getString(3));
				user.setUserName(rs.getString(4));
				user.setUserEmail(rs.getString(5));
				user.setUage(rs.getInt(6));
				user.setIsAdmin(rs.getInt(7));
			}

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
	public void userDelete(String userNum){

		Connection con = null;
		PreparedStatement pstmt = null;
		try {

			con = ds.getConnection();
			String sql = "DELETE FROM user WHERE userNum = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userNum);
		
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
	
	public void JoinCheck(int userNum, String userId, String userPw, 
			String userName, String userEmail, int uage, int isAdmin){// userJoinCheck.jsp 확인
		Connection con = null;
		PreparedStatement pstmt=null;
		
		try {
			
			con = ds.getConnection();
			String sql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userNum);
			pstmt.setString(2, userId);
			pstmt.setString(3, userPw);
			pstmt.setString(4, userName);
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


	public void UpdateCheck( String userPw, String userName, 
			String userEmail, int uage, String userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		
			con = ds.getConnection();
			String sql = "UPDATE user SET userPw=?, userName=?, userEmail=?, uage=? WHERE userId=?";
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

}
