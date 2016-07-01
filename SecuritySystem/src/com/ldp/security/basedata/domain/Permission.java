package com.ldp.security.basedata.domain;

/**
 * 许可类
 * @author jacrylodai
 *
 */
public class Permission {

	/**
	 * 添加数据
	 */
	public static final int CREATE = 0;

	/**
	 * 读取数据
	 */
	public static final int RETRIEVE = 1;

	/**
	 * 修改数据
	 */
	public static final int UPDATE = 2;

	/**
	 * 删除数据
	 */
	public static final int DELETE = 3;

	/**
	 * 显示菜单
	 */
	public static final int SHOW_MENU = 4;

	/**
	 * 其他操作，如登录，访问主页，注销系统，归类到其他权限
	 */
	public static final int OTHER = 5;

	/**
	 * 根据请求命令判断是哪一种请求
	 * @param command request请求命令
	 * @return
	 */
	public static int getPermissionByRequestCommand(String command) {
		
		if(command.startsWith("save")){
			return CREATE;
		}
		if(command.startsWith("list") || command.startsWith("view")){
			return RETRIEVE;
		}
		if(command.startsWith("update")){
			return UPDATE;
		}
		if(command.startsWith("delete")){
			return DELETE;
		}
		return OTHER;
	}
	
}
