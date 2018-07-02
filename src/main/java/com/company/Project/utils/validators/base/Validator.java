package com.company.Project.utils.validators.base;

public interface Validator<T> {
  boolean isValid(T object);
}
