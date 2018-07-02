package com.company.Project.services.base;

import com.company.Project.entities.Problem;
import com.company.Project.entities.Solution;
import com.company.Project.entities.User;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Set;

public interface SolutionsService {

  List<Solution> getSolutionsByUser(User user);

  List<Solution> getSolutionsByUserAndPage(User user, int pageNumber);

  List<Solution> getSolutionsByProblem(Problem problem);

  List<Solution> getSolutionsByProblemAndPage(Problem problem, int pageNumber);

  void createSolution(Solution solution) throws InvalidObjectException;
}
