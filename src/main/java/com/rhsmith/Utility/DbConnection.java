package com.rhsmith.Utility;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.JDBCConnectionException;

/**
 * @author himanshusharma
 */
public class DbConnection {
	private static DbConnection connection = null;

	private DbConnection() {

	}

	public static DbConnection getInstance() {
		if (connection == null) {
			return connection = new DbConnection();
		} else {
			return connection;
		}
	}

	@SuppressWarnings("finally")
	public Session getSession() {
		Session session = null;
		try {
			Configuration cfg = new Configuration();
			cfg.configure();
			@SuppressWarnings("deprecation")
			SessionFactory sessionFactory = cfg.buildSessionFactory();
			session = sessionFactory.openSession();
			return session;
		} catch (JDBCConnectionException exception) {
		} finally {
			return session;

		}
	}
}
