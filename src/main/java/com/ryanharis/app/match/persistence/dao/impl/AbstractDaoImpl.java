package com.ryanharis.app.match.persistence.dao.impl;

import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.common.exceptions.errordetails.MatchAppErrorDetails;
import com.ryanharis.app.match.persistence.dao.AbstractDao;
import com.ryanharis.app.match.persistence.entities.IDomainObject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbstractDaoImpl<T extends IDomainObject> implements AbstractDao<T> {
  private static Logger LOGGER;

  private final Class<T> clazz;
  @PersistenceContext
  private EntityManager entityManager;

  public AbstractDaoImpl(final Class<T> clazz) {
    this.clazz = clazz;
    LOGGER = LoggerFactory.getLogger(clazz);
  }

  @Override
  public List<T> getAll() throws MatchAppException {
    try {
      final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
      final CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);

      final Root<T> root = criteriaQuery.from(clazz);
      final CriteriaQuery<T> all = criteriaQuery.select(root);
      criteriaQuery.distinct(true);
      final TypedQuery<T> query = entityManager.createQuery(all);
      return query.getResultList();
    } catch (final Exception e) {
      LOGGER.error("Error occurred in getAll while trying to read from db with message:{}", e.getMessage());
      throw new MatchAppException(MatchAppErrorDetails.DB_READ_ERROR, e, new String[] {clazz.getSimpleName()});
    }
  }

  @Override
  public T getById(Serializable id) throws MatchAppException {
    final T domainObject;
    try {
      domainObject = entityManager.find(clazz, id);
    } catch (final Exception e) {
      LOGGER.error("Error while trying to fetch entity with id:{} and message:{}", id,
          e.getMessage());
      throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_FIND_ENTITY, e, new String[] {
          clazz.getSimpleName(), id.toString()
      });
    }
    if (domainObject == null) {
      LOGGER.error("{} with id {} not found", clazz.getSimpleName(), id);
      throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_FIND_ENTITY, new String[] {
          clazz.getSimpleName(), id.toString()
      });
    }
    return domainObject;
  }

  @Override
  public void delete(T domainObject) throws MatchAppException {
    try {
      delete(domainObject.getId());
    } catch (MatchAppException e) {
      LOGGER.error("Error while trying to delete entity {} with id: {} and message {}", clazz.getSimpleName(),
          domainObject.getId(), e.getMessage(), e);
      throw e;
    }
  }

  @Override
  public void delete(Serializable id) throws MatchAppException {
    try {
      T entity = getById(id);
      entityManager.remove(entity);
    } catch (MatchAppException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("Error while trying to delete entity {} with id: {} and message {}", clazz.getSimpleName(),
          id, e.getMessage(), e);
      throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_DELETE_ENTITY, e,
          new String[] {clazz.getSimpleName(), id.toString()});
    }
  }

  // @Override
  // public void delete(T domainObject) throws MatchAppException {
  //   try {
  //     boolean isManagedEntity = entityManager.contains(domainObject);
  //     if (isManagedEntity) {
  //       entityManager.remove(domainObject);
  //     } else {
  //       delete(domainObject.getId());
  //     }
  //   } catch (final Exception e) {
  //     LOGGER.error("Error occurred in delete while trying to remove entity with id:{} and message:{}",
  //         domainObject.getId(), e.getMessage());
  //     throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_DELETE_ENTITY, e,
  //         new String[] {clazz.getSimpleName(), domainObject.getId().toString()});
  //   }
  // }
  //
  // @Override
  // public void delete(Serializable id) throws MatchAppException {
  //   try {
  //     entityManager.remove(entityManager.find(clazz, id));
  //   } catch (final Exception e) {
  //     LOGGER.error("Error occurred in delete while trying to remove entity with id:{} and message:{}",
  //         id, e.getMessage());
  //     throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_DELETE_ENTITY, e,
  //         new String[] {clazz.getSimpleName(), id.toString()});
  //   }
  // }

  @Override
  public T save(T domainObject) throws MatchAppException {
    try {
      entityManager.persist(domainObject);
      return domainObject;
    } catch (final Exception e) {
      LOGGER.error("Error occurred in save while trying to persist entity with id:{} and message:{}",
          domainObject.getId(), e.getMessage());
      throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_PERSIST_ENTITY, e,
          new String[] {clazz.getSimpleName()});
    }
  }

  @Override
  public T update(T domainObject) throws MatchAppException {
    return update(domainObject, false);
  }

  private T update(T domainObject, boolean shouldFlush) throws MatchAppException {
    try {
      if (shouldFlush) {
        final T mergedObject = entityManager.merge(domainObject);
        entityManager.flush();
        return mergedObject;
      }
      return entityManager.merge(domainObject);
    } catch (final Exception e) {
      LOGGER.error("Error occurred in update while trying to merge entity with id:{} and message:{}",
          domainObject.getId(), e.getMessage());
      throw new MatchAppException(MatchAppErrorDetails.UNABLE_TO_UPDATE_ENTITY, e,
          new String[] {clazz.getSimpleName(), domainObject.getId().toString()});
    }
  }
}
