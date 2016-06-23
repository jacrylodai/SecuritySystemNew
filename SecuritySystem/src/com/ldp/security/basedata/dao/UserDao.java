package com.ldp.security.basedata.dao;

import java.util.List;

import com.ldp.security.basedata.domain.User;

public interface UserDao {

	public void saveUser(User user);
	
	public void updateUser(User user);
	
	public User loadUserById(long userId);

	public List<User> getUserListByUsername(String username);
	
}
