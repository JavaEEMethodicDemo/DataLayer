package com.itheima.test;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;

public class HibernateDemo2 {
	/**
	 * 1. 使用QUery接口进行带条件的查询
	 */
	@Test
	public void testQuery1(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			String hql = "select p from Person p where p.name like ?";//*是sql。
			Query query = session.createQuery(hql);
			query.setString(0, "王%");//下标从0开始
			List<Person> ps = query.list();
			for(Person p:ps)
				System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	/**
	 * 2. 使用QUery接口进行带条件的查询
	 */
	@Test
	public void testQuery2(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			//传统：问号占位符。
//			String hql = "select p from Person p where p.id>? and p.name like ?";//*是sql。
//			Query query = session.createQuery(hql);
//			query.setString(0, "王%");//下标从0开始
//			query.setInteger(1, 2);//下标从0开始
			
			//具名参数查询：优势在于参数顺序无关 推荐
			String hql = "select p from Person p where p.name like :name and p.id=:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", 2);//对应HQL语句中   :id
			query.setString("name", "王%");//   :name
			
			List<Person> ps = query.list();
			for(Person p:ps)
				System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	/**
	 * 3. 使用QUery接口进行带条件的查询:返回结果只有一条
	 */
	@Test
	public void testQuery3(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			String hql = "select p from Person p where p.id>:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", 2);//对应HQL语句中   :id
			
			List<Person> ps = query.list();
			for(Person p:ps)
				System.out.println(p);
			
//			Person p = (Person)query.uniqueResult();//只能用在结果只有一条的情况下。否则报错
//			System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	/**
	 * 4. 使用QUery接口进行带条件的查询:分页查询。数据库无关，不用换。
	 */
	@Test
	public void testQuery4(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			String hql = "from Person";
			Query query = session.createQuery(hql);
			query.setFirstResult(0);//分页查询每页开始的记录条数
			query.setMaxResults(2);//每次取多少条
			List<Person> ps = query.list();
			for(Person p:ps)
				System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
}
