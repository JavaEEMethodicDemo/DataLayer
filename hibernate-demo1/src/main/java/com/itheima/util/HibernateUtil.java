package com.itheima.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//Hibenateπ§æﬂ¿‡
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	static{
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public static Session getSession(){
		return sessionFactory.openSession();
	}
}
