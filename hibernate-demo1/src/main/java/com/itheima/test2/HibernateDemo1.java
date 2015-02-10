package com.itheima.test2;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.itheima.domain.Person;
//使用标准的模板代码来�?
public class HibernateDemo1 {
	/**
	 * 给Person表添加一条记�?
	 */
	@Test
	public void testAddPerson(){
		Person p = new Person();
		p.setName("张三");
		p.setBirthday(new Date());
		
		
		//加载Hibernate的配置文件，并完成环境的初始�?
		Configuration cfg = new Configuration();
		cfg.configure();//加载hibernate.cfg.xml
//		cfg.addResource("com/itheima/domain/Person-hbm.xml");//不推�?
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		//获取Session对象（类似JDBC Connection�?
		Session session = sessionFactory.openSession();
		//�?��事务
		Transaction tx = session.beginTransaction();// start transaction
		//保存数据
		
		session.save(p);
		tx.commit();// commit
		//关闭资源
		session.close();
		sessionFactory.close();
	}
	/**
	 * 查询�?��对象：根据主�?
	 */
	@Test
	public void testQueryOnePerson(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Object obj = session.get(Person.class, 1);//根据主键查询单个对象
		Person p = (Person)obj;
		System.out.println(p);
		
		session.close();
		sessionFactory.close();
	}
	/**
	 * 更新指定的对�?
	 */
	@Test
	public void testUpdatePerson(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Person p = (Person)session.get(Person.class, 1);//根据主键查询单个对象
		p.setName("小雪");
		session.update(p);//更新
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	/**
	 * 删除指定的对象：通过主键
	 */
	@Test
	public void testDelPerson(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Person p = (Person)session.get(Person.class, 4);//根据主键查询单个对象
		session.delete(p);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 查询�?��的：稍微复杂点的查询
	 * 方式�?��Query接口，编写HQL（Hibernate Query Language类似面向对象的SQL语句�?
	 */
	@Test
	public void testFindAll(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("from Person");//此处的Person是类�?
		List<Person> ps = query.list();
		for(Person p:ps)
			System.out.println(p);
		
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 查询�?��的：稍微复杂点的查询
	 * 方式二：编写原生态的SQL语句.不建议使用：效率高�?挂着羊头卖狗�?
	 */
	@Test
	public void testFindAll2(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		SQLQuery squery = session.createSQLQuery("select * from PERSON");//纯SQL语句
		//此种情况，查询出来后，需要封装到JavaBean中�?麻烦
//		List<Object[]> objs = squery.list();//投影，返回的是Object数组。每�?��数组的元素就是记录的每列的�?
//		for(Object[] obj:objs){
//			System.out.println("-------------------");
//			for(Object o:obj)
//				System.out.println(o);
//		}
		
		squery.addEntity(Person.class);//给我封装到Peron这个实体�?
		List<Person> ps = squery.list();//投影，返回的是Object数组。每�?��数组的元素就是记录的每列的�?
		for(Person p:ps)
			System.out.println(p);
		
		session.close();
		sessionFactory.close();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//方式�?��Query接口，投影查�?
	@Test
	public void testFindAll1(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
		Query query = session.createQuery("select p.name,p.id from Person p");//此处的Person是类�?
		List<Object[]> objs = query.list();//投影，返回的是Object数组。每�?��数组的元素就是记录的每列的�?
		for(Object[] obj:objs){
			System.out.println("-------------------");
			for(Object o:obj)
				System.out.println(o);
		}
		
		
		session.close();
		sessionFactory.close();
	}
}
