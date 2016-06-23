<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>已审核-车间反恐报表维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function viewDepartmentSecurityForm() {
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
					action="report/securityForm/departmentSecurityFormFunc.do?command=viewDepartmentSecurityForm"
						+"&securityFormId="+selectedId;
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
							<b>反恐报表管理&gt;&gt;已审核-车间反恐报表维护</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							&nbsp;
							<b>关联车间&gt;&gt;${department.departmentName }</b>
						</td>
					</tr>
				</table>
				
			</div>
				
			<hr width="97%" align="center" size=0>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="20%">
						报表起始日期
					</td>
					<td class="rd6" width="20%">
						报表终止日期
					</td>
					<td class="rd6" width="20%">
						统计天数
					</td>
					<td class="rd6" width="20%">
						创建时间
					</td>
					<td class="rd6">
						创建用户
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="6">没有信息</td>
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
							</font>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="black">当前第</font>&nbsp;
							<font color="red">${pageModel.currentPageNumber }</font>&nbsp;
							<font color="black">页</font>
						</div>
					</td>
					<td nowrap class="rd19" align="right">
					
						<pg:pager items="${pageModel.totalCount }" maxPageItems="${pageModel.pageSize }" 
			            	maxIndexPages="10" export="currentPageNumber=pageNumber"
			            	url="report/securityForm/departmentSecurityFormFunc.do">
			            <pg:param name="command" value="listDepartmentVerifySecurityForm"/>
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
							
						<input name="btnView" class="button1" type="button"
							id="btnView" value="查看" onClick="viewDepartmentSecurityForm()">&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
