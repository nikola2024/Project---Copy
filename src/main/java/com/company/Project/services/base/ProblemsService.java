package com.company.Project.services.base;

import com.company.Project.entities.Problem;
import com.company.Project.entities.User;

import java.io.InvalidObjectException;
import java.util.List;
import java.util.Set;

public interface ProblemsService {
  List<Problem> getAllProblems();

  List<Problem> getProblemsByCategory(String category);

  Problem getProblemById(int id);

  List<Problem> getProblemsByUser(User user);

  List<Problem> getAllProblemsByPage(int pageNumber);

  List<Problem> getAllProblemsByUserAndPage(User user, int pageNumber);

  List<Problem> getProblemsByCategoryAndPage(String category, int pageNumber);

  void createProblem(Problem problem) throws InvalidObjectException;
}
