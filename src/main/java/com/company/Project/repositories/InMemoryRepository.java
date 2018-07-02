package com.company.Project.repositories;

import com.company.Project.entities.base.ModelEntity;
import com.company.Project.repositories.base.GenericRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository<T extends ModelEntity> implements GenericRepository<T> {
  private List<T> entities;

  public InMemoryRepository() {
    this.entities = new ArrayList<>();
  }

  @Override
  public List<T> getAll() {
    return entities;
  }

  @Override
  public T getById(int id) {
    for (T entity : entities) {
      if (entity.getId() == id) {
        return entity;
      }
    }

    return null;
  }

  @Override
  public T create(T entity) {
    int id = -1;
    if (entities.size() == 0) {
      id = 1;
    } else {
      id = entities.get(entities.size() - 1).getId() + 1;
    }

    entity.setId(id);
    this.entities.add(entity);
    return entity;
  }
}

