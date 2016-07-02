<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>用户维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function saveUser() {
		window.location.href="<%=basePath %>"
			+"basedata/user/userFunc.do?command=saveUserPrepare"
			+"&departmentId=${param.departmentId }";	
	}
	
	function updateUser() {
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
				with(document.userForm){
					action="basedata/user/userFunc.do?command=updateUserPrepare"
						+"&userId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function updateUserPassword() {
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
				with(document.userForm){
					action="basedata/user/userFunc.do?command=updateUserPasswordPrepare"
						+"&userId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function deleteUser() {
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
			with(document.userForm){
				action="basedata/user/userFunc.do?command=deleteUser"
					+"&departmentId=${param.departmentId }";
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
		<form name="userForm" id="userForm">
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
							
							<b>基础数据管理&gt;&gt;用户维护</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>所属部门&gt;&gt;${department.departmentName }</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<b><a href="basedata/department/departmentFunc.do?command=listDepartment&parentId=${department.parentDepartment.departmentId }">
							返回部门列表</a></b>
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
					<td class="rd6" width="18%">
						用户名
					</td>
					<td class="rd6" width="18%">
						角色
					</td>
					<td class="rd6" width="18%">
						责任人
					</td>
					<td class="rd6" width="18%">
						手机
					</td>
					<td class="rd6">
						办公电话
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="6">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="user" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" id="checkbox${user.userId }" name="selectFlag" class="checkbox1"
										value="${user.userId }">
								</td>
								<td class="rd8">
									${user.username }
								</td>
								<td class="rd8">
									${user.role.roleName }
								</td>
								<td class="rd8">
									${user.contactPeople }
								</td>
								<td class="rd8">
									${user.mobilePhone }
								</td>
								<td class="rd8">
									${user.officePhone }
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
			            	url="basedata/user/userFunc.do">
			            <pg:param name="command" value="listUser"/>
			            <pg:param name="departmentId" value="${param.departmentId }"/>
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
							value="添加" onClick="saveUser()">
						<input name="btnModify" class="button1" type="button"
							id="btnModify" value="修改" onClick="updateUser()">
						<input name="btnModifyPassword" class="button1" type="button"
							id="btnModifyPassword" value="修改密码" 
							onClick="updateUserPassword()">
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteUser()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
