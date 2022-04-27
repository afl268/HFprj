<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="JoinCheck.jsp" method="post">
	<input type ="hidden" name="userNum">
		아이디 : <input type="text" name="userId"/><br/>
		비밀번호 : <input type="password" name="userPw"/><br/>
		이름 : <input type="text" name="userName"/><br/>	
        이메일 : <input type="text" name="userEmail"/><br/>
        나이 : <input type="text" name="uage"/><br/>	
    <input type ="hidden" name="isAdmin">
	<input type="submit" value="가입하기">
	</form>
</body>
</html>