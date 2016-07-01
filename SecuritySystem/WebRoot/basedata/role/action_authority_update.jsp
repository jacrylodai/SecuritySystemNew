<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>设置访问资源权限</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
	function checkForm(){
	
		return true;
	}

	function updateActionAuthority() {

		if(!checkForm()){
			return;
		}else{
			with(document.authorityForm){
				action="basedata/role/roleFunc.do?command=updateActionAuthority";
				method="post";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="authorityForm" target="_self" id="authorityForm">
			<input type="hidden" name="roleId" value="${role.roleId }">
			<div align="center">
				<table width="95%" border="0" cellspacing="2" cellpadding="2">
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;角色维护&gt;&gt;设置访问资源权限</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>关联的角色：${role.roleName }</b>
						</td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<c:set var="tdShortWidth" value="12" scope="page"></c:set>
				<c:set var="tdBaseWidth" value="40" scope="page"></c:set>
				<c:set var="tdLongWidth" value="20" scope="page"></c:set>
				
				<table width="95%" border="1" cellspacing="0" cellpadding="0"
					align="center" class="table1">
					<tr>
						<td class="rd6" width="${tdBaseWidth }%">
							资源名称
						</td>
						<td class="rd6" width="${tdShortWidth }%">
							添加权限
						</td>
						<td class="rd6" width="${tdShortWidth }%">
							读取权限
						</td>
						<td class="rd6" width="${tdShortWidth }%">
							修改权限
						</td>
						<td class="rd6" width="${tdShortWidth }%">
							删除权限
						</td>
						<td class="rd6">
							其他权限
						</td>
					</tr>
					
					<c:forEach var="topResource" items="${resourceTree}">
							
						<tr>
							<td class="rd7">${topResource.resourceName }</td>
							
						</tr>
						
						<c:forEach var="subResource" items="${topResource.subResourceList}">
							
							<input type="hidden" 
								name="resourceId_${subResource.resourceId }_authorityId" 
								value="${authorityMap[subResource.resourceId].roleResourceAuthorityId }">
							
							<tr>
								<td class="rd7">———${subResource.resourceName }</td>
																
								<td class="rd8">
								<input type="checkbox" name="resourceId_${subResource.resourceId }_create"
									${authorityMap[subResource.resourceId].create ? 'checked':'' }>
								</td>
								
								<td class="rd8">
								<input type="checkbox" name="resourceId_${subResource.resourceId }_retrieve"
									${authorityMap[subResource.resourceId].retrieve ? 'checked':'' }>
								</td>
								
								<td class="rd8">
								<input type="checkbox" name="resourceId_${subResource.resourceId }_update"
									${authorityMap[subResource.resourceId].update ? 'checked':'' }>
								</td>
								
								<td class="rd8">
								<input type="checkbox" name="resourceId_${subResource.resourceId }_delete"
									${authorityMap[subResource.resourceId].delete ? 'checked':'' }>
								</td>
								
								<td class="rd8">
								<input type="checkbox" name="resourceId_${subResource.resourceId }_other"
									${authorityMap[subResource.resourceId].other ? 'checked':'' }>
								</td>
								
							</tr>
							
						</c:forEach>
					</c:forEach>
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="updateActionAuthority()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
