package com.ryanharis.app.match.common.models.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Arrays;
import lombok.Getter;

@Getter
public enum Specifier {

  ONE("1"),
  TWO("2"),
  DRAW("X");

  private final String code;

  Specifier(String code) {
    this.code = code;
  }

  @JsonCreator
  public static Specifier fromCode(String code) {
    return Arrays.stream(values())
        .filter(s -> s.code.equals(code))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Unknown specifier: " + code));
  }

  @JsonValue
  public String getCode() {
    return code;
  }
}

