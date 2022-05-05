package com.farsousa.taskapi.interceptor;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.farsousa.taskapi.dto.response.ValidationErrorResponse;

@RestControllerAdvice
public class ValidationExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ValidationErrorResponse> handle(MethodArgumentNotValidException exception) {
		List<ValidationErrorResponse> validationErrors = new ArrayList<>();
		
		List<FieldError> fieldErros = exception.getBindingResult().getFieldErrors();
		fieldErros.forEach(e -> {
			String message = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ValidationErrorResponse validationError = new ValidationErrorResponse(e.getField(), message);
			validationErrors.add(validationError);
		});
		
		return validationErrors;
	}

}
