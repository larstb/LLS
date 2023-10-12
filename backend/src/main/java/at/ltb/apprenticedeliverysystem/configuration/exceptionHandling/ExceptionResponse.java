package at.ltb.apprenticedeliverysystem.configuration.exceptionHandling;

import java.time.LocalDateTime;

public record ExceptionResponse (String message, String requestUri, Integer status,
                                 String exceptionCode, LocalDateTime timeStamp) {

}
