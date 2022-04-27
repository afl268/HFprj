<%@page import="kr.co.HF.domain.UserVO"%>
<%@page import="kr.co.HF.domain.UserDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String userId = request.getParameter("userNum");

	UserDAO dao = UserDAO.getInstance();

	UserVO user = dao.getUserInfo(userId);

%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	아이디 : <%= user.getUserId() %>	<br/>
	비밀번호 : <%= user.getUserPw() %>	<br/>
	이름 : <%= user.getUserName() %>	<br/>
	이메일 : <%= user.getUserEmail() %>	<br/>
	나이 : <%= user.getUage() %>	<br/>

</body>
</html>
