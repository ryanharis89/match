package com.ryanharis.app.match.api.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serial;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorDetails implements Serializable {
  public static final String GENERIC_ERROR_MSG = "Generic internal error";
  public static final int GENERIC_ERROR_CODE = 10;
  public static final ApiErrorDetails GENERIC_ERROR = new ApiErrorDetails(GENERIC_ERROR_CODE,
      GENERIC_ERROR_MSG);

  public static final String NO_DATA_FOUND_MSG = "No data found. {0}";
  public static final int NO_DATA_FOUND_CODE = 11;
  public static final ApiErrorDetails NO_DATA_FOUND = new ApiErrorDetails(NO_DATA_FOUND_CODE,
      NO_DATA_FOUND_MSG);

  public static final String NO_DATA_FOUND_WITH_DETAILS_MSG = "No data found. {0} : {1}";
  public static final int NO_DATA_FOUND_WITH_DETAILS_CODE = 12;
  public static final ApiErrorDetails NO_DATA_FOUND_WITH_DETAILS = new ApiErrorDetails(NO_DATA_FOUND_WITH_DETAILS_CODE,
      NO_DATA_FOUND_WITH_DETAILS_MSG);

  public static final String BAD_REQUEST_MSG = "Bad request";
  public static final int BAD_REQUEST_CODE = 13;
  public static final ApiErrorDetails BAD_REQUEST = new ApiErrorDetails(BAD_REQUEST_CODE,
      BAD_REQUEST_MSG);

  public static final String BAD_REQUEST_DET_MSG = "Bad request, {0}";
  public static final int BAD_REQUEST_DET_CODE = 14;
  public static final ApiErrorDetails BAD_REQUEST_DET = new ApiErrorDetails(BAD_REQUEST_DET_CODE,
      BAD_REQUEST_DET_MSG);

  @Serial
  private static final long serialVersionUID = 3004400606225627151L;
  private String errorMessage;
  private Integer errorCode;
  private Object errorData;

  public ApiErrorDetails(final int errorCode, final String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public ApiErrorDetails(final Integer errorCode, final String errorMessage, final Object errorData) {
    this.errorMessage = errorMessage;
    this.errorCode = errorCode;
    this.errorData = errorData;
  }

  @Override
  public String toString() {
    return "ApiErrorDetails{" +
        "errorMessage='" + errorMessage + '\'' +
        ", errorCode=" + errorCode +
        '}';
  }


}
