package com.ryanharis.app.match.common.exceptions.errordetails;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchAppErrorDetails {
  public final static MatchAppErrorDetails GENERIC_ERROR = new MatchAppErrorDetails(10, "Generic internal error");
  public final static MatchAppErrorDetails DB_READ_ERROR = new MatchAppErrorDetails(11, "Database retrieval error");
  public final static MatchAppErrorDetails UNABLE_TO_DELETE_ENTITY = new MatchAppErrorDetails(12, "Unable to delete " +
      "entry");
  public final static MatchAppErrorDetails NON_UNIQUE_DB_ENTRY = new MatchAppErrorDetails(13, "There are more than 1 " +
      "entries with this identifier");
  public final static MatchAppErrorDetails UNABLE_TO_PERSIST_ENTITY = new MatchAppErrorDetails(14, "Unable to persist" +
      " entry");
  public final static MatchAppErrorDetails UNABLE_TO_UPDATE_ENTITY = new MatchAppErrorDetails(15, "Unable to update" +
      " entry");
  public final static MatchAppErrorDetails BAD_REQUEST = new MatchAppErrorDetails(16, "Bad request");
  public final static MatchAppErrorDetails UNABLE_TO_FIND_ENTITY = new MatchAppErrorDetails(17, "Unable to find " +
      "entity");


  private final int errorCode;
  private String errorMessage;

  public MatchAppErrorDetails(int errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }
}
