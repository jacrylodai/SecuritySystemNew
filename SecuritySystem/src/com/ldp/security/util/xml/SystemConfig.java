package com.ldp.security.util.xml;

import java.util.List;
import java.util.Map;

import com.ldp.security.util.business.excel.common.FormVersion;

/**
 * 系统配置
 * @author Administrator
 *
 */
public class SystemConfig {

	//每页显示数量
	private int pageSize;
	
	//自动安检仪最大数量
	private int securityMachineMaxCount;
	
	//系统文件目录
	private String systemDataFolder;
	
	private Map<Integer, FormVersion> formVersionMap;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSecurityMachineMaxCount() {
		return securityMachineMaxCount;
	}

	public void setSecurityMachineMaxCount(int securityMachineMaxCount) {
		this.securityMachineMaxCount = securityMachineMaxCount;
	}

	public String getSystemDataFolder() {
		return systemDataFolder;
	}

	public void setSystemDataFolder(String systemDataFolder) {
		this.systemDataFolder = systemDataFolder;
	}

	public Map<Integer, FormVersion> getFormVersionMap() {
		return formVersionMap;
	}

	public void setFormVersionMap(Map<Integer, FormVersion> formVersionMap) {
		this.formVersionMap = formVersionMap;
	}

}
