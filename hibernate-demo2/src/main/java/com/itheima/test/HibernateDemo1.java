package com.itheima.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;
/**
 * 简单演示get和load的区别：面试题
 * @author yajun
 *
 */
public class HibernateDemo1 {
	/**
	 * 1. 实体类的映射文件，演示increment
	 */
	@Test
	public void testaddPerson(){
		Person p = new Person();
		p.setName("陈星宇1");
		p.setBirthday(new Date());
		p.setGender("男");
		Session session = HibernateUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		session.save(p);//准备执行sql语句
		System.out.println(p.getName());
		tx.commit();
		
		session.close();
		System.out.println(p);
	}
	/**
	 * 2. 未关闭session之前，调用load得到的对象
	 */
	@Test
	public void testQueryOnePerson(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
//		Object obj = session.get(Person.class, 1);//此处准备执行的SQL
		Object obj  = session.load(Person.class, 1);//返回的是Person的类的代理子类实例（只有主键id的值）
		Person p = (Person)obj;
		System.out.println(p);//真正用到该对象的属性时
		
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 3. get和load的区别
	 * 关闭session之后
	 */
	@Test
	public void testQueryOnePerson1(){
		Person p = findById(1);
		System.out.println(p);
	}
	/**
	 * get和load的区别
	 */
	public Person findById(Integer id){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
//		Object obj = session.get(Person.class, 1);//此处准备执行的SQL   饿汉子：立即检索
		Object obj  = session.load(Person.class, 1);//返回的是Person的类的代理子类实例（只有主键id的值）   懒汉子：延迟检索
		
		session.close();
		sessionFactory.close();
		return (Person)obj;
	}
}
