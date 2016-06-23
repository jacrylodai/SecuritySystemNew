package com.ldp.security.basedata.manager;

import java.util.List;

import com.ldp.security.basedata.domain.User;
import com.ldp.security.util.PageModel;

public interface UserManager {

	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public User loadUserById(long userId);

	/**
	 * 对当前部门下的用户进行分页显示
	 * @param departmentId 部门id
	 * @return
	 */
	public PageModel<User> listUserInPage(long departmentId);
	
	/**
	 * 判断在数据库中用户名是否是唯一的
	 * @param username
	 * @return
	 */
	public boolean getIsUsernameUnique(String username);

	public User loadUserByUsername(String username);
	
	/**
	 * 得到当前部门下的所有用户
	 * @param departmentId
	 * @return
	 */
	public List<User> getUserListByDepartmentId(long departmentId);
	
	/**
	 * 验证输入的密码是否与用户的密码一致，用于修改密码的验证
	 * @param userId 用户id
	 * @param password 输入的密码
	 * @return
	 */
	public boolean checkIsUserPasswordCorrect(long userId,String password);
	
	/**
	 * 修改用户密码
	 * @param userId
	 * @param password
	 */
	public void updateUserPassword(long userId,String password);

	public void deleteUserById(long userId);
	
}
