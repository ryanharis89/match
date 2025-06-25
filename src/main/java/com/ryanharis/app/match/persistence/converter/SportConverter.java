package com.ryanharis.app.match.persistence.converter;

import com.ryanharis.app.match.common.models.enums.Sport;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SportConverter implements AttributeConverter<Sport, Integer> {

  @Override
  public Integer convertToDatabaseColumn(Sport sport) {
    return sport != null ? sport.getId() : null;
  }

  @Override
  public Sport convertToEntityAttribute(Integer id) {
    if (id == null) {
      return null;
    }
    for (Sport sport : Sport.values()) {
      if (sport.getId() == id) {
        return sport;
      }
    }
    throw new IllegalArgumentException("Unknown id: " + id);
  }
}
