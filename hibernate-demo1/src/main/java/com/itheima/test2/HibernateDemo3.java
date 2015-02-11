package com.itheima.test2;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;
/**
 * 查询方式二：使用Criteria.特点：更加面向对象。不需要写语句
 */
public class HibernateDemo3 {
	/*
	 * 1. 使用QUery接口查询所有的
	 */
	@Test
	public void testQuery1(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Person.class);
			List<Person> list = criteria.list();
			for(Person person:list){
				System.out.println(person);
			}
			
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	/*
	 * 2. 使用QUery接口进行带条件的查询
	 */
	@Test
	public void testQuery2(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Person.class);
//			criteria.add(Restrictions.eq("name", "王海霞"));
			criteria.add(Restrictions.like("name", "王%"));
			List<Person> list = criteria.list();
			for(Person person:list){
				System.out.println(person);
			}
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
}
