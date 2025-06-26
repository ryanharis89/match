package com.ryanharis.app.match.api.exceptions;

import static com.ryanharis.app.match.api.exceptions.ErrorDetailsMapper.codesToHTTP;
import com.ryanharis.app.match.common.exceptions.MatchAppException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MatchAppException.class)
  public ResponseEntity<ApiErrorDetails> handleMatchAppException(final MatchAppException ex) {
    final Optional<Map.Entry<ApiErrorDetails, HttpStatus>> data =
        codesToHTTP.row(ex.getErrorDetails().getErrorCode()).entrySet().stream().findAny();
    final String[] errorData = ex.getErrorData();

    final ResponseEntity<ApiErrorDetails> response;
    if (data.isEmpty()) {
      response = new ResponseEntity<>(ApiErrorDetails.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    } else {
      final ApiErrorDetails errorDetails = data.get().getKey();
      final String errorMessage = errorData == null ? errorDetails
          .getErrorMessage() : MessageFormat.format(errorDetails
          .getErrorMessage(), errorData);

      response = new ResponseEntity<>(new ApiErrorDetails(errorDetails.getErrorCode(), errorMessage),
          data.get().getValue());
    }
    return response;
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiErrorDetails> handleException(final Exception ex) {
    return new ResponseEntity<>(ApiErrorDetails.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    final String errorMessage = MessageFormat.format(ApiErrorDetails.BAD_REQUEST_DET.getErrorMessage(),
        ex.getMessage());
    return new ResponseEntity<>(new ApiErrorDetails(ApiErrorDetails.BAD_REQUEST_DET.getErrorCode(), errorMessage),
        HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    final ApiErrorDetails errorDetails = ApiErrorDetails.BAD_REQUEST_DET;
    final String errorMessage = MessageFormat.format(errorDetails
        .getErrorMessage(), new String[] {"missing " + ex.getParameterName() + " request parameter"});
    return new ResponseEntity<>(new ApiErrorDetails(errorDetails
        .getErrorCode(), errorMessage), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
      HttpStatusCode status,
      WebRequest request) {
    final ApiErrorDetails errorDetails = ApiErrorDetails.BAD_REQUEST_DET;
    String errorMessageDetails = "type mismatch or illegal value on request ";
    if (ex instanceof MethodArgumentTypeMismatchException) {
      errorMessageDetails = errorMessageDetails + ", parameter: " + ((MethodArgumentTypeMismatchException) ex)
          .getName();
    }
    final String errorMessage = MessageFormat
        .format(errorDetails.getErrorMessage(), new String[] {errorMessageDetails});

    return new ResponseEntity<>(new ApiErrorDetails(errorDetails
        .getErrorCode(), errorMessage), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    final Optional<ObjectError> any =
        ex.getAllErrors().stream().findAny();
    final ResponseEntity<Object> response;
    if (any.isPresent()) {
      final String errorMessage =
          MessageFormat.format(ApiErrorDetails.BAD_REQUEST_DET.getErrorMessage(), any.get().getDefaultMessage());

      response = new ResponseEntity<>(new ApiErrorDetails(ApiErrorDetails.BAD_REQUEST_DET.getErrorCode(), errorMessage),
          HttpStatus.BAD_REQUEST);
    } else {
      response = new ResponseEntity<>(ApiErrorDetails.GENERIC_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return response;
  }
}
