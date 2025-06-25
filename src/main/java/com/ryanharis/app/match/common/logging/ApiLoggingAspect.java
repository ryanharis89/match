package com.ryanharis.app.match.common.logging;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryanharis.app.match.common.utils.APIUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Order(0)
@Component
public class ApiLoggingAspect {
  private static final Logger LOGGER = LoggerFactory.getLogger(ApiLoggingAspect.class);

  private final ObjectMapper objectMapper;

  @Autowired
  public ApiLoggingAspect(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @Around("restControllerPointcut()")
  public Object beforeAPICall(final ProceedingJoinPoint joinPoint) throws Throwable {

    Object returnValue;

    final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
        .getRequest();

    final String requestURI = request.getRequestURI();

    final MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint =
        (MethodInvocationProceedingJoinPoint) joinPoint;
    final String restControllerClass = methodInvocationProceedingJoinPoint.getTarget().getClass().getSimpleName();
    final String arguments = APIUtils.getParameterValues(methodInvocationProceedingJoinPoint);
    String httpMethod = request.getMethod();

    final LocalDateTime start = LocalDateTime.now();
    LOGGER.info("Received {} API call in {} at {} with arguments: {} @{}", httpMethod, restControllerClass, requestURI,
        arguments, start);

    try {
      returnValue = joinPoint.proceed();
    } catch (final Exception e) {
      LOGGER.error("{} API call at {},{} failed with exception: {}", httpMethod, restControllerClass, requestURI,
          e.getMessage());
      throw e;
    }

    final LocalDateTime stop = LocalDateTime.now();
    long durationMs = start.until(stop, ChronoUnit.MILLIS);
    LOGGER.info("{} API call in {} at {} finished @{} with duration {} ms. API response:\n{}", httpMethod,
        restControllerClass, requestURI, stop, durationMs, getResponseAsJson(returnValue));
    return returnValue;
  }

  private String getResponseAsJson(final Object returnValue) {
    try {
      return objectMapper.writeValueAsString(returnValue);
    } catch (final Exception e) {
      return "An error occurred while transforming response to json";
    }
  }

  @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
  public void restControllerPointcut() {
  }
}
