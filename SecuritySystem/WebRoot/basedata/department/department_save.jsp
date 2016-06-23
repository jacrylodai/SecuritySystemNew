<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
	function goBack() {
		history.back(1);
	}

	function checkForm(){
		
		var departmentName=document.getElementById("departmentName").value;
		if(isEmpty(departmentName)){
			alert("部门名称不能为空！")
			return false;
		}	
		
		return true;
	}	
	
	function saveDepartment() {

		if(!checkForm()){
			return;
		}else{
			with(document.departmentForm){
				action="basedata/department/departmentFunc.do?command=saveDepartment";
				method="post";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="departmentForm" target="_self" id="departmentForm">
			<input type="hidden" name="parentId" value="${parentDepartment.departmentId }">
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
							<b>基础数据管理&gt;&gt;车间维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>所属上级部门&gt;&gt;${parentDepartment.departmentName }</b>
						</td>
					</tr>
				</table>
				<hr width="97%" align="center" size=0>
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">
							<font color="#FF0000">*</font>部门名称:&nbsp;
						</td>
						<td width="40px" align="left">
							<input name="departmentName" type="text" class="text1" id="departmentName"
								size="20" maxlength="20">					
						</td>
					</tr>
					
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="saveDepartment()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
