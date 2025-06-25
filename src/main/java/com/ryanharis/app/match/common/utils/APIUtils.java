package com.ryanharis.app.match.common.utils;

import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;

public class APIUtils {
  public static String getParameterValues(final MethodInvocationProceedingJoinPoint joinPoint) {
    final StringBuilder builder = new StringBuilder();

    final CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();

    final String[] parameterNames = codeSignature.getParameterNames();
    final Object[] values = joinPoint.getArgs();
    builder.append("(");
    for (int i = 0; i < parameterNames.length; i++) {
      if (values[i] != null) {
        builder.append(parameterNames[i]).append(":").append(values[i]).append(",");
      }
    }

    if (builder.length() > 1) {
      builder.deleteCharAt(builder.length() - 1);
    }

    builder.append(")");

    return builder.toString();
  }
}
