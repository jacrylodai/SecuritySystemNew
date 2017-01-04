package com.ldp.security.util.business.excel.common;

import java.util.Map;

public class FormVersion {

	private String versionName;
	
	private int versionCode;
	
	private String versionDateStr;
	
	private String versionConfigFilePath;
	
	private String versionClassPath;
	
	private Map<String,BasePosition> basePositionMap;

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionDateStr() {
		return versionDateStr;
	}

	public void setVersionDateStr(String versionDateStr) {
		this.versionDateStr = versionDateStr;
	}

	public String getVersionConfigFilePath() {
		return versionConfigFilePath;
	}

	public void setVersionConfigFilePath(String versionConfigFilePath) {
		this.versionConfigFilePath = versionConfigFilePath;
	}

	public String getVersionClassPath() {
		return versionClassPath;
	}

	public void setVersionClassPath(String versionClassPath) {
		this.versionClassPath = versionClassPath;
	}

	public Map<String, BasePosition> getBasePositionMap() {
		return basePositionMap;
	}

	public void setBasePositionMap(Map<String, BasePosition> basePositionMap) {
		this.basePositionMap = basePositionMap;
	}
	
}
