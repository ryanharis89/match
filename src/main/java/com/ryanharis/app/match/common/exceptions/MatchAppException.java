package com.ryanharis.app.match.common.exceptions;

import com.ryanharis.app.match.common.exceptions.errordetails.MatchAppErrorDetails;
import java.io.Serial;
import lombok.Getter;

@Getter
public class MatchAppException extends Exception {
  @Serial
  private static final long serialVersionUID = 3004400606225627151L;
  private final MatchAppErrorDetails errorDetails;
  private final String[] errorData;

  public MatchAppException(String message, MatchAppErrorDetails errorDetails, String[] errorData) {
    super(message);
    this.errorDetails = errorDetails;
    this.errorData = errorData;
  }

  public MatchAppException(final MatchAppErrorDetails errorDetails, final Throwable t, final String[] errorData) {
    super(t.getMessage());
    this.errorDetails = errorDetails;
    this.errorData = errorData;
  }

  public MatchAppException(final MatchAppErrorDetails errorDetails, final String[] errorData) {
    super(errorDetails.getErrorMessage());
    this.errorDetails = errorDetails;
    this.errorData = errorData;
  }
}
