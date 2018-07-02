package com.company.Project.utils.loggers;

import com.company.Project.utils.loggers.base.LoggerProvider;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Log4LoggerProvider<T> implements LoggerProvider<T>{

  private Logger logger;

  @Override
  public void info(Object message) {
    logger.info(message);
  }

  @Override
  public void error(Object message) {
    logger.error(message);
  }

  public void setClass(Class<T> tClass) {
    logger = Logger.getLogger(tClass);
  }
}
