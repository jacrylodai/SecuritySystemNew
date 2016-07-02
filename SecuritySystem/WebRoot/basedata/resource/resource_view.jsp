<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
</script>
	</head>

	<body class="body1">
		<form name="resourceForm" target="_self" id="resourceForm">
			<input type="hidden" name="resourceType" value="${param.resourceType }">
			<input type="hidden" name="parentResourceId" value="${param.parentResourceId }">
			<input type="hidden" name="resourceId" value="${resource.resourceId }">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>基础数据管理&gt;&gt;资源维护&gt;&gt;查看</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>资源类型&gt;&gt;
							<c:choose>
								<c:when test="${param.resourceType == RESOURCE_TYPE_MENU}">
								菜单资源
								</c:when>								
								<c:when test="${param.resourceType == RESOURCE_TYPE_ACTION_RESOURCE}">
								Action请求访问资源
								</c:when>
							</c:choose>
							</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>父资源&gt;&gt;${parentResource.resourceName }</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>资源名称:
						</td>
						<td align="left">
							<input name="resourceName" type="text" class="text1"
							 id="resourceName" size="20" maxlength="20"
							 value="${resource.resourceName }">
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>资源Url地址:
						</td>
						<td align="left">
							<input name="resourceUrlPath" type="text" class="text1"
							 id="resourceUrlPath" size="50"
							 value="${resource.resourceUrlPath }">
						</td>
					</tr>
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>排序号:
						</td>
						<td align="left">
							<input name="orderNumber" type="text" class="text1"
							 id="orderNumber" size="20" maxlength="20"
							 value="${resource.orderNumber }">
						</td>
					</tr>
				</table>
				
				<div align="center">
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
