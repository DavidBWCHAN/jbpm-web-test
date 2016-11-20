<%@page import="java.rmi.dgc.VMID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dao.*" %>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String title ="";
String remark="";
String isSubmit = request.getParameter("isSubmit");
String purchaseId = request.getParameter("purchaseId");
String taskId = request.getParameter("taskId");
BusinessDAO dao = new BusinessDAO();
String issueperson = request.getSession().getAttribute("loginuser").toString();
if(isSubmit!=null && isSubmit.equals("true")){
	title= request.getParameter("title");
	remark= request.getParameter("remark");
	String sql=null;
	JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
	if(purchaseId!=null && ! purchaseId.equals("")){
		sql="update test_purchase set title=?, remark=? where id=?";
		dao.saveOrUpdateOrDelete(sql,new Object[]{purchaseId, title,remark});
		
		TaskInstance ti=jbpmContext.loadTaskInstance(Long.parseLong(taskId));
		ti.end();
		out.println("<h1>修改采购单操作成功</h1>");
	}else{
		sql = "insert into test_purchase values(?,?,?)";
		purchaseId = new VMID().toString();
		dao.saveOrUpdateOrDelete(sql,new Object[]{purchaseId,title,remark});
		
		jbpmContext.setActorId(issueperson);
		
		ProcessDefinition pd = jbpmContext.getGraphSession().findLatestProcessDefinition("purchase");
		ProcessInstance pi=pd.createProcessInstance();
		ContextInstance ci=pi.getContextInstance();
		ci.setVariable("issue_person", issueperson);
		
		TaskInstance ti=pi.getTaskMgmtInstance().createStartTaskInstance();
		ti.setVariable("purchaseId", purchaseId);
		ti.end();
		
		out.println("<h1>新增采购单操作成功</h1>");
	}
	
	jbpmContext.close();
	return;
}else{
	if(purchaseId!=null && !purchaseId.equals("")){
		String sql = "select * from test_purchase where id=?";
		Map map=(Map)dao.query(sql, new Object[]{purchaseId}).get(0);
		title=map.get("title").toString();
		remark=map.get("remark").toString();
	}else{
		purchaseId="";
	}
}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>填写采购申请单</title>
</head>
<body>
<form action="write-purchase.jsp" method="post">
采购标题:<input type="text" name="title" value="<%=title %>"><br>
采购内容:<input type="text" name="remark" value="<%=remark %>"><br>
<input type="hidden" name="isSubmit" value="true">
<input type="hidden" name="taskId" value="<%=taskId %>">
<input type="hidden" name="purchaseId" value="<%=purchaseId %>">
<input type="submit" value="提交采购单">
</form>
</body>
</html>