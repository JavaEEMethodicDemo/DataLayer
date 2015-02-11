package com.itheima.test;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.itheima.domain.OrderItem;
import com.itheima.domain.Person;
import com.itheima.domain.Student;
import com.itheima.util.HibernateUtil;
public class HibernateDemo2 {
	@Test
	public void testaddStudent(){
		Student s = new Student();
		s.setFirstName("��ѩ");
		s.setSecondName("��");
		s.setGender("����");
		Session session = HibernateUtil.getSession();
		
		Transaction tx = session.beginTransaction();
		session.save(s);
		tx.commit();
		
		session.close();
	}
	//��������������ѯʵ��
	@Test
	public void testQueryStudent(){
		Session session = HibernateUtil.getSession();
		
		//������������
		Student s1 = new Student();
		s1.setFirstName("zhting");
		s1.setSecondName("w");
		
		Student s = (Student) session.get(Student.class, s1);//��������:ʹ��ʵ����������Ϊ����
		System.out.println(s);
		
		session.close();
	}
	//�������ԵĻ�ȡ
	@Test
	public void testQueryOrder(){
		Session session = HibernateUtil.getSession();
		
		
		OrderItem o = (OrderItem) session.get(OrderItem.class, 1);
		System.out.println(o);
		
		session.close();
	}
}
