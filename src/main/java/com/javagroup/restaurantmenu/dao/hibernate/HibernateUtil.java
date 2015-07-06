package com.javagroup.restaurantmenu.dao.hibernate;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.*;
import org.hibernate.service.ServiceRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HibernateUtil {

	private final static Logger log = LogManager.getLogger(HibernateUtil.class);
	private static SessionFactory sessionFactory;

	static {
		try {
			rebuildSessionFactory();
		} catch (Throwable ex) {
			// We have to catch Throwable, otherwise we will miss
			// NoClassDefFoundError and other subclasses of Error
			log.error("Building SessionFactory failed.", ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			rebuildSessionFactory();
		}
		return sessionFactory;
	}

	public static void shutdown() {
		log.debug("Shutting down Hibernate");
		// Close caches and connection pools
		getSessionFactory().close();

		// Clear static variables
		sessionFactory = null;
	}

	public static void rebuildSessionFactory() {
		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
		Configuration configuration = new Configuration().configure();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
}
