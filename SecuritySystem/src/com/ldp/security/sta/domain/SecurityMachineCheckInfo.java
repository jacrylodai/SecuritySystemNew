package com.ldp.security.sta.domain;

/**
 * 自动安检仪检查信息类
 * @author Administrator
 *
 */
public class SecurityMachineCheckInfo {

	//安检仪名称
	private String name;
	
	//检查数量
	private int checkNum;

	public SecurityMachineCheckInfo(String name, int checkNum) {
		super();
		this.name = name;
		this.checkNum = checkNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(int checkNum) {
		this.checkNum = checkNum;
	}
	
}
