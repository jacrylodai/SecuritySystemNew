package com.ldp.security.sta.domain;

import java.util.ArrayList;
import java.util.List;

import com.ldp.security.util.business.CaculateUtil;
import com.ldp.security.util.xml.XMLConfigReader;

public class KeyunCaculateObject {

	//期间旅客发送人数
	private long periodNumberOfPassengers;

	//重点人员数
	private int squareSpecialPeopleNum;

	//预检人数
	private long squareCheckPeopleNum;

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

	public long getSquareCheckPeopleNum() {
		return squareCheckPeopleNum;
	}

	public void setSquareCheckPeopleNum(long squareCheckPeopleNum) {
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

	public int getSaleSpecialPeopleNum() {
		return saleSpecialPeopleNum;
	}

	public void setSaleSpecialPeopleNum(int saleSpecialPeopleNum) {
		this.saleSpecialPeopleNum = saleSpecialPeopleNum;
	}

	public static final KeyunCaculateObject caculateKeyunCaculateObjectList(
			List<KeyunCaculateObject> keyunCacuObjList) {
		
		//期间旅客发送人数
		long periodNumberOfPassengers = 0;

		//重点人员数
		int squareSpecialPeopleNum = 0;

		//预检人数
		long squareCheckPeopleNum = 0;

		int dangerousObjectItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig()
			.getDangerousObjectItemCount();
		//危险品数量列表
		List<Integer> squareDangerousObjectNumList =
			new ArrayList<Integer>();
		CaculateUtil.initialDataList(dangerousObjectItemCount, squareDangerousObjectNumList);

		int specialCodeItemCount = 
			XMLConfigReader.getInstance().getDataDictConfig()
			.getSpecialCodeItemCount();
		//查缉一体人数列表
		List<Integer> specialCodePeopleNumList = 
			new ArrayList<Integer>();
		CaculateUtil.initialDataList(specialCodeItemCount, specialCodePeopleNumList);

		//验证查获假证人数
		int yanzhengUsingFakePaperNum = 0;

		//公安拘留人数
		int arrestPeopleNum = 0;

		//罚款人数
		int finePeopleNum = 0;

		//学习人数
		int studyPeopleNum = 0;

		//售票假证人数
		int saleUsingFakePaperNum = 0;
		
		//售票重点人员人数
		int saleSpecialPeopleNum = 0;

		//候车安全巡查次数
		int waitingHallCheckPeopleNum = 0;

		//候车闲杂人员清理数
		int waitingHallXianzaPeopleNum = 0;

		//检票口防尾随人数
		int jianpiaoWeisuiPeopleNum = 0;

		//京疆藏列车总票证不符人数
		int specialTrainIdentityWrongPeopleNum = 0;
		
		for(KeyunCaculateObject keyunCacuObj:keyunCacuObjList){
			
			List<Integer> originSquareDangerousObjectNumList = 
				keyunCacuObj.getSquareDangerousObjectNumList();
			CaculateUtil.addDataFromOriginToDes(dangerousObjectItemCount
					, squareDangerousObjectNumList, originSquareDangerousObjectNumList);
			
			List<Integer> originSpecialCodePeopleNumList = 
				keyunCacuObj.getSpecialCodePeopleNumList();
			CaculateUtil.addDataFromOriginToDes(specialCodeItemCount
					, specialCodePeopleNumList, originSpecialCodePeopleNumList);
			
			periodNumberOfPassengers += keyunCacuObj.getPeriodNumberOfPassengers();
			squareSpecialPeopleNum += keyunCacuObj.getSquareSpecialPeopleNum();
			squareCheckPeopleNum += keyunCacuObj.getSquareCheckPeopleNum();
			yanzhengUsingFakePaperNum += keyunCacuObj.getYanzhengUsingFakePaperNum();
			arrestPeopleNum += keyunCacuObj.getArrestPeopleNum();
			finePeopleNum += keyunCacuObj.getFinePeopleNum();
			studyPeopleNum += keyunCacuObj.getStudyPeopleNum();
			saleUsingFakePaperNum += keyunCacuObj.getSaleUsingFakePaperNum();
			saleSpecialPeopleNum += keyunCacuObj.getSaleSpecialPeopleNum();
			waitingHallCheckPeopleNum += keyunCacuObj.getWaitingHallCheckPeopleNum();
			waitingHallXianzaPeopleNum += keyunCacuObj.getWaitingHallXianzaPeopleNum();
			jianpiaoWeisuiPeopleNum += keyunCacuObj.getJianpiaoWeisuiPeopleNum();
			specialTrainIdentityWrongPeopleNum += keyunCacuObj.getSpecialTrainIdentityWrongPeopleNum();
		}
		
		KeyunCaculateObject resultKeyunCacuObj = new KeyunCaculateObject();
		resultKeyunCacuObj.setPeriodNumberOfPassengers(periodNumberOfPassengers);
		resultKeyunCacuObj.setSquareSpecialPeopleNum(squareSpecialPeopleNum);
		resultKeyunCacuObj.setSquareCheckPeopleNum(squareCheckPeopleNum);
		resultKeyunCacuObj.setSquareDangerousObjectNumList(squareDangerousObjectNumList);
		resultKeyunCacuObj.setSpecialCodePeopleNumList(specialCodePeopleNumList);
		resultKeyunCacuObj.setYanzhengUsingFakePaperNum(yanzhengUsingFakePaperNum);
		resultKeyunCacuObj.setArrestPeopleNum(arrestPeopleNum);
		resultKeyunCacuObj.setFinePeopleNum(finePeopleNum);
		resultKeyunCacuObj.setStudyPeopleNum(studyPeopleNum);
		resultKeyunCacuObj.setSaleUsingFakePaperNum(saleUsingFakePaperNum);
		resultKeyunCacuObj.setSaleSpecialPeopleNum(saleSpecialPeopleNum);
		resultKeyunCacuObj.setWaitingHallCheckPeopleNum(waitingHallCheckPeopleNum);
		resultKeyunCacuObj.setWaitingHallXianzaPeopleNum(waitingHallXianzaPeopleNum);
		resultKeyunCacuObj.setJianpiaoWeisuiPeopleNum(jianpiaoWeisuiPeopleNum);
		resultKeyunCacuObj.setSpecialTrainIdentityWrongPeopleNum(specialTrainIdentityWrongPeopleNum);
		
		return resultKeyunCacuObj;
	}
	
}
