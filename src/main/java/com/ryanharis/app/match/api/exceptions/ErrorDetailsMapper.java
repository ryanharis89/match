package com.ryanharis.app.match.api.exceptions;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.ryanharis.app.match.common.exceptions.errordetails.MatchAppErrorDetails;
import org.springframework.http.HttpStatus;

public class ErrorDetailsMapper {

  public static final Table<Integer, ApiErrorDetails, HttpStatus> codesToHTTP = HashBasedTable
      .create();

  static {
    //500
    codesToHTTP
        .put(MatchAppErrorDetails.GENERIC_ERROR.getErrorCode(), ApiErrorDetails.GENERIC_ERROR, HttpStatus
            .INTERNAL_SERVER_ERROR);
    codesToHTTP
        .put(MatchAppErrorDetails.UNABLE_TO_DELETE_ENTITY.getErrorCode(), ApiErrorDetails.GENERIC_ERROR, HttpStatus
            .INTERNAL_SERVER_ERROR);
    codesToHTTP
        .put(MatchAppErrorDetails.UNABLE_TO_UPDATE_ENTITY.getErrorCode(), ApiErrorDetails.GENERIC_ERROR, HttpStatus
            .INTERNAL_SERVER_ERROR);
    codesToHTTP
        .put(MatchAppErrorDetails.UNABLE_TO_PERSIST_ENTITY.getErrorCode(), ApiErrorDetails.GENERIC_ERROR, HttpStatus
            .INTERNAL_SERVER_ERROR);
    codesToHTTP
        .put(MatchAppErrorDetails.NON_UNIQUE_DB_ENTRY.getErrorCode(), ApiErrorDetails.GENERIC_ERROR, HttpStatus
            .INTERNAL_SERVER_ERROR);
    //404
    codesToHTTP
        .put(MatchAppErrorDetails.BAD_REQUEST.getErrorCode(), ApiErrorDetails.BAD_REQUEST_DET, HttpStatus.NOT_FOUND);
    //404
    codesToHTTP
        .put(MatchAppErrorDetails.DB_READ_ERROR.getErrorCode(), ApiErrorDetails.NO_DATA_FOUND, HttpStatus.NOT_FOUND);
    codesToHTTP
        .put(MatchAppErrorDetails.UNABLE_TO_FIND_ENTITY.getErrorCode(), ApiErrorDetails.NO_DATA_FOUND_WITH_DETAILS,
            HttpStatus.NOT_FOUND);
  }
}
