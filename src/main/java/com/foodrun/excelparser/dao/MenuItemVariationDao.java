package com.foodrun.excelparser.dao;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.foodrun.excelparser.domain.MenuItemVariation;
import com.foodrun.excelparser.util.HibernateUtil;

public class MenuItemVariationDao {
	
	public String saveMenuItemVariation(String menuItemId, String text, BigDecimal cost) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		String id = null;
		try {
			transaction = session.beginTransaction();
			MenuItemVariation menuItemVariation = new MenuItemVariation();
			menuItemVariation.setMenuItemId(menuItemId);
			menuItemVariation.setText(text);
			menuItemVariation.setCost(cost);
			id = (String) session.save(menuItemVariation);
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
