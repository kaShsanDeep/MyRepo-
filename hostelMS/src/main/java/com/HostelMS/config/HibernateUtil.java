
package com.HostelMS.config;

import java.util.Properties;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import com.HostelMS.model.room;
import com.HostelMS.model.user;

public class HibernateUtil {

	private static SessionFactory sesFact;

	public static SessionFactory getSessionFactory() {

		if (sesFact == null) {

			try {
				// Instance of configuration() method
				Configuration config = new Configuration();
				Properties pro = new Properties();
				// To add property of the Persistent class.
				pro.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				pro.put(Environment.URL, "jdbc:mysql://localhost:3306/HostelDB");
				pro.put(Environment.PASS, "111999");
				pro.put(Environment.USER, "root");
				pro.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				pro.put(Environment.SHOW_SQL, "false");
				pro.put(Environment.HBM2DDL_AUTO, "update");
				pro.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				config.setProperties(pro);
				config.addAnnotatedClass(user.class);
				config.addAnnotatedClass(room.class);

				// session building
				sesFact = config.buildSessionFactory();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return sesFact;

	}

	public static Session getSession() {
		return getSessionFactory().openSession();
	}

}