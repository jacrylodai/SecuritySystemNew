package com.ldp.security.basedata.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.ldp.security.basedata.dao.UserDao;
import com.ldp.security.basedata.domain.User;
import com.ldp.security.common.manager.AbstractManager;
import com.ldp.security.util.PageModel;
import com.ldp.security.util.constants.Constants;
import com.ldp.security.util.database.ParameterObject;
import com.ldp.security.util.encrypt.EncryptUtils;

public class UserManagerImpl extends AbstractManager<User> 
	implements UserManager {
	
	private static final Logger logger = Logger.getLogger(UserManagerImpl.class);

	private UserDao userDao;
		
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public User loadUserById(long userId) {
		
		User user = userDao.loadUserById(userId);
		return user;
	}

	public void saveUser(User user) {

		userDao.saveUser(user);
	}

	public void updateUser(User user) {

		userDao.updateUser(user);
	}

	public PageModel<User> listUserInPage(long departmentId) {
				
		String hql = 
			"from User user" +
			" where user.department.departmentId=:departmentId" +
			" and user.isDelete=:isDelete" +
			" order by user.role.roleId asc,user.username asc";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		ParameterObject paraObj = null;
		
		paraObj = new ParameterObject("departmentId",departmentId);
		paraObjList.add(paraObj);
		paraObj = new ParameterObject("isDelete",Constants.VALUE_NO);
		paraObjList.add(paraObj);
		
		return findDataByHqlParameterListInPage(hql, paraObjList);
	}

	public boolean getIsUsernameUnique(String username) {

		List<User> userList = userDao.getUserListByUsername(username);
		if(userList.size()>1){
			logger.error("有重复的用户名"+username, new RuntimeException());
		}
		if(userList.size()>=1){
			return false;
		}else{
			return true;
		}
	}

	public User loadUserByUsername(String username) {

		List<User> userList = userDao.getUserListByUsername(username);
		if(userList.size()>1){
			logger.error("有重复的用户名"+username, new RuntimeException());
		}
		if(userList.size()>=1){
			return userList.get(0);
		}else{
			return null;
		}
	}

	public void deleteUserById(long userId) {

		User user = userDao.loadUserById(userId);
		user.setIsDelete(Constants.VALUE_YES);
		userDao.updateUser(user);
	}

	public List<User> getUserListByDepartmentId(long departmentId) {
		
		String hql = 
			"from User user" +
			" where user.department.departmentId=:departmentId" +
			" and user.isDelete=:isDelete";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("departmentId",departmentId));
		paraObjList.add(new ParameterObject("isDelete",Constants.VALUE_NO));
		
		return findDataByHqlParameterListInList(hql, paraObjList);
	}

	public boolean checkIsUserPasswordCorrect(long userId, String password) {

		String hql = "from User user where user.userId=:userId";
		
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("userId",userId));
		
		List<User> userList = findDataByHqlParameterListInList(hql, paraObjList);
		
		//用户不存在
		if(userList.size() == 0){
			return false;
		}
		User user = userList.get(0);
		
		//对密码进行2次MD5加密
		String passwordEncHexStr = null;
		try {
			passwordEncHexStr = 
				EncryptUtils.encryptMD5TwiceToHexStr(password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		if(passwordEncHexStr.equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
		
	}

	public void updateUserPassword(long userId, String password) {

		String hql = 
			"update User user set user.password=:password where user.userId=:userId";
		List<ParameterObject> paraObjList = new ArrayList<ParameterObject>();
		paraObjList.add(new ParameterObject("password",password));
		paraObjList.add(new ParameterObject("userId",userId));
		executeUpdateOrDeleteByHqlParameterListInList(hql, paraObjList);
		
	}

}
