package com.ryanharis.app.match.common.models.enums;

import lombok.Getter;

@Getter
public enum Sport {
  FOOTBALL(1, "Football"),
  BASKETBALL(2, "Basketball");

  private final int id;
  private final String sportName;

  Sport(int id, String sportName) {
    this.id = id;
    this.sportName = sportName;
  }
}
