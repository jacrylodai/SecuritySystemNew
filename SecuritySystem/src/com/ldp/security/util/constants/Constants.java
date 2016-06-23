package com.ldp.security.util.constants;

/**
 * 常量类
 * @author Administrator
 *
 */
public class Constants {

	/**
	 * 提示信息key
	 */
	public static final String MESSAGE_KEY = "MESSAGE_KEY";
	
	/**
	 * 跳转地址key
	 */
	public static final String REDIRECT_URL_KEY = "REDIRECT_URL_KEY";
	
	public static final String MESSAGE_SAVE_SUCCESS = "添加成功";

	public static final String MESSAGE_UPDATE_SUCCESS = "修改成功";

	public static final String MESSAGE_DELETE_SUCCESS = "删除成功";
	
	public static final String MESSAGE_UPLOAD_SUCCESS = "上传成功";
	
	/**
	 * 值-是
	 */
	public static final int VALUE_YES = 'Y';
	
	/**
	 * 值-否
	 */
	public static final int VALUE_NO = 'N';
	
	/**
	 * 登录模块
	 */
	public static final String LOGIN_FUNC = "/system/login/loginFunc.do";

	/**
	 * 部门管理模块
	 */
	public static final String DEPARTMENT_FUNC = "/basedata/department/departmentFunc.do";

	/**
	 * 用户管理模块
	 */
	public static final String USER_FUNC = "/basedata/user/userFunc.do";

	/**
	 * 系统主页模块
	 */
	public static final String SITE_INDEX_FUNC = "/system/siteIndex/siteIndexFunc.do";

	/**
	 * 修改密码模块
	 */
	public static final String PASSWORD_FUNC = "/system/password/passwordFunc.do";

	/**
	 * 车间反恐报表管理模块
	 */
	public static final String DEPARTMENT_SECURITY_FORM_FUNC = 
		"/report/securityForm/departmentSecurityFormFunc.do";

	/**
	 * 车站反恐报表管理模块
	 */
	public static final String STATION_SECURITY_FORM_FUNC = 
		"/report/securityForm/stationSecurityFormFunc.do";

	/**
	 * 车间重点人员管理模块，未使用
	 */
	public static final String DEPARTMENT_SPECIAL_PEOPLE_INFO_FUNC = 
		"/report/specialPeopleInfo/departmentSpecialPeopleInfoFunc.do";

	/**
	 * 自定义反恐报表统计模块
	 */
	public static final String CUSTOM_STA_PERIOD_SECURITY_FUNC = 
		"/sta/staPeriodSecurity/customStaPeriodSecurityFunc.do";

	
}
