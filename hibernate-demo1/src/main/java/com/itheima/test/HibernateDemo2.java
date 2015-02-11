package com.itheima.test;

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
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			String hql = "select p from Person p where p.name like ?";//*��sql��
			Query query = session.createQuery(hql);
			query.setString(0, "��%");//�±��0��ʼ
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
	 * 2. ʹ��QUery�ӿڽ��д������Ĳ�ѯ
	 */
	@Test
	public void testQuery2(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			//��ͳ���ʺ�ռλ����
//			String hql = "select p from Person p where p.id>? and p.name like ?";//*��sql��
//			Query query = session.createQuery(hql);
//			query.setString(0, "��%");//�±��0��ʼ
//			query.setInteger(1, 2);//�±��0��ʼ
			
			//����������ѯ���������ڲ���˳���޹� �Ƽ�
			String hql = "select p from Person p where p.name like :name and p.id=:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", 2);//��ӦHQL�����   :id
			query.setString("name", "��%");//   :name
			
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
	 * 3. ʹ��QUery�ӿڽ��д������Ĳ�ѯ:���ؽ��ֻ��һ��
	 */
	@Test
	public void testQuery3(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			String hql = "select p from Person p where p.id>:id";
			Query query = session.createQuery(hql);
			query.setInteger("id", 2);//��ӦHQL�����   :id
			
			List<Person> ps = query.list();
			for(Person p:ps)
				System.out.println(p);
			
//			Person p = (Person)query.uniqueResult();//ֻ�����ڽ��ֻ��һ��������¡����򱨴�
//			System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	
	/**
	 * 4. ʹ��QUery�ӿڽ��д������Ĳ�ѯ:��ҳ��ѯ�����ݿ��޹أ����û���
	 */
	@Test
	public void testQuery4(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			String hql = "from Person";
			Query query = session.createQuery(hql);
			query.setFirstResult(0);//��ҳ��ѯÿҳ��ʼ�ļ�¼����
			query.setMaxResults(2);//ÿ��ȡ������
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
