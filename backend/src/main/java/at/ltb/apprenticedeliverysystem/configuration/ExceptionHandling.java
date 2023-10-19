package at.ltb.apprenticedeliverysystem.configuration;

import at.ltb.apprenticedeliverysystem.configuration.exceptionhandling.ExceptionEnum;
import at.ltb.apprenticedeliverysystem.configuration.exceptionhandling.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(Exception.class)
    public @ResponseBody ExceptionResponse handleException(final Exception exception, final HttpServletRequest request,
                                                           final HttpServletResponse response) {
        ExceptionEnum exceptionResponse = ExceptionEnum.getException(exception.getClass());
        response.setStatus(exceptionResponse.getHttpStatus().value());

        return new ExceptionResponse(exception.getMessage(), request.getRequestURI(),
                exceptionResponse.getHttpStatus().value(), exceptionResponse.getCode(), LocalDateTime.now());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public @ResponseBody ExceptionResponse handleAccessDeniedException(final Exception exception, final HttpServletRequest request,
                                                           final HttpServletResponse response) {
        ExceptionEnum exceptionResponse = ExceptionEnum.UNAUTHORIZED;
        response.setStatus(exceptionResponse.getHttpStatus().value());

        return new ExceptionResponse(exceptionResponse.getCode(), request.getRequestURI(),
                exceptionResponse.getHttpStatus().value(), exceptionResponse.getCode(), LocalDateTime.now());
    }
}
