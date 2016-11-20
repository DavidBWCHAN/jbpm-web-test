<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>财务处理页</title>
</head>
<body>
<%
JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
try{
	long taskId=Long.parseLong(request.getParameter("taskId"));
	TaskInstance ti = jbpmContext.getTaskInstance(taskId);
	String isSubmit = request.getParameter("isSubmit");
	if(isSubmit!=null && isSubmit.equals("true")){
		ti.end();
		out.println("<h1>财务处理完成!</h1>");
	}else{
		String title=ti.getVariable("title").toString();
		String money_count = ti.getVariable("money_count").toString();
		String remark = ti.getVariable("remark").toString();
		String issueperson = ti.getVariable("issue_person").toString();	
		%>
		报销人:<%=issueperson %><br>
		报销主题:<%=title %><br>
		报销金额:<%=money_count %><br>
		报销说明:<%=remark %><br>
		<form action="cashier-process.jsp" method="post">
		<input type="hidden" name="isSubmit" value="true">
		<input type="hidden" name="taskId" value="<%=taskId %>">
		<input type="submit" value="财务处理完成">
		</form>
		<%
	}
		
}finally{
	jbpmContext.close();	
}
%>
</body>
</html>