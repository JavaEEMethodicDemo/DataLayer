package com.itheima.test;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.itheima.domain.Person;
import com.itheima.util.HibernateUtil;
//Session�Ļ��棺һ������
public class HibernateDemo3 {
	/**
	 * 1. ֤������Ĵ���
	 */
	@Test
	public void testaddStudent(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//OIDһ��
		Person p2 = (Person) session.get(Person.class, 1);//OIDһ��.��Ϊ�������иö���ֱ��ȡ�������ٴ�ȥ���ݿ��ѯ
		System.out.println(p1==p2);//true
		tx.commit();
		session.close();
	}
	/** 2. һ������Ĺ���
	 * session.flush():ˢ�»��棨ͬ�����ݿ⣩�����������ݻ���
	 * session.clear():��ջ��档
	 * session.evict():���ָ����ʵ��
	 */
	@Test
	public void testSessionManager1(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//�õ��־ö��󣬷���Session�Ļ�����
		System.out.println(p1);
		p1.setName("����");
		session.save(p1);
//		session.flush();//ֻ�Ǹ������ݿ�.���������ݻ���
//		session.clear();//��ջ���
		session.evict(p1);//�ӻ��������ָ����ʵ��
		
		Person p2 = (Person) session.get(Person.class, 1);
		System.out.println(p2);
		tx.commit();
		session.close();
	}
	/**
	 * 3. ���գ�commitʱ��ˢ��
	 */
	//
	@Test
	public void testSessionManager2(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//�õ��־ö��󣬷���Session�Ļ�����
		p1.setName("������2");//ˢ�����ݿ�����ֻ��ˢ�³־�̥ʵ��
		tx.commit();//��ʱ����ͬ�����ݿ�
		session.close();
	}
	@Test
	/**
	 * 4. �ֹ�flush��ˢ��
	 */
	public void testSessionManager3(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//�õ��־ö��󣬷���Session�Ļ�����
		p1.setName("������");//ˢ�����ݿ�����ֻ��ˢ�³־�̥ʵ��
		session.flush();//ͬ�����ݿ�
		tx.commit();//��ʱ����ͬ�����ݿ�
		session.close();
	}
	@Test
	/**
	 * 5. ִ��Query�Ĳ�ѯʱ,Ҳ���Զ��Ѹı��ʵ����ͬ�������ݿ���
	 */
	public void testSessionManager4(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		
		Person p1 = (Person) session.get(Person.class, 1);//�õ��־ö��󣬷���Session�Ļ�����
		p1.setName("������1");//ˢ�����ݿ�����ֻ��ˢ�³־�̥ʵ��
		
		Query q = session.createQuery("from Person");//һ��ǿ���Ե�sql���
		System.out.println(q.list().get(0));
		
		
		session.flush();//ͬ�����ݿ�
		tx.commit();//��ʱ����ͬ�����ݿ�
		session.close();
	}
	@Test
	/**
	 * 6. ��֤ˢ��ģʽ
	 */
	public void testSessionManager5(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
//		session.setFlushMode(FlushMode.COMMIT);//�����ύ���ֹ�flushʱ����ѯʱ����ˢ
//		session.setFlushMode(FlushMode.MANUAL);//�����ύ���ֹ�flushʱ����ѯʱ����ˢ
		Person p1 = (Person) session.get(Person.class, 1);//�õ��־ö��󣬷���Session�Ļ�����
		p1.setName("������");//ˢ�����ݿ�����ֻ��ˢ�³־�̥ʵ��
		
		Query q = session.createQuery("from Person");//һ��ǿ���Ե�sql���
		System.out.println(q.list().get(0));//�ӻ�����ȡ��
		
		
		tx.commit();//��ʱ����ͬ�����ݿ�
		session.flush();//ͬ�����ݿ�
		session.close();
	}
	/**
	 * 7. refresh:�����ݿ��е����ݸ��ǵ������е�����
	 */
	@Test
	public void testSessionManager6(){
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		Person p1 = (Person) session.get(Person.class, 1);//�õ��־ö��󣬷���Session�Ļ�����
		p1.setName("������");
		
		session.refresh(p1);//�����ݿ� �е����ݸ��ǵ������е�����
		
		
		tx.commit();//��ʱ����ͬ�����ݿ�
		session.close();
	}
}
