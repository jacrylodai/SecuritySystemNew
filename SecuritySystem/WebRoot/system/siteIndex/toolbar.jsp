<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Untitled Document</title>
		<link rel="stylesheet" type="text/css" href="style/drp.css">

		<script language="JavaScript">


var dateMsgE;

function pageOnLoad(){
	dateMsgE = document.getElementById("dateMsg");
	showTimeRefresh()
}

function showTimeRefresh(){
	var s="";
	var currDate=new Date();
	var year = currDate.getYear();
	s += year+"年";
	var month = currDate.getMonth()+1;
	s += month +"月";
	var day = currDate.getDate();
	s+= day+"日 ";
	s += currDate.getHours()+":";
	s += currDate.getMinutes()+":";
	s += currDate.getSeconds();
	dateMsgE.innerHTML=s;
	window.setTimeout(showTimeRefresh,1000);
}

function logout(){

	window.top.location.href="<%=basePath %>system/login/loginFunc.do?command=logout";
}

</script>
	</head>

	<body class="body1" topmargin="0" leftmargin="0" onload="pageOnLoad()">
		<table width="100%" height="100%" border="0" cellpadding="0"
			cellspacing="0" bgcolor="#C0C0C0">
			<tr>
				<td width="5%" nowrap>
					
				</td>				
				<td width="30%">
					当前时间：<span id="dateMsg"></span>
				</td>
				<td width="20%">
					用户名:${sessionScope[USER_SESSION_ID].username }
				</td>
				<td width="20%">
					部门名称:${sessionScope[USER_SESSION_ID].department.departmentName }
				</td>
				
				<td width="15%">
					角色:${sessionScope[USER_SESSION_ID].role.roleName }
				</td>
				<td align="center">
					<a href="javascript:logout();">注销</a>
				</td>
			</tr>
		</table>
	</body>
</html>
