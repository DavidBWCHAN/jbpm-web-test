<%@page import="com.sun.java.swing.plaf.windows.resources.windows"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试工作流系统</title>
</head>
<style type="text/css">
<!--
body {
	margin-left:0px;
	margin-top:0px;
	margin-right:0px;
	margin-botton:0px;
}
-->
</style>
<script type="text/javascript"> 
<%-- function logout(){ 
<% 
session.invalidate(); //运用invalidate()比较好，退出时使session失效
response.sendRedirect("login.jsp");
%> 
}  --%>
</script> 
<body>
<table width="100%" height="700" border="1" cellpadding="0" cellspacing="0">
<tr>
	<td width="15%" height="100%" valign="top" align="left">
	<ul>
		<a href="task-list.jsp" target="mainFrame" >我的待办任务</a><br>
	</ul>
	<ul>
		<a href="write.jsp" target="mainFrame" >我要报销</a><br>
	</ul>
	<ul>
		<a href="write-purchase.jsp" target="mainFrame" >我要申请采购</a><br>
	</ul>
	</td>
	<td width="80%" valign="top"><iframe src="task-list.jsp" name="mainFrame"
	 frameborder="0" marginheight="0" marginwidth="0" height="700" width="100%"></iframe></td>
</table>
</body>
</html>