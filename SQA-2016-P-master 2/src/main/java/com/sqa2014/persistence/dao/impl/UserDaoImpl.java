package com.sqa2014.persistence.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

import com.sqa2014.persistence.HibernateUtil;
import com.sqa2014.persistence.dao.UserDao;
import com.sqa2014.persistence.entities.User;
import rest.lets.dao.impl.GenericDaoImpl;

public class UserDaoImpl extends GenericDaoImpl implements UserDao {

	private static Class<?>[] annotatedClasses = { User.class };

	public UserDaoImpl() {
		super(User.class, annotatedClasses);
	}

	public boolean validateUser(String id, String pass) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery("SELECT * FROM softwearhouse.Users WHERE id =? and password=?");
			query.setInteger(0, Integer.valueOf(id));
			query.setString(1, pass);
			return !query.list().isEmpty();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public Long getLastId() {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			SQLQuery query = session.createSQLQuery("SELECT * FROM softwearhouse.Users order by 1 desc limit 1");
			return ((User)query.list().get(0)).getId();
		} catch (Exception e) {
			e.printStackTrace();
			return Long.valueOf("0");
		}
	}
}
