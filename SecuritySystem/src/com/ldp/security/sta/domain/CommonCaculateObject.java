package com.ldp.security.sta.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ldp.security.util.business.CaculateUtil;
import com.ldp.security.util.xml.XMLConfigReader;

public class CommonCaculateObject {

	//自动安检仪检查人数列表
	private List<Integer> securityMachineCheckNumList;
	
	//所有的自动安检仪检查总人数
	private long allSecurityMachineCheckNum;

	//安检仪故障数
	private int securityMachineTroubleNum;
	
	//安检查获违禁品列表
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

	public List<Integer> getSecurityMachineCheckNumList() {
		return securityMachineCheckNumList;
	}

	public void setSecurityMachineCheckNumList(
			List<Integer> securityMachineCheckNumList) {
		this.securityMachineCheckNumList = securityMachineCheckNumList;
	}

	public int getSecurityMachineTroubleNum() {
		return securityMachineTroubleNum;
	}

	public void setSecurityMachineTroubleNum(int securityMachineTroubleNum) {
		this.securityMachineTroubleNum = securityMachineTroubleNum;
	}

	public List<Integer> getCheckDangerousObjectNumList() {
		return checkDangerousObjectNumList;
	}

	public void setCheckDangerousObjectNumList(
			List<Integer> checkDangerousObjectNumList) {
		this.checkDangerousObjectNumList = checkDangerousObjectNumList;
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

	public static CommonCaculateObject caculateCommonCaculateObjectList(
			List<CommonCaculateObject> commonCacuObjList) {
		
		int securityMachineMaxCount = 
			XMLConfigReader.getInstance().getSystemConfig()
			.getSecurityMachineMaxCount();
		//自动安检仪检查人数列表
		List<Integer> securityMachineCheckNumList = 
			new ArrayList<Integer>();
		CaculateUtil.initialDataList(securityMachineMaxCount, securityMachineCheckNumList);

		//所有的自动安检仪检查总人数
		long allSecurityMachineCheckNum = 0;
		
		//安检仪故障数
		int securityMachineTroubleNum = 0;
		
		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig()
			.getDangerousObjectItemCount();
		//安检查获违禁品列表
		List<Integer> checkDangerousObjectNumList = 
			new ArrayList<Integer>();
		CaculateUtil.initialDataList(dangerousObjectItemCount, checkDangerousObjectNumList);

		//站区检查次数
		int zhanquCheckNum = 0;

		//视频监控检查次数
		int cctvCheckNum = 0;

		//视频监控故障数量
		int cctvTroubleNum = 0;

		//装备检查次数
		int equipmentCheckNum = 0;

		//装备故障数量
		int equipmentTroubleNum = 0;
		
		//民兵巡逻次数
		int militiamanCheckNum = 0;
		
		//反恐培训次数
		int trainningCount = 0;
		
		//反恐培训人数
		int trainningPeopleNum = 0;
		
		//反恐演练次数
		int practiceCount = 0;

		//反恐演练人数
		int practicePeopleNum = 0;
		
		for(CommonCaculateObject commonCacuObj:commonCacuObjList){
			
			List<Integer> originSecuMachCheckNumList = commonCacuObj.getSecurityMachineCheckNumList();
			CaculateUtil.addDataFromOriginToDes(securityMachineMaxCount
					, securityMachineCheckNumList, originSecuMachCheckNumList);
			
			List<Integer> oriCheckDangObjNumList = commonCacuObj.getCheckDangerousObjectNumList();
			CaculateUtil.addDataFromOriginToDes(dangerousObjectItemCount
					, checkDangerousObjectNumList, oriCheckDangObjNumList);
			
			allSecurityMachineCheckNum += commonCacuObj.getAllSecurityMachineCheckNum();
			
			securityMachineTroubleNum += commonCacuObj.getSecurityMachineTroubleNum();
			zhanquCheckNum += commonCacuObj.getZhanquCheckNum();
			cctvCheckNum += commonCacuObj.getCctvCheckNum();
			cctvTroubleNum += commonCacuObj.getCctvTroubleNum();
			equipmentCheckNum += commonCacuObj.getEquipmentCheckNum();
			equipmentTroubleNum += commonCacuObj.getEquipmentTroubleNum();
			militiamanCheckNum += commonCacuObj.getMilitiamanCheckNum();
			
			trainningCount += commonCacuObj.getTrainningCount();
			trainningPeopleNum += commonCacuObj.getTrainningPeopleNum();
			
			practiceCount += commonCacuObj.getPracticeCount();
			practicePeopleNum += commonCacuObj.getPracticePeopleNum();
		}
		
		CommonCaculateObject resultCommCacuObj = new CommonCaculateObject();
		resultCommCacuObj.setSecurityMachineCheckNumList(securityMachineCheckNumList);
		resultCommCacuObj.setAllSecurityMachineCheckNum(allSecurityMachineCheckNum);
		resultCommCacuObj.setSecurityMachineTroubleNum(securityMachineTroubleNum);
		resultCommCacuObj.setCheckDangerousObjectNumList(checkDangerousObjectNumList);
		resultCommCacuObj.setZhanquCheckNum(zhanquCheckNum);
		resultCommCacuObj.setCctvCheckNum(cctvCheckNum);
		resultCommCacuObj.setCctvTroubleNum(cctvTroubleNum);
		resultCommCacuObj.setEquipmentCheckNum(equipmentCheckNum);
		resultCommCacuObj.setEquipmentTroubleNum(equipmentTroubleNum);
		resultCommCacuObj.setMilitiamanCheckNum(militiamanCheckNum);
		
		resultCommCacuObj.setTrainningCount(trainningCount);
		resultCommCacuObj.setTrainningPeopleNum(trainningPeopleNum);
		
		resultCommCacuObj.setPracticeCount(practiceCount);
		resultCommCacuObj.setPracticePeopleNum(practicePeopleNum);
		
		return resultCommCacuObj;
	}
	
}
