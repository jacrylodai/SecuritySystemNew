package com.ldp.security.report.domain;

/**
 * 反恐填报表excel导入类
 * @author Administrator
 *
 */
public class SecurityFormSheet {

	//反恐填报表
	private SecurityForm securityForm;
	
	//上报单位名称
	private String reportDepartmentName;
	
	//上报单位id
	private long reportDepartmentId;

	public SecurityForm getSecurityForm() {
		return securityForm;
	}

	public void setSecurityForm(SecurityForm securityForm) {
		this.securityForm = securityForm;
	}

	public String getReportDepartmentName() {
		return reportDepartmentName;
	}

	public void setReportDepartmentName(String reportDepartmentName) {
		this.reportDepartmentName = reportDepartmentName;
	}

	public long getReportDepartmentId() {
		return reportDepartmentId;
	}

	public void setReportDepartmentId(long reportDepartmentId) {
		this.reportDepartmentId = reportDepartmentId;
	}
	
}
