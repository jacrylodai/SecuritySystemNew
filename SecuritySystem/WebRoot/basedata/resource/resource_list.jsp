<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>资源维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function viewResource() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		var selectedId;
		for(var i=0;i<checkFlags.length;i++){
			var checkElt = checkFlags[i];
			if(checkElt.checked){
				count++;
				selectedId = checkElt.value;
			}
		}
		if(count<1){
			alert("没有选择数据");
		}else
			if(count>1){
				alert("只能选择一条数据");
			}else{
				with(document.resourceForm){
					action="basedata/resource/resourceFunc.do?command=viewResource"
						+"&resourceId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function saveResource() {
		with(document.resourceForm){
			action="basedata/resource/resourceFunc.do?command=saveResourcePrepare";
			method="post";
			submit();
		}
	}
	
	function updateResource() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		var selectedId;
		for(var i=0;i<checkFlags.length;i++){
			var checkElt = checkFlags[i];
			if(checkElt.checked){
				count++;
				selectedId = checkElt.value;
			}
		}
		if(count<1){
			alert("没有选择数据");
		}else
			if(count>1){
				alert("只能选择一条数据");
			}else{
				with(document.resourceForm){
					action="basedata/resource/resourceFunc.do?command=updateResourcePrepare"
						+"&resourceId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function deleteResource() {
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
			with(document.resourceForm){
				action="basedata/resource/resourceFunc.do?command=deleteResource";
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
		<form name="resourceForm" id="resourceForm">
			<input type="hidden" name="resourceType" value="${param.resourceType }">
			<input type="hidden" name="parentResourceId" value="${param.parentResourceId }">
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
							
							<b>基础数据管理&gt;&gt;资源维护</b>
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
								<c:when test="${param.resourceType == RESOURCE_TYPE_MENU}">菜单资源
								</c:when>								
								<c:when test="${param.resourceType == RESOURCE_TYPE_ACTION_RESOURCE}">Action请求访问资源
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
				
				<c:if test="${parentResource.resourceLevel gt RESOURCE_LEVEL_ROOT }">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
								<b><a href="basedata/resource/resourceFunc.do?command=listResource&resourceType=${param.resourceType }&parentResourceId=${parentResource.parentResource.resourceId }">
								返回上级资源列表</a></b>
													
						</td>
					</tr>
				</table>
				</c:if>	
			</div>
				
			<hr width="97%" align="center" size=0>
			
			<c:set var="tdShortWidth" value="5" scope="page"></c:set>
			<c:set var="tdBaseWidth" value="70" scope="page"></c:set>
			<c:set var="tdLongWidth" value="20" scope="page"></c:set>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						资源名称
					</td>
					<td class="rd6">
						排序号
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="3">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="resource" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" id="checkbox${resource.resourceId }" name="selectFlag" class="checkbox1"
										value="${resource.resourceId }">
								</td>
								<td class="rd8">
								
								<c:choose>
									<c:when test="${resource.resourceLevel lt RESOURCE_LEVEL_SECOND_RESOURCE}">
									<a href="basedata/resource/resourceFunc.do?command=listResource&resourceType=${param.resourceType }&parentResourceId=${resource.resourceId }">${resource.resourceName }</a>
									</c:when>
									<c:otherwise>
									${resource.resourceName }
									</c:otherwise>
								</c:choose>
								
								</td>
								<td class="rd8">
								${resource.orderNumber }
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
			            	url="basedata/resource/resourceFunc.do">
			            <pg:param name="command" value="listResource"/>
			            <pg:param name="resourceType" value="${param.resourceType }"/>
			            <pg:param name="parentResourceId" value="${param.parentResourceId}"/>
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
							
						
						<input name="btnView" type="button" class="button1" id="btnView"
							value="查看" onClick="viewResource()">
						<input name="btnAdd" type="button" class="button1" id="btnAdd"
							value="添加" onClick="saveResource()">
						<input name="btnUpdate" type="button" class="button1" id="btnUpdate"
							value="修改" onClick="updateResource()">
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteResource()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
