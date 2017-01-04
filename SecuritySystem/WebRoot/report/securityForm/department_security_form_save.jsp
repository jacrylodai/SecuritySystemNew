<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>添加</title>
		<link rel="stylesheet" href="style/drp.css">
		<script language="javascript" type="text/javascript" 
			src="datePicker/WdatePicker.js"></script>
		<script src="script/client_validate.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/securityFormManager.js'></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}
	
	var departmentId = "${department.departmentId }";
	var isConflict = false;
	
	function checkConflict(){
		
		if(!validateDate()){
			clearDateMessage();
			return;
		}
		
		var startDateString = document.getElementById("startDateString").value;
		var endDateString = document.getElementById("endDateString").value;
		
		securityFormManager.checkConflictDateByDepartmentId(
			departmentId,startDateString,endDateString,
				function(result){
					
					isConflict = result;
					var dateMessage = document.getElementById("dateMessage");
					if(isConflict){
						dateMessage.innerHTML = 
							"<font color='red'>报表起始日期，报表结束日期与系统中的数据冲突，请重新输入</font>";
					}else{
						dateMessage.innerHTML = ""; 
					}
				});
	}
	
	function validateDate(){
	
		var startDateString = document.getElementById("startDateString").value;
		if(isEmpty(startDateString)){
			return false;
		}
		
		var endDateString = document.getElementById("endDateString").value;
		if(isEmpty(endDateString)){
			return false;
		}
		
		if(compareDate(startDateString,endDateString) == 1){
			return false;
		}
		
		return true;
	}

	function clearDateMessage(){
		
		var dateMessage = document.getElementById("dateMessage");
		dateMessage.innerHTML = "";
	}
	
	function checkForm(){
			
		var startDateString = document.getElementById("startDateString").value;
		if(isEmpty(startDateString)){
			alert("报表起始日期不能为空");
			return false;
		}
		
		var endDateString = document.getElementById("endDateString").value;
		if(isEmpty(endDateString)){
			alert("报表结束日期不能为空");
			return false;
		}
		
		if(compareDate(startDateString,endDateString) == 1){
			alert("报表起始日期必须小于或等于报表终止日期");
			return false;
		}
		
		if(isConflict){
			alert("报表起始日期，报表结束日期与系统中的数据冲突，请重新输入");
			return false;
		}
		
		return true;
	}	
	
	function saveSecurityForm() {

		if(!checkForm()){
			return;
		}else{
			with(document.securityFormForm){
				action="report/securityForm/departmentSecurityFormFunc.do?command=saveDepartmentSecurityForm";
				method="post";
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
							
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>反恐报表管理&gt;&gt;反恐报表维护&gt;&gt;添加</b>
						</td>
					</tr>
				</table>
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>关联部门&gt;&gt;${department.departmentName }</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>上报人&gt;&gt;${reportUser.username }</b>
						</td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<h1>反恐报表录入</h1>   
				
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					
					<tr>
						<td width="40%" height="30px" align="right">
							<font color="#FF0000">*</font>报表起始日期:
						</td>
						<td width="40px" align="left">
							<input id="startDateString" name="startDateString"
								 class="Wdate" type="text" onClick="WdatePicker()"
								 onblur="checkConflict()">
						</td>
					</tr>
					
					<tr>
						<td width="40%" height="30px" align="right">
							<font color="#FF0000">*</font>报表终止日期:
						</td>
						<td width="40px" align="left">
							<input id="endDateString" name="endDateString"
								 class="Wdate" type="text" onClick="WdatePicker()"
								 onblur="checkConflict()">
						</td>
					</tr>
					
					<tr>
						<td width="40%" height="30px" align="right">
						</td>
						<td width="40px" align="left">
							<span id="dateMessage"></span>
						</td>
					</tr>
					
				</table>
				
				<h2>安检查危</h2>                
                
                <h4>安检仪故障次数</h4>
                                
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">故障次数：</td>
						<td width="13%" align="left">
						<input name="securityMachineTroubleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>    
                               
                <h4>安检查获违禁品数量</h4>                
				
                <table width="90%" border="1" cellpadding="0" cellspacing="0">
                        
                    <c:forEach var="dangerousObjectItem" items="${dangerousObjectItemList}" varStatus="var">
					<tr>
					  <td width="15%" height="30px" align="left">${var.index+1}-${dangerousObjectItem.itemName }</td>
                        <td width="60%">${dangerousObjectItem.itemDescription }</td>
						<td width="15%" align="left"><input name="dangerousObjectItemNum_${var.index }" type="text" size="8">件</td>
					</tr>  
                    </c:forEach>
                    
				</table>
				
				<hr width="97%" align="center" size=0>
				
                <h2>站区封闭</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">路外负责区域巡查次数：</td>
						<td width="13%" align="left">
						<input name="zhanquCheckNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>    
                	  
               	<hr width="97%" align="center" size=0>
               	
                <h2>视频监控</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">视频监控检查次数：</td>
						<td width="13%" align="left">
						<input name="cctvCheckNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">视频故障数量：</td>
						<td width="13%" align="left">
						<input name="cctvTroubleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>   
							
				<hr width="97%" align="center" size=0>
				
                <h2>反恐装备巡查</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">装备巡查次数：</td>
						<td width="13%" align="left">
						<input name="equipmentCheckNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">装备故障数量：</td>
						<td width="13%" align="left">
						<input name="equipmentTroubleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>     
				
				<hr width="97%" align="center" size=0>
				
                <h2>民兵巡逻</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">巡逻次数：</td>
						<td width="13%" align="left">
						<input name="militiamanCheckNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>   
				 
				<hr width="97%" align="center" size=0>
                
                <h2>反恐重点工作开展情况</h2>       
                
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">反恐培训参加次数：</td>
						<td width="13%" align="left">
						<input name="trainningCount" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">反恐培训参加人数：</td>
						<td width="13%" align="left">
						<input name="trainningPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
					
					<tr>
						<td width="20%" height="30px" align="right">反恐演练参加次数：</td>
						<td width="13%" align="left">
						<input name="practiceCount" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">反恐演练参加人数：</td>
						<td width="13%" align="left">
						<input name="practicePeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>         
                
				<hr width="97%" align="center" size=0>
				
                <h2>其他需汇报的工作</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
                    	<td align="center">
						<textarea name="otherWorkInfo" rows="8" cols="90"></textarea>
                        </td>
					</tr>
				</table>   
				
				<hr width="97%" align="center" size=0>
                
                <h2>广场预检</h2> 
               
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">预检人数：</td>
						<td width="13%" align="left">
						<input name="squareCheckPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">发现重点人员：</td>
						<td width="13%" align="left">
						<input name="squareSpecialPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>
				               
                <h4>预检查获违禁品数量</h4>                
				
                <table width="90%" border="1" cellpadding="0" cellspacing="0">
                        
                    <c:forEach var="dangerousObjectItem" items="${dangerousObjectItemList}" varStatus="var">
					<tr>
					  <td width="15%" height="30px" align="left">${var.index+1}-${dangerousObjectItem.itemName }</td>
                        <td width="60%">${dangerousObjectItem.itemDescription }</td>
						<td width="15%" align="left">
						<input name="squareDangerousObjectNum_${var.index }" type="text" size="8">件</td>
					</tr>  
                    </c:forEach>
                    
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<h2>安检查危仪</h2>                
                               
                <h4>通过自动安检仪人数</h4>                
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
				
					<c:forEach varStatus="var" begin="0" end="${securityMachineMaxCount-1 }" step="2">
					<tr>
						<c:forEach varStatus="varInside" begin="${var.index}" 
							end="${(var.index+1)>(securityMachineMaxCount-1) ? (securityMachineMaxCount-1):(var.index+1)}" step="1">
							<td width="20%" height="30px" align="right">${varInside.index+1 }号机：</td>
							<td width="13%" align="left">
							<input name="securityMachineCheckNum_${varInside.index }" type="text" size="8">
							</td>
						</c:forEach>
					</tr>
					</c:forEach>
					                    
				</table>    
                                
				<hr width="97%" align="center" size=0>
				
                <h2>验证验票</h2>                
                
                <h4>查缉一体人数</h4>                
                                
				<table width="80%" border="1" cellpadding="0" cellspacing="0">
				
					<c:forEach varStatus="var" begin="0" end="${specialCodeItemCount-1 }" step="2">
					<tr>
						<c:forEach varStatus="varInside" begin="${var.index}" end="${(var.index+1)>(specialCodeItemCount-1) ? (specialCodeItemCount-1):(var.index+1)}" step="1">
							<td height="30px" align="left">${specialCodeItemList[varInside.index].itemName }</td>
							<td align="left">${specialCodeItemList[varInside.index].itemDescription }</td>
							<td align="left">
							<input name="specialCodePeopleNum_${varInside.index }" type="text" size="8">个
							</td>
						</c:forEach>
					</tr>
					</c:forEach>
					                    
				</table>                 
               
                <h4>查获假证人数</h4>                            
               
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">使用假证：</td>
						<td width="13%" align="left">
						<input name="yanzhengUsingFakePaperNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>     
                               
                <h4>公安处罚或拘留人数</h4>                                      
               
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">拘留人数：</td>
						<td width="13%" align="left">
						<input name="arrestPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">罚款人数：</td>
						<td width="13%" align="left">
						<input name="finePeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">学习人数：</td>
						<td width="13%" align="left">
						<input name="studyPeopleNum" type="text" size="8"></td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
                <h2>售票</h2>              
                				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">使用假证人数：</td>
						<td width="13%" align="left">
						<input name="saleUsingFakePaperNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">重点人员购票人数：</td>
						<td width="13%" align="left">
						<input name="saleSpecialPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>              
                  				
				<hr width="97%" align="center" size=0>
				
                <h2>候车厅</h2>                     
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">期间旅客发送人数：</td>
						<td width="13%" align="left">
						<input name="periodNumberOfPassengers" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">安全巡查次数：</td>
						<td width="13%" align="left">
						<input name="waitingHallCheckPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">闲杂人员清理人数：</td>
						<td width="13%" align="left">
						<input name="waitingHallXianzaPeopleNum" type="text" size="8"></td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
                <h2>检票口</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">检票口防尾随人数：</td>
						<td width="13%" align="left">
						<input name="jianpiaoWeisuiPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right">京疆藏列车总票证不符人数：</td>
						<td width="13%" align="left">
						<input name="specialTrainIdentityWrongPeopleNum" type="text" size="8"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>    
						
                     
                <h4>京疆藏列车复检情况</h4>
                          
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
                	<tr>
                    	<td align="center">
                        说明：请按照车次-票证不符人数，进行登记。如：G310-10,Z50-18
                        </td>
                    </tr>
					<tr>
                    	<td align="center">
						<textarea name="specialTrainIdentityWrongInfo" rows="8" cols="90"></textarea>
                        </td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<div align="center">
					<input name="btnAdd" class="button1" type="button" id="btnAdd"
						value="保存" onClick="saveSecurityForm()">
					
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
