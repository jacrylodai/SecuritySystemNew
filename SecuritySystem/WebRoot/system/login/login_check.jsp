<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<HTML>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<TITLE>系统登录</TITLE>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<SCRIPT language=JavaScript>
		function init(){
			if(window.top!=window){
				window.top.location.href = window.location.href;
			}
			var userIdE=document.getElementById("username");
			userIdE.focus();
		}

		function nextInput(e,nextEId){
			var keyNum;
			
			if(window.event!=null){
				keyNum=e.keyCode;
			}else{
				keyNum=e.which;
			}
			
			if(keyNum==13){
				var nextE=document.getElementById(nextEId);
				nextE.focus();
			}
			return true;
		}

		function enterToSubmit(e){
			var keyNum;
			
			if(window.event!=null){
				keyNum=e.keyCode;
			}else{
				keyNum=e.which;
			}
			
			if(keyNum==13){
				submitForm();
			}
			return true;
		}
		
		function validate(){
				
			var usernameE = document.getElementById("username");
			var username = usernameE.value;
			if(isEmpty(username)){
				alert("用户名不能为空");
				usernameE.focus();
				return false;
			}
			var valiReg = new RegExp("^[\\w]{4,}$");
			if(!valiReg.test(username)){
				alert("用户名只能由字母或数字组成，至少为4位");
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
			
			return true;
		}

		function submitForm()
		{
			if(!validate()){
				return;
			}
			with(document.getElementById("loginForm")){
				action="system/login/loginFunc.do?command=loginCheck";
				method="post";
				submit();
			}
		}
</SCRIPT>

		<META content="MSHTML 6.00.2800.1264" name=GENERATOR>
	</HEAD>
	<BODY leftMargin=0 topMargin=0 onload="init()">
		<%@ include file="../../title_template.jsp" %>
		<FORM id="loginForm" name="loginForm">
			<TABLE height="80%" cellSpacing=0 cellPadding=0 width="100%" border=0>
				<TBODY>
					
					<tr>				
						<td align="center" height="30%">
							<html:messages id="msg" message="false">
								<div class="errorText">
								<bean:write name="msg"/>
								</div>
							</html:messages>
						</td>
					</tr>	
					<TR>
						<!-- The login dialog -->
						<TD vAlign="top" align=middle width="100%" height="50%">
							<!-- login dialog -->
							<TABLE cellSpacing=0 cellPadding=0 border=0>
								<!-- title bar -->
								<TBODY>
														
									<TR>
										<TD width=8>
											<IMG height=9 src="images/top_left.gif" width=8 border=0>
										</TD>
										<TD background="images/top.gif" colSpan=3>
											<IMG height=9 src="images/spacer.gif" width=1 border=0>
										</TD>
										<TD width=11>
											<IMG height=9 src="images/top_right.gif" width=11 border=0>
										</TD>
									</TR>
									<TR>
										<TD width=8 background="images/left.gif">
											<IMG height=15 src="images/spacer.gif" width=8 border=0>
										</TD>
										<TD align=left width=140 bgColor=#000033 colSpan=3>
											<FONT face="verdana, arial, helvetica, sans-serif"
												color=#ffffff size=-1><B>登录系统</B> </FONT>
										</TD>
										<TD width=11 background="images/right.gif">
											<IMG height=15 src="images/spacer.gif" width=11 border=0>
										</TD>
									</TR>
									<TR>
										<TD width=8>
											<IMG height=9 src="images/middle_left.gif" width=8 border=0>
										</TD>
										<TD background="images/top.gif" colSpan=3>
											<IMG height=9 src="images/spacer.gif" width=1 border=0>
										</TD>
										<TD width=11>
											<IMG height=9 src="images/middle_right.gif" width=11 border=0>
										</TD>
									</TR>
									<TR bgColor=white>
										<TD width=8 background="images/left.gif">
											<IMG height=1 src="images/spacer.gif" width=8 border=0>
										</TD>
										<TD colSpan=3>
											<TABLE cellSpacing=5 cellPadding=0 align=center
												background="images/login1x1.gif" border=0 valign="middle">
												<TBODY>
													<TR>
														<TD align="right">
															用户名:
														</TD>
														<TD align="left">
															<INPUT id="username" name="username" type="text" size="20" maxlength="20"
																value="${loginForm.username }"
															 	onkeyup="return nextInput(event,'password')">
														</TD>
													</TR>
													<TR>
														<TD align="right">
															密码:
														</TD>
														<TD align="left">
															<INPUT id="password" name="password" type="password" size="20"
																maxlength="20"
															 	onkeyup="return enterToSubmit(event)">
														</TD>
													</TR>
												</TBODY>
											</TABLE>
										</TD>
										<TD width=8 background="images/right.gif">
											<IMG height=1 src="images/spacer.gif" width=8 border=0>
										</TD>
									</TR>
									<!-- end of inner main area, right side -->
									<!-- inner button bar -->
									<TR>
										<TD width=8>
											<IMG height=6 src="images/command_top_left.gif" width=8
												border=0>
										</TD>
										<TD background="images/command_top.gif" colSpan=3>
											<IMG height=6 src="images/spacer.gif" border=0>
										</TD>
										<TD width=11>
											<IMG height=6 src="images/command_top_right.gif" width=11
												border=0>
										</TD>
									</TR>
									<TR bgColor=#cccccc>
										<TD width=8>
											<IMG height=20 src="images/command_mid_left.gif" width=8
												border=0>
										</TD>
										<TD>
										</TD>
										<!-- command buttons -->
										<TD align=right colSpan=2>
															<input type="button" id="submitBtn" value="登录" 
																onclick="submitForm()">
										</TD>
										<TD width=11>
											<IMG height=20 src="images/command_mid_right.gif" width=11
												border=0>
										</TD>
									</TR>
									<TR>
										<TD width=8>
											<IMG height=8 src="images/command_bottom_left.gif" width=8
												border=0>
										</TD>
										<TD background="images/command_bottom.gif" colSpan=3>
											<IMG height=8 src="images/spacer.gif" width=1 border=0>
										</TD>
										<TD width=11>
											<IMG height=8 src="images/command_bottom_right.gif" width=11
												border=0>
										</TD>
									</TR>
									
									
								</TBODY>
							</TABLE>
						</TD>
					</TR>
	
					<tr>
						<td align="center" height="50">
						<a href="help.jsp">关于</a>
						</td>
					</tr>	
				</TBODY>
			</TABLE>
			<CENTER></CENTER>
		</FORM>
	</BODY>
</HTML>
