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
 * ��ѯ��ʽ����ʹ��Criteria.�ص㣺����������󡣲���Ҫд���
 */
public class HibernateDemo3 {
	/*
	 * 1. ʹ��QUery�ӿڲ�ѯ���е�
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
	 * 2. ʹ��QUery�ӿڽ��д������Ĳ�ѯ
	 */
	@Test
	public void testQuery2(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Person.class);
//			criteria.add(Restrictions.eq("name", "����ϼ"));
			criteria.add(Restrictions.like("name", "��%"));
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
