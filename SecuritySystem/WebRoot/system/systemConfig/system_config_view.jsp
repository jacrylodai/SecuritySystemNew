<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看</title>
		<link rel="stylesheet" href="style/drp.css">
		<script src="script/client_validate.js"></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
	function regenerateDepartmentExcelTemplate(){
		var btnReg = document.getElementById("btnReg");
		btnReg.disabled = true;
		window.location.href = 
			"system/systemConfig/systemConfigFunc.do?command=regenerateDepartmentExcelTemplate";
	}
	
</script>
	</head>

	<body class="body1">
		<form name="systemConfigForm" target="_self" id="systemConfigForm">
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
							
							<b>系统功能&gt;&gt;系统配置维护&gt;&gt;查看</b>
						</td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
							
					
				<h5>反恐报表模板重新生成</h5>
					
				<table width="85%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td>对所有车间部门的反恐报表模板进行重新生成，
						一般用于对反恐报表源模板文件进行修改后，需要重新生成
						。或者是跨年后要重新生成整年反恐报表模板文件。
						</td>
					</tr>
					<tr>
						<td>
						<font color="red">
						注意，保存时间需要几十分钟左右，视生成模板文件的车间多少而定
						，一般一个车间需要1分钟的时间来生成，请耐心等待
						</font>
						</td>
					</tr>
					<tr>
						<td><input type="button" id="btnReg" name="btnReg" value="重新生成反恐报表模板"
								onclick="regenerateDepartmentExcelTemplate()">
						</td>
					</tr>
				</table>
				
				<div align="center">
				</div>		
			</div>
		</form>
	</body>
</html>
