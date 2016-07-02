<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改密码</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}

	function checkForm(){
			
		var valiReg = new RegExp("^[\\w]{4,30}$");
		
		var passwordE = document.getElementById("password");
		var password = passwordE.value;
		
		var affirmPasswordE = document.getElementById("affirmPassword");
		var affirmPassword = affirmPasswordE.value;
		
		if(isEmpty(password)){
			alert("密码不能为空");
			passwordE.focus();
			return false;
		}
		
		if(isEmpty(affirmPassword)){
			alert("确认密码不能为空");
			affirmPasswordE.focus();
			return false;
		}
	
		if(!valiReg.test(password)){
			alert("密码只能由字母或数字组成，至少为4位");
			passwordE.focus();
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
		
		return true;
	}	
	
	function updateUserPassword() {

		if(!checkForm()){
			return;
		}else{
			with(document.userForm){
				action="basedata/user/userFunc.do?command=updateUserPassword";
				method="post";
				submit();
			}
		}

	}
	
</script>
	</head>

	<body class="body1">
		<form name="userForm" target="_self" id="userForm">
			<input type="hidden" name="userId" value="${user.userId }">
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
							
							<b>基础数据管理&gt;&gt;用户维护&gt;&gt;修改密码</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>所属部门&gt;&gt;${user.department.departmentName }</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>用户名&gt;&gt;${user.username }</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					
					<tr>
						<td width="20%" height="30px" align="right">
							密码:
						</td>
						<td width="40px" align="left">
							<input name="password" type="password" class="text1" id="password"
								size="20" maxlength="20">					
						</td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">
							确认密码:
						</td>
						<td width="40px" align="left">
							<input name="affirmPassword" type="password" class="text1" id="affirmPassword"
								size="20" maxlength="20">					
						</td>
					</tr>
					
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="updateUserPassword()">
					
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
