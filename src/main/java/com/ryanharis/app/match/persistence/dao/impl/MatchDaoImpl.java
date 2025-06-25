package com.ryanharis.app.match.persistence.dao.impl;

import com.ryanharis.app.match.persistence.dao.MatchDao;
import com.ryanharis.app.match.persistence.entities.Match;
import org.springframework.stereotype.Repository;

@Repository
public class MatchDaoImpl extends AbstractDaoImpl<Match> implements MatchDao {

  public MatchDaoImpl() {
    super(Match.class);
  }
}
