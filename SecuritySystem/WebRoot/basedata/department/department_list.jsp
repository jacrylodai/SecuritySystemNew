<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>部门维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function saveDepartment() {
		window.location.href="<%=basePath %>"
			+"basedata/department/departmentFunc.do?command=saveDepartmentPrepare"
			+"&parentId=${parentDepartment.departmentId }";	
	}
	
	function updateDepartment() {
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
				with(document.departmentForm){
					action="basedata/department/departmentFunc.do?command=updateDepartmentPrepare"
						+"&departmentId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function deleteDepartment() {
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
			with(document.departmentForm){
				action="basedata/department/departmentFunc.do?command=deleteDepartment";
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
		<form name="departmentForm" id="departmentForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="35">
					<tr>
						<td class="p1" height="18" nowrap>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="522" class="p1" height="17" nowrap>
							<img src="images/mark_arrow_02.gif" width="14" height="14">
							&nbsp;
							<b>基础数据管理&gt;&gt;部门维护</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>上级部门&gt;&gt;${parentDepartment.departmentName }</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<c:if test="${not empty parentDepartment.parentDepartment }">
								<b><a href="basedata/department/departmentFunc.do?command=listDepartment&parentId=${parentDepartment.parentDepartment.departmentId }">
								返回上级部门列表</a></b>
							</c:if>							
						</td>
					</tr>
				</table>
			</div>
				
			<hr width="97%" align="center" size=0>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="20%">
						部门编码
					</td>
					<td class="rd6" width="40%">
						部门名称
					</td>
					<td class="rd6">
						操作
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="6">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="department" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" id="checkbox${department.departmentId }" name="selectFlag" class="checkbox1"
										value="${department.departmentId }">
								</td>
								
								<td class="rd8">
								${department.departmentId }
								</td>
								<td class="rd8">
								<c:choose>
									<c:when test="${department.level lt Department_LEVEL_DEPARTMENT }">
										<a href="basedata/department/departmentFunc.do?command=listDepartment&parentId=${department.departmentId }">
										${department.departmentName }
										</a>
									</c:when>
									<c:otherwise>
										${department.departmentName }
									</c:otherwise>
								</c:choose>
								</td>
								<td class="rd8">
								<a href="basedata/user/userFunc.do?command=listUser&departmentId=${department.departmentId }">
								用户管理</a>&nbsp;
								
								<c:choose>
									<c:when test="${department.level eq Department_LEVEL_DEPARTMENT }">
										<a href="basedata/department/departmentFunc.do?command=downloadDepartmentExcelTemplate&departmentId=${department.departmentId }">
										下载填报表模板</a>
									</c:when>
								</c:choose>
								
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
							&nbsp;共&nbsp;${pageModel.pageCount }&nbsp;页
							&nbsp;总共&nbsp;${pageModel.totalCount }&nbsp;条记录
							</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="black">当前第</font>&nbsp;
							<font color="red">${pageModel.currentPageNumber }</font>&nbsp;
							<font color="black">页</font>
						</div>
					</td>
					<td nowrap class="rd19" align="right">
					
						<pg:pager items="${pageModel.totalCount }" maxPageItems="${pageModel.pageSize }" 
			            	maxIndexPages="10" export="currentPageNumber=pageNumber"
			            	url="basedata/department/departmentFunc.do">
			            <pg:param name="command" value="listDepartment"/>
			            <pg:param name="parentId" value="${parentDepartment.departmentId }"/>
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
							
						&nbsp;
						<input name="btnAdd" type="button" class="button1" id="btnAdd"
							value="添加" onClick="saveDepartment()">&nbsp;
						<input name="btnModify" class="button1" type="button"
							id="btnModify" value="修改" onClick="updateDepartment()">&nbsp;
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteDepartment()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
