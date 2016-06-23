<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>未上报-重点关注人员信息维护</title>
		<link rel="stylesheet" href="style/drp.css">
		<script type="text/javascript">
	
	function saveSpecialPeopleInfo() {
		window.location.href="<%=basePath %>"
			+"report/specialPeopleInfo/departmentSpecialPeopleInfoFunc.do?command=saveDepartmentSpecialPeopleInfoPrepare";
	}
	
	function viewSpecialPeopleInfo() {
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
				with(document.specialPeopleInfoForm){
					action="report/specialPeopleInfo/specialPeopleInfoFunc.do?command=viewSpecialPeopleInfo"
						+"&securityFormId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function updateSpecialPeopleInfo() {
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
				with(document.specialPeopleInfoForm){
					action="report/specialPeopleInfo/specialPeopleInfoFunc.do?command=updateSpecialPeopleInfoPrepare"
						+"&securityFormId="+selectedId;
					method="post";
					submit();
				}
			}
	}
	
	function deleteSpecialPeopleInfo() {
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
			with(document.specialPeopleInfoForm){
				action="report/specialPeopleInfo/specialPeopleInfoFunc.do?command=deleteSpecialPeopleInfo";
				method="post";
				submit();
			}
		}
	}
	
	function confirmSpecialPeopleInfo() {
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
			with(document.specialPeopleInfoForm){
				action="report/specialPeopleInfo/specialPeopleInfoFunc.do?command=confirmSpecialPeopleInfo";
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
		<form name="specialPeopleInfoForm" id="specialPeopleInfoForm">
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
							<b>反恐报表管理&gt;&gt;未上报-重点关注人员信息维护</b>
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
			
			<c:set var="tdBaseWidth" value="8" scope="page"></c:set>
			<c:set var="tdMediaWidth" value="14" scope="page"></c:set>
			
			<table width="95%" border="1" cellspacing="0" cellpadding="0"
				align="center" class="table1">
				<tr>
					<td class="rd6" width="50px">
						<input type="checkbox" id="ifAll" name="ifAll"
							 onClick="checkAll()">
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						车次
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						发站
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						到站
					</td>
					<td class="rd6" width="${tdMediaWidth }%">
						购票时间
					</td>
					<td class="rd6" width="${tdMediaWidth }%">
						开车时间
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						旅客姓名
					</td>
					<td class="rd6" width="${tdMediaWidth }%">
						旅客身份证
					</td>
					<td class="rd6" width="${tdBaseWidth }%">
						票号
					</td>
					<td class="rd6">
						席位
					</td>
				</tr>
				<c:choose>
					<c:when test="${empty pageModel.data }">
						<tr>
							<td colspan="6">没有信息</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="specialPeopleInfo" items="${pageModel.data }">
							<tr>
								<td class="rd8">
									<input type="checkbox" 
									id="checkbox${specialPeopleInfo.specialPeopleInfoId }"
									 name="selectFlag" class="checkbox1"
										value="${specialPeopleInfo.specialPeopleInfoId }">
								</td>
								<td class="rd8">
								${specialPeopleInfo.trainNumber }
								</td>
								<td class="rd8">
								${specialPeopleInfo.startLocation }
								</td>
								<td class="rd8">
								${specialPeopleInfo.destination }
								</td>
								<td class="rd8">
								${specialPeopleInfo.buyTicketTimeString }
								</td>
								<td class="rd8">
								${specialPeopleInfo.trainStartTimeString }
								</td>
								<td class="rd8">
								${specialPeopleInfo.passengerName }
								</td>
								<td class="rd8">
								${specialPeopleInfo.passengerIdentity }
								</td>
								<td class="rd8">
								${specialPeopleInfo.ticketNumber }
								</td>
								<td class="rd8">
								${specialPeopleInfo.sitNumber }
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
			            	url="report/specialPeopleInfo/departmentSpecialPeopleInfoFunc.do">
			            <pg:param name="command" value="listDepartmentNotConfirmSpecialPeopleInfo"/>
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
							
						&nbsp;
						<input name="btnAdd" type="button" class="button1" id="btnAdd"
							value="添加" onClick="saveSpecialPeopleInfo()">&nbsp;
						<input name="btnView" class="button1" type="button"
							id="btnView" value="查看" onClick="viewSpecialPeopleInfo()">&nbsp;
						<input name="btnModify" class="button1" type="button"
							id="btnModify" value="修改" onClick="updateSpecialPeopleInfo()">&nbsp;
						<input name="btnDelete" class="button1" type="button"
							id="btnDelete" value="删除" onClick="deleteSpecialPeopleInfo()">&nbsp;
						<input name="btnConfirm" class="button1" type="button"
							id="btnConfirm" value="上报" onClick="confirmSpecialPeopleInfo()">
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
