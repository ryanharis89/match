package com.ryanharis.app.match.api.mapper;

import com.ryanharis.app.match.common.models.MatchOddsDto;
import com.ryanharis.app.match.persistence.entities.MatchOdds;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchOddsMapper {
  MatchOdds toEntity(MatchOddsDto dto);

  MatchOddsDto toDto(MatchOdds entity);
}
