package com.ryanharis.app.match.persistence.dao;

import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.persistence.entities.IDomainObject;
import java.io.Serializable;
import java.util.List;

public interface AbstractDao<T extends IDomainObject> {

  List<T> getAll() throws MatchAppException;

  T getById(Serializable id) throws MatchAppException;

  void delete(T domainObject) throws MatchAppException;

  void delete(Serializable id) throws MatchAppException;

  T save(T domainObject) throws MatchAppException;

  T update(T domainObject) throws MatchAppException;
}
