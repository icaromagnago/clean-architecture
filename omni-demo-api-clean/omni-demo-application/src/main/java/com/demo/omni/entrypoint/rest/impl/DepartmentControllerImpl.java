package com.demo.omni.entrypoint.rest.impl;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.omni.core.usecase.GetAllDepartmentsOrderByCode;
import com.demo.omni.entrypoint.rest.BaseResponseDto;
import com.demo.omni.entrypoint.rest.DepartmentController;

@RestController
@RequestMapping("/departments")
public class DepartmentControllerImpl implements DepartmentController {
	
	private MessageSource messageSource;
	private GetAllDepartmentsOrderByCode getAllDepartmentsUseCase;
	
	public DepartmentControllerImpl(MessageSource messageSource, GetAllDepartmentsOrderByCode getAllDepartmentsUseCase) {
		this.messageSource = messageSource;
		this.getAllDepartmentsUseCase = getAllDepartmentsUseCase;
	}

	@Override
	@GetMapping
	public ResponseEntity<BaseResponseDto> list() {
		var departments = getAllDepartmentsUseCase.execute();
		
		return ResponseEntity.ok(new BaseResponseDto(HttpStatus.OK.getReasonPhrase(), List.of(getSuccessMessage()), departments));
	}
	
	private String getSuccessMessage() {
		return messageSource.getMessage("success.message", null, LocaleContextHolder.getLocale());
	}

}
