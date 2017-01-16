package com.sqa2014.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@SuppressWarnings("deprecation")
public class HibernateUtil {

      private static final SessionFactory sessionFactory;

      static {
              sessionFactory = new Configuration().configure("hibernate.remote.cfg.xml").buildSessionFactory();
      }

      public static SessionFactory getSessionFactory() {
          return sessionFactory;
      }

}