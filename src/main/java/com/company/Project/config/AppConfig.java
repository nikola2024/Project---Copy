package com.company.Project.config;

import com.company.Project.entities.Problem;
import com.company.Project.entities.Solution;
import com.company.Project.entities.User;
import com.company.Project.repositories.HibernateRepository;
import com.company.Project.repositories.base.GenericRepository;
import com.company.Project.utils.validators.ProblemValidator;
import com.company.Project.utils.validators.SolutionValidator;
import com.company.Project.utils.validators.UserValidator;
import com.company.Project.utils.validators.base.Validator;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  @Autowired
  GenericRepository<Problem> provideProblemsGenericRepository(SessionFactory sessionFactory) {
    HibernateRepository<Problem> repo = new HibernateRepository<>(sessionFactory);
    repo.setEntityClass(Problem.class);

    return repo;
  }

  @Bean
  @Autowired
  GenericRepository<User> provideUsersGenericRepository(SessionFactory sessionFactory) {
    HibernateRepository<User> repo = new HibernateRepository<>(sessionFactory);
    repo.setEntityClass(User.class);

    return repo;
  }

  @Bean
  @Autowired
  GenericRepository<Solution> provideSolutionsGenericRepository(SessionFactory sessionFactory) {
    HibernateRepository<Solution> repo = new HibernateRepository<>(sessionFactory);
    repo.setEntityClass(Solution.class);

    return repo;
  }

  @Bean
  SessionFactory provideSessionFactory() {
    return HibernateUtils.getSessionFactory();
  }

  @Bean
  Validator<Problem> provideProductValidator() {
    return new ProblemValidator();
  }

  @Bean
  Validator<Solution> provideSolutionValidator(){
    return new SolutionValidator();
  }

  @Bean
  Validator<User> provideUserValidator(){return new UserValidator();}
}
