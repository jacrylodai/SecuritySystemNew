package com.ldp.security.report.actionform;

import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import com.ldp.security.report.domain.SecurityForm;
import com.ldp.security.util.date.DateUtil;
import com.ldp.security.util.validate.ClientValidate;

public class SecurityFormActionForm extends ActionForm {
	
	private long securityFormId;

	private Long departmentId;
	
	private long reportUserId;
	
	private String startDateString;
	
	private String endDateString;

	//id
	private long commonFormId;

	//安检仪故障数
	private int securityMachineTroubleNum;

	//站区检查次数
	private int zhanquCheckNum;

	//视频监控检查次数
	private int cctvCheckNum;

	//视频监控故障数量
	private int cctvTroubleNum;

	//装备检查次数
	private int equipmentCheckNum;

	//装备故障数量
	private int equipmentTroubleNum;
	
	//民兵巡逻次数
	private int militiamanCheckNum;
	
	//反恐培训次数
	private int trainningCount;
	
	//反恐培训人数
	private int trainningPeopleNum;
	
	//反恐演练次数
	private int practiceCount;

	//反恐演练人数
	private int practicePeopleNum;

	//其他汇报工作
	private String otherWorkInfo;

	//id
	private long keyunFormId;
	
	private long periodNumberOfPassengers;

	//重点人员数
	private int squareSpecialPeopleNum;

	//预检人数
	private int squareCheckPeopleNum;

	//危险品数量列表
	private List<Integer> squareDangerousObjectNumList;

	//查缉一体人数列表
	private List<Integer> specialCodePeopleNumList;

	//验证查获假证人数
	private int yanzhengUsingFakePaperNum;

	//公安拘留人数
	private int arrestPeopleNum;

	//罚款人数
	private int finePeopleNum;

	//学习人数
	private int studyPeopleNum;

	//售票假证人数
	private int saleUsingFakePaperNum;
	
	//售票重点人员人数
	private int saleSpecialPeopleNum;

	//候车安全巡查次数
	private int waitingHallCheckPeopleNum;

	//候车闲杂人员清理数
	private int waitingHallXianzaPeopleNum;

	//检票口防尾随人数
	private int jianpiaoWeisuiPeopleNum;

	//京疆藏列车总票证不符人数
	private int specialTrainIdentityWrongPeopleNum;

	//京疆藏列车复检情况
	private String specialTrainIdentityWrongInfo;
	
	private long[] selectFlag;
	
	//返回列表页面的类型，是返回已上报页面，还是返回已导入页面
	private Integer listState;
	
	//导入时上传的excel或压缩文件
	private FormFile securityFormFile;	

	public long getSecurityFormId() {
		return securityFormId;
	}

	public void setSecurityFormId(long securityFormId) {
		this.securityFormId = securityFormId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public long getReportUserId() {
		return reportUserId;
	}

	public void setReportUserId(long reportUserId) {
		this.reportUserId = reportUserId;
	}

	public String getStartDateString() {
		return startDateString;
	}

	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}

	public String getEndDateString() {
		return endDateString;
	}

	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}

	public long getCommonFormId() {
		return commonFormId;
	}

	public void setCommonFormId(long commonFormId) {
		this.commonFormId = commonFormId;
	}

	public int getSecurityMachineTroubleNum() {
		return securityMachineTroubleNum;
	}

	public void setSecurityMachineTroubleNum(int securityMachineTroubleNum) {
		this.securityMachineTroubleNum = securityMachineTroubleNum;
	}

	public int getZhanquCheckNum() {
		return zhanquCheckNum;
	}

	public void setZhanquCheckNum(int zhanquCheckNum) {
		this.zhanquCheckNum = zhanquCheckNum;
	}

	public int getCctvCheckNum() {
		return cctvCheckNum;
	}

	public void setCctvCheckNum(int cctvCheckNum) {
		this.cctvCheckNum = cctvCheckNum;
	}

	public int getCctvTroubleNum() {
		return cctvTroubleNum;
	}

	public void setCctvTroubleNum(int cctvTroubleNum) {
		this.cctvTroubleNum = cctvTroubleNum;
	}

	public int getEquipmentCheckNum() {
		return equipmentCheckNum;
	}

	public void setEquipmentCheckNum(int equipmentCheckNum) {
		this.equipmentCheckNum = equipmentCheckNum;
	}

	public int getEquipmentTroubleNum() {
		return equipmentTroubleNum;
	}

	public void setEquipmentTroubleNum(int equipmentTroubleNum) {
		this.equipmentTroubleNum = equipmentTroubleNum;
	}

	public int getMilitiamanCheckNum() {
		return militiamanCheckNum;
	}

	public void setMilitiamanCheckNum(int militiamanCheckNum) {
		this.militiamanCheckNum = militiamanCheckNum;
	}

	public int getTrainningPeopleNum() {
		return trainningPeopleNum;
	}

	public void setTrainningPeopleNum(int trainningPeopleNum) {
		this.trainningPeopleNum = trainningPeopleNum;
	}

	public int getPracticePeopleNum() {
		return practicePeopleNum;
	}

	public void setPracticePeopleNum(int practicePeopleNum) {
		this.practicePeopleNum = practicePeopleNum;
	}

	public String getOtherWorkInfo() {
		return otherWorkInfo;
	}

	public void setOtherWorkInfo(String otherWorkInfo) {
		this.otherWorkInfo = otherWorkInfo;
	}

	public long getKeyunFormId() {
		return keyunFormId;
	}

	public void setKeyunFormId(long keyunFormId) {
		this.keyunFormId = keyunFormId;
	}

	public long getPeriodNumberOfPassengers() {
		return periodNumberOfPassengers;
	}

	public void setPeriodNumberOfPassengers(long periodNumberOfPassengers) {
		this.periodNumberOfPassengers = periodNumberOfPassengers;
	}

	public int getSquareSpecialPeopleNum() {
		return squareSpecialPeopleNum;
	}

	public void setSquareSpecialPeopleNum(int squareSpecialPeopleNum) {
		this.squareSpecialPeopleNum = squareSpecialPeopleNum;
	}

	public int getSquareCheckPeopleNum() {
		return squareCheckPeopleNum;
	}

	public void setSquareCheckPeopleNum(int squareCheckPeopleNum) {
		this.squareCheckPeopleNum = squareCheckPeopleNum;
	}

	public List<Integer> getSquareDangerousObjectNumList() {
		return squareDangerousObjectNumList;
	}

	public void setSquareDangerousObjectNumList(
			List<Integer> squareDangerousObjectNumList) {
		this.squareDangerousObjectNumList = squareDangerousObjectNumList;
	}

	public List<Integer> getSpecialCodePeopleNumList() {
		return specialCodePeopleNumList;
	}

	public void setSpecialCodePeopleNumList(List<Integer> specialCodePeopleNumList) {
		this.specialCodePeopleNumList = specialCodePeopleNumList;
	}

	public int getYanzhengUsingFakePaperNum() {
		return yanzhengUsingFakePaperNum;
	}

	public void setYanzhengUsingFakePaperNum(int yanzhengUsingFakePaperNum) {
		this.yanzhengUsingFakePaperNum = yanzhengUsingFakePaperNum;
	}

	public int getArrestPeopleNum() {
		return arrestPeopleNum;
	}

	public void setArrestPeopleNum(int arrestPeopleNum) {
		this.arrestPeopleNum = arrestPeopleNum;
	}

	public int getFinePeopleNum() {
		return finePeopleNum;
	}

	public void setFinePeopleNum(int finePeopleNum) {
		this.finePeopleNum = finePeopleNum;
	}

	public int getStudyPeopleNum() {
		return studyPeopleNum;
	}

	public void setStudyPeopleNum(int studyPeopleNum) {
		this.studyPeopleNum = studyPeopleNum;
	}

	public int getSaleUsingFakePaperNum() {
		return saleUsingFakePaperNum;
	}

	public void setSaleUsingFakePaperNum(int saleUsingFakePaperNum) {
		this.saleUsingFakePaperNum = saleUsingFakePaperNum;
	}

	public int getWaitingHallCheckPeopleNum() {
		return waitingHallCheckPeopleNum;
	}

	public void setWaitingHallCheckPeopleNum(int waitingHallCheckPeopleNum) {
		this.waitingHallCheckPeopleNum = waitingHallCheckPeopleNum;
	}

	public int getWaitingHallXianzaPeopleNum() {
		return waitingHallXianzaPeopleNum;
	}

	public void setWaitingHallXianzaPeopleNum(int waitingHallXianzaPeopleNum) {
		this.waitingHallXianzaPeopleNum = waitingHallXianzaPeopleNum;
	}

	public int getJianpiaoWeisuiPeopleNum() {
		return jianpiaoWeisuiPeopleNum;
	}

	public void setJianpiaoWeisuiPeopleNum(int jianpiaoWeisuiPeopleNum) {
		this.jianpiaoWeisuiPeopleNum = jianpiaoWeisuiPeopleNum;
	}

	public int getSpecialTrainIdentityWrongPeopleNum() {
		return specialTrainIdentityWrongPeopleNum;
	}

	public void setSpecialTrainIdentityWrongPeopleNum(
			int specialTrainIdentityWrongPeopleNum) {
		this.specialTrainIdentityWrongPeopleNum = specialTrainIdentityWrongPeopleNum;
	}

	public String getSpecialTrainIdentityWrongInfo() {
		return specialTrainIdentityWrongInfo;
	}

	public void setSpecialTrainIdentityWrongInfo(
			String specialTrainIdentityWrongInfo) {
		this.specialTrainIdentityWrongInfo = specialTrainIdentityWrongInfo;
	}

	public int getSaleSpecialPeopleNum() {
		return saleSpecialPeopleNum;
	}

	public void setSaleSpecialPeopleNum(int saleSpecialPeopleNum) {
		this.saleSpecialPeopleNum = saleSpecialPeopleNum;
	}

	public long[] getSelectFlag() {
		return selectFlag;
	}

	public void setSelectFlag(long[] selectFlag) {
		this.selectFlag = selectFlag;
	}

	public Integer getListState() {
		return listState;
	}

	public void setListState(Integer listState) {
		this.listState = listState;
	}

	public FormFile getSecurityFormFile() {
		return securityFormFile;
	}

	public void setSecurityFormFile(FormFile securityFormFile) {
		this.securityFormFile = securityFormFile;
	}

	public int getTrainningCount() {
		return trainningCount;
	}

	public void setTrainningCount(int trainningCount) {
		this.trainningCount = trainningCount;
	}

	public int getPracticeCount() {
		return practiceCount;
	}

	public void setPracticeCount(int practiceCount) {
		this.practiceCount = practiceCount;
	}

	public void validateData() {

		if(!ClientValidate.isDate(startDateString)){
			throw new RuntimeException("报表起始日期格式错误");
		}

		if(!ClientValidate.isDate(endDateString)){
			throw new RuntimeException("报表终止日期格式错误");
		}
		
		if(DateUtil.compareTowDate(startDateString, endDateString) == 1){
			throw new RuntimeException("报表起始日期必须小于或等于报表终止日期");
		}
		
		int intervalDays = DateUtil.getIntervalDaysByDateString(startDateString, endDateString);
		int lastDays = intervalDays + 1;
		if(lastDays>SecurityForm.MAX_LAST_DAYS){
			throw new RuntimeException(
					"统计天数最多只能是"+SecurityForm.MAX_LAST_DAYS+"天");
		}
		
		if(otherWorkInfo.length()>SecurityForm.LONG_TEXT_SIZE){
			throw new RuntimeException("其他需汇报的工作，内容长度限制在"
					+SecurityForm.LONG_TEXT_SIZE+"字内");
		}
		
		if(specialTrainIdentityWrongInfo.length()>SecurityForm.LONG_TEXT_SIZE){
			throw new RuntimeException("京疆藏列车复检情况，内容长度限制在"
					+SecurityForm.LONG_TEXT_SIZE+"字内");
		}
			
	}
		
}
