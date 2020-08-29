package com.demo.omni.entrypoint.rest.exceptionhandler;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.demo.omni.core.usecase.exception.DepartmentCodeAlreadyExists;
import com.demo.omni.entrypoint.rest.BaseResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var message = Optional.ofNullable(ex.getCause()).orElse(ex).toString();
		BaseResponseDto baseResponseDto = new BaseResponseDto(status.getReasonPhrase(), List.of(message));
		
		return handleExceptionInternal(ex, baseResponseDto, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		BaseResponseDto baseResponseDto = new BaseResponseDto(status.getReasonPhrase());
		
		ex.getBindingResult().getGlobalErrors().forEach(error -> baseResponseDto.addMessage(error.getDefaultMessage()));
		ex.getBindingResult().getFieldErrors().forEach(error -> baseResponseDto.addMessage(error.getField() + ": " + error.getDefaultMessage()));
		
		return handleExceptionInternal(ex, baseResponseDto, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(RuntimeException ex,  WebRequest request) {
		
		BaseResponseDto baseResponseDto = new BaseResponseDto(HttpStatus.NOT_FOUND.getReasonPhrase(), List.of(ex.getMessage()));
		
		return handleExceptionInternal(ex, baseResponseDto, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler({ DepartmentCodeAlreadyExists.class })
	public ResponseEntity<Object> handleDepartmentCodeAlreadyExists(DepartmentCodeAlreadyExists ex,  WebRequest request) {
		
		BaseResponseDto baseResponseDto = new BaseResponseDto(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), 
				List.of(ex.getMessage()));
		
		return handleExceptionInternal(ex, baseResponseDto, new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
	}
	
}
