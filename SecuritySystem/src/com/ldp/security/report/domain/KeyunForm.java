package com.ldp.security.report.domain;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ldp.security.util.business.CaculateUtil;

/**
 * 客运填报表
 * @author Administrator
 *
 */
public class KeyunForm {
	
	private static final Gson gson = new Gson();
	
	private static final Type TYPE_LIST_INTEGER =
		new TypeToken<List<Integer>>(){}.getType();

	//id
	private long keyunFormId;
	
	//期间旅客发送人数
	private long periodNumberOfPassengers;

	//重点人员数
	private int squareSpecialPeopleNum;

	//预检人数
	private int squareCheckPeopleNum;

	//危险品数量串
	private String squareDangerousObjectNumString;

	//危险品数量列表
	private List<Integer> squareDangerousObjectNumList;

	//查缉一体人数串
	private String specialCodePeopleNumString;

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

	public long getKeyunFormId() {
		return keyunFormId;
	}

	public void setKeyunFormId(long keyunFormId) {
		this.keyunFormId = keyunFormId;
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

	public String getSquareDangerousObjectNumString() {
		return squareDangerousObjectNumString;
	}

	public void setSquareDangerousObjectNumString(
			String squareDangerousObjectNumString) {
		this.squareDangerousObjectNumString = squareDangerousObjectNumString;
		squareDangerousObjectNumList = 
			gson.fromJson(squareDangerousObjectNumString, TYPE_LIST_INTEGER);
	}

	public List<Integer> getSquareDangerousObjectNumList() {
		return squareDangerousObjectNumList;
	}

	public void setSquareDangerousObjectNumList(
			List<Integer> squareDangerousObjectNumList) {
		this.squareDangerousObjectNumList = squareDangerousObjectNumList;
		squareDangerousObjectNumString = gson.toJson(squareDangerousObjectNumList);
	}

	public int getSquareDangerousObjectTotalNum() {
		return CaculateUtil.addAllDataFromList(squareDangerousObjectNumList);
	}

	public String getSpecialCodePeopleNumString() {
		return specialCodePeopleNumString;
	}

	public void setSpecialCodePeopleNumString(String specialCodePeopleNumString) {
		this.specialCodePeopleNumString = specialCodePeopleNumString;
		specialCodePeopleNumList = 
			gson.fromJson(specialCodePeopleNumString, TYPE_LIST_INTEGER);
	}

	public List<Integer> getSpecialCodePeopleNumList() {
		return specialCodePeopleNumList;
	}

	public void setSpecialCodePeopleNumList(List<Integer> specialCodePeopleNumList) {
		this.specialCodePeopleNumList = specialCodePeopleNumList;
		specialCodePeopleNumString = gson.toJson(specialCodePeopleNumList);
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

	public long getPeriodNumberOfPassengers() {
		return periodNumberOfPassengers;
	}

	public void setPeriodNumberOfPassengers(long periodNumberOfPassengers) {
		this.periodNumberOfPassengers = periodNumberOfPassengers;
	}

	public int getSpecialCodePeopleTotalNum() {
		return CaculateUtil.addAllDataFromList(specialCodePeopleNumList);
	}

	public int getSaleSpecialPeopleNum() {
		return saleSpecialPeopleNum;
	}

	public void setSaleSpecialPeopleNum(int saleSpecialPeopleNum) {
		this.saleSpecialPeopleNum = saleSpecialPeopleNum;
	}
	
}
