<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="include.jsp"%>
<%@ page import="org.dom4j.*"%>
<%@ page import="org.dom4j.xpath.*"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看流程图</title>
</head>
<body>
<%!
//根据父Token取出当前流程所有的字Token
private void findAllTokens(Token parent, List allTokens){
	Map children =parent.getChildren();
	if(children !=null && children.size()>0){
		Collection childTokens = children.values();
		for(Iterator iterator = childTokens.iterator();iterator.hasNext();){
			Token child = (Token) iterator.next();
			findAllTokens(child, allTokens);
		}
	}
	allTokens.add(parent);
}

//找到当前流程的Root Token
private Token findRootToken(Token token){
	if(token.getParent()!=null){
		token=token.getParent();
		findRootToken(token);
	}
	return token;
}

%>
<%
String tokenId= request.getParameter("tokenId");
if(tokenId==null){
	throw new IllegalArgumentException("tokenId can not be null!");
}
JbpmContext jbpmContext = JbpmConfiguration.getInstance().createJbpmContext();
try{
	Token currentToken = jbpmContext.getToken(Long.parseLong(tokenId));
	String currentNodeName  = currentToken.getNode().getName();
	ProcessDefinition pd = currentToken.getProcessInstance().getProcessDefinition();
	out.println("以下为流程:"+pd.getName()+"的进度图:");
	byte[] gpdBytes = pd.getFileDefinition().getBytes("gpd.xml");
	//将gpd.xml的二进制内容解析成xml结构化数据
	Element gpdRootElement = DocumentHelper.parseText(new String (gpdBytes)).getRootElement();
	
	//取出图片的宽和高
	int[] imageDimension = new int[2];
	imageDimension[0]=Integer.parseInt(gpdRootElement.attributeValue("width"));
	imageDimension[1]=Integer.parseInt(gpdRootElement.attributeValue("height"));
	
	//定义生成流程图片的URL
	String imageUrl = request.getContextPath()+"/jbpmProcessImageServlet?processDefinitionId="+pd.getId();
	
	//输出背景图片为流程图的DIV
	out.println("<div style=\"position:relative; background-image:url("+imageUrl + "); width:" + imageDimension[0] + "px;height:" + imageDimension[1] + "px;\">");
	
	
	List allTokenList = new ArrayList();
	Token rootToken=findRootToken(currentToken);
	findAllTokens(rootToken, allTokenList);
	for(int i=0; i<allTokenList.size();i++){
		Token token=(Token)allTokenList.get(i);
		String curNodeColor="green";
		if(token.equals(currentToken)){
			curNodeColor="red";
		}
		//取出当前节点所在位置（x,y）及节点尺寸
		int[] currentNodeDimension = new int[4];
		String xpathStr = "//node[@name='" + token.getNode().getName() + "']";//使用xpath来查找gpd.xml里的当前节点
		XPath xpath = new DefaultXPath(xpathStr);
		Element currentXmlNode = (Element) xpath.selectSingleNode(gpdRootElement);
		currentNodeDimension[0]=Integer.parseInt(currentXmlNode.attributeValue("width"));
		currentNodeDimension[1]=Integer.parseInt(currentXmlNode.attributeValue("height"));
		currentNodeDimension[2]=Integer.parseInt(currentXmlNode.attributeValue("x"));
		currentNodeDimension[3]=Integer.parseInt(currentXmlNode.attributeValue("y"));
		
		//输出当前token所在节点位置
		out.println("<div style=\"position:absolute; left:" + currentNodeDimension[2] + 
		"px;top:" + currentNodeDimension[3] + "px;width:" +(currentNodeDimension[0]-6) + 
		"px;height:" + (currentNodeDimension[1]-6) + "px;border:5px solid "+ curNodeColor + "\">");
		out.println("</div>");	
	}
	out.println("</div>");	
}finally{
	jbpmContext.close();
}
%>
</body>
</html>