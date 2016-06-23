package com.ldp.security.basedata.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.ldp.security.basedata.domain.User;
import com.ldp.security.util.HibernateSessionHolder;

public class UserDaoImpl implements UserDao {

	public User loadUserById(long userId) {
		
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		User user = (User) conn.get(User.class, userId);
		return user;
	}

	public void saveUser(User user) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.save(user);
	}

	public void updateUser(User user) {

		HibernateTemplate conn = HibernateSessionHolder.getConn();
		conn.update(user);
	}

	public List<User> getUserListByUsername(final String username) {

		final String hql = "from User user where user.username=?";
		HibernateTemplate conn = HibernateSessionHolder.getConn();
		
		List<User> userList = (List<User>) conn.execute(new HibernateCallback(){

			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				
				Query query = session.createQuery(hql);
				query.setString(0, username);
				List<User> userList = query.list();
				return userList;
			}
			
		});
		
		return userList;
	}

}
