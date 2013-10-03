package com.foodrun.excelparser.util;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.foodrun.excelparser.domain.MenuItem;
import com.foodrun.excelparser.domain.MenuItemVariation;
import com.foodrun.excelparser.domain.MenuPage;
import com.foodrun.excelparser.domain.MenuSection;
import com.foodrun.excelparser.domain.Restaurant2;

public class HibernateUtil {
	
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	
	static {
			try{
				Configuration cfg = new Configuration()
				.addPackage("com.foodrun.excelparser.domain")
				.addAnnotatedClass(Restaurant2.class)
				.addAnnotatedClass(MenuPage.class)
				.addAnnotatedClass(MenuSection.class)
				.addAnnotatedClass(MenuItem.class)
				.addAnnotatedClass(MenuItemVariation.class);
				cfg.configure();
				serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();
				sessionFactory = cfg.buildSessionFactory(serviceRegistry);
			}
			catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new HibernateException(ex);
			}
		}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
