package com.itheima.test2;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.itheima.util.HibernateUtil;

public class HibernateTemplate {
	//��׼��Hibernate����ģ��
	public static void main(String[] args) {
		Session session = null;
		Transaction tx = null;
		try{
			session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			//crud
			tx.commit();
		}catch(Exception e){
			if(tx!=null){
				tx.rollback();
			}
			throw new RuntimeException(e);
		}finally{
			if(session!=null)
				session.close();
		}
	}

}
