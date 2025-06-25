package com.ryanharis.app.match.persistence.converter;

import com.ryanharis.app.match.common.models.enums.Specifier;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.util.Objects;

@Converter(autoApply = true)
public class SpecifierConverter implements AttributeConverter<Specifier, String> {

  @Override
  public String convertToDatabaseColumn(Specifier specifier) {
    return specifier != null ? specifier.getCode() : null;
  }

  @Override
  public Specifier convertToEntityAttribute(String code) {
    if (code == null) {
      return null;
    }
    for (Specifier specifier : Specifier.values()) {
      if (Objects.equals(specifier.getCode(), code)) {
        return specifier;
      }
    }
    throw new IllegalArgumentException("Unknown code: " + code);
  }
}
