package com.fargotest.core.errorhandling;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.fargotest.core.exception.BadRequestException;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Mono<String> onException(Exception e) {
		String errorType = "Server Error.";
		String className =  (e.getCause()!=null) ? e.getCause().getClass().getName() : e.getClass().getName();
		e.printStackTrace();
		return Mono.just( errorType + " " + className + "->" + e.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Mono<String> onBadRequestException(BadRequestException e) {
		return Mono.just("Bad Request. " +  e.getMessage());
	}

	@ExceptionHandler(TypeMismatchException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public Mono<String> onTypeMismatchException(TypeMismatchException e) {
		return Mono.just("Invalid. " +  e.getMessage());
	}
}

