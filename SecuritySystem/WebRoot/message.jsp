<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.ldp.security.util.constants.Constants"%>
<%@ include file="common/common.jsp"%>
<%	
	String message = (String)request.getAttribute(Constants.MESSAGE_KEY);
	String redirectUrl = (String)request.getAttribute(Constants.REDIRECT_URL_KEY);
	String redirectUrlFullPath = basePath + redirectUrl;
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<base href="<%=basePath%>">
		<link rel="stylesheet" href="style/drp.css" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>无标题文档</title>
		<script type="text/javascript">
		function pageOnLoad(){
			window.setTimeout(refresh,2000);
		}
		
		function refresh(){
		
			window.location.href="<%=redirectUrlFullPath%>";		
		}
		</script>
	</head>

	<body class="body1" onload="pageOnLoad()">
		<span id="warningMsg">
		<font color="red"><%=message %></font>
		，将在2秒钟后刷新，或点击
		<a href="<%=redirectUrlFullPath%>">立即刷新</a>
		</span>
	</body>
</html>
