package com.itheima.test2;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;
//查询方式二：使用Criteria.特点：更加面向对象。不需要写语句
public class HibernateDemo3 {
	//使用QUery接口查询所有的
	@Test
	public void testQuery1(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria c = session.createCriteria(Person.class);//指定类也就是告诉框架查哪个表
			List<Person> ps = c.list();//和QUery中的list完全一致
			for(Person p:ps)
				System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	//使用QUery接口进行带条件的查询
	@Test
	public void testQuery2(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria c = session.createCriteria(Person.class);//指定类也就是告诉框架查哪个表
			//设置条件
//			c.add(Restrictions.eq("id", 1));//设置查询条件。Criterion代表着一个条件对象
			c.add(Restrictions.gt("id", 3));// id不是数据库字段。而是指定类的属性，getter setter
			List<Person> ps = c.list();//和QUery中的list完全一致
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
