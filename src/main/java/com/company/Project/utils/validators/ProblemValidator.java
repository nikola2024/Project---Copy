package com.company.Project.utils.validators;

import com.company.Project.entities.Problem;
import com.company.Project.utils.validators.base.Validator;

public class ProblemValidator implements Validator<Problem> {
  private static final int MIN_TITLE_LENGTH = 2;
  private static final int MAX_TITLE_LENGTH = 45;
  private static final int MIN_STATEMENT_LENGTH = 4;
  private static final int MAX_STATEMENT_LENGTH = 2500;
  private static final int MIN_DIFFICULTY_LVL = 0;
  private static final int MAX_DIFFICULTY_LVL = 10;

  @Override
  public boolean isValid(Problem problem) {
    return problem != null &&
        isTitleValid(problem.getTitle()) &&
        isDifficultyValid(problem.getDifficultyLevel()) &&
        isStatementValid(problem.getStatement()) &&
        isCategoryValid(problem.getCategory());
  }

  private boolean isTitleValid(String title){
    return title != null &&
        title.length() >= MIN_TITLE_LENGTH &&
        title.length() <= MAX_TITLE_LENGTH;
  }

  private boolean isStatementValid(String statement){
    return statement != null &&
        statement.length() >= MIN_STATEMENT_LENGTH &&
        statement.length() <= MAX_STATEMENT_LENGTH;
  }

  private boolean isDifficultyValid(int difficultyLevel){
    return difficultyLevel >= MIN_DIFFICULTY_LVL &&
    difficultyLevel <= MAX_DIFFICULTY_LVL;
  }

  private boolean isCategoryValid(String category){
    return category!="" && category!=null;
  }
}
