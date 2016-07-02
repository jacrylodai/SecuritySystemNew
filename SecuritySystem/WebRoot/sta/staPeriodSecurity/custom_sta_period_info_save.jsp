<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加</title>
		<link rel="stylesheet" href="style/drp.css">
		<script language="javascript" type="text/javascript" src="datePicker/WdatePicker.js"></script>
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
	function checkForm(){
			
		var startDateString = document.getElementById("startDateString").value;
		if(isEmpty(startDateString)){
			alert("报表起始日期不能为空");
			return false;
		}
		
		var endDateString = document.getElementById("endDateString").value;
		if(isEmpty(endDateString)){
			alert("报表结束日期不能为空");
			return false;
		}
		
		if(compareDate(startDateString,endDateString) == 1){
			alert("报表起始日期必须小于或等于报表终止日期");
			return false;
		}
		
		return true;
	}	
	
	function saveStaPeriodInfo() {

		if(!checkForm()){
			return;
		}else{
			var btnAdd = document.getElementById("btnAdd");
			btnAdd.disabled = true;
			
			with(document.staPeriodInfoForm){
				action="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=saveCustomStaPeriodInfo";
				method="post";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="staPeriodInfoForm" target="_self" id="staPeriodInfoForm">
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
							<b>统计管理&gt;&gt;自定义统计维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>被统计部门&gt;&gt;${department.departmentName }</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>统计人&gt;&gt;${staUser.username }</b>
						</td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<h1>自定义周期选择</h1>   
				
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					
					<tr>
						<td width="40%" height="30px" align="right">
							<font color="#FF0000">*</font>统计起始日期:&nbsp;
						</td>
						<td width="40px" align="left">
							<input id="startDateString" name="startDateString"
								 class="Wdate" type="text" onClick="WdatePicker()"
								 >
						</td>
					</tr>
					
					<tr>
						<td width="40%" height="30px" align="right">
							<font color="#FF0000">*</font>统计终止日期:&nbsp;
						</td>
						<td width="40px" align="left">
							<input id="endDateString" name="endDateString"
								 class="Wdate" type="text" onClick="WdatePicker()"
								 >
						</td>
					</tr>
					
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="saveStaPeriodInfo()">
					&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
