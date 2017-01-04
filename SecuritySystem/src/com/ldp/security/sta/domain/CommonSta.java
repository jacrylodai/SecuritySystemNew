package com.ldp.security.sta.domain;

import java.util.List;

import com.google.gson.Gson;
import com.ldp.security.util.business.CaculateUtil;
import com.ldp.security.util.gson.GsonUtil;

/**
 * 公共统计类
 * @author Administrator
 *
 */
public class CommonSta {
	
	private static final Gson gson = new Gson();
			
	private long commonStaId;
	
	//自动安检仪检查人数串，限20台
	private String securityMachineCheckNumString;
	
	private List<Integer> securityMachineCheckNumList;
	
	//所有的自动安检仪检查总人数
	private long allSecurityMachineCheckNum;

	//安检仪故障数
	private int securityMachineTroubleNum;

	//安检查获违禁品数串
	private String checkDangerousObjectNumString;
	
	private List<Integer> checkDangerousObjectNumList;

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

	public long getCommonStaId() {
		return commonStaId;
	}

	public void setCommonStaId(long commonStaId) {
		this.commonStaId = commonStaId;
	}

	public String getSecurityMachineCheckNumString() {
		return securityMachineCheckNumString;
	}

	public void setSecurityMachineCheckNumString(
			String securityMachineCheckNumString) {
		this.securityMachineCheckNumString = securityMachineCheckNumString;
		securityMachineCheckNumList = 
			GsonUtil.convertFromGsonToListInteger(securityMachineCheckNumString);
	}

	public int getSecurityMachineTroubleNum() {
		return securityMachineTroubleNum;
	}

	public void setSecurityMachineTroubleNum(int securityMachineTroubleNum) {
		this.securityMachineTroubleNum = securityMachineTroubleNum;
	}

	public String getCheckDangerousObjectNumString() {
		return checkDangerousObjectNumString;
	}

	public void setCheckDangerousObjectNumString(
			String checkDangerousObjectNumString) {
		this.checkDangerousObjectNumString = checkDangerousObjectNumString;
		checkDangerousObjectNumList = GsonUtil.convertFromGsonToListInteger(checkDangerousObjectNumString);
		
	}

	public int getCheckDangerousObjectTotalNum() {
		return CaculateUtil.addAllDataFromList(checkDangerousObjectNumList);
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

	public List<Integer> getSecurityMachineCheckNumList() {
		return securityMachineCheckNumList;
	}

	public void setSecurityMachineCheckNumList(
			List<Integer> securityMachineCheckNumList) {
		this.securityMachineCheckNumList = securityMachineCheckNumList;
		securityMachineCheckNumString = gson.toJson(securityMachineCheckNumList);
	}

	public List<Integer> getCheckDangerousObjectNumList() {
		return checkDangerousObjectNumList;
	}

	public void setCheckDangerousObjectNumList(
			List<Integer> checkDangerousObjectNumList) {
		this.checkDangerousObjectNumList = checkDangerousObjectNumList;
		checkDangerousObjectNumString = gson.toJson(checkDangerousObjectNumList);
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

	public long getAllSecurityMachineCheckNum() {
		return allSecurityMachineCheckNum;
	}

	public void setAllSecurityMachineCheckNum(long allSecurityMachineCheckNum) {
		this.allSecurityMachineCheckNum = allSecurityMachineCheckNum;
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
	
}
