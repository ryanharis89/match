package com.ryanharis.app.match.service.impl;

import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.persistence.dao.MatchDao;
import com.ryanharis.app.match.persistence.entities.Match;
import com.ryanharis.app.match.service.MatchService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {
  private static final Logger LOGGER = LoggerFactory.getLogger(MatchServiceImpl.class);


  private final MatchDao matchDao;

  @Autowired
  public MatchServiceImpl(MatchDao matchDao) {
    this.matchDao = matchDao;
  }

  @Override
  public List<Match> getAll() throws MatchAppException {
    LOGGER.info("Inside getAll");
    return matchDao.getAll();
  }

  @Override
  public Match getById(Long id) throws MatchAppException {
    LOGGER.info("Inside getById with id:{}", id);
    return matchDao.getById(id);
  }

  @Override
  public Match create(Match match) throws MatchAppException {
    LOGGER.info("Inside create with mapped entity:{}", match);
    return matchDao.save(match);
  }

  @Override
  public Match update(Match match) throws MatchAppException {
    LOGGER.info("Inside update with mapped entity:{}", match);
    return matchDao.update(match);
  }

  @Override
  public void delete(Match match) throws MatchAppException {
    LOGGER.info("Inside delete with mapped entity:{}", match);
    matchDao.delete(match);
  }

  @Override
  public void delete(Long id) throws MatchAppException {
    LOGGER.info("Inside delete with id:{}", id);
    matchDao.delete(id);
  }
}
