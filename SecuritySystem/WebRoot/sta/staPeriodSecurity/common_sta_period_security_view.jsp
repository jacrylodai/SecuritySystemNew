<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../../common/common.jsp" %>
<html>
	<head>
		<base href="<%=basePath %>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>查看</title>
		<link rel="stylesheet" href="style/drp.css">
		<script language="javascript" type="text/javascript" src="datePicker/WdatePicker.js"></script>
		<script src="script/client_validate.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/securityFormManager.js'></script>
		<script type="text/javascript">
		
	function goBack() {
		history.back(1);
	}

</script>
	</head>

	<body class="body1">
		<form name="staPeriodSecurityForm" target="_self" id="staPeriodSecurityForm">
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
							
							<b>统计管理&gt;&gt;统计维护&gt;&gt;查看</b>
						</td>
					</tr>
				</table>
				
				<table width="95%" border="0" cellspacing="0" cellpadding="0"
					height="25">
					<tr>
						<td width="522" class="p1" height="25" nowrap>
							<img src="images/mark_arrow_03.gif" width="14" height="14">
							
							<b>被统计部门&gt;&gt;${staPeriodSecurity.staDepartment.departmentName }</b>
						</td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
				<h4>统计周期信息</h4>
				
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
					
				<h4>反恐统计表信息</h4>
				
				<table width="95%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="${tdInfoNameWidth }%" height="30px" align="right">预期统计天数：</td>
						<td width="${tdInfoValueWidth }%" align="left">${staPeriodSecurity.estimateStaDays }</td>
						<td width="${tdInfoNameWidth }%" height="30px" align="right">实际统计天数：</td>
						<td width="${tdInfoValueWidth }%" align="left">${staPeriodSecurity.actualStaDays }</td>
						<td width="${tdInfoNameWidth }%" height="30px" align="right"></td>
						<td width="${tdInfoValueWidth }%" align="left"></td>
					</tr>
				</table>
										
				<hr width="97%" align="center" size=0>	
				
				<h2>安检查危</h2>                
                               
                <h4>通过自动安检仪人数</h4>    
                
				<c:set var="checkInfoListLength" 
					value="${fn:length(commonSta.securityMachineCheckInfoList) }"
					scope="page"></c:set>    
				
				<c:choose>
					<c:when test="${checkInfoListLength>0}">
					
						<table width="95%" border="0" cellpadding="0" cellspacing="0">
				
							<c:forEach varStatus="var" begin="0" end="${checkInfoListLength-1 }" step="2">
							<tr>
								<c:forEach varStatus="varInside" begin="${var.index}" 
									end="${(var.index+1)>(checkInfoListLength-1) ? (checkInfoListLength-1):(var.index+1)}" step="1">
									<td width="40%" height="30px" align="right">${commonSta.securityMachineCheckInfoList[varInside.index].name }：</td>
									<td width="10%" align="left">${commonSta.securityMachineCheckInfoList[varInside.index].checkNum }</td>
								</c:forEach>
							</tr>
							</c:forEach>
							                    
						</table>
					</c:when>
				</c:choose>
				
				
                <h4>安检仪故障次数</h4>
                                
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">故障次数：</td>
						<td width="13%" align="left">
						<input name="securityMachineTroubleNum" type="text"
							 size="8" value="${commonSta.securityMachineTroubleNum }"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>    
                               
                <h4>安检查获违禁品数量</h4>       
                <h4>安检查获违禁品总数量：${commonSta.checkDangerousObjectTotalNum }</h4>
                <h4>安检违禁品总数量占旅客发送人数比例：${staPeriodSecurity.checkDangerousObjectTotalNumOnPeriodNumOfPassengers }（万分比）</h4>                
				
                <table width="90%" border="1" cellpadding="0" cellspacing="0">
                        
                    <c:forEach var="dangerousObjectItem" items="${dangerousObjectItemList}" varStatus="var">
					<tr>
					  <td width="15%" height="30px" align="left">${var.index+1}-${dangerousObjectItem.itemName }</td>
                        <td width="60%">${dangerousObjectItem.itemDescription }</td>
						<td width="15%" align="left">
						<input name="dangerousObjectItemNum_${var.index }" type="text"
							 size="8" value="${commonSta.checkDangerousObjectNumList[var.index] }">件</td>
					</tr>  
                    </c:forEach>
                    
				</table>
				
				<hr width="97%" align="center" size=0>
				
                <h2>站区封闭</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">路外负责区域巡查次数：</td>
						<td width="13%" align="left">
						<input name="zhanquCheckNum" type="text" size="8"
							value="${commonSta.zhanquCheckNum }"></td>
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
						<input name="cctvCheckNum" type="text" size="8"
							value="${commonSta.cctvCheckNum }"></td>
						<td width="20%" height="30px" align="right">视频故障数量：</td>
						<td width="13%" align="left">
						<input name="cctvTroubleNum" type="text" size="8"
							value="${commonSta.cctvTroubleNum }"></td>
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
						<input name="equipmentCheckNum" type="text" size="8"
							value="${commonSta.equipmentCheckNum }"></td>
						<td width="20%" height="30px" align="right">装备故障数量：</td>
						<td width="13%" align="left">
						<input name="equipmentTroubleNum" type="text" size="8"
							value="${commonSta.equipmentTroubleNum }"></td>
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
						<input name="militiamanCheckNum" type="text" size="8"
							value="${commonSta.militiamanCheckNum }"></td>
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
						<td width="20%" height="30px" align="right">反恐培训参加人数：</td>
						<td width="13%" align="left">
						<input name="trainningPeopleNum" type="text" size="8"
							value="${commonSta.trainningPeopleNum }"></td>
						<td width="20%" height="30px" align="right">反恐演练参加人数：</td>
						<td width="13%" align="left">
						<input name="practicePeopleNum" type="text" size="8"
							value="${commonSta.practicePeopleNum }"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>         
				
				<hr width="97%" align="center" size=0>
                
                <h2>广场预检</h2> 
               
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">预检人数：</td>
						<td width="13%" align="left">
						<input name="squareCheckPeopleNum" type="text" size="8"
							value="${keyunSta.squareCheckPeopleNum }"></td>
						<td width="20%" height="30px" align="right">发现重点人员：</td>
						<td width="13%" align="left">
						<input name="squareSpecialPeopleNum" type="text" size="8"
							value="${keyunSta.squareSpecialPeopleNum }"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>
				               
                <h4>预检查获违禁品数量</h4>
                <h4>预检查获违禁品总数量：${keyunSta.squareDangerousObjectTotalNum }</h4>
                <h4>预检违禁品总数量占旅客发送人数比例：${staPeriodSecurity.squareDangerousObjectTotalNumOnPeriodNumOfPassengers }（万分比）</h4>                   
				
                <table width="90%" border="1" cellpadding="0" cellspacing="0">
                        
                    <c:forEach var="dangerousObjectItem" items="${dangerousObjectItemList}" varStatus="var">
					<tr>
					  <td width="15%" height="30px" align="left">${var.index+1}-${dangerousObjectItem.itemName }</td>
                        <td width="60%">${dangerousObjectItem.itemDescription }</td>
						<td width="15%" align="left">
						<input name="squareDangerousObjectNum_${var.index }" type="text"
							value="${keyunSta.squareDangerousObjectNumList[var.index] }" size="8">件</td>
					</tr>  
                    </c:forEach>
                    
				</table>
				
				<hr width="97%" align="center" size=0>
				
                <h2>验证验票</h2>                
                
                <h4>查缉一体人数</h4>
                <h4>查缉一体总人数：${keyunSta.specialCodePeopleTotalNum }</h4>
                <h4>查缉一体总人数占旅客发送人数比例：${staPeriodSecurity.specialCodePeopleTotalNumOnPeriodNumOfPassengers }（万分比）</h4>                
                                
				<table width="70%" border="1" cellpadding="0" cellspacing="0">
				
					<c:forEach varStatus="var" begin="0" end="${specialCodeItemCount-1 }" step="2">
					<tr>
						<c:forEach varStatus="varInside" begin="${var.index}" end="${(var.index+1)>(specialCodeItemCount-1) ? (specialCodeItemCount-1):(var.index+1)}" step="1">
							<td height="30px" align="left">${specialCodeItemList[varInside.index].itemName }</td>
							<td align="left">${specialCodeItemList[varInside.index].itemDescription }</td>
							<td align="left">
							<input name="specialCodePeopleNum_${varInside.index }"
								value="${keyunSta.specialCodePeopleNumList[varInside.index] }"  type="text" size="8">个
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
						<input name="yanzhengUsingFakePaperNum" type="text" size="8"
							value="${keyunSta.yanzhengUsingFakePaperNum }"></td>
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
						<input name="arrestPeopleNum" type="text" size="8"
							value="${keyunSta.arrestPeopleNum }"></td>
						<td width="20%" height="30px" align="right">罚款人数：</td>
						<td width="13%" align="left">
						<input name="finePeopleNum" type="text" size="8"
							value="${keyunSta.finePeopleNum }"></td>
						<td width="20%" height="30px" align="right">学习人数：</td>
						<td width="13%" align="left">
						<input name="studyPeopleNum" type="text" size="8"
							value="${keyunSta.studyPeopleNum }"></td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
				
                <h2>售票</h2>              
                				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">使用假证人数：</td>
						<td width="13%" align="left">
						<input name="saleUsingFakePaperNum" type="text" size="8"
							value="${keyunSta.saleUsingFakePaperNum }"></td>
						<td width="20%" height="30px" align="right">重点人员购票人数：</td>
						<td width="13%" align="left">
						<input name="saleSpecialPeopleNum" type="text" size="8"
							value="${keyunSta.saleSpecialPeopleNum }"></td>
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
						<input name="periodNumberOfPassengers" type="text" size="8"
							value="${keyunSta.periodNumberOfPassengers }"></td>
						<td width="20%" height="30px" align="right">安全巡查次数：</td>
						<td width="13%" align="left">
						<input name="waitingHallCheckPeopleNum" type="text" size="8"
							value="${keyunSta.waitingHallCheckPeopleNum }"></td>
						<td width="20%" height="30px" align="right">闲杂人员清理人数：</td>
						<td width="13%" align="left">
						<input name="waitingHallXianzaPeopleNum" type="text" size="8"
							value="${keyunSta.waitingHallXianzaPeopleNum }"></td>
					</tr>
				</table>
				
				<hr width="97%" align="center" size=0>
                <h2>检票口</h2>                 
				
				<table width="80%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="20%" height="30px" align="right">检票口防尾随人数：</td>
						<td width="13%" align="left">
						<input name="jianpiaoWeisuiPeopleNum" type="text" size="8"
							value="${keyunSta.jianpiaoWeisuiPeopleNum }"></td>
						<td width="20%" height="30px" align="right">京疆藏列车总票证不符人数：</td>
						<td width="13%" align="left">
						<input name="specialTrainIdentityWrongPeopleNum" type="text" size="8"
							value="${keyunSta.specialTrainIdentityWrongPeopleNum }"></td>
						<td width="20%" height="30px" align="right"></td>
						<td width="13%" align="left"></td>
					</tr>
				</table>    
						
				<hr width="97%" align="center" size=0>
				
				<div align="center">
					<input name="btnBack" class="button1" type="button" id="btnBack"
						value="返回" onclick="goBack()" />
				</div>
			</div>
		</form>
	</body>
</html>
