package com.foodrun.excelparser.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.foodrun.excelparser.domain.MenuItem;
import com.foodrun.excelparser.util.HibernateUtil;

public class MenuItemDao {
	public String saveMenuItem(String name, String description, String menusectionId, String servingId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String id = null;
		try {
			transaction = session.beginTransaction();
			MenuItem menuItem = new MenuItem();
			menuItem.setName(name);
			menuItem.setDescription(description);
			menuItem.setMenusectionId(menusectionId);
			menuItem.setServingId(servingId);
			id = (String) session.save(menuItem);
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
