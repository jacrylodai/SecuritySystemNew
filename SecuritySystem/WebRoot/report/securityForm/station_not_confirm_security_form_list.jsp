<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>未上报-车站反恐报表维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script language="javascript" type="text/javascript" 
			src="datePicker/WdatePicker.js"></script>
		<script type="text/javascript">
	
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
					action="report/securityForm/stationSecurityFormFunc.do?command=updateStationSecurityFormPrepare&listState=${SecurityForm_STATE_NOT_CONFIRM}"
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
				action="report/securityForm/stationSecurityFormFunc.do?command=deleteStationSecurityForm&listState=${SecurityForm_STATE_NOT_CONFIRM}";
				method="post";
				submit();
			}
		}
	}
	
	function confirmStationSecurityForm() {
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
		if(!window.confirm("你确定要上报这些数据")){
			return;
		}
		if(count>0){
			with(document.securityFormForm){
				action="report/securityForm/stationSecurityFormFunc.do?command=confirmStationSecurityForm";
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
	
	function querySecurityForm(){
	
		with(document.securityFormForm){
			action="report/securityForm/stationSecurityFormFunc.do?command=listStationNotConfirmSecurityForm";
			method="post";
			submit();
		}
	}
	
	function clearQueryParam(){
	
		var startDateString = document.getElementById("startDateString");
		startDateString.value = "";
		
		var endDateString = document.getElementById("endDateString");
		endDateString.value = "";
		
		var departmentIdSelector = document.getElementById("departmentIdSelector");
		var optionArr = departmentIdSelector.options;
		optionArr[0].selected = true;
		
		querySecurityForm();
	}
	
	function pageOnLoad(){
	
		var departmentIdSelector = document.getElementById("departmentIdSelector");
		var optionArr = departmentIdSelector.options;
		
		for(var i=0;i<optionArr.length;i++){
			
			var option = optionArr[i];
			if(option.value == ${param.departmentId} ){
				option.selected = true;
				break;
			}
		}
	}
	
</script>
	</head>

	<body class="body1" onload="pageOnLoad()">
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
							<b>反恐报表管理&gt;&gt;未上报-车站反恐报表维护</b>
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
			                
			<table width="80%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="20%" height="30px" align="right">报表起始日期：</td>
					<td width="30%" align="left">
						<input id="startDateString" name="startDateString"
							 class="Wdate" type="text" onClick="WdatePicker()"
							 value="${param.startDateString }">
					</td>
					<td width="20%" height="30px" align="right">报表终止日期：</td>
					<td width="30%" align="left">
						<input id="endDateString" name="endDateString"
							 class="Wdate" type="text" onClick="WdatePicker()"
							 value="${param.endDateString }">
					</td>
				</tr>
				
				<tr>
					<td width="20%" height="30px" align="right">选择车间：</td>
					<td width="30%" align="left">
						<select id="departmentIdSelector" name="departmentId">
							<option value="-1">所有车间</option>
							<c:forEach var="department" items="${subDepartmentList}">
								<option value="${department.departmentId }">${department.departmentName }</option>
							</c:forEach>
						</select>
					</td>
					<td width="30%" align="left" colspan="2">
						<input type="button" value="查询" onclick="querySecurityForm()">
						<input type="button" value="清空搜索条件" onclick="clearQueryParam()">
					</td>
				</tr>
			</table>  
			
			<hr width="97%" align="center" size=0>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="25%">
						车间名称
					</td>
					<td class="rd6" width="15%">
						报表起始日期
					</td>
					<td class="rd6" width="15%">
						报表终止日期
					</td>
					<td class="rd6" width="10%">
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
							<td colspan="7">没有信息</td>
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
			            	url="report/securityForm/stationSecurityFormFunc.do">
			            <pg:param name="command" value="listStationNotConfirmSecurityForm"/>
			            <pg:param name="startDateString" value="${param.startDateString }"/>
			            <pg:param name="endDateString" value="${param.endDateString }"/>
			            <pg:param name="departmentId" value="${param.departmentId}"/>
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
						<input name="btnView" class="button1" type="button"
							id="btnView" value="查看" onClick="viewStationSecurityForm()">&nbsp;
						<input name="btnModify" class="button1" type="button"
							id="btnModify" value="修改" onClick="updateStationSecurityForm()">&nbsp;
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteStationSecurityForm()">&nbsp;
						<input name="btnConfirm" class="button1" type="button"
							id="btnConfirm" value="上报" onClick="confirmStationSecurityForm()">&nbsp;
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
