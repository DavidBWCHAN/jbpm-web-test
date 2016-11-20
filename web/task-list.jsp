<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="include.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>我的待办任务列表</title>
</head>
<body>
<table width="100%" border="1" cellpadding="0" cellspacing="0">
	<tr bgcolor="#FDFDD0">
		<td>任务名称</td><td>创建时间</td><td>流程图</td><td>操作</td>
	</tr>
<%
JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
try{
	String currentperson = request.getSession().getAttribute("loginuser").toString();
	List<TaskInstance> taskList = jbpmContext.getTaskList(currentperson);
	SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	for(Iterator iter = taskList.iterator();iter.hasNext();){
		out.println("<tr>");
		TaskInstance ti = (TaskInstance)iter.next();
		out.println("<td>" + ti.getName()+"</td>");
		out.println("<td>" + sd.format(ti.getCreate())+"</td>");
		out.println("<td><a href=\"process-image.jsp?tokenId=" + ti.getToken().getId()+"\"target=\"_blank\"\">查看流程进度图</a></td>");
		
		String description = ti.getDescription();
		String url = "cashier-process.jsp";
		if(description.equals("payment.manager.approve") || description.equals("payment.super.manager.approve")){
			url="manager-process.jsp";
		}else if(description.equals("approve.purchase.apply")){
			url="manager-approve-purchase.jsp";
		}else if(description.equals("cancel.purchase.apply")){
			url="cancel-apply.jsp";
		}else if(description.equals("mod.purchase.apply")){
			url="write-purchase.jsp";
		}
		url+="?taskId=" + ti.getId();
		if(ti.getVariable("purchaseId")!=null){
			url+="&purchaseId="+ti.getVariable("purchaseId");
		}
		out.println("<td><a href=\"" + url + "\">处理该任务</a></td>");
		out.println("</tr>");
	}
}finally{
	jbpmContext.close();
}
%>
</table>
</body>
</html>