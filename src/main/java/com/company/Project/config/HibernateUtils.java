package com.company.Project.config;

import com.company.Project.entities.Problem;
import com.company.Project.entities.Solution;
import com.company.Project.entities.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
  static SessionFactory sessionFactory;

  static {
    org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration()
        .configure();

    configuration.addAnnotatedClass(Problem.class);
    configuration.addAnnotatedClass(User.class);
    configuration.addAnnotatedClass(Solution.class);

    StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();

    serviceRegistryBuilder.applySettings(configuration.getProperties());
    StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }
}