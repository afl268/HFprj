<%@page import="kr.co.HF.domain.UserDAO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// userUpdateForm.jsp에서 날려주는 데이터를 받아주세요.
	// 단, 이름에 한글이 포함될 수 있으므로 utf-8로 바꾸는걸 먼저 하고 그 다음 받아주세요.
	request.setCharacterEncoding("utf-8");

	String userId = request.getParameter("fId");
	String userPw = request.getParameter("fPw");
	String userName = request.getParameter("fName");
	String userEmail = request.getParameter("fEmail");
	String struage = request.getParameter("fuage");
	int uage = Integer.parseInt(struage);
	UserDAO dao = UserDAO.getInstance();
	
	// 가입로직을 실행해주세요.
	dao.UpdateCheck(userPw, userName, userEmail, uage, userId);
	
	// 웰컴페이지로 돌아가기.
	response.sendRedirect("LoginComplete.jsp");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>
