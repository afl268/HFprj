<%@page import="kr.co.HF.domain.UserVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.HF.domain.UserDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	UserDAO dao = UserDAO.getInstance();
	List<UserVO> userList = dao.getAllUserList();
%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>회원번호</th>
				<th>유저아이디</th>
				<th>유저비번</th>
				<th>유저이름</th>
				<th>이메일</th>
				<th>나이</th>
			</tr>
		</thead>
		<tbody>
			<% for(UserVO user : userList){ %>
				<tr>
					<td><%= user.getUserNum() %></td>
					<td><%= user.getUserId() %></td>
					<td><%= user.getUserPw() %></td>
					<td><%= user.getUserName() %></td>
					<td><%= user.getUserEmail() %></td>
					<td><%= user.getUage() %></td>
				</tr>		
			<% } %>
		</tbody>
	</table>
</body>
</html>
