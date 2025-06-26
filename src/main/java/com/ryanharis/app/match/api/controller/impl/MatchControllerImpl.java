package com.ryanharis.app.match.api.controller.impl;

import com.ryanharis.app.match.api.controller.MatchController;
import com.ryanharis.app.match.api.mapper.MatchMapper;
import com.ryanharis.app.match.common.exceptions.MatchAppException;
import com.ryanharis.app.match.common.exceptions.errordetails.MatchAppErrorDetails;
import com.ryanharis.app.match.common.models.MatchDto;
import com.ryanharis.app.match.persistence.entities.Match;
import com.ryanharis.app.match.service.MatchService;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatchControllerImpl implements MatchController {
  private static final Logger LOGGER = LoggerFactory.getLogger(MatchControllerImpl.class);

  private final MatchService matchService;
  private final MatchMapper matchMapper;

  @Autowired
  public MatchControllerImpl(MatchService matchService, MatchMapper matchMapper) {
    this.matchService = matchService;
    this.matchMapper = matchMapper;
  }

  @Override
  public List<MatchDto> getAll() throws MatchAppException {
    return matchService.getAll().stream().map(matchMapper::toDto).collect(Collectors.toList());
  }

  @Override
  public MatchDto getById(Long id) throws MatchAppException {
    return matchMapper.toDto(matchService.getById(id));
  }

  @Override
  public MatchDto create(MatchDto matchDto) throws MatchAppException {
    Match match = matchMapper.toEntity(matchDto);
    return matchMapper.toDto(matchService.create(match));
  }

  @Override
  public MatchDto update(Long id, MatchDto matchDto) throws MatchAppException {
    if (!id.equals(matchDto.getId())) {
      LOGGER.error("Mismatch between provided id:{} and dto.id:{}", id, matchDto.getId());
      throw new MatchAppException(MatchAppErrorDetails.BAD_REQUEST,
          new String[] {"id:" + id + " vs matchDto.id:" + matchDto.getId()});
    }
    Match match = matchMapper.toEntity(matchDto);
    return matchMapper.toDto(matchService.update(match));
  }

  @Override
  public void delete(Long id) throws MatchAppException {
    matchService.delete(id);
  }
}
