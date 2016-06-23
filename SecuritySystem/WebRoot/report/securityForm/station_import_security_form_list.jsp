<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>已导入-车站反恐报表维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function importStationSecurityForm() {
		window.location.href="<%=basePath %>"
			+"report/securityForm/stationSecurityFormFunc.do?command=uploadStationSecurityFormPrepare";
	}
	
	function viewStationSecurityForm() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		var selectedId;
		for(var i=0;i<checkFlags.length;i++){
			var checkElt = checkFlags[i];
			if(checkElt.checked){
				count++;
				selectedId = checkElt.value;
			}
		}
		if(count<1){
			alert("没有选择数据");
		}else
			if(count>1){
				alert("只能选择一条数据");
			}else{
				with(document.securityFormForm){
					action="report/securityForm/stationSecurityFormFunc.do?command=viewStationSecurityForm"
						+"&securityFormId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function updateStationSecurityForm() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		var selectedId;
		for(var i=0;i<checkFlags.length;i++){
			var checkElt = checkFlags[i];
			if(checkElt.checked){
				count++;
				selectedId = checkElt.value;
			}
		}
		if(count<1){
			alert("没有选择数据");
		}else
			if(count>1){
				alert("只能选择一条数据");
			}else{
				with(document.securityFormForm){
					action="report/securityForm/stationSecurityFormFunc.do?command=updateStationSecurityFormPrepare&listState=${SecurityForm_STATE_IMPORT}"
						+"&securityFormId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function deleteStationSecurityForm() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		for(var i=0;i<checkFlags.length;i++){
			if(checkFlags[i].checked){
				count++
			}
		}
		if(count==0){
			alert("你没有选择数据");
			return;
		}
		if(!window.confirm("你确定要删除这些数据")){
			return;
		}
		if(count>0){
			with(document.securityFormForm){
				action="report/securityForm/stationSecurityFormFunc.do?command=deleteStationSecurityForm&listState=${SecurityForm_STATE_IMPORT}";
				method="post";
				submit();
			}
		}
	}
	
	function verifyStationSecurityForm() {
		var checkFlags=document.getElementsByName("selectFlag");
		var count=0;
		for(var i=0;i<checkFlags.length;i++){
			if(checkFlags[i].checked){
				count++
			}
		}
		if(count==0){
			alert("你没有选择数据");
			return;
		}
		if(!window.confirm("你确定要审核这些数据")){
			return;
		}
		if(count>0){
			with(document.securityFormForm){
				action="report/securityForm/stationSecurityFormFunc.do?command=verifyImportStationSecurityForm";
				method="post";
				submit();
			}
		}
	}
				
	function verifyAllStationSecurityForm() {
		
		if(!window.confirm("你确定要审核所有数据")){
			return;
		}
		with(document.securityFormForm){
			action="report/securityForm/stationSecurityFormFunc.do?command=verifyImportAllStationSecurityForm";
			method="post";
			submit();
		}
	}
	
	function checkAll() {
		var ifAll=document.getElementById("ifAll");
		var checkFlags=document.getElementsByName("selectFlag");
		for(var i=0;i<checkFlags.length;i++){
			checkFlags[i].checked=ifAll.checked;
		}
	}
	
</script>
	</head>

	<body class="body1">
		<form name="securityFormForm" id="securityFormForm">
			<div align="center">
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="35">
					<tr>
						<td class="p1" height="18" nowrap>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="522" class="p1" height="17" nowrap>
							<img src="images/mark_arrow_02.gif" width="14" height="14">
							&nbsp;
							<b>反恐报表管理&gt;&gt;已导入-车站反恐报表维护</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>所属车站&gt;&gt;${station.departmentName }</b>
						</td>
					</tr>
				</table>
				
			</div>
				
			<hr width="97%" align="center" size=0>
			
			<c:set var="tdShortWidth" value="6" scope="page"></c:set>
			<c:set var="tdBaseWidth" value="12" scope="page"></c:set>
			<c:set var="tdLongWidth" value="18" scope="page"></c:set>
						
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="${tdLongWidth }%">
						车间名称
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						报表起始日期
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						报表终止日期
					</td>
					<td class="rd6" width="${tdShortWidth }%">
						统计天数
					</td>
					<td class="rd6" width="${tdLongWidth }%">
						创建时间
					</td>
					<td class="rd6" width="${tdShortWidth }%">
						创建用户
					</td>
					<td class="rd6" width="${tdShortWidth }%">
						是否错误
					</td>
					<td class="rd6">
						错误原因
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="9">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="securityForm" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" id="checkbox${securityForm.securityFormId }" name="selectFlag" class="checkbox1"
										value="${securityForm.securityFormId }">
								</td>								
								<td class="rd8">
								${securityForm.department.departmentName }
								</td>
								<td class="rd8">
								${securityForm.startDateString }
								</td>
								<td class="rd8">
								${securityForm.endDateString }
								</td>
								<td class="rd8">
								${securityForm.lastDays }
								</td>
								<td class="rd8">
								${securityForm.reportTimeString }
								</td>
								<td class="rd8">
								${securityForm.reportUser.username }
								</td>
								<td class="rd8">
								<c:choose>
									<c:when test="${empty securityForm.errorMessage}">
										正确
									</c:when>
									<c:otherwise>
										<font color="red">错误</font>
									</c:otherwise>
								</c:choose>
								</td>
								<td class="rd8">
								${securityForm.errorMessage }
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</table>
			
			<table width="95%" height="30" border="0" align="center"
				cellpadding="0" cellspacing="0" class="rd1">
				<tr>
					<td nowrap class="rd19" height="2">
						<div align="left">
							<font color="black">
							共${pageModel.pageCount }页
							&nbsp;总共${pageModel.totalCount }条记录
							</font>&nbsp;
							<font color="black">当前第</font>
							<font color="red">${pageModel.currentPageNumber }</font>
							<font color="black">页</font>
						</div>
					</td>
					<td nowrap class="rd19" align="right">
					
						<pg:pager items="${pageModel.totalCount }" maxPageItems="${pageModel.pageSize }" 
			            	maxIndexPages="10" export="currentPageNumber=pageNumber"
			            	url="report/securityForm/stationSecurityFormFunc.do">
			            <pg:param name="command" value="listStationImportSecurityForm"/>
			            <pg:param name="parentId" value="${param.parentId }"/>
						<pg:first>
							<a href="${pageUrl }">首页</a>
						</pg:first>
						<pg:prev>
							<a href="${pageUrl }">前页</a>
						</pg:prev>
						<pg:pages>
							<c:choose>
								<c:when test="${currentPageNumber == pageNumber }">
								<font color="red">${pageNumber }</font>
								</c:when>
								<c:otherwise>
								<a href="${pageUrl }">${pageNumber }</a>
								</c:otherwise>
							</c:choose>
						</pg:pages>
						<pg:next>
							<a href="${pageUrl }">后页</a>
						</pg:next>
						<pg:last>	
							<a href="${pageUrl }">尾页</a>
						</pg:last>
						</pg:pager>	
							
						<input name="btnImport" class="button1" type="button"
							id="btnImport" value="导入" onClick="importStationSecurityForm()">
						<input name="btnView" class="button1" type="button"
							id="btnView" value="查看" onClick="viewStationSecurityForm()">
						<input name="btnModify" class="button1" type="button"
							id="btnModify" value="修改" onClick="updateStationSecurityForm()">
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteStationSecurityForm()">
						<input name="btnVerify" class="button1" type="button"
							id="btnVerify" value="审核" onClick="verifyStationSecurityForm()">
						<input name="btnVerifyAll" class="button1" type="button"
							id="btnVerifyAll" value="审核所有数据" onClick="verifyAllStationSecurityForm()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
