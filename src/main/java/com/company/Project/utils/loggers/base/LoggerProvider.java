package com.company.Project.utils.loggers.base;

public interface LoggerProvider<T> {
  void info(Object message);

  void error(Object message);

  void setClass(Class<T> tClass);
}
