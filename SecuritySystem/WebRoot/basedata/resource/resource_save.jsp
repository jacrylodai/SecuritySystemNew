<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
	function checkForm(){
	
		var resourceName = document.getElementById("resourceName").value;
		if(isEmpty(resourceName)){
			alert("资源名称不能为空");
			return false;
		}
		
		var resourceUrlPath = document.getElementById("resourceUrlPath").value;
		if(isEmpty(resourceUrlPath)){
			alert("资源Url地址不能为空");
			return false;
		}
		
		var orderNumber = document.getElementById("orderNumber").value;
		if(isEmpty(orderNumber)){
			alert("排序号不能为空");
			return false;
		}
		if(!isPositiveInteger(orderNumber)){
			alert("排序号必须是正整数");
			return false;
		}
		
		return true;
	}

	function saveResource() {

		if(!checkForm()){
			return;
		}else{
			with(document.resourceForm){
				action="basedata/resource/resourceFunc.do?command=saveResource";
				method="post";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="resourceForm" target="_self" id="resourceForm">
			<input type="hidden" name="resourceType" value="${param.resourceType }">
			<input type="hidden" name="parentResourceId" value="${param.parentResourceId }">
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
							
							<b>基础数据管理&gt;&gt;资源维护&gt;&gt;添加</b>
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
							 id="resourceName" size="20" maxlength="20">
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>资源Url地址:
						</td>
						<td align="left">
							<input name="resourceUrlPath" type="text" class="text1"
							 id="resourceUrlPath" size="50">
						</td>
					</tr>
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>排序号:
						</td>
						<td align="left">
							<input name="orderNumber" type="text" class="text1"
							 id="orderNumber" size="20" maxlength="20">
						</td>
					</tr>
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="saveResource()">
					
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
