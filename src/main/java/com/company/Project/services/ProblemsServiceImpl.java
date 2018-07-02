package com.company.Project.services;

import com.company.Project.entities.Problem;
import com.company.Project.entities.User;
import com.company.Project.repositories.HibernateRepository;
import com.company.Project.repositories.base.GenericRepository;
import com.company.Project.services.base.ProblemsService;
import com.company.Project.utils.validators.base.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemsServiceImpl implements ProblemsService{
  private static final int PAGE_SIZE = 10;
  private final GenericRepository<Problem> problemsRepository;
  private final Validator<Problem> problemValidator;

  @Autowired
  public ProblemsServiceImpl(GenericRepository<Problem> problemsRepository, Validator<Problem> problemValidator) {
    this.problemsRepository = problemsRepository;
    this.problemValidator = problemValidator;
  }

  @Override
  public List<Problem> getAllProblems() {
    return problemsRepository.getAll();
  }

  @Override
  public List<Problem> getProblemsByCategory(String categoryName) {
    List<Problem> problems = problemsRepository.getAll();

    return problems.stream()
        .filter(problem -> problem.getCategory().equals(categoryName))
        .collect(Collectors.toList());
  }

  @Override
  public Problem getProblemById(int id) {
    return problemsRepository.getById(id);
  }

  @Override
  public List<Problem> getProblemsByUser(User user) {
    List<Problem> ProblemsByUser = new ArrayList<>();
    ProblemsByUser.addAll(user.getProblems());
    return ProblemsByUser;
  }

  @Override
  public List<Problem> getAllProblemsByPage(int pageNumber) {
    int fromIndex = pageNumber * PAGE_SIZE;
    int toIndex = (pageNumber + 1) * PAGE_SIZE;

    return getAllProblems()
        .subList(fromIndex, toIndex);
  }

  @Override
  public List<Problem> getAllProblemsByUserAndPage(User user, int pageNumber) {
    int fromIndex = pageNumber * PAGE_SIZE;
    int toindex = (pageNumber + 1) * PAGE_SIZE;

    return getProblemsByUser(user)
        .subList(fromIndex,toindex);
  }

  @Override
  public List<Problem> getProblemsByCategoryAndPage(String categoryName, int pageNumber) {
    int fromIndex = pageNumber * PAGE_SIZE;
    int toIndex = (pageNumber + 1) * PAGE_SIZE;

    return getProblemsByCategory(categoryName)
        .subList(fromIndex, toIndex);
  }

  @Override
  public void createProblem(Problem problem) throws InvalidObjectException {
    if(!problemValidator.isValid(problem)) {
      throw new InvalidObjectException("Invalid problem");
    }

    problemsRepository.create(problem);
  }
}
