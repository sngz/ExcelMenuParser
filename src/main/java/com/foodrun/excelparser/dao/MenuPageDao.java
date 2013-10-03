package com.foodrun.excelparser.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.foodrun.excelparser.domain.MenuPage;
import com.foodrun.excelparser.util.HibernateUtil;

public class MenuPageDao {
	
	public String saveMenuPage(String restaurantId, String name, String description) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String id = null;
		try {
			transaction = session.beginTransaction();
			MenuPage menuPage = new MenuPage();
			menuPage.setRestaurantId(restaurantId);
			menuPage.setName(name);
			menuPage.setDescription(description);
			id = (String) session.save(menuPage);
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
