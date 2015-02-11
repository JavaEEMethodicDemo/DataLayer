package com.itheima.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;
//Session的缓存：一级缓存
public class HibernateDemo3 {
	/**
	 * 1. 证明缓存的存在
	 */
	@Test
	public void testaddStudent(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//OID一样
		Person p2 = (Person) session.get(Person.class, 1);//OID一样.因为缓存中有该对象，直接取，不会再次去数据库查询
		System.out.println(p1==p2);//true
		tx.commit();
		session.close();
	}
	/** 2. 一级缓存的管理
	 * session.flush():刷新缓存（同步数据库）。缓存中数据还在
	 * session.clear():清空缓存。
	 * session.evict():清空指定的实体
	 */
	@Test
	public void testSessionManager1(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//得到持久对象，放入Session的缓存中
		System.out.println(p1);
		p1.setName("往昔");
		session.save(p1);
//		session.flush();//只是更新数据库.缓存中数据还在
//		session.clear();//清空缓存
		session.evict(p1);//从缓存中清除指定的实体
		
		Person p2 = (Person) session.get(Person.class, 1);
		System.out.println(p2);
		tx.commit();
		session.close();
	}
	/**
	 * 3. 快照：commit时会刷新
	 */
	//
	@Test
	public void testSessionManager2(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//得到持久对象，放入Session的缓存中
		p1.setName("陈星宇2");//刷新数据库数据只能刷新持久胎实体
		tx.commit();//此时进行同步数据库
		session.close();
	}
	@Test
	/**
	 * 4. 手工flush会刷新
	 */
	public void testSessionManager3(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//得到持久对象，放入Session的缓存中
		p1.setName("陈星宇");//刷新数据库数据只能刷新持久胎实体
		session.flush();//同步数据库
		tx.commit();//此时进行同步数据库
		session.close();
	}
	@Test
	/**
	 * 5. 执行Query的查询时,也会自动把改变的实体类同步到数据库中
	 */
	public void testSessionManager4(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//得到持久对象，放入Session的缓存中
		p1.setName("陈星宇1");//刷新数据库数据只能刷新持久胎实体
		
		Query q = session.createQuery("from Person");//一条强制性的sql语句
		System.out.println(q.list().get(0));
		
		
		session.flush();//同步数据库
		tx.commit();//此时进行同步数据库
		session.close();
	}
	@Test
	/**
	 * 6. 验证刷新模式
	 */
	public void testSessionManager5(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
//		session.setFlushMode(FlushMode.COMMIT);//事务提交和手工flush时。查询时不会刷
//		session.setFlushMode(FlushMode.MANUAL);//事务提交和手工flush时。查询时不会刷
		Person p1 = (Person) session.get(Person.class, 1);//得到持久对象，放入Session的缓存中
		p1.setName("陈星宇");//刷新数据库数据只能刷新持久胎实体
		
		Query q = session.createQuery("from Person");//一条强制性的sql语句
		System.out.println(q.list().get(0));//从缓存中取得
		
		
		tx.commit();//此时进行同步数据库
		session.flush();//同步数据库
		session.close();
	}
	/**
	 * 7. refresh:用数据库中的数据覆盖掉缓存中的数据
	 */
	@Test
	public void testSessionManager6(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Person p1 = (Person) session.get(Person.class, 1);//得到持久对象，放入Session的缓存中
		p1.setName("陈星宇");
		
		session.refresh(p1);//用数据库 中的数据覆盖掉缓存中的数据
		
		
		tx.commit();//此时进行同步数据库
		session.close();
	}
}
