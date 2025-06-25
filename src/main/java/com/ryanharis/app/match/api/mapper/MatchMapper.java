package com.ryanharis.app.match.api.mapper;

import com.ryanharis.app.match.common.models.MatchDto;
import com.ryanharis.app.match.persistence.entities.Match;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = MatchOddsMapper.class)
public abstract class MatchMapper {
  public abstract Match toEntity(MatchDto dto);

  public abstract MatchDto toDto(Match entity);

  @AfterMapping
  protected void linkOdds(@MappingTarget Match match) {
    if (match.getOdds() != null) {
      match.getOdds().forEach(odd -> odd.setMatch(match));
    }
  }
}
