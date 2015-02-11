package com.itheima.test2;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;

public class HibernateDemo2 {
	/**
	 * 1. ʹ��QUery�ӿڽ��д������Ĳ�ѯ
	 */
	@Test
	public void testQuery1(){
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p where p.name like ? and p.gender=?");
			query.setString(0, "��%");
			query.setString(1, "Ů");
			
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
	 * 2. ʹ��QUery�ӿڽ��д������Ĳ�ѯ
	 */
	@Test
	public void testQuery2(){
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p where p.name like :name and p.gender=:gender");
			query.setString("name", "��%");
			query.setString("gender", "��");
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
	 * 3. ʹ��QUery�ӿڽ��д������Ĳ�ѯ:���ؽ��ֻ��һ��
	 */
	@Test
	public void testQuery3(){
		Session session=null;
		try {
			session = HibernateUtil.getSession();
			Query query = session.createQuery("select p from Person p where p.name=:name");
			query.setString("name", "������");
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
	 * 4. ʹ��QUery�ӿڽ��д������Ĳ�ѯ:��ҳ��ѯ�����ݿ��޹أ����û���
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
