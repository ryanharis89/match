package com.ryanharis.app.match.service;

import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.persistence.entities.Match;
import java.util.List;

public interface MatchService {
  List<Match> getAll() throws MatchAppException;

  Match getById(Long id) throws MatchAppException;

  Match create(Match match) throws MatchAppException;

  Match update(Match match) throws MatchAppException;

  void delete(Match match) throws MatchAppException;

  void delete(Long id) throws MatchAppException;
}
