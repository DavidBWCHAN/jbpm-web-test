<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>经理审批页</title>
</head>
<body>
<%
JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
try{
	long taskId=Long.parseLong(request.getParameter("taskId"));
	TaskInstance ti = jbpmContext.getTaskInstance(taskId);
	String isSubmit = request.getParameter("isSubmit");
	if(isSubmit!=null && isSubmit.equals("true")){
		String result = request.getParameter("approve_result");
		if(ti.getDescription().equals("payment.manager.approve")){
			ti.getContextInstance().setVariable("manager_approve_result", result);
			if(result.equals("1")){
				ti.end("agree");	
			}else{
				ti.end("disagree");	
			}
			out.println("<h1>部门经理审批完成!</h1>");
		}else{
			ti.getContextInstance().setVariable("super_manager_approve_result", result);
			ti.end();
			out.println("<h1>总经理审批完成!</h1>");
		}
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
		<form action="manager-process.jsp" method="post">
		审批意见：
		<input type="radio" name="approve_result" value="1" checked="checked" /> 同意&nbsp;&nbsp;
		<input type="radio" name="approve_result" value="0" />不同意<br>
		<input type="hidden" name="isSubmit" value="true">
		<input type="hidden" name="taskId" value="<%=taskId %>">
		<input type="submit" value="提交审批结果">
		</form>
		
		<%
	}
}finally{
	jbpmContext.close();	
}
%>
</body>
</html>