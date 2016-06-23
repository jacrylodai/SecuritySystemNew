<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>上传</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript" src="script/client_validate.js"
			 charset="UTF-8"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
	function checkForm(){
	
		var securityFormFileName = document.getElementById("securityFormFile").value;
		if(isEmpty(securityFormFileName)){
			alert("你还没有选择要上传的文件");
			return false;
		}
		
		var fileNameSuffix = getFileNameSuffix(securityFormFileName);
		if(fileNameSuffix!="xls" && fileNameSuffix!="zip"){
			alert("只能上传指定的文件格式，限定xls-电子表格文件，zip-压缩文件 格式");
			return false;
		}
				
		return true;
	}	
	
	function uploadSecurityForm() {

		if(!checkForm()){
			return;
		}else{
			with(document.securityFormForm){
				
				action="report/securityForm/stationSecurityFormFunc.do?command=uploadStationSecurityForm";
				method="post";
				enctype="multipart/form-data";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="securityFormForm" target="_self" id="securityFormForm">
			<input type="hidden" name="departmentId" value="${department.departmentId }">
			<input type="hidden" name="reportUserId" value="${reportUser.userId }">
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
							<b>反恐报表管理&gt;&gt;反恐报表维护&gt;&gt;上传</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>关联部门&gt;&gt;${department.departmentName }</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>上报人&gt;&gt;${reportUser.username }</b>
						</td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<h1>反恐报表上传</h1>   
				
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					
					<tr>
						<td width="40%" height="30px" align="right">
							<font color="#FF0000">请选择上传文件:&nbsp;
						</td>
						<td width="40px" align="left">
							<input type="file" id="securityFormFile"
								 name="securityFormFile">
						</td>
					</tr>
					
					<tr>
						<td>
						</td>
						<td>说明：只能上传xls或zip文件，且最大不超过10M
						</td>
					</tr>
					
				</table>
				
				
				<hr width="97%" align="center" size=0>
				
				<div align="center">
					<input name="btnUpload" class="button1" type="button" id="btnUpload"
						value="上传" onClick="uploadSecurityForm()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
