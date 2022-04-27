<%@page import="kr.co.HF.domain.UserDAO"%>
<%@page import="kr.co.HF.domain.UserVO"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	String formId = request.getParameter("formId");
	String formPw = request.getParameter("formPw");
	
	String userId = null;
	String userPw = null;

	UserDAO dao = UserDAO.getInstance(); 
	UserVO user = dao.getUserInfo(formId);
	
	userId = user.getUserId();
	userPw = user.getUserPw();

	if(userId != null){
		if(formPw.equals(userPw)){
			session.setAttribute("s_id", userId);	
			response.sendRedirect("LoginComplete.jsp");
		} else {
			response.sendRedirect("PwFail.jsp");
		}
	} else {
		response.sendRedirect("IdFail.jsp");
	}

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
