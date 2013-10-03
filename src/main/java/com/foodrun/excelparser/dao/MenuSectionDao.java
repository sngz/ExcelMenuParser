package com.foodrun.excelparser.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.foodrun.excelparser.domain.MenuSection;
import com.foodrun.excelparser.util.HibernateUtil;

public class MenuSectionDao {
	public String saveMenuSection(String name, String description, String menuPageId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String id = null;
		try {
			transaction = session.beginTransaction();
			MenuSection menuSection = new MenuSection();
			menuSection.setName(name);
			menuSection.setDescription(description);
			menuSection.setMenuPageId(menuPageId);
			id = (String) session.save(menuSection);
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
