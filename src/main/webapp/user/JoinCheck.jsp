1<%@page import="kr.co.HF.domain.UserDAO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");

String struserNum = request.getParameter("userNum");
int userNum = 0;
if(struserNum.equals("")) {

} else {
	 userNum = Integer.parseInt(struserNum);
}
String userId = request.getParameter("userId");
String userPw = request.getParameter("userPw");
String userName = request.getParameter("userName");
String userEmail = request.getParameter("userEmail");
String struage = request.getParameter("uage");
int uage = Integer.parseInt(struage);
String strisAdmin = request.getParameter("isAdmin");

int isAdmin = 0;
if(struserNum.equals("")) {

} else {
	 userNum = Integer.parseInt(strisAdmin);
}
UserDAO dao = UserDAO.getInstance();

dao.JoinCheck(userNum, userId, userPw, userName, userEmail, uage, isAdmin);
response.sendRedirect("LoginForm.jsp");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>.</title>
</head>
<body>

</body>
</html>