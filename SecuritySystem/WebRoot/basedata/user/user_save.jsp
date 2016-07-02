<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="com.ldp.security.basedata.domain.User"%>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/userManager.js'></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}

	var isUsernameUnique = true;
	
	function checkUsernameUnique(){
	
		if(!validateUsername()){
			isUsernameUnique = true;
			clearUsernameMsg();
			return;
		}
		
		var usernameE = document.getElementById("username");
		var username = usernameE.value;
		userManager.getIsUsernameUnique(username,
		
			function(result){
				isUsernameUnique = result;
				var usernameMsg = document.getElementById("usernameMsg");
				if(!isUsernameUnique){
					usernameMsg.innerHTML = 
						"<font color='red'>用户名已经存在，请重新输入</font>";
				}else{
					usernameMsg.innerHTML = ""; 
				}
			});
	}
	
	function validateUsername(){
	
		var username = document.getElementById("username").value;
		if(isEmpty(username)){
			return false;
		}
		var valiReg = new RegExp("^[\\w]{4,30}$");
		if(!valiReg.test(username)){
			return false;
		}
		
		return true;
	}
	
	function clearUsernameMsg(){
		var usernameMsg = document.getElementById("usernameMsg");
		usernameMsg.innerHTML = "";
	}
	
	function checkForm(){
			
		var usernameE = document.getElementById("username");
		var username = usernameE.value;
		if(isEmpty(username)){
			alert("用户名不能为空");
			usernameE.focus();
			return false;
		}
		var valiReg = new RegExp("^[\\w]{4,30}$");
		if(!valiReg.test(username)){
			alert("用户名只能由字母或数字组成，至少为4位，最多30位");
			usernameE.focus();
			return false;
		}
		
		if(!isUsernameUnique){
			alert("用户名已经存在，请重新输入");
			usernameE.focus();
			return false;
		}
		
		var passwordE = document.getElementById("password");
		var password = passwordE.value;
		if(isEmpty(password)){
			alert("密码不能为空")
			passwordE.focus();
			return false;
		}
		if(!valiReg.test(password)){
			alert("密码只能由字母或数字组成，至少为4位");
			passwordE.focus();
			return false;
		}
		
		var affirmPasswordE = document.getElementById("affirmPassword");
		var affirmPassword = affirmPasswordE.value;
		if(isEmpty(affirmPassword)){
			alert("确认密码不能为空");
			affirmPasswordE.focus();
			return false;
		}
		if(!valiReg.test(affirmPassword)){
			alert("确认密码只能由字母或数字组成，至少为4位");
			affirmPasswordE.focus();
			return false;
		}
		if(password!=affirmPassword){
			alert("密码和确认密码不一致");
			return false;
		}
		
		var roleIdSelector = document.getElementById("roleIdSelector");
		if(roleIdSelector.selectedIndex == 0){
		
			alert("请选择角色");
			return false;
		}		
		
		return true;
	}	
	
	function saveUser() {

		if(!checkForm()){
			return;
		}else{
			with(document.userForm){
				action="basedata/user/userFunc.do?command=saveUser";
				method="post";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="userForm" target="_self" id="userForm">
			<input type="hidden" name="departmentId" value="${param.departmentId }">
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
							
							<b>基础数据管理&gt;&gt;用户维护&gt;&gt;添加</b>
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
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>用户名:
						</td>
						<td width="40px" align="left">
							<input name="username" type="text" class="text1" id="username"
								onblur="checkUsernameUnique()" size="20" maxlength="20">
							<span id="usernameMsg"></span>					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>密码:
						</td>
						<td width="40px" align="left">
							<input name="password" type="password" class="text1" id="password"
								size="20" maxlength="20">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>确认密码:
						</td>
						<td width="40px" align="left">
							<input name="affirmPassword" type="password" class="text1" id="affirmPassword"
								size="20" maxlength="20">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>角色:
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
							责任人:
						</td>
						<td width="40px" align="left">
							<input name="contactPeople" type="text" class="text1" id="contactPeople"
								size="20" maxlength="20">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							手机:
						</td>
						<td width="40px" align="left">
							<input name="mobilePhone" type="text" class="text1" id="mobilePhone"
								size="20" maxlength="20">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							办公电话:
						</td>
						<td width="40px" align="left">
							<input name="officePhone" type="text" class="text1" id="officePhone"
								size="20" maxlength="20">					
						</td>
					</tr>
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="saveUser()">
					
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
