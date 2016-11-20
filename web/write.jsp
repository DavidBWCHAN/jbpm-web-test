<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="include.jsp" %>
<%
String isSubmit = request.getParameter("isSubmit");
if(isSubmit!=null && isSubmit.equals("true")){
	String title= request.getParameter("title");
	String money_count= request.getParameter("money_count");
	String remark= request.getParameter("remark");
	String issueperson= request.getSession().getAttribute("loginuser").toString();
	JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
	try{
		jbpmContext.setActorId(issueperson);
		
		ProcessDefinition pd = jbpmContext.getGraphSession().findLatestProcessDefinition("payment");
		ProcessInstance pi = pd.createProcessInstance();
		ContextInstance ci = pi.getContextInstance();
		ci.setVariable("issue_person", issueperson);
		
		TaskInstance ti = pi.getTaskMgmtInstance().createStartTaskInstance();
		ti.setVariable("title", title);
		ti.setVariable("money_count", money_count);
		ti.setVariable("remark", remark);
		
		ti.end();
	}finally{
		jbpmContext.close();
	}
	out.println("<h1>报销申请提交成功!</h1>");
	return;
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>填写报销单</title>
</head>
<body>
<form action="write.jsp" method="post">
报销主题:<input type="text" name="title"><br>
报销金额:<input type="text" name="money_count"><br>
报销说明:<input type="text" name="remark"><br>
<input type="hidden" name="isSubmit" value="true">
<input type="submit" value="提交报销">
</form>
</body>
</html>