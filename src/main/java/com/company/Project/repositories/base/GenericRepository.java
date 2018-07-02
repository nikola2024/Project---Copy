package com.company.Project.repositories.base;


import com.company.Project.entities.base.ModelEntity;

import java.util.List;

public interface GenericRepository<T extends ModelEntity> {
  List<T> getAll();

  T getById(int id);

  T create(T entity);
}
