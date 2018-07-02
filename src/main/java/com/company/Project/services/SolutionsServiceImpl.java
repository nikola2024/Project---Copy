package com.company.Project.services;

import com.company.Project.entities.Problem;
import com.company.Project.entities.Solution;
import com.company.Project.entities.User;
import com.company.Project.repositories.base.GenericRepository;
import com.company.Project.services.base.SolutionsService;
import com.company.Project.utils.validators.base.Validator;
import org.springframework.stereotype.Service;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionsServiceImpl implements SolutionsService {
  private static final int PAGE_SIZE = 10;
  private final Validator<Solution> solutionValidator;
  private final GenericRepository<Solution> solutionRepository;

  public SolutionsServiceImpl(Validator<Solution> solutionValidator, GenericRepository<Solution> solutionRepository) {
    this.solutionValidator = solutionValidator;
    this.solutionRepository = solutionRepository;
  }

  @Override
  public List<Solution> getSolutionsByUser(User user) {
    List<Solution> SolutionsByUser = new ArrayList<>();
    SolutionsByUser.addAll(user.getSolutions());
    return SolutionsByUser;
  }

  @Override
  public List<Solution> getSolutionsByUserAndPage(User user, int pageNumber) {
    int fromIndex = pageNumber * PAGE_SIZE;
    int toindex = (pageNumber + 1) * PAGE_SIZE;

    return getSolutionsByUser(user)
        .subList(fromIndex,toindex);
  }

  @Override
  public List<Solution> getSolutionsByProblem(Problem problem) {
    List<Solution> SolutionsByProblem = new ArrayList<>();
    SolutionsByProblem.addAll(problem.getSolutions());
    return SolutionsByProblem;
  }

  @Override
  public List<Solution> getSolutionsByProblemAndPage(Problem problem, int pageNumber) {
    int fromIndex = pageNumber * PAGE_SIZE;
    int toindex = (pageNumber + 1) * PAGE_SIZE;

    return getSolutionsByProblem(problem)
        .subList(fromIndex,toindex);
  }

  @Override
  public void createSolution(Solution solution) throws InvalidObjectException {
    if(!solutionValidator.isValid(solution)) {
      throw new InvalidObjectException("Invalid problem");
    }

    solutionRepository.create(solution);
  }
}
