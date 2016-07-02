<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}

	function checkForm(){
					
		var roleIdSelector = document.getElementById("roleIdSelector");
		if(roleIdSelector.selectedIndex == 0){
		
			alert("请选择角色");
			return false;
		}		
		
		return true;
	}	
	
	function updateUser() {

		if(!checkForm()){
			return;
		}else{
			with(document.userForm){
				action="basedata/user/userFunc.do?command=updateUser";
				method="post";
				submit();
			}
		}

	}
	
	function pageOnLoad(){
	
		var roleIdSelector = document.getElementById("roleIdSelector");
		var optionArr = roleIdSelector.options;
		for(var i=0;i<optionArr.length;i++){
			var option = optionArr[i];
			if(option.value == ${user.role.roleId}){
				option.selected = true;
				break;
			}
		}
	}

</script>
	</head>

	<body class="body1" onload="pageOnLoad()">
		<form name="userForm" target="_self" id="userForm">
			<input type="hidden" name="userId" value="${user.userId }">
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
							<b>基础数据管理&gt;&gt;用户维护&gt;&gt;修改</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>所属部门&gt;&gt;${department.departmentName }</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>用户名:&nbsp;
						</td>
						<td width="40px" align="left">
							<input name="username" type="text" class="text1" id="username"
								size="20" maxlength="20" value="${user.username }" readonly="true">
							<span id="usernameMsg"></span>					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>角色:&nbsp;
						</td>
						<td width="40px" align="left">
							<select name="roleId" id="roleIdSelector">
								<option value="-1">请选择</option>
								<c:forEach var="role" items="${availableRoleList}">
									<option value="${role.roleId }">${role.roleName }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							责任人:&nbsp;
						</td>
						<td width="40px" align="left">
							<input name="contactPeople" type="text" class="text1" id="contactPeople"
								size="20" maxlength="20" value="${user.contactPeople }">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							手机:&nbsp;
						</td>
						<td width="40px" align="left">
							<input name="mobilePhone" type="text" class="text1" id="mobilePhone"
								size="20" maxlength="20" value="${user.mobilePhone }">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							办公电话:&nbsp;
						</td>
						<td width="40px" align="left">
							<input name="officePhone" type="text" class="text1" id="officePhone"
								size="20" maxlength="20" value="${user.officePhone }">					
						</td>
					</tr>
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="updateUser()">
					&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
