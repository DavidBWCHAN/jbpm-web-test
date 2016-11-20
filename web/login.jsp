<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String loginflag = request.getParameter("loginflag");
if(loginflag!=null && loginflag.equals("ok")){
	String username = request.getParameter("username");
	String userpwd = request.getParameter("userpwd");
	if(username.equals("user1") || username.equals("user2") || username.equals("manager1") || username.equals("manager2")
			|| username.equals("supermanager") || username.equals("cashier")){
		request.getSession().setAttribute("loginuser", username);
		response.sendRedirect("main.jsp");
	}
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户登录</title>
</head>
<body>
<form action="login.jsp" method="get">
用户名:<input type="text" name="username"><br>
密码：<input type="password" name="userpwd"><br>
<input type="hidden" name="loginflag" value="ok">
<input type="submit" value="登录">
</form>
</body>
</html>