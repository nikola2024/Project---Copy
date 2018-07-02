package com.company.Project.utils.validators;

import com.company.Project.entities.Solution;
import com.company.Project.utils.validators.base.Validator;

public class SolutionValidator implements Validator<Solution> {
  private static final int MIN_TEXT_LENGTH = 4;
  private static final int MAX_TEXT_LENGTH = 2500;

  @Override
  public boolean isValid(Solution solution) {
    return solution != null &&
        isTextValid(solution.getText());
  }

  private boolean isTextValid(String text){
    return text != null &&
        text.length() >= MIN_TEXT_LENGTH &&
        text.length() <= MAX_TEXT_LENGTH;
  }
}
