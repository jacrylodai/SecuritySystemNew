<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>角色维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function saveRole() {
		window.location.href="<%=basePath %>"
			+"basedata/role/roleFunc.do?command=saveRolePrepare";
	}
	
	function deleteRole() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		for(var i=0;i<checkFlags.length;i++){
			if(checkFlags[i].checked){
				count++
			}
		}
		if(count==0){
			alert("你没有选择数据");
			return;
		}
		if(!window.confirm("你确定要删除这些数据")){
			return;
		}
		if(count>0){
			with(document.roleForm){
				action="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=deleteRole";
				method="post";
				submit();
			}
		}
	}
			
	function checkAll() {
		var ifAll=document.getElementById("ifAll");
		var checkFlags=document.getElementsByName("selectFlag");
		for(var i=0;i<checkFlags.length;i++){
			checkFlags[i].checked=ifAll.checked;
		}
	}
	
</script>
	</head>

	<body class="body1">
		<form name="roleForm" id="roleForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="35">
					<tr>
						<td class="p1" height="18" nowrap>
							
						</td>
					</tr>
					<tr>
						<td width="522" class="p1" height="17" nowrap>
							<img src="images/mark_arrow_02.gif" width="14" height="14">
							
							<b>基础数据管理&gt;&gt;角色维护</b>
						</td>
					</tr>
				</table>
			</div>
				
			<hr width="97%" align="center" size=0>
			
			<c:set var="tdShortWidth" value="9" scope="page"></c:set>
			<c:set var="tdBaseWidth" value="33" scope="page"></c:set>
			<c:set var="tdLongWidth" value="20" scope="page"></c:set>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						角色名称
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						角色类别
					</td>
					<td class="rd6">
						操作
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="7">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="role" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" id="checkbox${role.roleId }" name="selectFlag" class="checkbox1"
										value="${role.roleId }">
								</td>
								<td class="rd8">
								${role.roleName }
								</td>
								<td class="rd8">
								${role.roleType.dataDictName }
								</td>
								<td class="rd8">
								<a href="basedata/role/roleFunc.do?command=updateMenuAuthorityPrepare&roleId=${role.roleId }">设置菜单权限</a>
								<a href="basedata/role/roleFunc.do?command=updateActionAuthorityPrepare&roleId=${role.roleId }">设置访问资源权限</a>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2">
						<div align="left">
							<font color="black">
							共${pageModel.pageCount }页
							总共${pageModel.totalCount }条记录
							</font>
							<font color="black">当前第</font>
							<font color="red">${pageModel.currentPageNumber }</font>
							<font color="black">页</font>
						</div>
					</td>
					<td nowrap class="rd19" align="right">
					
						<pg:pager items="${pageModel.totalCount }" maxPageItems="${pageModel.pageSize }" 
			            	maxIndexPages="10" export="currentPageNumber=pageNumber"
			            	url="basedata/role/roleFunc.do">
			            <pg:param name="command" value="listRole"/>
						<pg:first>
							<a href="${pageUrl }">首页</a>
						</pg:first>
						<pg:prev>
							<a href="${pageUrl }">前页</a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber == pageNumber }">
								<font color="red">${pageNumber }</font>
								</c:when>
								<c:otherwise>
								<a href="${pageUrl }">${pageNumber }</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a href="${pageUrl }">后页</a>
						</pg:next>
						<pg:last>	
							<a href="${pageUrl }">尾页</a>
						</pg:last>
						</pg:pager>	
							
						
						<input name="btnAdd" type="button" class="button1" id="btnAdd"
							value="添加" onClick="saveRole()">
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteRole()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
