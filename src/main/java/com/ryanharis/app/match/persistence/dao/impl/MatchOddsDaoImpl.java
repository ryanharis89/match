package com.ryanharis.app.match.persistence.dao.impl;

import com.ryanharis.app.match.persistence.dao.MatchOddsDao;
import com.ryanharis.app.match.persistence.entities.MatchOdds;
import org.springframework.stereotype.Repository;

@Repository
public class MatchOddsDaoImpl extends AbstractDaoImpl<MatchOdds> implements MatchOddsDao {

  public MatchOddsDaoImpl() {
    super(MatchOdds.class);
  }
}
