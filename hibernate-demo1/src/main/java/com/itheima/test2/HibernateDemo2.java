package com.itheima.test2;

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
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p where p.name like ? and p.gender=?");
			query.setString(0, "王%");
			query.setString(1, "女");
			
			List<Person> list = query.list();
			for(Person person:list){
				System.out.println(person);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}

	}
	/**
	 * 2. 使用QUery接口进行带条件的查询
	 */
	@Test
	public void testQuery2(){
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p where p.name like :name and p.gender=:gender");
			query.setString("name", "王%");
			query.setString("gender", "男");
			List<Person> list = query.list();
			for(Person person:list){
				System.out.println(person);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}
	}
	/**
	 * 3. 使用QUery接口进行带条件的查询:返回结果只有一条
	 */
	@Test
	public void testQuery3(){
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p where p.name=:name");
			query.setString("name", "王海鹏");
			Person person=(Person) query.uniqueResult();
			System.out.println(person);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}
	}
	
	/**
	 * 4. 使用QUery接口进行带条件的查询:分页查询。数据库无关，不用换。
	 */
	@Test
	public void testQuery4(){
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p");
			query.setFirstResult(1);
			query.setMaxResults(2);
			List<Person> list = query.list();
			for(Person person:list){
				System.out.println(person);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(session!=null){
				session.close();
			}
		}
	}
}
