package com.gfyy.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//Hibenate工具类
public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	static{
		Configuration cfg = new Configuration().configure();
		sessionFactory = cfg.buildSessionFactory();
	}
	
	public static Session getSession(){
//		return sessionFactory.openSession();
		/**
		 	绑定session与线程，若线程中有session，则从线程中取，若没有，则新建一个，并放到session中
		 */
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 
	 */
	public static void main(String[] args) {
		Session s1 = getSession();
		Session s2 = getSession();
		System.out.println(s1==s2);
	}
}
