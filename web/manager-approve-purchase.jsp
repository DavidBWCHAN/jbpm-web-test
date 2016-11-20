<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dao.*" %>
<%@ include file="include.jsp" %>
<%
String title =null;
String remark=null;
String isSubmit = request.getParameter("isSubmit");
String purchaseId = request.getParameter("purchaseId");
String taskId = request.getParameter("taskId");
if(isSubmit!=null && isSubmit.equals("true")){
	JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
	TaskInstance ti= jbpmContext.loadTaskInstance(Long.parseLong(taskId));
	String approve_result=request.getParameter("approve_result");
	ti.getContextInstance().setVariable("purchase_manager_approve_result", approve_result);
	ti.end();
	jbpmContext.close();
	out.println("<h1>审批处理成功</h1>");
	return;
}else{
	BusinessDAO dao = new BusinessDAO();
	String sql = "select * from test_purchase where id=?";
	Map map=(Map)dao.query(sql, new Object[]{purchaseId}).get(0);
	title=map.get("title").toString();
	remark=map.get("remark").toString();
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主管审批采购单</title>
</head>
<body>
<form action="manager-approve-purchase.jsp" method="post">
采购标题:<input type="text" name="title" value="<%=title %>"><br>
采购内容:<input type="text" name="remark" value="<%=remark %>"><br>
<input type="hidden" name="isSubmit" value="true">
<input type="hidden" name="taskId" value="<%=taskId %>">
<input type="hidden" name="purchaseId" value="<%=purchaseId %>">
<input type="radio" name="approve_result" value="1" checked="checked" />同意 &nbsp;&nbsp;
<input type="radio" name="approve_result" value="0" />不同意 <br>
<input type="submit" value="提交审批结果">
</form>
</body>
</html>