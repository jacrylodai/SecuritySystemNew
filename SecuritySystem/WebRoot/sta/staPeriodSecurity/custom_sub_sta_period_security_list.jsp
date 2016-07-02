<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看统计详情</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function goBack() {
		window.location.href="<%=basePath %>sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=listCustomStaPeriodInfo&startDateString=&endDateString=";
	}
	
</script>
	</head>

	<body class="body1">
		<form name="staPeriodSecurityForm" id="staPeriodSecurityForm">
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
				
			</div>
				
			<hr width="97%" align="center" size=0>
				
			<h4>统计周期信息</h4>
			
			<c:set var="tdShortWidth" value="14" scope="page"></c:set>
			<c:set var="tdBaseWidth" value="22" scope="page"></c:set>
			<c:set var="tdLongWidth" value="20" scope="page"></c:set>
			
			<c:set var="tdInfoNameWidth" value="13" scope="page"></c:set>
			<c:set var="tdInfoValueWidth" value="20" scope="page"></c:set>
				             
			<table width="95%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="${tdInfoNameWidth }%" height="30px" align="right">统计起始日期：</td>
					<td width="${tdInfoValueWidth }%" align="left">${staPeriodInfo.startDateString }</td>
					<td width="${tdInfoNameWidth }%" height="30px" align="right">统计终止日期：</td>
					<td width="${tdInfoValueWidth }%" align="left">${staPeriodInfo.endDateString }</td>
					<td width="${tdInfoNameWidth }%" height="30px" align="right">统计天数：</td>
					<td width="${tdInfoValueWidth }%" align="left">${staPeriodInfo.lastDays }</td>
				</tr>
				
				<tr>
					<td width="${tdInfoNameWidth }%" height="30px" align="right">统计时间：</td>
					<td width="${tdInfoValueWidth }%" align="left">${staPeriodInfo.staTimeString }</td>
					<td width="${tdInfoNameWidth }%" height="30px" align="right">统计用户：</td>
					<td width="${tdInfoValueWidth }%" align="left">${staPeriodInfo.staUser.username }</td>
					<td width="${tdInfoNameWidth }%" height="30px" align="right"></td>
					<td width="${tdInfoValueWidth }%" align="left"></td>
				</tr>
			</table>                                
				
			<hr width="97%" align="center" size=0>
			
			<h4>被统计的父部门</h4>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="${tdBaseWidth }%">
						部门名称
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						预期统计天数
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						实际统计天数
					</td>
					<td class="rd6">
						操作
					</td>
				</tr>
				<tr>
					<td class="rd8">
					${mainStaPeriodSecurity.staDepartment.departmentName }
					</td>
					<td class="rd8">
					${mainStaPeriodSecurity.estimateStaDays }
					</td>
					<td class="rd8">
					${mainStaPeriodSecurity.actualStaDays }
					</td>
					<td class="rd8">
					<a href="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=viewStaPeriodSecurity&staPeriodInfoId=${staPeriodInfo.staPeriodInfoId }&staDepartmentId=${mainStaPeriodSecurity.staDepartment.departmentId }">查看统计内容</a>
					</td>
				</tr>
			</table>
			
			<h4>被统计的子部门</h4>
			
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="${tdBaseWidth }%">
						部门名称
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						预期统计天数
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						实际统计天数
					</td>
					<td class="rd6">
						操作
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="4">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="staPeriodSecurity" items="${pageModel.data }">
							<tr>
								<td class="rd8">
								<c:choose>
									<c:when test="${staPeriodSecurity.staDepartment.level lt 4 }">
										<a href="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=listCustomSubStaPeriodSecurity&staPeriodInfoId=${staPeriodInfo.staPeriodInfoId }&staParentDepartmentId=${staPeriodSecurity.staDepartment.departmentId }">
										${staPeriodSecurity.staDepartment.departmentName }
										</a>
									</c:when>
									<c:otherwise>
										${staPeriodSecurity.staDepartment.departmentName }
									</c:otherwise>
								</c:choose>
								</td>
								<td class="rd8">
								${staPeriodSecurity.estimateStaDays }
								</td>
								<td class="rd8">
								${staPeriodSecurity.actualStaDays }
								</td>
								<td class="rd8">
								<a href="sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=viewStaPeriodSecurity&staPeriodInfoId=${staPeriodInfo.staPeriodInfoId }&staDepartmentId=${staPeriodSecurity.staDepartment.departmentId }">查看统计内容</a>
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
			            <pg:param name="command" value="listCustomSubStaPeriodSecurity"/>
			            <pg:param name="staPeriodInfoId" value="${staPeriodInfo.staPeriodInfoId}"/>
			            <pg:param name="staParentDepartmentId" value="${param.staParentDepartmentId}"/>
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
					</td>
				</tr>
			</table>
			
			<hr width="97%" align="center" size=0>
			
			<div align="center">
				<input name="btnBack" class="button1" type="button" id="btnBack"
					value="返回" onclick="goBack()" />
			</div>
			
		</form>
	</body>
</html>
