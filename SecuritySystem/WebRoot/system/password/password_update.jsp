<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改密码</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/userManager.js'></script>
		<script type="text/javascript">
		
	function validate(){
	
		var oldPasswordE = document.getElementById("oldPassword");
		var oldPassword = oldPasswordE.value;
		if(isEmpty(oldPassword)){
			alert("原密码不能为空");
			oldPasswordE.focus();
			return false;
		}
		var valiReg = new RegExp("^[\\w]{4,}$");
		if(!valiReg.test(oldPassword)){
			alert("原密码只能由字母或数字组成，至少为4位");
			oldPasswordE.focus();
			return false;
		}
		if(false == isCorrect){
			alert("原密码错误，请重新输入");			
			oldPasswordE.focus();
			return false;
		}
		
		var newPasswordE = document.getElementById("newPassword");
		var newPassword = newPasswordE.value;
		if(isEmpty(newPassword)){
			alert("新密码不能为空");
			newPasswordE.focus();
			return false;
		}
		if(!valiReg.test(newPassword)){
			alert("新密码只能由字母或数字组成，至少为4位");
			newPasswordE.focus();
			return false;
		}	
		if(oldPassword==newPassword){
			alert("新密码应该不等于原密码");
			return false;
		}
		
		var affirmNewPasswordE = document.getElementById("affirmNewPassword");
		var affirmNewPassword = affirmNewPasswordE.value;
		if(isEmpty(affirmNewPassword)){
			alert("确认密码不能为空");
			affirmNewPasswordE.focus();
			return false;
		}
		if(!valiReg.test(affirmNewPassword)){
			alert("确认密码只能由字母或数字组成，至少为4位");
			affirmNewPasswordE.focus();
			return false;
		}
		if(newPassword!=affirmNewPassword){
			alert("新密码和确认密码不一致");
			return false;
		}
		
		return true;
	}
	
	function updatePasword() {
		if(!validate()){
			return;
		}
		with(document.getElementById("userForm")){
			action = "system/password/passwordFunc.do?command=updatePassword";
			method = "post";
			submit();
		}
	}
		
	function clearPasswordMessage(){
		var msg=document.getElementById("valiPasswordMsg");
		msg.innerHTML="";
	}
	
	var isCorrect= false;
	var userId = "${sessionScope[USER_SESSION_ID].userId}";
	
	function checkUserPassword(){
		
		if(!validatePassword()){
			isCorrect = false;
			clearPasswordMessage();
			return;
		}
		
		var oldPassword = document.getElementById("oldPassword").value;
		
		userManager.checkIsUserPasswordCorrect(userId,oldPassword,
			function(result){
			
				isCorrect = result;
				
				var msg=document.getElementById("valiPasswordMsg");
				if(isCorrect){
					msg.innerHTML = "";
				}else{
					msg.innerHTML = 
						"<font color='red'>原密码错误，请重新输入</font>";
				}
			});
	}
	
	function validatePassword(){
		
		var oldPassword = document.getElementById("oldPassword").value;
		
		if(isEmpty(oldPassword)){
			return false;
		}
		var valiReg = new RegExp("^[\\w]{4,}$");
		if(!valiReg.test(oldPassword)){
			return false;
		}
		
		return true;
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
							
							<b>系统功能&gt;&gt;修改密码</b>
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<table width="98%" height="91" border="0" cellpadding="0"
					cellspacing="0" align="center">
					<tr>
						<td width="22%" height="30">
							<div align="right">
								<font color="#FF0000">*</font>原密码:
							</div>
						</td>
						<td width="78%" align="left">
							<input name="oldPassword" type="password" class="text1"
								id="oldPassword" size="25"
								onblur="checkUserPassword();">
							<span id="valiPasswordMsg">
							</span>
						</td>
					</tr>
					<tr>
						<td width="22%" height="27">
							<div align="right">
								<font color="#FF0000">*</font>新密码:
							</div>
						</td>
						<td width="78%" align="left">
							<input name="newPassword" type="password" class="text1"
								id="newPassword" size="25">
						</td>
					</tr>
					<tr>
						<td width="22%" height="34">
							<div align="right">
								<font color="#FF0000">*</font>确认密码:
							</div>
						</td>
						<td width="78%" align="left">
							<input name="affirmNewPassword" type="password" class="text1"
								id="affirmNewPassword" size="25">
						</td>
					</tr>
				</table>
				<hr width="100%" align="center" size=0>
				<p>
					<input name="btnUpdate" class="button1" type="button"
						id="btnUpdate" value="修改" onClick="updatePasword()">
				</p>
			</div>
		</form>
	</body>
</html>
