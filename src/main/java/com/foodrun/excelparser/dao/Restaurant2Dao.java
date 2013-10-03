package com.foodrun.excelparser.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.foodrun.excelparser.domain.Restaurant2;
import com.foodrun.excelparser.util.HibernateUtil;

public class Restaurant2Dao {
	
	public String saveRestaurant2(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String id = null;
		try {
			transaction = session.beginTransaction();
			Restaurant2 restaurant2 = new Restaurant2();
			restaurant2.setName(name);
			id = (String) session.save(restaurant2);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
}
