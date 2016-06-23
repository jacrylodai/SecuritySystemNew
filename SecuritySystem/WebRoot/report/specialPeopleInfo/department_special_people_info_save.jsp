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

	function changeInputFlag(inputFlagElt){
	
		var isChecked = inputFlagElt.checked;
		var index = inputFlagElt.value;
		
		var passengerName = document.getElementById("passengerName_"+index);
		var passengerIdentity = document.getElementById("passengerIdentity_"+index);
		var ticketNumber = document.getElementById("ticketNumber_"+index);
		var sitNumber = document.getElementById("sitNumber_"+index);
		
		if(isChecked){
						
			passengerName.disabled=false;
			passengerIdentity.disabled=false;
			ticketNumber.disabled=false;
			sitNumber.disabled=false;
		}else{
			
			passengerName.value="";
			passengerIdentity.value="";
			ticketNumber.value="";
			sitNumber.value="";
			
			passengerName.disabled=true;
			passengerIdentity.disabled=true;
			ticketNumber.disabled=true;
			sitNumber.disabled=true;
		}
		
	}
	
	function checkForm(){
			
		return true;
	}	
	
	function saveDepartmentSpecialPeopleInfo() {

		if(!checkForm()){
			return;
		}else{
			with(document.specialPeopleInfoForm){
				action="report/specialPeopleInfo/departmentSpecialPeopleInfoFunc.do?command=saveDepartmentSpecialPeopleInfo";
				method="post";
				submit();
			}
		}

	}

</script>
	</head>

	<body class="body1">
		<form name="specialPeopleInfoForm" target="_self" id="specialPeopleInfoForm">
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
							<b>反恐报表管理&gt;&gt;重点关注人员信息维护&gt;&gt;添加</b>
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
				
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="15%" height="30px" align="right">
							<font color="#FF0000">*</font>车次:&nbsp;
						</td>
						<td width="15%" align="left">
							<input name="trainNumber" type="text" class="text1" id=""
								size="8">					
						</td>
						
						<td width="15%" height="30px" align="right">
							<font color="#FF0000">*</font>发站:&nbsp;
						</td>
						<td width="15%" align="left">
							<input name="startLocation" type="text" class="text1" id=""
								size="16">					
						</td>
						
						<td width="15%" height="30px" align="right">
							<font color="#FF0000">*</font>到站:&nbsp;
						</td>
						<td width="15%" align="left">
							<input name="destination" type="text" class="text1" id=""
								size="16">					
						</td>
					</tr>
					
					<tr>
						<td width="15%" height="30px" align="right">
							<font color="#FF0000">*</font>购票时间:&nbsp;
						</td>
						<td width="15%" align="left">
							<input name="buyTicketTimeString" class="Wdate" type="text"
								 onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>				
						</td>
						
						<td width="15%" height="30px" align="right">
							<font color="#FF0000">*</font>开车时间:&nbsp;
						</td>
						<td width="15%" align="left">
							<input name="trainStartTimeString"  class="Wdate" type="text"
								 onclick="WdatePicker({startDate:'%y-%M-%d 00:00:00',dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>			
						</td>
					</tr>
					
				</table>
				
				<hr width="97%" align="center" size=0>
								
				<table width="95%" border="0" cellpadding="2" cellspacing="0">
					<tr>
						<td width="40px" align="center">确认录入
						</td>
						<td width="20%" align="center">旅客姓名
						</td>
						<td width="40%" align="center">旅客身份证
						</td>
						<td width="20%" align="center">票号
						</td>
						<td align="center">席位号
						</td>
					</tr>
					
					<c:forEach varStatus="var" begin="0" end="9" step="1">
						<tr>
							<td align="center">
							<input type="checkbox" name="inputFlag_${var.index }" value="${var.index }"
								id="inputFlag_${var.index }" onclick="changeInputFlag(this)"> 
							</td>
							<td align="center">
							<input name="passengerName_${var.index }" type="text"
								class="text1" id="passengerName_${var.index }"
								size="10" disabled="true">	
							</td>
							<td align="center">							
							<input name="passengerIdentity_${var.index }" type="text"
								class="text1" id="passengerIdentity_${var.index }"
								size="22" disabled="true">	
							</td>
							<td align="center">
							<input name="ticketNumber_${var.index }" type="text"
								class="text1" id="ticketNumber_${var.index }"
								size="12" disabled="true">	
							</td>
							<td align="center">
							<input name="sitNumber_${var.index }" type="text"
								class="text1" id="sitNumber_${var.index }"
								size="16" disabled="true">	
							</td>
						</tr>
					</c:forEach>
				</table>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="saveDepartmentSpecialPeopleInfo()">
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
