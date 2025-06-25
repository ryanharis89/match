package com.ryanharis.app.match.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.persistence.dao.MatchDao;
import com.ryanharis.app.match.persistence.entities.Match;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit tests for MatchServiceImpl class")
class MatchServiceImplTest {

  @Mock
  private MatchDao matchDao;

  @InjectMocks
  private MatchServiceImpl matchService;

  private Match sampleMatch;

  @BeforeEach
  void setUp() {
    sampleMatch = new Match();
    sampleMatch.setId(1L);
  }

  @Test
  @DisplayName("Should return list of all matches")
  void testGetAll() throws MatchAppException {
    List<Match> matches = Collections.singletonList(sampleMatch);
    when(matchDao.getAll()).thenReturn(matches);

    List<Match> result = matchService.getAll();

    assertNotNull(result);
    assertEquals(1, result.size());
    verify(matchDao, times(1)).getAll();
  }

  @Test
  @DisplayName("Should return a unique match by ID")
  void testGetById() throws MatchAppException {
    when(matchDao.getById(1L)).thenReturn(sampleMatch);

    Match result = matchService.getById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    verify(matchDao, times(1)).getById(1L);
  }

  @Test
  @DisplayName("Should create a new match")
  void testCreate() throws MatchAppException {
    when(matchDao.save(sampleMatch)).thenReturn(sampleMatch);

    Match result = matchService.create(sampleMatch);

    assertNotNull(result);
    verify(matchDao, times(1)).save(sampleMatch);
  }

  @Test
  @DisplayName("Should update an existing match")
  void testUpdate() throws MatchAppException {
    when(matchDao.update(sampleMatch)).thenReturn(sampleMatch);

    Match result = matchService.update(sampleMatch);

    assertNotNull(result);
    verify(matchDao, times(1)).update(sampleMatch);
  }

  @Test
  @DisplayName("Should delete a match by entity")
  void testDeleteByEntity() throws MatchAppException {
    doNothing().when(matchDao).delete(sampleMatch);

    assertDoesNotThrow(() -> matchService.delete(sampleMatch));
    verify(matchDao, times(1)).delete(sampleMatch);
  }

  @Test
  @DisplayName("Should delete a match by ID")
  void testDeleteById() throws MatchAppException {
    doNothing().when(matchDao).delete(1L);

    assertDoesNotThrow(() -> matchService.delete(1L));
    verify(matchDao, times(1)).delete(1L);
  }
}
