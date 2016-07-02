<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>自定义统计维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function saveStaPeriodInfo() {
		window.location.href="<%=basePath %>"
			+"sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=saveCustomStaPeriodInfoPrepare";
	}
	
	function deleteStaPeriodInfo() {
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
			with(document.staPeriodInfoForm){
				action="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=deleteStaPeriodInfo";
				method="post";
				submit();
			}
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
		<form name="staPeriodInfoForm" id="staPeriodInfoForm">
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
							<b>统计管理&gt;&gt;自定义统计维护</b>
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
				
			</div>
				
			<hr width="97%" align="center" size=0>
			
			<c:set var="tdShortWidth" value="9" scope="page"></c:set>
			<c:set var="tdBaseWidth" value="13" scope="page"></c:set>
			<c:set var="tdLongWidth" value="20" scope="page"></c:set>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						统计起始日期
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						统计终止日期
					</td>
					<td class="rd6" width="${tdShortWidth }%">
						统计天数
					</td>
					<td class="rd6" width="${tdLongWidth }%">
						统计时间
					</td>
					<td class="rd6" width="${tdShortWidth }%">
						统计用户
					</td>
					<td class="rd6">
						操作
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="7">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="staPeriodInfo" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" id="checkbox${staPeriodInfo.staPeriodInfoId }" name="selectFlag" class="checkbox1"
										value="${staPeriodInfo.staPeriodInfoId }">
								</td>
								<td class="rd8">
								${staPeriodInfo.startDateString }
								</td>
								<td class="rd8">
								${staPeriodInfo.endDateString }
								</td>
								<td class="rd8">
								${staPeriodInfo.lastDays }
								</td>
								<td class="rd8">
								${staPeriodInfo.staTimeString }
								</td>
								<td class="rd8">
								${staPeriodInfo.staUser.username }
								</td>
								<td class="rd8">
								<a href="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=listCustomSubStaPeriodSecurity&staPeriodInfoId=${staPeriodInfo.staPeriodInfoId }&staParentDepartmentId=${staPeriodInfo.creatorDepartment.departmentId }">查看统计详情</a>&nbsp;
								<a href="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=downloadDepartmentStaExcelReport&staPeriodInfoId=${staPeriodInfo.staPeriodInfoId }">下载统计报表</a>
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
							&nbsp;共&nbsp;${pageModel.pageCount }&nbsp;页
							&nbsp;总共&nbsp;${pageModel.totalCount }&nbsp;条记录
							</font>&nbsp;&nbsp;
							<font color="black">当前第</font>&nbsp;
							<font color="red">${pageModel.currentPageNumber }</font>&nbsp;
							<font color="black">页</font>
						</div>
					</td>
					<td nowrap class="rd19" align="right">
					
						<pg:pager items="${pageModel.totalCount }" maxPageItems="${pageModel.pageSize }" 
			            	maxIndexPages="10" export="currentPageNumber=pageNumber"
			            	url="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do">
			            <pg:param name="command" value="listCustomStaPeriodInfo"/>
			            <pg:param name="startDateString" value="${param.startDateString }"/>
			            <pg:param name="endDateString" value="${param.endDateString }"/>
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
							
						&nbsp;
						<input name="btnAdd" type="button" class="button1" id="btnAdd"
							value="添加" onClick="saveStaPeriodInfo()">&nbsp;
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteStaPeriodInfo()">&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
