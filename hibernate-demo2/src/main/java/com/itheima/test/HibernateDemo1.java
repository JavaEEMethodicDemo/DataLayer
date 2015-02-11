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
 * ����ʾget��load������������
 * @author yajun
 *
 */
public class HibernateDemo1 {
	/**
	 * 1. ʵ�����ӳ���ļ�����ʾincrement
	 */
	@Test
	public void testaddPerson(){
		Person p = new Person();
		p.setName("������1");
		p.setBirthday(new Date());
		p.setGender("��");
		Session session = HibernateUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		session.save(p);//׼��ִ��sql���
		System.out.println(p.getName());
		tx.commit();
		
		session.close();
		System.out.println(p);
	}
	/**
	 * 2. δ�ر�session֮ǰ������load�õ��Ķ���
	 */
	@Test
	public void testQueryOnePerson(){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
//		Object obj = session.get(Person.class, 1);//�˴�׼��ִ�е�SQL
		Object obj  = session.load(Person.class, 1);//���ص���Person����Ĵ�������ʵ����ֻ������id��ֵ��
		Person p = (Person)obj;
		System.out.println(p);//�����õ��ö��������ʱ
		
		session.close();
		sessionFactory.close();
	}
	
	/**
	 * 3. get��load������
	 * �ر�session֮��
	 */
	@Test
	public void testQueryOnePerson1(){
		Person p = findById(1);
		System.out.println(p);
	}
	/**
	 * get��load������
	 */
	public Person findById(Integer id){
		Configuration cfg = new Configuration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		Session session = sessionFactory.openSession();
		
//		Object obj = session.get(Person.class, 1);//�˴�׼��ִ�е�SQL   �����ӣ���������
		Object obj  = session.load(Person.class, 1);//���ص���Person����Ĵ�������ʵ����ֻ������id��ֵ��   �����ӣ��ӳټ���
		
		session.close();
		sessionFactory.close();
		return (Person)obj;
	}
}
