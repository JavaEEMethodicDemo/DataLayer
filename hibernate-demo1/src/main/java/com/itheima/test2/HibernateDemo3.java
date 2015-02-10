package com.itheima.test2;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;
//��ѯ��ʽ����ʹ��Criteria.�ص㣺����������󡣲���Ҫд���
public class HibernateDemo3 {
	//ʹ��QUery�ӿڲ�ѯ���е�
	@Test
	public void testQuery1(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria c = session.createCriteria(Person.class);//ָ����Ҳ���Ǹ��߿�ܲ��ĸ���
			List<Person> ps = c.list();//��QUery�е�list��ȫһ��
			for(Person p:ps)
				System.out.println(p);
		}catch(Exception e){
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}
	//ʹ��QUery�ӿڽ��д������Ĳ�ѯ
	@Test
	public void testQuery2(){
		Session session = null;
		try{
			session = HibernateUtil.getSession();
			Criteria c = session.createCriteria(Person.class);//ָ����Ҳ���Ǹ��߿�ܲ��ĸ���
			//��������
//			c.add(Restrictions.eq("id", 1));//���ò�ѯ������Criterion������һ����������
			c.add(Restrictions.gt("id", 3));// id�������ݿ��ֶΡ�����ָ��������ԣ�getter setter
			List<Person> ps = c.list();//��QUery�е�list��ȫһ��
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
